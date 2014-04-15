package com.pwr.Quest;

public class QuestFactory {
	public static QuestPoint createQuest(QuestType type){
		QuestPoint quest = null;
		
		switch(type){
			case TEXTQUEST:
				quest = new TextQuest();
				break;
			case CHOICEQUEST:
				quest = new ChoiceQuest();
				break;
			case FIELDQUEST:
				quest = new FieldQuest();
				break;
			case ORDERQUEST:
				quest = new OrderQuest();
				break;
			case RANGEQUEST:
				quest = new RangeQuest();
				break;
			default:
				quest = new TextQuest();
				break;
		}
		return quest;
	}
}
