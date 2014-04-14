package Quest;

import Editor.XmlBuilder;
import static Quest.QuestType.TEXTQUEST;
import java.util.ArrayList;

public class Campaign {

	private ArrayList<QuestPoint> quests;
	private ArrayList<TreasureBox> boxes;

	public Campaign() {
		quests = new ArrayList();
		boxes = new ArrayList();
	}

	public void addQuiz(QuestPoint p) {
		quests.add(p);
	}

	public void addTreasureBox(TreasureBox b) {
		boxes.add(b);
	}

	public void setQuiz() {

	}

	public QuestPoint getQuiz() {
		return null;
	}

	public TreasureBox getTreasureBox() {
		return null;
	}
        


	public ArrayList<QuestPoint> getQuizes() {
		return quests;
	}

	public ArrayList<TreasureBox> getTreasureBoxes() {
		return boxes;
	}
        
        public void createXml(String title)
        {
            XmlBuilder xml = new XmlBuilder(title);
            for(int i=0;i<quests.size();i++)
            {
                QuestPoint tempQuest = quests.get(i);
                if(tempQuest.getQuestType()==QuestType.TEXTQUEST)
                {
                    TextQuest quest = (TextQuest)tempQuest;
                    xml.addQuizText(quest.getQuestName(),  quest.getSoundPaths(),quest.getPicturePaths(), quest.getQuestDescription(), quest.getPreNote(),
                                                        quest.getPostNote(), quest.getGoTo(), quest.getPoints(), quest.getDate(), quest.getQuestAnswer(), quest.getQuestTimeout(), quest.getWrong());
                }
                else if(tempQuest.getQuestType()==QuestType.FIELDQUEST)
                {
                    FieldQuest quest = (FieldQuest)tempQuest;
                    xml.addQuizGPS(quest.getQuestName(),  quest.getSoundPaths(),quest.getPicturePaths(), quest.getQuestDescription(), quest.getPreNote(),
                                                        quest.getPostNote(), quest.getGoTo(), quest.getPoints(), quest.getDate(), quest.getXCoordinate(),
                                                        quest.getYCoordninate(), quest.getWidth(), quest.getHeight(),quest.getQuestTimeout(),quest.getWrong());
                }
                else if(tempQuest.getQuestType()==QuestType.RANGEQUEST)
                {
                    RangeQuest quest = (RangeQuest)tempQuest;
                    xml.addQuizDecision(quest.getQuestName(),  quest.getSoundPaths(),quest.getPicturePaths(), quest.getQuestDescription(), quest.getPreNote(),
                                                        quest.getPostNote(), quest.getPoints(), quest.getDate(), quest.getGoToList(), quest.getQuestAnswer(),
                                                        quest.getQuestTimeout(),quest.getWrong());
                }
                else if(tempQuest.getQuestType()==QuestType.CHOICEQUEST)
                {
                    ChoiceQuest quest = (ChoiceQuest)tempQuest;
                    xml.addQuizMofn(quest.getQuestName(),  quest.getSoundPaths(),quest.getPicturePaths(), quest.getQuestDescription(), quest.getPreNote(),
                                                        quest.getPostNote(),quest.getGoTo(), quest.getPoints(), quest.getDate(),quest.getQuestAnswer(),quest.getQuestAnswerCorrect(),
                                                        quest.getQuestTimeout(),quest.getWrong());
                }
                else if(tempQuest.getQuestType()==QuestType.ORDERQUEST)
                {
                    OrderQuest quest = (OrderQuest)tempQuest;
                    xml.addQuizPermutation(quest.getQuestName(),  quest.getSoundPaths(),quest.getPicturePaths(), quest.getQuestDescription(), quest.getPreNote(),
                                                        quest.getPostNote(),quest.getGoTo(), quest.getPoints(), quest.getDate(),quest.getWrong(),quest.getQuestTimeout(),quest.getQuestAnswer());
                }
            }
            
            for(int i=0;i<boxes.size();i++)
            {
                TreasureBox box = boxes.get(i);
                xml.addTreasureBox(box.getxCoordinate(), box.getyCoordinate(), box.getWidth(), box.getHeight(), box.getNote());
            }
        }
}
