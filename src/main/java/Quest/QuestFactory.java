package Quest;

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
			//Wprowadzic pozostale typy do fabryki
			default:
				quest = new TextQuest();
				break;
		}
		return quest;
	}
}
