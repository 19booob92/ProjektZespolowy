package NewInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class NewQuizView extends JFrame {

	private JPanel leftSidePanel;
	private JPanel rightSidePanel;
	private JSplitPane splitPane;
	private JTextField textField;

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

		JLabel lblNewQuiz = new JLabel("Nowy Quiz");
		lblNewQuiz.setBounds(10, 11, 207, 14);
		leftSidePanel.add(lblNewQuiz);

		JLabel lblTitle = new JLabel("Tytu\u0142");
		lblTitle.setBounds(20, 39, 46, 14);
		leftSidePanel.add(lblTitle);

		textField = new JTextField();
		textField.setBounds(63, 36, 154, 20);
		leftSidePanel.add(textField);
		textField.setColumns(10);

		String[] quizTypes = { "Zagadka terenowa",
				"Zagadka wielokrotnego wyboru", "Zagadka uporządkowania",
				"Zagadka tekstowa" };
		JComboBox comboBox = new JComboBox(quizTypes);
		comboBox.setBounds(63, 70, 154, 20);
		leftSidePanel.add(comboBox);

		JLabel lblOpcje = new JLabel("Typ");
		lblOpcje.setBounds(20, 73, 46, 14);
		leftSidePanel.add(lblOpcje);

		JLabel lblOpcjeQuizu = new JLabel("Opcje quizu");
		lblOpcjeQuizu.setBounds(72, 149, 126, 14);
		leftSidePanel.add(lblOpcjeQuizu);
		rightSidePanel.setLayout(null);

		JLabel lblWidokOpcjiW = new JLabel(
				"Widok opcji w zaleznosci od wybranego quizu, czyli test wyboru, google mapa, porzadkowanie, tekst");
		lblWidokOpcjiW.setBounds(10, 68, 510, 14);
		rightSidePanel.add(lblWidokOpcjiW);
		splitPane.setResizeWeight(0.3);
		splitPane.setBounds(10, 11, 764, 418);
		getContentPane().add(splitPane);
		setVisible(true);
	}
}
