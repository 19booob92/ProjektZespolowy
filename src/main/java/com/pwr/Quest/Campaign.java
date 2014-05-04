package com.pwr.Quest;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.TransformerException;

import com.pwr.Editor.XmlBuilder;
import com.pwr.Editor.ZipPacker;
import com.pwr.Graph.QuizDataObject;
import com.pwr.NewInterface.NewQuizView;
import java.io.IOException;

public class Campaign extends Observable{

	private ArrayList<QuestPoint> quests;
	private ArrayList<TreasureBox> boxes;
	private ArrayList<String> introPics;
	private ArrayList<String> outroPics;
    private ZipPacker zip;
    private boolean changed = false;
        
	public Campaign() {
		quests = new ArrayList();
		boxes = new ArrayList();
		introPics = new ArrayList();
		outroPics = new ArrayList();
	}
	
	public List<QuizDataObject> convertQuiz() {
		List<QuizDataObject> quizDTOs = new ArrayList<>();
		for (QuestPoint q : quests)
		{
			quizDTOs.add(q.convert());
		}
		return quizDTOs;
	}
        
	public void changeState () {
		changed = !changed;
		setChanged();
		notifyObservers();
	}
	
	public void addQuiz(QuestPoint p) {
		quests.add(p);
	}

	public void addTreasureBox(TreasureBox b) {
		boxes.add(b);
	}

	public void setQuiz() {

	}

	public QuestPoint getLastQuiz() {
		return quests.get(quests.size()-1);
	}

	public TreasureBox getTreasureBox() {
		return null;
	}
        


	public ArrayList<QuestPoint> getQuizes() {
		return quests;
	}
	
	public ArrayList<String> getQuizesNames() {
		ArrayList names = new ArrayList();
		for (QuestPoint p : quests){
			names.add(p.toString());
		}
		return names;		
	}

	public ArrayList<TreasureBox> getTreasureBoxes() {
		return boxes;
	}
        
        public void createXml(String title)
        {
            XmlBuilder xml = new XmlBuilder(title);
            xml.resetId();
            zip = new ZipPacker("paczka.zip");
            xml.createIntro(introPics, introPics);
            xml.createOutro(outroPics, outroPics);
            for(int i=0;i<quests.size();i++)
            {
                ZipPacking(quests.get(i));
                QuestPoint tempQuest = quests.get(i);
                if(tempQuest.getQuestType()==QuestType.TEXTQUEST)
                {
                    TextQuest quest = (TextQuest)tempQuest;
                    xml.addQuizText(quest.getQuestName(),  quest.getPicturePaths(),quest.getSoundPaths(), quest.getQuestDescription(), quest.getPreNote(),
                                                        quest.getPostNote(), quest.getGoTo(), quest.getPoints(), quest.getDate(), quest.getQuestAnswer(), quest.getQuestTimeout(), quest.getWrong());
                }
                else if(tempQuest.getQuestType()==QuestType.FIELDQUEST)
                {
                    FieldQuest quest = (FieldQuest)tempQuest;
                    xml.addQuizGPS(quest.getQuestName(), quest.getPicturePaths(), quest.getSoundPaths(), quest.getQuestDescription(), quest.getPreNote(),
                                                        quest.getPostNote(), quest.getGoTo(), quest.getPoints(), quest.getDate(), quest.getXCoordinate(),
                                                        quest.getYCoordninate(), quest.getWidth(), quest.getHeight(),quest.getQuestTimeout(),quest.getWrong());
                }
                else if(tempQuest.getQuestType()==QuestType.DECISIONQUEST)
                {
                    DecisionQuest quest = (DecisionQuest)tempQuest;
                    xml.addQuizDecision(quest.getQuestName(),  quest.getPicturePaths(),quest.getSoundPaths(), quest.getQuestDescription(), quest.getPreNote(),
                                                        quest.getPostNote(), quest.getPoints(), quest.getDate(), quest.getGoToList(), quest.getQuestAnswer(),
                                                        quest.getQuestTimeout(),quest.getWrong());
                }
                else if(tempQuest.getQuestType()==QuestType.CHOICEQUEST)
                {
                    ChoiceQuest quest = (ChoiceQuest)tempQuest;
                    xml.addQuizMofn(quest.getQuestName(),  quest.getPicturePaths(), quest.getSoundPaths(),quest.getQuestDescription(), quest.getPreNote(),
                                                        quest.getPostNote(),quest.getGoTo(), quest.getPoints(), quest.getDate(),quest.getQuestAnswer(),quest.getQuestAnswerCorrect(),
                                                        quest.getQuestTimeout(),quest.getWrong());
                }
                else if(tempQuest.getQuestType()==QuestType.ORDERQUEST)
                {
                    OrderQuest quest = (OrderQuest)tempQuest;
                    xml.addQuizPermutation(quest.getQuestName(),  quest.getPicturePaths(),quest.getSoundPaths(), quest.getQuestDescription(), quest.getPreNote(),
                                                        quest.getPostNote(),quest.getGoTo(), quest.getPoints(), quest.getDate(),quest.getWrong(),quest.getQuestTimeout(),quest.getQuestAnswer());
                }
            }
            
            for(int i=0;i<boxes.size();i++)
            {
                TreasureBox box = boxes.get(i);
                xml.addTreasureBox(box.getxCoordinate(), box.getyCoordinate(), box.getWidth(), box.getHeight(), box.getNote());
            }
            
            try {
                xml.saveXml();
            } catch (TransformerException ex) {
                Logger.getLogger(Campaign.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
            
            try {
			zip.addFile("Config.xml");
		} catch (IOException ex) {
			Logger.getLogger(NewQuizView.class.getName()).log(
					Level.SEVERE, null, ex);
		}
            zip.closeZip();
        }
        
        private void ZipPacking(QuestPoint newQuest)
	{
		for (int i = 0; i < newQuest.getPicturePaths().size(); i++) {
			try {
				zip.addFile(newQuest.getPicturePaths().get(i));
			} catch (IOException ex) {
				Logger.getLogger(NewQuizView.class.getName())
						.log(Level.SEVERE, null, ex);
			}
		}

		for (int i = 0; i < newQuest.getSoundPaths().size(); i++) {
			try {
				zip.addFile(newQuest.getSoundPaths().get(i));
			} catch (IOException ex) {
				Logger.getLogger(NewQuizView.class.getName())
						.log(Level.SEVERE, null, ex);
			}
		}

	}

}
