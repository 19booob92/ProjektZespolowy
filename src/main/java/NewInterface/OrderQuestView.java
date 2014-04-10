package NewInterface;

import javax.swing.JPanel;

import Quest.Campaign;
import Quest.QuestFactory;
import Quest.QuestPoint;
import Quest.QuestType;

public class OrderQuestView extends QuestView {

	private QuestPoint quest;

	public OrderQuestView() {
		super();
		quest = QuestFactory.createQuest(QuestType.ORDERQUEST);
	}

}
