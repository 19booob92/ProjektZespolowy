package NewInterface;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Quest.QuestFactory;
import Quest.QuestPoint;
import Quest.QuestType;

public class FieldQuestView extends QuestView {

	private QuestPoint quest;

	public FieldQuestView() {
		super();
		this.add(new JLabel("Field"));
		quest = QuestFactory.createQuest(QuestType.FIELDQUEST);
		this.setSize(new Dimension(200, 200));
	}
}
