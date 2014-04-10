package Quest;

import java.util.ArrayList;


public class ChoiceQuest extends QuestPoint implements DescribeQuest{
	
	public ChoiceQuest()
	{
		super(QuestType.CHOICEQUEST);
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
    }

    @Override
    public String getGoTo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setGoTo(String goTo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
