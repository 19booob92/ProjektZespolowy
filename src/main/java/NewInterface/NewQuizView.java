package NewInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		rightSidePanel.setLayout(null);

		createLeftSidePanel();
		createRightSidePanel();

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

		textField = new JTextField();
		textField.setBounds(63, 36, 154, 20);
		leftSidePanel.add(textField);
		textField.setColumns(10);

		String[] quizTypes = { "Zagadka terenowa",
				"Zagadka wielokrotnego wyboru", "Zagadka uporządkowania",
				"Zagadka tekstowa" };
		final JComboBox questTypeCombo = new JComboBox(quizTypes);
		questTypeCombo.setBounds(63, 70, 154, 20);
		leftSidePanel.add(questTypeCombo);

		JLabel lblOpcje = new JLabel("Typ");
		lblOpcje.setBounds(20, 73, 46, 14);
		leftSidePanel.add(lblOpcje);

		JLabel lblOpcjeQuizu = new JLabel("Opcje quizu");
		lblOpcjeQuizu.setBounds(72, 149, 126, 14);
		leftSidePanel.add(lblOpcjeQuizu);

		questTypeCombo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int choice = questTypeCombo.getSelectedIndex();
				switch (choice) {
				case 0:
					rightSidePanel.add(new FieldQuestView());
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				default:
					break;
				}
			}

		});
	}

	private void createRightSidePanel() {
		JLabel lblWidokOpcjiW = new JLabel(
				"Widok opcji w zaleznosci od wybranego quizu, czyli test wyboru, google mapa, porzadkowanie, tekst");
		lblWidokOpcjiW.setBounds(10, 68, 510, 14);
		rightSidePanel.add(lblWidokOpcjiW);
	}
}
