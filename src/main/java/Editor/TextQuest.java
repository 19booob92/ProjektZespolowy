package Editor;

public class TextQuest extends QuestPoint{

	private String questAnswer;
	private String questContent;
	private double questTimeout;
	
	public TextQuest()
	{
		super();
	}
	
	public TextQuest(String answer, String content, double timeout){
		super();
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

	public String getQuestContent() {
		return questContent;
	}

	public void setQuestContent(String questContent) {
		this.questContent = questContent;
	}

	public double getQuestTimeout() {
		return questTimeout;
	}

	public void setQuestTimeout(double questTimeout) {
		this.questTimeout = questTimeout;
	}
	
}
