package com.pwr.NewInterface;

import javax.swing.JPanel;

import com.pwr.Quest.Campaign;
import com.pwr.Quest.QuestFactory;
import com.pwr.Quest.QuestPoint;
import com.pwr.Quest.QuestType;

public class DecisionQuestView extends QuestView implements DescribeView{

	private QuestPoint quest;

	public DecisionQuestView() {
		super();
		quest = QuestFactory.createQuest(QuestType.DECISIONQUEST);
	}

	@Override
	public String introduceYourself() {
		return "RangeQuest";
	}
}
