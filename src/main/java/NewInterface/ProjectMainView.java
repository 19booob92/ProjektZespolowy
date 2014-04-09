package NewInterface;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JLabel;

import Quest.QuestPoint;

public class ProjectMainView extends JFrame {

	private JPanel leftSidePanel;
	private JPanel rightSidePanel;

	private JPanel projectTabPane;
	private JPanel userTabPane;

	private JTabbedPane tabbedPane;
	private JSplitPane splitPane;

	private JMenuBar menuBar;
	private JMenu mnProject;

	private JButton btnNewQuiz;

	private JLabel lblOpcjeProjektu;
	private JLabel lblOpcjeUserow;
	private JLabel lblOperacjeNaProjekcie;

	private static ArrayList<QuestPoint> quest;

	public ProjectMainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		setSize(800, 500);

		leftSidePanel = new JPanel();
		rightSidePanel = new JPanel();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSidePanel,
				rightSidePanel);

		leftSidePanel.setLayout(null);
		rightSidePanel.setLayout(null);

		lblOperacjeNaProjekcie = new JLabel("Operacje na projekcie");
		lblOperacjeNaProjekcie.setBounds(53, 104, 164, 14);
		leftSidePanel.add(lblOperacjeNaProjekcie);

		createLeftSidePanel();
		createRightSidePanel();

		splitPane.setResizeWeight(0.3);
		splitPane.setBounds(10, 11, 764, 418);
		getContentPane().add(splitPane);
		createMenu();

		setVisible(true);
	}

	private void createLeftSidePanel() {
		btnNewQuiz = new JButton("Nowy Quiz");
		btnNewQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						new NewQuizView();
					}
				});
			}
		});
		btnNewQuiz.setBounds(10, 11, 207, 23);
		leftSidePanel.add(btnNewQuiz);
	}

	private void createRightSidePanel() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 530, 416);

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
		projectTabPane = new ProjectOptionsView();
		lblOpcjeProjektu = new JLabel(
				"Opcje projektu, nazwa, liczba quizow, byc moze graf lub lista punktow");
		projectTabPane.add(lblOpcjeProjektu);
	}

	private void createTabPage2() {
		userTabPane = new UserOptionsView();
		lblOpcjeUserow = new JLabel("Opcje uzytkownikow, listy, dodawanie itd.");
		userTabPane.add(lblOpcjeUserow);

	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

		try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
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
		new ProjectMainView();
	}
}
