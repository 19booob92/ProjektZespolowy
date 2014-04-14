package com.pwr.Quest;

import java.util.ArrayList;

public class FieldQuest extends QuestPoint implements DescribeQuest {
	private double xCoordinate;
	private double yCoordinate;
        private double width;
        private double height;
	private String goTo;
        
	public FieldQuest() {
		super(QuestType.FIELDQUEST);
		xCoordinate = 0;
		yCoordinate = 0;
	}

	public String toString()
	{
		return xCoordinate+", "+yCoordinate;
	}
	

        public void setXCoordinate(double x)
        {
            xCoordinate=x;
        }
        public void setYCoordinate(double y)
        {
            yCoordinate=y;
        }
        public double getXCoordinate()
        {
            return xCoordinate;
        }
        public double getYCoordninate()
        {
            return yCoordinate;
        }
        
        public void setXWidth(double width)
        {
            this.width=width;
        }
        public void setYWidth(double height)
        {
            this.height=height;
        }
        public double getWidth()
        {
            return height;
        }
        public double getHeight()
        {
            return height;
        }


    public String getGoTo() {
        return goTo;
    }

    public void setGoTo(String goTo) {
        this.goTo=goTo;
    }

    @Override
    public void setQuestAnswer(ArrayList<String> answ) {
        
    }

    @Override
    public void addQuestAnswer(String answ) {
        
    }

    @Override
    public ArrayList<String> getQuestAnswer() {
        return null;
    }
}
