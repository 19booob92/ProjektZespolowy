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
	
}
