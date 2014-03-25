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
	
	public MapPoint(double [] coords)
	{
		quest = new QuestPoint();
		xCoordinate= coords[0];
		yCoordinate = coords[1];
	}
}
