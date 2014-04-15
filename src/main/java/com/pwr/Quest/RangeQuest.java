package com.pwr.Quest;

import java.util.ArrayList;

public class RangeQuest extends QuestPoint implements DescribeQuest {
   
    private ArrayList<String> questAnswer = new ArrayList<String>();
    private ArrayList<String> goToList = new ArrayList<String>();
    
	public RangeQuest() {
		super(QuestType.RANGEQUEST);
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

    @Override
    public void setQuestAnswer(ArrayList<String> answ) {
        questAnswer=answ;
    }

    @Override
    public void addQuestAnswer(String answ) {
        questAnswer.add(answ);
    }

    
}
