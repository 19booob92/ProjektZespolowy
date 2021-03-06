package com.pwr.MainView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.pwr.DetailsView.GameSettingsDialog;
import com.pwr.DetailsView.TreasureBoxDialog;
//import com.pwr.Editor.QuestsTableView;
import com.pwr.Graph.QuizDataObject;
import com.pwr.Other.CreateHTMLRaport;
import com.pwr.Other.CreatePDFRaport;
import com.pwr.Other.SpringBootLoader;
import com.pwr.Quest.Campaign;
import com.pwr.Quest.QuestPoint;
import com.pwr.QuestView.NewQuizView;
import com.pwr.UserRegistration.Requests;
import com.pwr.UserRegistration.UserDTO;
import com.pwr.UserRegistration.UserDataRegister;
import com.pwr.UserView.ConfirmView;
import com.pwr.UserView.QuestTableView;
import com.pwr.UserView.UserDetailsView;
import com.pwr.UserView.UserOptionsView;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext.State;

@Component
public class ProjectMainView extends JFrame implements Serializable {

	public static File actualFolder=null;
	@Autowired
	private QuestTableView questTableView;
	@Autowired
	private Requests requests;
	@Autowired
	private UserDataRegister userDataRegister;
	@Autowired
	private UserDetailsView userDetailsView;
	@Autowired
	private ConfirmView confirmView;
	@Autowired
	private CreatePDFRaport createPDFRaport;
	@Autowired
	private CreateHTMLRaport createHTMLRaport;
	@Value("${instrukcja}")
	private String instrukcja;
	// chwilowe rozwiazanie, nie wiem jak przekazywac parametry do metody z
	// aspektu
	SplashWindow splashWindow;
	
	public static Toolkit tk = Toolkit.getDefaultToolkit();
	public static Dimension screenSize = tk.getScreenSize();
	public static final int SCREENWIDTH = screenSize.width;
	public static final int SCREENHEIGHT = screenSize.height;
	

	private JPanel leftSidePanel;
	private JPanel rightSidePanel;
	private JPanel userTabPane;
	private static JPanel statusPanel;
	private static JProgressBar progressBar;

	private TreasureBoxDialog treasureBoxDialog;
	private GameSettingsDialog gameSettingsDialog;

	private String filePath;
	private JScrollPane rightScroll;

	private ProjectOptionsView projectTabPane;

	private JTabbedPane tabbedPane;
	private JSplitPane splitPane;

	private JMenuBar menuBar;
	private JMenu mnProject;

	private JButton btnGenerateRaport;
	private JButton btnDeleteAll;
	private JButton btnNewQuiz;
	private JButton btnZapiszUstawieniaGry;
	private JButton btnNowaGra;
	private JButton btnNowaPaczka;
	private JButton btnNewUser;
	private JButton btnDeleteAllDoneQuests;
	private JButton btnLoadQuests;
	private JButton btnDeleteQuestts;
	private JButton btnSendPackageToServer;
	private JButton btnAddTreasureBox;
	private JButton btnEditGameSettings;

	private JLabel lblOpcjeProjektu;
	private JLabel lblOpcjeUserow;

	private static int windowWidth = 1200;
	private static int windowHeight = 600;

	private JTable table;
	private DefaultTableModel tableModel;

	private int colNum, rowNum;

	// Campaign vars
	private static Campaign campaign;
	private static ArrayList<QuestPoint> quest;

