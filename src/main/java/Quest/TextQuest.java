package Quest;


public class TextQuest extends QuestPoint implements DescribeQuest{

	private String questAnswer;
	private String questContent;
	private double questTimeout;
	
	public TextQuest()
	{
		super(QuestType.TEXTQUEST);
	}
	
	public TextQuest(String answer, String content, double timeout){
		super(QuestType.TEXTQUEST);
		questAnswer = answer;
		questContent = content;
		questTimeout = timeout;
	}

	public String getQuestAnswer() {
		return questAnswer;
	}

	public void setQuestAnswer(String questAnswer) {
		this.questAnswer = questAnswer;
	}

	public String getQuestDescription() {
		return questContent;
	}

	public void setQuestDescription(String descript) {
			questContent = descript;	
	}
	
}
