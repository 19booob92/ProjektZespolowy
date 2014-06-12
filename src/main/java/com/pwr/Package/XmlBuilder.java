/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pwr.Package;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

/**
 *
 * @author Michał Sypniewski
 */
public class XmlBuilder {
    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;
    private Document doc;
    private String name;
    private Element gameModule;
    private Element campaignModule;
    
    private static int quizTriggerId=0;
    public XmlBuilder(String tName)
    {
        try {
            
        name=tName;
        docFactory=DocumentBuilderFactory.newInstance();
        docBuilder=docFactory.newDocumentBuilder();
        doc = docBuilder.newDocument();    
        gameModule = doc.createElement("game");
        doc.appendChild(gameModule);
        
        Attr attr = doc.createAttribute("title");
	attr.setValue(name);
        gameModule.setAttributeNode(attr);
        
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        campaignModule = doc.createElement("campaign");
        
        gameModule.appendChild(campaignModule);
    }
    
    public void saveXml() throws TransformerException
    {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Config.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XmlBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createIntro(ArrayList<String> imageList, ArrayList<String> descriptionList)
    {

        Element introElement = doc.createElement("intro");
        gameModule.appendChild(introElement);
        for(int i=0;i<imageList.size();i++)
        {
        Path path = Paths.get(imageList.get(i));
        Element introModule = doc.createElement("introModule");
        Attr attr = doc.createAttribute("image");
        attr.setValue(path.getFileName().toString());
        introModule.setAttributeNode(attr);
        introModule.appendChild(doc.createTextNode(descriptionList.get(i)));
        introElement.appendChild(introModule);
        }    
    }
    
    public void createOutro(ArrayList<String> imageList, ArrayList<String> descriptionList)
    {
        Element outroElement = doc.createElement("outro");
        gameModule.appendChild(outroElement);
        for(int i=0;i<imageList.size();i++)
        {
        Path path = Paths.get(imageList.get(i));
        Element outroModule = doc.createElement("outroModule");
        Attr attr = doc.createAttribute("image");
        attr.setValue(path.getFileName().toString());
        outroModule.setAttributeNode(attr);
        outroModule.appendChild(doc.createTextNode(descriptionList.get(i)));
        outroElement.appendChild(outroModule);
        }    
    }
    public void addQuizGPS(String title,ArrayList<String> imageList, ArrayList<Boolean> imageInventoryList,ArrayList<String> soundList,
    		ArrayList<Boolean> soundInventoryList, int narration,String paragraphList, String preNote, String postNote,
    		String goTo, int points, String date,double x,double y,
            double width, double height, int timestop, String wrong)
    {
        Element quizModule = doc.createElement("quiz");
        createQuiz(title,imageList,imageInventoryList,soundList,soundInventoryList,narration,paragraphList,preNote,postNote,quizModule);
        
        Element answerModule = doc.createElement("answermodule");
        quizModule.appendChild(answerModule);
        
        Element answerElement = doc.createElement("answer");
        
        createAnswerModule("GPS",goTo,points,date,wrong,timestop,answerElement);
        
        answerModule.appendChild(answerElement);
        
        Element coordinatesModule = doc.createElement("coordinates");
        answerElement.appendChild(coordinatesModule);
        
        Element xElement = doc.createElement("x");
        xElement.appendChild(doc.createTextNode(Double.toString(x)));
        coordinatesModule.appendChild(xElement);
        
        Element yElement = doc.createElement("y");
        yElement.appendChild(doc.createTextNode(Double.toString(y)));
        coordinatesModule.appendChild(yElement);
        
        Element widthElement = doc.createElement("width");
        widthElement.appendChild(doc.createTextNode(Double.toString(width)));
        coordinatesModule.appendChild(widthElement);
        
        Element heightElement = doc.createElement("height");
        heightElement.appendChild(doc.createTextNode(Double.toString(height)));
        coordinatesModule.appendChild(heightElement);
    }
    
    public void addQuizText(String title,ArrayList<String> imageList, ArrayList<Boolean> imageInventoryList,ArrayList<String> soundList,
    		ArrayList<Boolean> soundInventoryList, int narration,
    		String paragraphList, String preNote, String postNote,String goTo, int points, String date, ArrayList<String> answerList,
            int timestop, String wrong)
    {
        Element quizModule = doc.createElement("quiz");
        createQuiz(title,imageList,imageInventoryList,soundList,soundInventoryList,narration,paragraphList,preNote,postNote,quizModule);
        
        Element answerModule = doc.createElement("answermodule");
        quizModule.appendChild(answerModule);
        
        Element answerElement = doc.createElement("answer");
        createAnswerModule("Text",goTo,points,date,wrong,timestop,answerElement);
        
        answerModule.appendChild(answerElement);
        
        for(int i=0;i<answerList.size();i++){
        	if(!answerList.get(i).equals(""))
        	{
		        Element correctElement = doc.createElement("correct");
		        correctElement.appendChild(doc.createTextNode(answerList.get(i)));
		        answerElement.appendChild(correctElement);
        	}
        }
    }
    
    public void addQuizDecision(String title,ArrayList<String> imageList, ArrayList<Boolean> imageInventoryList,ArrayList<String> soundList,
    		ArrayList<Boolean> soundInventoryList, int narration,
    		String paragraphList, String preNote, String postNote, int points, String date,ArrayList<String> goToList,
            ArrayList<String> answerList, int timestop, String wrong)
    {
        Element quizModule = doc.createElement("quiz");
        createQuiz(title,imageList,imageInventoryList,soundList,soundInventoryList,narration,paragraphList,preNote,postNote,quizModule);
        
        Element answerModule = doc.createElement("answermodule");
        quizModule.appendChild(answerModule);
        
        Element answerElement = doc.createElement("answer");
        
        createAnswerModule("Decision",null,points,date,wrong,timestop,answerElement);
        
        answerModule.appendChild(answerElement);
        for(int i=0;i<answerList.size();i++)
        {
        	if(!answerList.get(i).equals(""))
        	{
	            Element optionElement = doc.createElement("option");
	            Attr attr = doc.createAttribute("goto");
	            int gotoValue = Integer.parseInt(goToList.get(i));
	            attr.setValue(Integer.toString(gotoValue));
	            
	            optionElement.setAttributeNode(attr);
	            optionElement.appendChild(doc.createTextNode(answerList.get(i)));
	            answerElement.appendChild(optionElement);
        	}
        }
    }
    
    public void addQuizMofn(String title,ArrayList<String> imageList, ArrayList<Boolean> imageInventoryList,ArrayList<String> soundList,
    		ArrayList<Boolean> soundInventoryList, int narration,
    		String paragraphList, String preNote, String postNote, String goTo, int points, String date,
            ArrayList<String> answerList, ArrayList<Boolean> answerListBool, int timestop, String wrong)
    {
        Element quizModule = doc.createElement("quiz");
        createQuiz(title,imageList,imageInventoryList,soundList,soundInventoryList,narration,paragraphList,preNote,postNote,quizModule);
        
        Element answerModule = doc.createElement("answermodule");
        quizModule.appendChild(answerModule);
        
        Element answerElement = doc.createElement("answer");
        createAnswerModule("MofN",goTo,points,date,wrong,timestop,answerElement);
        answerModule.appendChild(answerElement);
        
        for(int i=0;i<answerList.size();i++)
        {
        	if(!answerList.get(i).equals(""))
        	{
	            Element optionElement = doc.createElement("option");
	            Attr attr = doc.createAttribute("correct");
	            attr.setValue(Boolean.toString(answerListBool.get(i)));
	            optionElement.setAttributeNode(attr);
	            optionElement.appendChild(doc.createTextNode(answerList.get(i)));
	            answerElement.appendChild(optionElement);
        	}
        }
    }
    
    public void addQuizPermutation(String title,ArrayList<String> imageList, ArrayList<Boolean> imageInventoryList,ArrayList<String> soundList,
    		ArrayList<Boolean> soundInventoryList, int narration,
    		String paragraphList, String preNote, String postNote, String goTo, int points,
            String date, String wrong, int timestop, ArrayList<String> answerList)
    {
        Element quizModule = doc.createElement("quiz");
        createQuiz(title,imageList,imageInventoryList,soundList,soundInventoryList,narration,paragraphList,preNote,postNote,quizModule);
        
        Element answerModule = doc.createElement("answermodule");
        quizModule.appendChild(answerModule);
        
        Element answerElement = doc.createElement("answer");
        createAnswerModule("Permutation",goTo,points,date,wrong,timestop,answerElement);
        
        answerModule.appendChild(answerElement);
        
        boolean shuffle[] = new boolean[answerList.size()];
        for(int i=0;i<shuffle.length;i++)
        {
        	shuffle[i]=false;
        }
        
        for(int i=0;i<answerList.size();i++)
        {
        	Random generator = new Random(); 
            int index = 0;
            while(true)
            {
            	index = generator.nextInt(answerList.size());
            	boolean end=true;
            	for(int t=0;t<shuffle.length;t++)
            	{
            		if(!shuffle[t])
            		{
            			end=false;
            		}
            	}
            	if(end)break;
            	if(!shuffle[index])
            		{
            			shuffle[index]=true;
            			break;
            		}
            }

            if(!answerList.get(index).equals(""))
            {
            Element optionElement = doc.createElement("option");
            Attr attr = doc.createAttribute("index");
            
            

            
            attr.setValue(Integer.toString(index+1));
            optionElement.setAttributeNode(attr);
            optionElement.appendChild(doc.createTextNode(answerList.get(index)));
            answerElement.appendChild(optionElement);
            }
            else
            {
            	answerList.remove(index-1);
            	index--;
            }
        }
        
    }
    
    public void addTreasureBox(double x, double y, double width, double height, String note)
    {
        Element treasureBoxModule = doc.createElement("treasurebox");
        campaignModule.appendChild(treasureBoxModule);
        
        Element coordinatesModule = doc.createElement("coordinates");
        treasureBoxModule.appendChild(coordinatesModule);
        
        Element xElement = doc.createElement("x");
        xElement.appendChild(doc.createTextNode(Double.toString(x)));
        coordinatesModule.appendChild(xElement);
        
        Element yElement = doc.createElement("y");
        yElement.appendChild(doc.createTextNode(Double.toString(y)));
        coordinatesModule.appendChild(yElement);
        
        Element widthElement = doc.createElement("width");
        widthElement.appendChild(doc.createTextNode(Double.toString(width)));
        coordinatesModule.appendChild(widthElement);
        
        Element heightElement = doc.createElement("height");
        heightElement.appendChild(doc.createTextNode(Double.toString(height)));
        coordinatesModule.appendChild(heightElement);
        
        Element noteModule = doc.createElement("note");
        noteModule.appendChild(doc.createTextNode(note));
        treasureBoxModule.appendChild(noteModule);
    }
    private void createQuiz(String title,ArrayList<String> imageList, ArrayList<Boolean> imageInventoryList,ArrayList<String> soundList,
    		ArrayList<Boolean> soundInventoryList, int narration, 
    		String paragraphList, String preNote, String postNote, Element quizModule)
    {
        quizTriggerId++;
        
        Attr attr = doc.createAttribute("title");
        attr.setValue(title);
        quizModule.setAttributeNode(attr);
        
        attr = doc.createAttribute("id");
        attr.setValue(Integer.toString(quizTriggerId));
        quizModule.setAttributeNode(attr);
        
        campaignModule.appendChild(quizModule);
        
        Element imageModule = doc.createElement("imagemodule");
        
        quizModule.appendChild(imageModule);
        
        if(imageList.size()==0)
        {
        	imageModule.appendChild(doc.createTextNode(""));
        }
        for(int i=0;i<imageList.size();i++)
        {
            Element imageElement = doc.createElement("image");
            attr = doc.createAttribute("src");
            Path path = Paths.get(imageList.get(i));
            if(!path.getFileName().toString().equals("")){
            attr.setValue(path.getFileName().toString());
            imageElement.setAttributeNode(attr);
            
            if(imageInventoryList.size()>=i+1)
            {
	            if(imageInventoryList.get(i))
	            {
	            	attr = doc.createAttribute("inventory");
	            	attr.setValue("true");
	                imageElement.setAttributeNode(attr);
	            }
            }
            imageModule.appendChild(imageElement);}
        }
        
        Element questionmoduleElement = doc.createElement("questionmodule");
        quizModule.appendChild(questionmoduleElement);
        
        if(paragraphList.equals(""))
        {
        	questionmoduleElement.appendChild(doc.createTextNode(""));
        }
        
            Element paragraphElement = doc.createElement("paragraph");
            paragraphElement.appendChild(doc.createTextNode(paragraphList));
            questionmoduleElement.appendChild(paragraphElement);
        
        Element soundModule = doc.createElement("soundmodule");
        
        quizModule.appendChild(soundModule);
        
        if(soundList.size()==0)
        {
        	soundModule.appendChild(doc.createTextNode(""));
        }
        
        for(int i=0;i<soundList.size();i++)
        {
            Element soundElement = doc.createElement("sound");
            attr = doc.createAttribute("src");
            attr.setValue(soundList.get(i));
            Path path = Paths.get(soundList.get(i));
            if(!path.getFileName().toString().equals("")){
            attr.setValue(path.getFileName().toString());
            soundElement.setAttributeNode(attr);
            
            if(soundInventoryList.size()>=i+1)
            {
	            if(soundInventoryList.get(i))
	            {
		           	 attr = doc.createAttribute("inventory");
		             attr.setValue("true");
		             soundElement.setAttributeNode(attr);
	            }
            }
            if(narration==i)
            {
            	 attr = doc.createAttribute("narration");
                 attr.setValue("true");
                 soundElement.setAttributeNode(attr);
            }
            soundModule.appendChild(soundElement);}
        }
        
        Element preNoteModule = doc.createElement("prenote");
        preNoteModule.appendChild(doc.createTextNode(preNote));
        if(preNote==null)
        {
            	preNoteModule.appendChild(doc.createTextNode(""));
        }
        quizModule.appendChild(preNoteModule);
        

        
        Element postNoteModule = doc.createElement("postnote");
        postNoteModule.appendChild(doc.createTextNode(postNote));
        if(postNote==null)
        {
            	postNoteModule.appendChild(doc.createTextNode(""));
        }
        quizModule.appendChild(postNoteModule);
        
    }
    
    private void createAnswerModule(String type, String goTo, int points, String date, String wrong, int timestop, Element answerElement)
    {
        Attr attr = doc.createAttribute("type");
        attr.setValue(type);
        answerElement.setAttributeNode(attr);
        
        if((goTo!=null)&&(!goTo.equals("")))
        {
        answerElement.setAttributeNode(attr);
        attr = doc.createAttribute("goto");
        int gotoValue = Integer.parseInt(goTo);
        attr.setValue(Integer.toString(gotoValue));
        
        answerElement.setAttributeNode(attr);
        }
        
        attr = doc.createAttribute("points");
        attr.setValue(Integer.toString(points));
        answerElement.setAttributeNode(attr);
        attr = doc.createAttribute("datetime");
        attr.setValue(date);
        answerElement.setAttributeNode(attr);
        if(timestop!=0)
        {
            attr = doc.createAttribute("timestop");
            attr.setValue(Integer.toString(timestop));
            answerElement.setAttributeNode(attr);
        }
        
        if((wrong!=null)&&(!wrong.equals("")) )
        {
            attr = doc.createAttribute("wrong");
            int wrongValue = Integer.parseInt(wrong);
            attr.setValue(Integer.toString(wrongValue));
            answerElement.setAttributeNode(attr);
        }   
    }
    
    public void addSettings(String date, String background1, String background2, String background3, String logo, String button)
    {
        Element settingsModule = doc.createElement("settings");
        gameModule.appendChild(settingsModule);
        
        Element startdateElement = doc.createElement("startdate");
        startdateElement.appendChild(doc.createTextNode(date));
        settingsModule.appendChild(startdateElement);
        
        if(!background1.equals(""))
        {
        	Path path = Paths.get(background1);
	        Element background1Element = doc.createElement("backGround1");
	        background1Element.appendChild(doc.createTextNode(path.getFileName().toString()));
	        settingsModule.appendChild(background1Element);
        }
        if(!background2.equals(""))
        {
        	Path path = Paths.get(background2);
	        Element background2Element = doc.createElement("backGround2");
	        background2Element.appendChild(doc.createTextNode(path.getFileName().toString()));
	        settingsModule.appendChild(background2Element);
        }
        
        if(!background3.equals(""))
        {
        	Path path = Paths.get(background3);
	        Element background3Element = doc.createElement("backGround3");
	        background3Element.appendChild(doc.createTextNode(path.getFileName().toString()));
	        settingsModule.appendChild(background3Element);
	    }
        if(!logo.equals(""))
        {
        	Path path = Paths.get(logo);
	        Element logoElement = doc.createElement("logo1");
	        logoElement.appendChild(doc.createTextNode(path.getFileName().toString()));
	        settingsModule.appendChild(logoElement);
        }
        if(!button.equals(""))
        {
        	Path path = Paths.get(button);
	        Element buttonElement = doc.createElement("button1");
	        buttonElement.appendChild(doc.createTextNode(path.getFileName().toString()));
	        settingsModule.appendChild(buttonElement);
        }
    }
    
    
    public void resetId()
    {
    	quizTriggerId=0;
    }    
}
