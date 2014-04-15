/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pwr.Quest;

import java.util.ArrayList;

/**
 *
 * @author Michał Sypniewski
 */
public class OrderQuest extends QuestPoint implements DescribeQuest {

    ArrayList<String> questAnswer = new ArrayList<String>();
    String goTo;
    
    public OrderQuest() {
    	super(QuestType.ORDERQUEST);
    }

    public String getGoTo() {
        return goTo;
    }

    public void setGoTo(String goTo) {
        this.goTo=goTo;
    }

    @Override
    public ArrayList<String> getQuestAnswer() {
        return questAnswer;
    }

    @Override
    public void setQuestAnswer(ArrayList<String> answ) {
        this.questAnswer=answ;
    }

    @Override
    public void addQuestAnswer(String answ) {
        questAnswer.add(answ);
    }
}
