package Quest;

import java.util.ArrayList;

public class RangeQuest extends QuestPoint implements DescribeQuest {
	public RangeQuest() {
		super(QuestType.RANGEQUEST);
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
}
