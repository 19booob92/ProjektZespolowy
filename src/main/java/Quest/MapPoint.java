package Quest;


public class MapPoint {
	private double xCoordinate;
	private double yCoordinate;
	private QuestPoint quest;
	
	public MapPoint()
	{ 
		quest= new TextQuest();
		xCoordinate = 0;
		yCoordinate = 0;
	}
	
	public MapPoint(double x, double y)
	{
		quest = new TextQuest();
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
