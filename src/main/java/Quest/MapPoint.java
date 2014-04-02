package Quest;


public class MapPoint {
	private double xCoordinate;
	private double yCoordinate;
	private QuestPoint quest;
	
	public MapPoint(QuestType type)
	{ 
		quest = QuestFactory.createQuest(type);
		xCoordinate = 0;
		yCoordinate = 0;
	}
	
	public MapPoint(QuestType type, double x, double y)
	{
		quest = QuestFactory.createQuest(type);
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
