package com.pwr.NewInterface;

import javax.swing.JPanel;

import com.pwr.Quest.Campaign;
import com.pwr.Quest.QuestFactory;
import com.pwr.Quest.QuestPoint;
import com.pwr.Quest.QuestType;

public class OrderQuestView extends QuestView {

	private QuestPoint quest;

	public OrderQuestView() {
		super();
		quest = QuestFactory.createQuest(QuestType.ORDERQUEST);
	}

}
