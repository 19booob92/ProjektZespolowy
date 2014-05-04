package com.pwr.Quest;

import java.util.ArrayList;

public class DecisionQuest extends QuestPoint implements DescribeQuest {
   
    private ArrayList<String> questAnswer = new ArrayList<String>();
    private ArrayList<String> goToList = new ArrayList<String>();
    
	public DecisionQuest() {
		super(QuestType.DECISIONQUEST);
	}

	@Override
	public ArrayList<String> getQuestAnswer() {
            return questAnswer;
	}

    public ArrayList<String> getGoToList() {
        return goToList;
    }

    public void setGoToList(ArrayList<String> goToList) {
        this.goToList = goToList;
    }

    public void setDecisionAnswer(ArrayList<String> answ, ArrayList<String> numbers) {
        questAnswer=answ;
        goToList=numbers;
    }

    @Override
    public void addQuestAnswer(String answ) {
        questAnswer.add(answ);
    }

	@Override
	public void setQuestAnswer(ArrayList<String> answ) {
		
	}

	@Override
	public void setGoTo(String goTo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getGoTo() {
		return null;
	}

    
}

