package NewInterface;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Quest.QuestFactory;
import Quest.QuestPoint;
import Quest.QuestType;

public class TextQuestView extends QuestView {

	private QuestPoint quest;
	private JTextArea questContent;
	private JTextField textField;
	
	private JLabel lblAnswer;
	private JLabel lblQuestContent;
	private Border border = BorderFactory.createLineBorder(Color.BLACK);
	
	public TextQuestView() {
		super();		
		this.add(new JLabel("Field"));
		setSize(panelWidth, panelHeight);
		lblQuestContent = new JLabel("Tre\u015B\u0107 Quizu");
		lblQuestContent.setBounds(26, 239, 75, 14);
		add(lblQuestContent);
		
		questContent = new JTextArea();
		questContent.setBorder(BorderFactory.createCompoundBorder(border, 
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		questContent.setLineWrap(true);
		questContent.setBounds(26, 264, 567, 61);
		add(questContent);
		
		lblAnswer = new JLabel("Odpowied\u017A");
		lblAnswer.setBounds(26, 353, 87, 14);
		add(lblAnswer);
		
		textField = new JTextField();
		textField.setBounds(26, 378, 567, 29);
		add(textField);
		textField.setColumns(10);
		quest = QuestFactory.createQuest(QuestType.FIELDQUEST);
	}
}
