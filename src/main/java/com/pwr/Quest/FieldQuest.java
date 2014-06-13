package com.pwr.Quest;

import java.util.ArrayList;

import com.pwr.Map.MapGetter;

public class FieldQuest extends QuestPoint implements DescribeQuest {
	private double xCoordinate;
	private double yCoordinate;
	private double x2Coordinate;
	private double y2Coordinate;
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
		public void countDimmension()
		{
			width = (y2Coordinate - yCoordinate);
			 height = (x2Coordinate - xCoordinate);
		}
		public void setX2Coordinate(double x)
		{
			x2Coordinate=x;
		}
		
		public void setY2Coordinate(double y)
		{
			y2Coordinate=y;
		}
		
		public double getX2Coordinate()
		{
			return x2Coordinate;
		}
		
		public double getY2Coordinate()
		{
			return y2Coordinate;
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
        public double getYCoordinate()
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
            return width;
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

	@Override
	public ArrayList<String> getGoToList() {
		// TODO Auto-generated method stub
		return null;
	}
}
