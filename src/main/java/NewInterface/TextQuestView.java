package NewInterface;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Quest.QuestFactory;
import Quest.QuestPoint;
import Quest.QuestType;

public class TextQuestView extends QuestView {

	private QuestPoint quest;
	private JTextArea questContent;
	private JTextField textField;
	
	public TextQuestView() {
		super();		
		this.add(new JLabel("Field"));
		setSize(panelWidth, panelHeight);
		JLabel lblQuestContent = new JLabel("Tre\u015B\u0107 Quizu");
		lblQuestContent.setBounds(26, 239, 75, 14);
		add(lblQuestContent);
		
		JTextArea questContent = new JTextArea();
		questContent.setLineWrap(true);
		questContent.setBounds(26, 264, 252, 61);
		add(questContent);
		
		JLabel lblAnswer = new JLabel("Odpowied\u017A");
		lblAnswer.setBounds(26, 353, 87, 14);
		add(lblAnswer);
		
		textField = new JTextField();
		textField.setBounds(26, 378, 252, 29);
		add(textField);
		textField.setColumns(10);
		quest = QuestFactory.createQuest(QuestType.FIELDQUEST);
	}
}
