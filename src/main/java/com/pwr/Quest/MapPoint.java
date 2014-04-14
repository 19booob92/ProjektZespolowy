package com.pwr.Quest;


public class MapPoint {
	private double xCoordinate;
	private double yCoordinate;
	private QuestPoint quest;
	
	public MapPoint()
	{ 
		quest = QuestFactory.createQuest(QuestType.FIELDQUEST);
		xCoordinate = 0;
		yCoordinate = 0;
	}
	
	public MapPoint(double x, double y)
	{
		quest = QuestFactory.createQuest(QuestType.FIELDQUEST);
		xCoordinate= x;
		yCoordinate = y;
	}
	
	public String toString()
	{
		return xCoordinate+", "+yCoordinate;
	}

	public QuestPoint getQuest() {
		return quest;
	}
	
	public void setQuest(QuestPoint quest) {
		this.quest = quest;
	}
}
