package NewInterface;

import javax.swing.JPanel;

import Quest.QuestFactory;
import Quest.QuestPoint;
import Quest.QuestType;

public class RangeQuestView extends QuestView {

	private QuestPoint quest;

	public RangeQuestView() {
		super();
		quest = QuestFactory.createQuest(QuestType.RANGEQUEST);
	}
}
