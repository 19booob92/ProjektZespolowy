package NewInterface;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;

public class NewQuizView extends JFrame {

	private JPanel leftSidePanel;
	private JPanel rightSidePanel;
	private JSplitPane splitPane;
	private JScrollPane leftScroll;
	private JScrollPane rightScroll;
	
	private JLabel lblType;
	
	private static final int panelWidth=800;
	private static final int panelHeight=800;
	
	private static final int windowWidth=1000;
	private static final int windowHeight=500;
	private JTextField textField;
	
	public NewQuizView() {
		super();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		setSize(windowWidth, windowHeight);
		leftSidePanel = new JPanel();
		leftSidePanel.setPreferredSize(new Dimension(320,panelHeight));
		rightSidePanel = new JPanel(new CardLayout());
		rightSidePanel.setPreferredSize(new Dimension(panelWidth,panelHeight));
		
		leftScroll = new JScrollPane(leftSidePanel);
		rightScroll = new JScrollPane(rightSidePanel);
		rightScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScroll, rightScroll);

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

		String[] quizTypes = { "Zagadka terenowa", "Zagadka tekstowa",
				"Zagadka wielokrotnego wyboru", "Zagadka uporządkowania",
				"Zagadka zasięgu" };

		final JComboBox questTypeCombo = new JComboBox(quizTypes);
		questTypeCombo.setBounds(63, 82, 154, 20);
		leftSidePanel.add(questTypeCombo);

		lblType = new JLabel("Typ");
		lblType.setBounds(20, 85, 46, 14);
		leftSidePanel.add(lblType);

		JLabel lblOpcjeQuizu = new JLabel("Opcje quizu");
		lblOpcjeQuizu.setBounds(72, 149, 126, 14);
		leftSidePanel.add(lblOpcjeQuizu);
		
		textField = new JTextField();
		textField.setBounds(63, 36, 154, 27);
		leftSidePanel.add(textField);
		textField.setColumns(10);

		rightSidePanel.add(new FieldQuestView(), "Zagadka terenowa");
		rightSidePanel.add(new TextQuestView(), "Zagadka tekstowa");
		rightSidePanel.add(new MultipleChoiceQuestView(),"Zagadka wielokrotnego wyboru");
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
