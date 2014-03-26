package Editor;

public class MapPoint {
	private double xCoordinate;
	private double yCoordinate;
	private QuestPoint quest;
	public MapPoint()
	{ 
		quest= new QuestPoint();
		xCoordinate = 0;
		yCoordinate = 0;
	}
	
	public MapPoint(double x, double y)
	{
		quest = new QuestPoint();
		xCoordinate= x;
		yCoordinate = y;
	}
	
	public String toString()
	{
		return xCoordinate+", "+yCoordinate;
	}

}
