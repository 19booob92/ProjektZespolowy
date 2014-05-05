package com.pwr.Editor;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import com.pwr.Quest.Campaign;
import com.pwr.Quest.ChoiceQuest;
import com.pwr.Quest.DecisionQuest;
import com.pwr.Quest.FieldQuest;
import com.pwr.Quest.OrderQuest;
import com.pwr.Quest.QuestFactory;
import com.pwr.Quest.QuestPoint;
import com.pwr.Quest.TextQuest;
import com.pwr.Quest.QuestType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XmlLoader {
	
	private File file;
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private Document doc;
	
	public XmlLoader(String name)
	{
		try {
			file = new File(name);
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void LoadXml(Campaign campaign)
	{
		NodeList nList = doc.getElementsByTagName("quiz");
		
		for(int i=0; i<nList.getLength();i++)
		{
			String title;
			String id;
			
			String date;
			String points;
			String goTo;
			String wrong;
			String timestop;
			
			String preNote;
			String postNote;
			
			ArrayList<String> imageList = new ArrayList();
			ArrayList<String> soundList = new ArrayList();
			ArrayList<String> paragraphList = new ArrayList();
			Node node = nList.item(i);
			if(node.getNodeType()==Node.ELEMENT_NODE)
			{
				System.out.println(i);
				Element element = (Element) node;
				System.out.println(element.getAttribute("title"));
				
				title=element.getAttribute("title");
				id=element.getAttribute("id");
				
				preNote=element.getElementsByTagName("prenote").item(0).getTextContent();
				postNote=element.getElementsByTagName("postnote").item(0).getTextContent();
				

				NodeList imageSrcList = element.getElementsByTagName("image");
				for(int j=0;j<imageSrcList.getLength();j++)
				{
					Element imageSrcElement = (Element) imageSrcList.item(j);
					if(imageSrcElement.getNodeType()==Element.ELEMENT_NODE)
					{
						System.out.println(imageSrcElement.getAttribute("src"));
						imageList.add(imageSrcElement.getAttribute("src"));
					}
				}
				
				
				NodeList soundSrcList = element.getElementsByTagName("sound");
				for(int j=0;j<soundSrcList.getLength();j++)
				{
					Element soundSrcElement = (Element) soundSrcList.item(j);
					if(soundSrcElement.getNodeType()==Element.ELEMENT_NODE)
					{
						System.out.println(soundSrcElement.getAttribute("src"));
						soundList.add(soundSrcElement.getAttribute("src"));
					}
				}
				
				NodeList paragraphSrcList = element.getElementsByTagName("questionmodule");
				Element paragraphSrcElement = (Element) paragraphSrcList.item(0);
				for(int j=0;j<paragraphSrcElement.getElementsByTagName("paragraph").getLength();j++)
				{
					if(paragraphSrcElement.getNodeType()==Element.ELEMENT_NODE)
					{
						System.out.println(paragraphSrcElement.getElementsByTagName("paragraph").item(j).getTextContent());
						paragraphList.add(paragraphSrcElement.getElementsByTagName("paragraph").item(j).getTextContent());
					}
				}
				
				
				NodeList answerSrcList = element.getElementsByTagName("answer");
				Element answerModuleElement = (Element) answerSrcList.item(0);
				
				String type = answerModuleElement.getAttribute("type");
				
				goTo = answerModuleElement.getAttribute("goto");
				points = answerModuleElement.getAttribute("points");
				if(points==""){points="0";}
				wrong = answerModuleElement.getAttribute("wrong");
				timestop = answerModuleElement.getAttribute("timestop");
				if(timestop==""){timestop="0";}
				date = answerModuleElement.getAttribute("datetime");
				
				ArrayList<String> answerList = new ArrayList();
				QuestPoint quest;
				
				switch(type)
				{
				case "Text":
					answerList = new ArrayList();
					NodeList answersList = answerModuleElement.getElementsByTagName("correct");
					for(int j=0;j<answersList.getLength();j++)
					{
						answerList.add(answersList.item(j).getTextContent());
					}					
					quest = (TextQuest) QuestFactory.createQuest(QuestType.TEXTQUEST);
					setQuest(quest,title,imageList,soundList,paragraphList,preNote,postNote);
					setQuestAnswer(quest,goTo,Integer.parseInt(points),date,wrong,Integer.parseInt(timestop));
					setTextQuest((TextQuest)quest,answerList);
					campaign.addQuiz(quest);
					campaign.createdTrue();
					break;
				case "GPS":
					String x = answerModuleElement.getElementsByTagName("x").item(0).getTextContent();
					String y = answerModuleElement.getElementsByTagName("y").item(0).getTextContent();
					String width = answerModuleElement.getElementsByTagName("width").item(0).getTextContent();
					String height = answerModuleElement.getElementsByTagName("height").item(0).getTextContent();
					quest = (FieldQuest) QuestFactory.createQuest(QuestType.FIELDQUEST);
					setQuest(quest,title,imageList,soundList,paragraphList,preNote,postNote);
					setQuestAnswer(quest,goTo,Integer.parseInt(points),date,wrong,Integer.parseInt(timestop));
					setGpsQuest((FieldQuest)quest,x,y,width,height);
					campaign.addQuiz(quest);
					campaign.createdTrue();
					break;
				case "MofN":
					answerList = new ArrayList();
					ArrayList<Boolean> answerListBool = new ArrayList();
					NodeList answersMList = answerModuleElement.getElementsByTagName("option");
					for(int j=0;j<answersMList.getLength();j++)
					{
						answerList.add(answersMList.item(j).getTextContent());
						Element answerMElement = (Element) answersMList.item(j);
						String tempAnswer = answerMElement.getAttribute("correct");
						if(tempAnswer.equals("true"))
						{
							answerListBool.add(true);
						}
						else if(tempAnswer.equals("false"))
						{
							answerListBool.add(false);
						}
					}
					quest = (ChoiceQuest) QuestFactory.createQuest(QuestType.CHOICEQUEST);
					setQuest(quest,title,imageList,soundList,paragraphList,preNote,postNote);
					setQuestAnswer(quest,goTo,Integer.parseInt(points),date,wrong,Integer.parseInt(timestop));
					setMofNQuest((ChoiceQuest)quest,answerList,answerListBool);
					campaign.addQuiz(quest);
					campaign.createdTrue();
					break;
				case "Decision":
					answerList=new ArrayList();
					ArrayList<String> goToList = new ArrayList();
					NodeList answersGoToList = answerModuleElement.getElementsByTagName("option");
					for(int j=0;j<answersGoToList.getLength();j++)
					{
						answerList.add(answersGoToList.item(j).getTextContent());
						Element answerGoToElement = (Element) answersGoToList.item(j);
						goToList.add(answerGoToElement.getAttribute("goto"));
					}
					quest = (DecisionQuest) QuestFactory.createQuest(QuestType.DECISIONQUEST);
					setQuest(quest,title,imageList,soundList,paragraphList,preNote,postNote);
					setQuestAnswer(quest,goTo,Integer.parseInt(points),date,wrong,Integer.parseInt(timestop));
					setDecisionQuest((DecisionQuest)quest,answerList,goToList);
					campaign.addQuiz(quest);
					campaign.createdTrue();
					break;
				case "Permutation":
					answerList=new ArrayList();
					ArrayList<String> indexList = new ArrayList();
					NodeList answersPermutationList = answerModuleElement.getElementsByTagName("option");
					for(int j=0;j<answersPermutationList.getLength();j++)
					{
						answerList.add(answersPermutationList.item(j).getTextContent());
						Element answerPermutationElement = (Element) answersPermutationList.item(j);
						indexList.add(answerPermutationElement.getAttribute("index"));
					}
					quest = QuestFactory.createQuest(QuestType.ORDERQUEST);
					setQuest(quest,title,imageList,soundList,paragraphList,preNote,postNote);
					setQuestAnswer(quest,goTo,Integer.parseInt(points),date,wrong,Integer.parseInt(timestop));
					setPermutationQuest((OrderQuest)quest,answerList,indexList);
					campaign.addQuiz(quest);
					campaign.createdTrue();
					break;
					default:
						break;
				}
				
				
			}
		}
		
		
	}
	
	private void setQuest(QuestPoint quest, String title,ArrayList<String> imageList,ArrayList<String> soundList, 
            ArrayList<String> paragraphList, String preNote, String postNote)
	{
		quest.setQuestName(title);
		quest.setPicturePaths(imageList);
		quest.setSoundPaths(soundList);
		quest.setQuestDescription(paragraphList);
		quest.setPreNote(preNote);
		quest.setPostNote(postNote);
	}
	
    private void setQuestAnswer(QuestPoint quest, String goTo, int points, String date, String wrong, int timestop)
	{
		quest.setGoTo(goTo);
		quest.setPoints(points);
		quest.setDate(date);
		quest.setWrong(wrong);
		quest.setQuestTimeout(timestop);
	}
    
    private void setTextQuest(TextQuest quest, ArrayList answerList)
    {
    	quest.setQuestAnswer(answerList);
    }
    
    private void setGpsQuest(FieldQuest quest, String x, String y, String width, String height)
    {
    	quest.setXCoordinate(Double.parseDouble(x));
    	quest.setXWidth(Double.parseDouble(width));
    	quest.setYCoordinate(Double.parseDouble(y));
    	quest.setYWidth(Double.parseDouble(height));
    }
    
    private void setMofNQuest(ChoiceQuest quest, ArrayList<String> answerList, ArrayList<Boolean> answerListBool)
    {
    	quest.setQuestAnswer(answerList, answerListBool);
    }
    
    private void setDecisionQuest(DecisionQuest quest, ArrayList<String> answerList, ArrayList<String> goToList)
    {
    	quest.setDecisionAnswer(answerList, goToList);
    }
    
    private void setPermutationQuest(OrderQuest quest, ArrayList<String> answerList, ArrayList<String> indexList)
    {
    	quest.setQuestAnswer(answerList);
    }

}
