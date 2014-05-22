package com.pwr.NewInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.pwr.Editor.QuestTableView;
//import com.pwr.Editor.QuestsTableView;
import com.pwr.Editor.UserDetailsView;
import com.pwr.Graph.QuizDataObject;
import com.pwr.Other.CreateHTMLRaport;
import com.pwr.Other.CreatePDFRaport;
import com.pwr.Other.SpringBootLoader;
import com.pwr.Quest.Campaign;
import com.pwr.Quest.QuestPoint;
import com.pwr.UserRegistration.Requests;
import com.pwr.UserRegistration.UserDTO;
import com.pwr.UserRegistration.UserDataRegister;

@Component
public class ProjectMainView extends JFrame implements Serializable {

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

	private JPanel leftSidePanel;
	private JPanel rightSidePanel;
	private JPanel userTabPane;

	private TreasureBoxDialog treasureBoxDialog;
	private GameSettingsDialog gameSettingsDialog;

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
	private JButton btnNewUser;
	private JButton btnDeleteAllDoneQuests;
	private JButton btnLoadQuests;
	private JButton btnDeleteQuestts;
	private JButton btnSendPackageToSerwer;
	private JButton btnAddTreasureBox;
	private JButton btnEditGameSettings;

	private JLabel lblOpcjeProjektu;
	private JLabel lblOpcjeUserow;
	private JLabel lblOperacjeNaProjekcie;

	private static int windowWidth = 800;
	private static int windowHeight = 500;

	private JTable table;
	private DefaultTableModel tableModel;

	private int colNum, rowNum;

	// Campaign vars
	private static Campaign campaign;
	private static ArrayList<QuestPoint> quest;

	public ProjectMainView() {
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

		leftSidePanel = new JPanel();
		rightSidePanel = new JPanel();
		rightSidePanel.setPreferredSize(new Dimension(1000, 750));
		rightScroll = new JScrollPane(rightSidePanel);
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSidePanel,
				rightScroll);

		leftSidePanel.setLayout(null);
		rightSidePanel.setLayout(null);

		lblOperacjeNaProjekcie = new JLabel("Operacje na projekcie");
		lblOperacjeNaProjekcie.setBounds(48, 6, 164, 14);
		leftSidePanel.add(lblOperacjeNaProjekcie);

		// createLeftSidePanelForProject();
		// createRightSidePanel();

		splitPane.setResizeWeight(0.3);
		splitPane.setBounds(10, 11, 764, 418);
		getContentPane().add(splitPane);
		// createMenu();
		//
		// addListenerForTabbedPane();
		//
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

		btnDeleteQuestts = new JButton("Usun questy");
		btnDeleteQuestts.setBounds(6, 72, 207, 28);
		leftSidePanel.add(btnDeleteQuestts);

