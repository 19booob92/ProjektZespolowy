package Quest;

import java.util.ArrayList;


public class TextQuest extends QuestPoint implements DescribeQuest{

        private String goTo;
	private String questAnswer;
	private ArrayList<String> questContent = new ArrayList<String>();
	private double questTimeout;
	
	public TextQuest()
	{
		super(QuestType.TEXTQUEST);
	}
	
	public TextQuest(String answer, String content, double timeout){
		super(QuestType.TEXTQUEST);
		questAnswer = answer;
		//questContent = content;
		questTimeout = timeout;
	}

	public String getQuestAnswer() {
		return questAnswer;
	}

	public void setQuestAnswer(String questAnswer) {
		this.questAnswer = questAnswer;
	}


    @Override
    public ArrayList<String> getQuestDescription() {
        return questContent;
    }

    @Override
    public void setQuestDescription(ArrayList<String> descript) {
        questContent=descript;
    }

    @Override
    public void addQuestDescription(String descript) {
        questContent.add(descript);
    }

    public String getGoTo() {
        return goTo;
    }

        @Override
    public void setGoTo(String goTo) {
        this.goTo = goTo;
    }
    
    public void nic()
    {
    }
    

}
