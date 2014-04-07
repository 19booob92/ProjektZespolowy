package NewInterface;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Quest.QuestFactory;
import Quest.QuestPoint;
import Quest.QuestType;

public class FieldQuestView extends QuestView {

	private QuestPoint quest;
	private JTextArea questContent;
	
	public FieldQuestView() {
		super();		
		this.add(new JLabel("Field"));
		
		JLabel lblTreQuizu = new JLabel("Tre\u015B\u0107 Quizu");
		lblTreQuizu.setBounds(26, 213, 75, 14);
		add(lblTreQuizu);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(188, 208, 252, 61);
		add(textArea);
		quest = QuestFactory.createQuest(QuestType.FIELDQUEST);
	}
}
