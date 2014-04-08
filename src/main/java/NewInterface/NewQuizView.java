package NewInterface;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;

public class NewQuizView extends JFrame {

	private JPanel leftSidePanel;
	private JPanel rightSidePanel;
	private JSplitPane splitPane;
	private JScrollPane leftScroll;
	private JScrollPane rightScroll;

	public NewQuizView() {
		super();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		setSize(800, 478);
		leftSidePanel = new JPanel();
		rightSidePanel = new JPanel(new CardLayout());
		leftScroll = new JScrollPane(leftSidePanel);
		rightScroll = new JScrollPane(rightSidePanel);
		rightScroll
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScroll,
				rightScroll);

		leftSidePanel.setLayout(null);

		createLeftSidePanel();

		splitPane.setResizeWeight(0.3);
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

		String[] quizTypes = { "Zagadka tekstowa", "Zagadka terenowa",
				"Zagadka wielokrotnego wyboru", "Zagadka uporządkowania",
				"Zagadka zasięgu" };

		final JComboBox questTypeCombo = new JComboBox(quizTypes);
		questTypeCombo.setBounds(63, 70, 154, 20);
		leftSidePanel.add(questTypeCombo);

		JLabel lblOpcje = new JLabel("Typ");
		lblOpcje.setBounds(20, 73, 46, 14);
		leftSidePanel.add(lblOpcje);

		JLabel lblOpcjeQuizu = new JLabel("Opcje quizu");
		lblOpcjeQuizu.setBounds(72, 149, 126, 14);
		leftSidePanel.add(lblOpcjeQuizu);

		rightSidePanel.add(new FieldQuestView(), "Zagadka terenowa");
		rightSidePanel.add(new TextQuestView(), "Zagadka tekstowa");
		rightSidePanel.add(new MultipleChoiceQuestView(),
				"Zagadka wielokrotnego wyboru");
		rightSidePanel.add(new OrderQuestView(), "Zagadka uporządkowania");
		rightSidePanel.add(new RangeQuestView(), "Zagadka zasięgu");

		questTypeCombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent aE) {
				CardLayout cl = (CardLayout) rightSidePanel.getLayout();
				cl.show(rightSidePanel, aE.getItem().toString());
			}
		});

	}

}
