package com.pwr.Quest;

import java.util.ArrayList;


public class ChoiceQuest extends QuestPoint implements DescribeQuest{
    
        private ArrayList<String> answerList = new ArrayList<String>();
        private ArrayList<Boolean> answerListCorrect = new ArrayList<Boolean>();
        private String goTo;
	
	public ChoiceQuest()
	{
		super(QuestType.CHOICEQUEST);
	}


	@Override
	public ArrayList<String> getQuestAnswer() {
		// TODO Auto-generated method stub
		return answerList;
	}
    public ArrayList<Boolean> getQuestAnswerCorrect()
    {
        return answerListCorrect;
    }

	public void setQuestAnswer(ArrayList<String> answ, ArrayList<Boolean> corr) {
		answerList=answ;
		answerListCorrect = corr;
	}

    public void addQuestAnswer(String answer,boolean correct)
    {
            answerListCorrect.add(correct);
    }

    public String getGoTo() {
        return goTo;
    }


    public void setGoTo(String goTo) {
        this.goTo=goTo;
    }


    @Override
    public void addQuestAnswer(String answ) {
        answerList.add(answ);
    }


	@Override
	public void setQuestAnswer(ArrayList<String> answ) {
		
	}


	@Override
	public ArrayList<String> getGoToList() {
		// TODO Auto-generated method stub
		return null;
	}

}
