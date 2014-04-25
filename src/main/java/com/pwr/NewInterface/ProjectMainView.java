package com.pwr.NewInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SplashScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;


import com.pwr.Editor.QuestTableView;
//import com.pwr.Editor.QuestsTableView;
import com.pwr.Editor.UserDetailsView;
import com.pwr.Quest.Campaign;
import com.pwr.Quest.QuestPoint;
import com.pwr.UserRegistration.Requests;
import com.pwr.UserRegistration.UserDTO;
import com.pwr.UserRegistration.UserDataRegister;

@Component
public class ProjectMainView extends JFrame {

	@Autowired
	private Requests requests;
	@Autowired
	private UserDataRegister userDataRegister;
	@Autowired
	private UserDetailsView userDetailsView;
	
	private static SplashWindow splash;
	private static final String SPLASH_IMAGE = "monit.gif";
	
	private JPanel leftSidePanel;
	private JPanel rightSidePanel;
	private JScrollPane rightScroll;

	private ProjectOptionsView projectTabPane;
	private JPanel userTabPane;

	private JTabbedPane tabbedPane;
	private JSplitPane splitPane;

	private JMenuBar menuBar;
	private JMenu mnProject;
	private JButton btnDeleteAll;
	
	private JButton btnNewQuiz;
	private JButton btnZapiszUstawieniaGry;
	private JButton btnNowaGra;

	private JButton btnNewUser;
	private JButton btnDeleteAllDoneQuests;

	private JLabel lblOpcjeProjektu;
	private JLabel lblOpcjeUserow;
	private JLabel lblOperacjeNaProjekcie;

	private static int windowWidth = 800;
	private static int windowHeight = 500;

	private JTable table;
	private DefaultTableModel tableModel;

	private int colNum, rowNum;

	// Campaign vars
	private Campaign campaign;
	private static ArrayList<QuestPoint> quest;

	public ProjectMainView() {
		campaign = new Campaign();
		//Sample Splash - Do zmiany
		splash = new SplashWindow(this, "load.gif");
		for(int i = 0; i < 50000; i++) {
		      System.out.println(i) ;
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setSize(windowWidth, windowHeight);

		leftSidePanel = new JPanel();
		rightSidePanel = new JPanel();
		rightSidePanel.setPreferredSize(new Dimension(800, 500));
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
		btnNewUser = new JButton("Dodaj u�ytkownika");

		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userDataRegister.setVisible(true);
				userDataRegister.show();
			}
		});

		btnNewUser.setBounds(10, 11, 207, 23);
		leftSidePanel.add(btnNewUser);
		
		
		btnDeleteAll = new JButton("Usun wszystkie dane");
		
		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requests.deleteAll();
			}
		});
		
		btnDeleteAll.setBounds(10, 40, 207, 23);
		leftSidePanel.add(btnDeleteAll);
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
				campaign.createXml("title");
			}
		});
		
		JButton btnDeleteQuestts = new JButton("Usun questy");
		btnDeleteQuestts.setBounds(6, 152, 206, 28);
		leftSidePanel.add(btnDeleteQuestts);

		btnDeleteQuestts.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new QuestTableView();
					}
				});
			}
		});
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

		JMenuItem mntmOpenProjectItem = new JMenuItem("Otw\u00F3rz");
		mnProject.add(mntmOpenProjectItem);

		JMenu mnUser = new JMenu("User");
		menuBar.add(mnUser);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnUser.add(mntmNewMenuItem_1);

		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_2);
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

	private List<UserDTO> getDataToTable() {
		try {
			List<UserDTO> usersList = requests.getAllUsers();
			return usersList;
		} catch (Exception except) {
			except.printStackTrace();
			return null;
		}
	}

	private void addTable() {
		tableModel = new DefaultTableModel(new String[] { "login", "e-mail",
				"points", "end time" }, 0);

		table = new JTable(tableModel);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
					rowNum = target.getSelectedRow();
					colNum = target.getSelectedColumn();
					
					userDetailsView.prepareUserDetailsView((String) tableModel.getValueAt(
							rowNum, 0));

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

	public void updateTable() {
		tableModel.setRowCount(0);
		addRowToTable(getDataToTable());
	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
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
}