		btnDeleteQuestts.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {

					@Override
					public void run() {
						questTableView.setVisible(true);
					}
				});
			}
		});

		btnDeleteAll = new JButton("Usun wszystkie dane");

		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmView.setVisible(true);
			}
		});

		btnDeleteAll.setBounds(6, 32, 206, 28);
		leftSidePanel.add(btnDeleteAll);

	}

	public static void invokeNewQuizView(final int id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new NewQuizView(campaign, id);
			}
		});
	}

	public static void invokeNewQuizView() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new NewQuizView(campaign);
			}
		});
	}

	private void createLeftSidePanelForProject() {
		btnNewQuiz = new JButton("Nowy quest");
		btnNewQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						new NewQuizView(campaign);
					}
				});
			}
		});

		btnNewQuiz.setBounds(6, 112, 207, 28);
		leftSidePanel.add(btnNewQuiz);

		btnGenerateRaport = new JButton("Raport");
		btnGenerateRaport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				createPDFRaport.generatePDF();
				createHTMLRaport.generateHTML();
			}
		});

		btnGenerateRaport.setBounds(6, 152, 207, 28);
		leftSidePanel.add(btnGenerateRaport);

		JButton btnZapiszUstawieniaGry = new JButton("Zapisz ustawienia gry");
		btnZapiszUstawieniaGry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnZapiszUstawieniaGry.setBounds(6, 72, 207, 28);
		leftSidePanel.add(btnZapiszUstawieniaGry);

		JButton btnNowaGra = new JButton("Nowa gra");
		btnNowaGra.setBounds(6, 32, 206, 28);
		leftSidePanel.add(btnNowaGra);

		btnNowaGra.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tworzNowaGre();
			}

		});

		btnLoadQuests = new JButton("Wczytaj zagadki");
		btnLoadQuests.setBounds(6, 190, 206, 28);

		btnLoadQuests.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				wczytajPaczke();
			}

		});

		leftSidePanel.add(btnLoadQuests);

		btnSendPackageToSerwer = new JButton("Wyslij paczke");
		btnSendPackageToSerwer.setBounds(6, 230, 206, 28);

		btnSendPackageToSerwer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					requests.sendFile();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		leftSidePanel.add(btnSendPackageToSerwer);

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
		btnEditGameSettings.setBounds(6, 306, 206, 28);
		btnEditGameSettings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameSettingsDialog = new GameSettingsDialog(campaign);
			}

		});

		leftSidePanel.add(btnEditGameSettings);
	}

	private void wczytajPaczke() {
		campaign.loadXml(getXML());
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
			campaign.createXml();
			JOptionPane.showMessageDialog(null, "Zapisano grę");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Nie udało się zapisać gry");
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

		tabbedPane.addTab("Project", projectTabPane);
		tabbedPane.addTab("User", userTabPane);
		rightSidePanel.add(tabbedPane);
	}

	private void createMenu() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnProject = new JMenu("Project");
		menuBar.add(mnProject);

		JMenuItem mntmNewProjectItem = new JMenuItem("Nowy projekt");
		mnProject.add(mntmNewProjectItem);

		JMenuItem mntmGenRaport = new JMenuItem("Raport");
		mnProject.add(mntmGenRaport);

		JMenuItem mntmOpenProjectItem = new JMenuItem("Otw\u00F3rz");
		mnProject.add(mntmOpenProjectItem);

		JMenu mnUser = new JMenu("User");
		menuBar.add(mnUser);

		JMenuItem usunQuestyItem = new JMenuItem("Usun questy");
		mnUser.add(usunQuestyItem);

		JMenuItem usunWszystkieDaneItem = new JMenuItem("Usun wszystkie dane");
		mnUser.add(usunWszystkieDaneItem);

		JMenuItem dodajUzytkownikaItem = new JMenuItem("Dodaj uzytkownika");
		mnUser.add(dodajUzytkownikaItem);

		JMenu mnPomoc = new JMenu("Pomoc");
		menuBar.add(mnPomoc);

		JMenuItem infoItem = new JMenuItem("Informacje");
		mnPomoc.add(infoItem);

		JMenuItem oProgramieItem = new JMenuItem("O Programie");
		mnPomoc.add(oProgramieItem);

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

		mntmNewProjectItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tworzNowaGre();
			}
		});

		infoItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, instrukcja);
			}
		});

		oProgramieItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Jakies informacje");
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
		tableModel = new DefaultTableModel(new String[] { "login", "points",
				"end time" }, 0);

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
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Choose Package file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		String str = "";
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			str = chooser.getSelectedFile().toString();
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
				;
				campaign.deleteTrue();
				ProjectOptionsView.updateView();
				break;
			}
		}

	}

	public static void quizConnectionsChanged(ArrayList<QuizDataObject> quizList) {
		for (QuizDataObject q : quizList) {
			for (QuestPoint p : campaign.getQuizes()) {
				if (Integer.parseInt(q.getId()) == p.getId()) {
					p.setWrong(q.getWrong());
					p.setGoTo(q.getCorrect()[0]);
					break;
				}
			}
		}
	}
}
