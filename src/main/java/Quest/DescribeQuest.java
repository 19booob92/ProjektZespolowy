package Quest;

import java.util.ArrayList;

public interface DescribeQuest {

	public ArrayList<String> getQuestDescription();
	
	public String getQuestAnswer();
	
	public void setQuestDescription(ArrayList<String> descript);
        
        public void addQuestDescription(String descript);
	
	public void setQuestAnswer(String answ);
        
        public String getGoTo();
        
        public void setGoTo(String goTo);
                
}
