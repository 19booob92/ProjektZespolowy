package NewInterface;

import javax.swing.JPanel;

import Quest.QuestFactory;
import Quest.QuestPoint;
import Quest.QuestType;

public class TextQuestView extends QuestView {

	private QuestPoint quest;

	public TextQuestView() {
		super();
		quest = QuestFactory.createQuest(QuestType.TEXTQUEST);
	}

}
