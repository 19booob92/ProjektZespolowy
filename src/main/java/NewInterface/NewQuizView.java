package NewInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.CardLayout;

public class NewQuizView extends JFrame {

	private JPanel leftSidePanel;
	private JPanel rightSidePanel;
	private JSplitPane splitPane;
	private JScrollPane scroll;
	
	public NewQuizView() {
		super();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		setSize(800, 478);
		leftSidePanel = new JPanel();
		rightSidePanel = new JPanel();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSidePanel,
				rightSidePanel);

		leftSidePanel.setLayout(null);

		createLeftSidePanel();
		createRightSidePanel();

		splitPane.setResizeWeight(1.0);
		splitPane.setBounds(10, 11, 764, 418);
		getContentPane().add(splitPane);
		setVisible(true);
	}

	private void createLeftSidePanel() {
		JLabel lblNewQuiz = new JLabel("Nowy Quiz");
		lblNewQuiz.setBounds(10, 11, 207, 14);
		leftSidePanel.add(lblNewQuiz);

		JLabel lblTitle = new JLabel("Tytu\u0142");
		lblTitle.setBounds(20, 39, 46, 14);
		leftSidePanel.add(lblTitle);

		String[] quizTypes = { "Zagadka terenowa", "Zagadka tekstowa",
				"Zagadka wielokrotnego wyboru", "Zagadka uporządkowania", "Zagadka zasięgu"
				 };
		final JComboBox questTypeCombo = new JComboBox(quizTypes);
		questTypeCombo.setBounds(63, 70, 154, 20);
		leftSidePanel.add(questTypeCombo);

		JLabel lblOpcje = new JLabel("Typ");
		lblOpcje.setBounds(20, 73, 46, 14);
		leftSidePanel.add(lblOpcje);

		JLabel lblOpcjeQuizu = new JLabel("Opcje quizu");
		lblOpcjeQuizu.setBounds(72, 149, 126, 14);
		leftSidePanel.add(lblOpcjeQuizu);
		
		rightSidePanel.add(new FieldQuestView(), "RangeQuestView");
		rightSidePanel.add(new MultipleChoiceQuestView(), "MultipleChoiceQuestView");
		rightSidePanel.add(new OrderQuestView(), "OrderQuestView");
		rightSidePanel.add(new RangeQuestView(), "RangeQuestView");
		
		questTypeCombo.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				CardLayout cl = (CardLayout)(rightSidePanel.getLayout());
			    cl.show(rightSidePanel, (String)e.getItem());
			}
		});

	}

	private void createRightSidePanel() {
		rightSidePanel.setLayout(new CardLayout(0, 0));
		JLabel lblWidokOpcjiW = new JLabel(
				"Widok opcji w zaleznosci od wybranego quizu, czyli test wyboru, google mapa, porzadkowanie, tekst");
		rightSidePanel.add(lblWidokOpcjiW, "name_85714711365735");
	}

	private void createTabPage1() {

	}

	private void createTabPage2() {

	}

	private void createTabPage3() {

	}

	private void createTabPage4() {

	}

	private void createTabPage5() {

	}
}
