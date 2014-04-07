package NewInterface;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Quest.QuestFactory;
import Quest.QuestPoint;
import Quest.QuestType;

public class MultipleChoiceQuestView extends QuestView {

	private QuestPoint quest;

	public MultipleChoiceQuestView() {
		super();
		this.add(new JLabel("Multi"));
		quest = QuestFactory.createQuest(QuestType.CHOICEQUEST);
	}

}
