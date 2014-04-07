package NewInterface;

import javax.swing.JPanel;

import Quest.QuestFactory;
import Quest.QuestPoint;
import Quest.QuestType;

public class FieldQuestView extends QuestView {

	private QuestPoint quest;

	public FieldQuestView() {
		super();
		quest = QuestFactory.createQuest(QuestType.TEXTQUEST);
	}

}
