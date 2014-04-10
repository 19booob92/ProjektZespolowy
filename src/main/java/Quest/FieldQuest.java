package Quest;

import java.util.ArrayList;

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
	public String getQuestAnswer() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setQuestAnswer(String answ) {
		// TODO Auto-generated method stub

	}

    @Override
    public ArrayList<String> getQuestDescription() {
        return null;
    }

    @Override
    public void setQuestDescription(ArrayList<String> descript) {
       
    }

    @Override
    public void addQuestDescription(String descript) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