	public ProjectMainView() {
		setTitle("Edytor LoW");
		campaign = new Campaign();

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				boolean quit = true;
				if (!campaign.isSaved()) {
					int dialogButton = JOptionPane
							.showConfirmDialog(
									null,
									"Projekt nad którym pracujesz nie jest zapisany.\n Czy chcesz kontynuować?",
									"Uwaga!", JOptionPane.YES_NO_OPTION);
					if (dialogButton == JOptionPane.YES_OPTION) {
						campaign.closeCampaign();
						System.exit(0);
					}
				} else {
					campaign.closeCampaign();
					System.exit(0);
				}

			}
		});
		getContentPane().setLayout(new BorderLayout());
		setSize(windowWidth, windowHeight);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);

		statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusPanel.setPreferredSize(new Dimension(this.getWidth(), 20));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		statusPanel.setBackground(Color.WHITE);
		JLabel info = new JLabel("");
		// Data startu: "+campaign.getGameDate()+"/Liczba quizÃ³w:
		// "+campaign.getQuizes().size()+"/"
		info.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(info);
		getContentPane().add(statusPanel, BorderLayout.SOUTH);

		leftSidePanel = new JPanel();
		rightSidePanel = new JPanel();
		rightSidePanel.setPreferredSize(new Dimension(1000, 750));
		rightScroll = new JScrollPane(rightSidePanel);
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSidePanel,
				rightScroll);
		splitPane.setEnabled(false);
		splitPane.setDividerLocation(220);

		leftSidePanel.setLayout(null);
		rightSidePanel.setLayout(null);

		splitPane.setBounds(10, 11, 764, 418);
		getContentPane().add(splitPane);

		setVisible(true);
	}

	public static Campaign getCampaign() {
		return campaign;
	}

	private void addListenerForTabbedPane() {
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (tabbedPane.getSelectedIndex() == 1) {
					leftSidePanel.removeAll();
					createLeftSidePanelForUserSettings();
					repaint();
				} else if (tabbedPane.getSelectedIndex() == 0) {
					leftSidePanel.removeAll();
					createLeftSidePanelForProject();
					repaint();
				}
			}
		});
	}

	private void createLeftSidePanelForUserSettings() {
		btnNewUser = new JButton("Dodaj użytkownika");

		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userDataRegister.setVisible(true);
			}
		});

		btnNewUser.setBounds(6, 112, 207, 28);
		leftSidePanel.add(btnNewUser);

		btnDeleteQuestts = new JButton("Usuń zagadki");
		btnDeleteQuestts.setBounds(6, 72, 207, 28);
		leftSidePanel.add(btnDeleteQuestts);

		btnDeleteQuestts.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				questTableView.setVisible(true);
			}
		});

		btnDeleteAll = new JButton("Usuń wszystkie dane");

		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmView.setVisible(true);
			}
		});

		btnDeleteAll.setBounds(6, 32, 206, 28);
		leftSidePanel.add(btnDeleteAll);

	}

	public static void invokeNewQuizView(final int id) {
		NewQuizView.getInstance(campaign, id).show();
	}

	public static void invokeNewQuizView() {
		NewQuizView.getInstance(campaign).show();
	}

	private void createLeftSidePanelForProject() {
		btnNewQuiz = new JButton("Nowa zagadka");
		btnNewQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewQuizView.getInstance(campaign).show();
			}
		});

		btnNewQuiz.setBounds(6, 230, 206, 28);
		leftSidePanel.add(btnNewQuiz);

		btnGenerateRaport = new JButton("Raport");
		btnGenerateRaport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				createPDFRaport.generatePDF();
				createHTMLRaport.generateHTML();
			}
		});

		btnGenerateRaport.setBounds(6, 306, 206, 28);
		leftSidePanel.add(btnGenerateRaport);

		JButton btnNowaGra = new JButton("Zapisz grę");
		btnNowaGra.setBounds(6, 45, 206, 28);
		leftSidePanel.add(btnNowaGra);

		btnNowaGra.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tworzNowaGre();
			}

		});
		
		btnNowaPaczka = new JButton("Nowa gra");
		btnNowaPaczka.setBounds(6,10,206,28);
		leftSidePanel.add(btnNowaPaczka);
		
		btnNowaPaczka.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "Twój obecny projekt zostanie utracony. Czy chcesz kontynuować?");
				if(dialogResult == JOptionPane.YES_OPTION){
					nowaPaczka();
					JOptionPane.showMessageDialog(null, "Utworzono nowy projekt gry.");
				} 
			}
		}
		
		
		);

		btnLoadQuests = new JButton("Wczytaj grę");
		btnLoadQuests.setBounds(6, 80, 206, 28);

		btnLoadQuests.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "Twój obecny projekt zostanie utracony. Czy chcesz kontynuować?");
				if(dialogResult == JOptionPane.YES_OPTION){
					wczytajPaczke();
					NewQuizView.setInstance();
				} 
		}

		});

		leftSidePanel.add(btnLoadQuests);

		btnSendPackageToServer = new JButton("Wyślij paczkę");
		btnSendPackageToServer.setBounds(6, 160, 207, 28);

		btnSendPackageToServer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread queryThread = new Thread() {
					public void run() {
						try {
							JFileChooser fileChooser = new JFileChooser();
							if(ProjectMainView.actualFolder!=null)
								fileChooser.setCurrentDirectory(ProjectMainView.actualFolder);
							FileNameExtensionFilter filter = new FileNameExtensionFilter("*.zip", "zip");
							fileChooser.setFileFilter(filter);
							fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
							if(JFileChooser.APPROVE_OPTION==fileChooser.showSaveDialog(confirmView))
							{
								filePath = fileChooser.getSelectedFile()
										.getAbsolutePath();
								ProjectMainView.actualFolder=fileChooser.getSelectedFile().getParentFile();
								updateStatusPanel(progressBar);
								requests.sendFile(filePath);
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null,
									"Upłynął czas na połączenie z serwerem");
						}
					}
				};
				queryThread.start();
			}
		});

		leftSidePanel.add(btnSendPackageToServer);

		btnAddTreasureBox = new JButton("Zarządzanie skrytkami");
		btnAddTreasureBox.setBounds(6, 268, 206, 28);

		btnAddTreasureBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				treasureBoxDialog = new TreasureBoxDialog(campaign);
			}

		});

		leftSidePanel.add(btnAddTreasureBox);

		btnEditGameSettings = new JButton("Ustawienia gry");
		btnEditGameSettings.setBounds(6, 125, 207, 28);
		btnEditGameSettings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameSettingsDialog = new GameSettingsDialog(campaign);
			}

		});

		leftSidePanel.add(btnEditGameSettings);
	}

	private void wczytajPaczke() {
		//this.campaign = new Campaign();
		String tempStr=getXML();
		if(tempStr.equals(""))
			return;
		this.campaign.clearCampaign();
		if(campaign.loadXml(tempStr))
		{
			NewQuizView.setCampaign(campaign);
		}	//projectTabPane.setCampaign(campaign);
			projectTabPane.initiateGameFields();
			
			projectTabPane.updateGraph();
			repaint();
		
	}
	
	private void nowaPaczka()
	{
		this.campaign.clearCampaign();
		this.campaign.setGameDate(projectTabPane.getGameDate());
		projectTabPane.initiateGameFields();
		projectTabPane.updateGraph();
		repaint();
	}

	private void tworzNowaGre() {
		campaign.setIntroText(projectTabPane.introTextList);
		campaign.setOutroText(projectTabPane.outroTextList);
		campaign.setGameTitle(projectTabPane.getGameTitle());
		campaign.setGameDate(projectTabPane.getGameDate());
		campaign.setIntroPics(rewriteJListToArrayList(projectTabPane.introPics));
		campaign.setOutroPics(rewriteJListToArrayList(projectTabPane.outroPics));
		try {
			if(campaign.createXml())
			JOptionPane.showMessageDialog(null, "Zapisano grę");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Nie udało się zapisać gry");
			ex.printStackTrace();
		}

	}

	private ArrayList rewriteJListToArrayList(JList list) {
		ArrayList newList = new ArrayList();
		for (int i = 0; i < list.getModel().getSize(); i++) {
			if (list.getModel().getElementAt(i) != null)
				newList.add(list.getModel().getElementAt(i));
		}
		return newList;
	}

	private void createRightSidePanel() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1000, 816);

		createTabPage1();
		createTabPage2();

		tabbedPane.addTab("Gra", projectTabPane);
		tabbedPane.addTab("Użytkownicy", userTabPane);
		rightSidePanel.add(tabbedPane);
	}

	private void createMenu() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnProject = new JMenu("Projekt");
		menuBar.add(mnProject);

		JMenuItem mntmNewProjectItem = new JMenuItem("Nowa gra");
		mnProject.add(mntmNewProjectItem);
		
		JMenuItem mntmSaveProjectItem = new JMenuItem("Zapisz grę");
		mnProject.add(mntmSaveProjectItem);
		
		JMenuItem mntmOpenProjectItem = new JMenuItem("Wczytaj grę");
		mnProject.add(mntmOpenProjectItem);
		
		mnProject.addSeparator();

		JMenuItem mntmGenRaport = new JMenuItem("Raport");
		mnProject.add(mntmGenRaport);
		
		JMenuItem mntmCloseProgramItem = new JMenuItem("Zakończ");
		mnProject.add(mntmCloseProgramItem);

		JMenu mnUser = new JMenu("Użytkownicy");
		menuBar.add(mnUser);

		JMenuItem dodajUzytkownikaItem = new JMenuItem("Dodaj użytkownika");
		mnUser.add(dodajUzytkownikaItem);
		
		JMenuItem usunQuestyItem = new JMenuItem("Usuń zagadki");
		mnUser.add(usunQuestyItem);

		JMenuItem usunWszystkieDaneItem = new JMenuItem("Usuń wszystkie dane");
		mnUser.add(usunWszystkieDaneItem);


		JMenu mnPomoc = new JMenu("Pomoc");
		menuBar.add(mnPomoc);

		JMenuItem infoGameItem = new JMenuItem("Informacje o tworzeniu gier");
		mnPomoc.add(infoGameItem);

		JMenuItem infoUserItem = new JMenuItem("Informacje o zarządzaniu użytkownikami");
		//mnPomoc.add(infoUserItem);
		
		JMenuItem oProgramieItem = new JMenuItem("O programie");
		mnPomoc.add(oProgramieItem);
		
		JMenuItem infoAuthorsItem = new JMenuItem("Twórcy");
		mnPomoc.add(infoAuthorsItem);

		mntmGenRaport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				createPDFRaport.generatePDF();
				createHTMLRaport.generateHTML();
			}
		});

		mntmOpenProjectItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				wczytajPaczke();
			}
		});

		mntmSaveProjectItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tworzNowaGre();
			}
		});
		
		mntmCloseProgramItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		mntmNewProjectItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "Twój obecny projekt zostanie utracony. Czy chcesz kontynuować?");
				if(dialogResult == JOptionPane.YES_OPTION){
					nowaPaczka();
					JOptionPane.showMessageDialog(null, "Utworzono nowy projekt gry.");
				} 
			}
		});

		infoGameItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, instrukcja);
			}
		});

		oProgramieItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"Program utworzony na potrzeby Instytutu Pamięć i Przyszłość jako narzędzie wspomagające proces tworzenia gier edukacyjnych na platformę Android");
			}
		});
		
		infoAuthorsItem.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JOptionPane
				.showMessageDialog(
						null,
						"O autorach\n\nKrzysztof Pozorski:\n\tProjektowanie:\n\t\tpomysł, \t\tmechanika gry\n"
						+ "\tAndroid:\n\t\tdevelopment\n\tEdytor:\n\t\tnarzędzia, \t\tkonsulting, "
						+ "\t\tintegracja z aplikacją mobilną, \ttestowanie\n\nMichał Kowalik:\n\tAndroid:\n"
						+ "\t\tintegracja aplikacji z web serwisem\n\tWeb serwis:\n\t\tbaza danych, "
						+ "\t\tdevelopment, \t\ttestowanie\n\tEdytor:\n\t\tintegracja z web serwisem, \ttestowanie\n\n"
						+ "Arkadiusz Janz:\n\tEdytor:\n\t\tGUI, \t\tLogika\n\nMateusz Olczak:\n\tWeb serwis:\n\t\tintegracja z edytorem, "
						+ "\t\tkonsulting\t\nAdministracja\n\nMichał Sypniewski:\n\tEdytor:\n\t\tmapa, \t\tgenerowanie plikow wynikowych, "
						+ "\t\tpomoc przy GUI, \t\tobsługa I/O, \ttestowanie\n");
			}
		});

		usunQuestyItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {
				questTableView.setVisible(true);
			}
		});

		usunWszystkieDaneItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {
				confirmView.setVisible(true);
			}
		});

		dodajUzytkownikaItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {
				userDataRegister.setVisible(true);
			}
		});

	}

	private void createTabPage1() {
		projectTabPane = new ProjectOptionsView(campaign);
		lblOpcjeProjektu = new JLabel(
				"Opcje projektu, nazwa, liczba quizow, byc moze graf lub lista punktow");
		projectTabPane.add(lblOpcjeProjektu);
	}

	private void createTabPage2() {
		userTabPane = new UserOptionsView();
		lblOpcjeUserow = new JLabel("Opcje uzytkownikow, listy, dodawanie itd.");
		userTabPane.add(lblOpcjeUserow);

		getDataToTable();

		addTable();
		addRowToTable(getDataToTable());

	}

	public List<UserDTO> getDataToTable() {
		try {
			splashWindow = new SplashWindow(this, "359.gif");

			List<UserDTO> usersList = requests.getAllUsers();
			requests.crossPoint(splashWindow);
			return usersList;
		} catch (Exception except) {
			JOptionPane.showMessageDialog(null,
					"Sprawdz polaczenie z internetem");
			return null;
		}
	}

	private void addTable() {
		tableModel = new DefaultTableModel(new String[] { "nazwa", "punkty",
				"czas zakończenia" }, 0);

		table = new JTable(tableModel);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
					rowNum = target.getSelectedRow();
					colNum = target.getSelectedColumn();
					if (rowNum >= 0) {
						userDetailsView
								.prepareUserDetailsView((String) tableModel
										.getValueAt(rowNum, 0));
					}
				}
			}
		});

		userTabPane.setLayout(new BorderLayout());
		userTabPane.add(table.getTableHeader(), BorderLayout.NORTH);
		userTabPane.add(table, BorderLayout.CENTER);
	}

	private void addRowToTable(List<UserDTO> usersList) {
		for (UserDTO user : usersList) {
			if (user.getUserGame() != null) {
				tableModel.addRow(user.toArray());
			} else {
				tableModel.addRow(user.toArrayOnlyUser());
			}
		}
	}

	private String getXML() {
		JFileChooser chooser = new JFileChooser();
		if(ProjectMainView.actualFolder!=null)
			chooser.setCurrentDirectory(ProjectMainView.actualFolder);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.zip", "zip");
		chooser.setFileFilter(filter);
		chooser.setDialogTitle("Choose Package file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		String str = "";
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			str = chooser.getSelectedFile().toString();
			ProjectMainView.actualFolder=chooser.getSelectedFile().getParentFile();
		}
		return str;
	}

	public void updateTable() {
		tableModel.setRowCount(0);
		addRowToTable(getDataToTable());
	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		ApplicationContext context = new AnnotationConfigApplicationContext(
				SpringBootLoader.class);

		ProjectMainView projectMainView = (ProjectMainView) context
				.getBean(ProjectMainView.class);
		projectMainView.createRightSidePanel();
		projectMainView.createLeftSidePanelForProject();
		projectMainView.createMenu();
		projectMainView.setVisible(true);
		projectMainView.addListenerForTabbedPane();
	}

	public static void invokeQuizRemoving(int id) {
		for (QuestPoint q : campaign.getQuizes()) {
			if (q.getId() == id) {
				campaign.removeQuiz(q);
				campaign.deleteTrue();
				ProjectOptionsView.updateView();
				break;
			}
		}
	}

	public void updateStatusPanel(java.awt.Component component) {
		File f = new File(filePath);
		if (f.isFile()) {
			progressBar.setMaximum((int) (f.length()));
			statusPanel.removeAll();
			statusPanel.add(component);
			statusPanel.revalidate();
			statusPanel.repaint();
		}
	}

	public static void updateProgressBar(final long bytes) {
		progressBar.setValue((int) bytes);
		statusPanel.revalidate();
		statusPanel.repaint();
	}

	public static void quizConnectionsChanged(ArrayList<QuizDataObject> quizList) {
		for (QuizDataObject q : quizList) {
			for (QuestPoint p : campaign.getQuizes()) {
				if (Integer.parseInt(q.getId()) == p.getId()) {
					p.setWrong(q.getWrong());
					p.setGoTo(q.getCorrect());
					break;
				}
			}
		}
	}
}
