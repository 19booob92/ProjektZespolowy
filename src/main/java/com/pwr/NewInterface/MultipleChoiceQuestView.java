package com.pwr.NewInterface;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.pwr.Quest.Campaign;
import com.pwr.Quest.QuestFactory;
import com.pwr.Quest.QuestPoint;
import com.pwr.Quest.QuestType;

public class MultipleChoiceQuestView extends QuestView {

	private QuestPoint quest;

	public MultipleChoiceQuestView() {
		super();
		this.add(new JLabel("Multi"));
		quest = QuestFactory.createQuest(QuestType.CHOICEQUEST);
	}

}
