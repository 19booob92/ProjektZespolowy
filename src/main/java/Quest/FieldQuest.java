package Quest;

public class FieldQuest extends QuestPoint implements DescribeQuest {
	private double xCoordinate;
	private double yCoordinate;
	
	public FieldQuest() {
		super(QuestType.FIELDQUEST);
		xCoordinate = 0;
		yCoordinate = 0;
	}

	public String toString()
	{
		return xCoordinate+", "+yCoordinate;
	}
	
	@Override
	public String getQuestDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQuestAnswer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQuestDescription(String descript) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setQuestAnswer(String answ) {
		// TODO Auto-generated method stub

	}
}
