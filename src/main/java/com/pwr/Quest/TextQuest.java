package com.pwr.Quest;

import java.util.ArrayList;


public class TextQuest extends QuestPoint implements DescribeQuest{

    private String goTo;
	private ArrayList<String> questAnswer = new ArrayList<String>();
	private double questTimeout;
	
	public TextQuest()
	{
		super(QuestType.TEXTQUEST);
	}
	
	public TextQuest(String answer, String content, double timeout){
		super(QuestType.TEXTQUEST);
		questAnswer.add(answer);
		//questContent = content;
		questTimeout = timeout;
	}

	public ArrayList<String> getQuestAnswer() {
		return questAnswer;
	}

	public void setQuestAnswer(String questAnswer) {
		this.questAnswer.add(questAnswer);
	}
        

    public String getGoTo() {
        return goTo;
    }

    public void setGoTo(String goTo) {
        this.goTo = goTo;
    }

    @Override
    public void setQuestAnswer(ArrayList<String> answ) {
        questAnswer=answ;
    }

    @Override
    public void addQuestAnswer(String answ) {
        questAnswer.add(answ);
    }

	@Override
	public ArrayList<String> getGoToList() {
		// TODO Auto-generated method stub
		return null;
	}
        

}
