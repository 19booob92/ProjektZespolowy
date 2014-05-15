package com.pwr.Quest;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.TransformerException;

import org.apache.commons.io.FileUtils;

import com.pwr.Editor.PackageCoder;
import com.pwr.Editor.XmlBuilder;
import com.pwr.Editor.XmlLoader;
import com.pwr.Editor.ZipPacker;
import com.pwr.Editor.ZipUnpacker;
import com.pwr.Graph.QuizDataObject;
import com.pwr.NewInterface.NewQuizView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Campaign extends Observable {

	private ArrayList<QuestPoint> quests;
	private ArrayList<TreasureBox> boxes;
	private ArrayList<String> introText;
	private ArrayList<String> outroText;
	private ArrayList<String> introPics;
	private ArrayList<String> outroPics;
	private ZipPacker zip;
	private ZipUnpacker zipUnpacker;
	private boolean created = false;
	private boolean edited = false;
	private boolean saved = true;
	
	private String gameTitle="";
	private String date="";

	public Campaign() {
		quests = new ArrayList();
		boxes = new ArrayList();
		introPics = new ArrayList();
		outroPics = new ArrayList();
		introText = new ArrayList();
		outroText = new ArrayList();
	}

	public List<QuizDataObject> convertQuiz() {
		List<QuizDataObject> quizDTOs = new ArrayList<>();
		for (QuestPoint q : quests) {
			quizDTOs.add(q.convert());
		}
		return quizDTOs;
	}

	public void createdTrue() {
		created = true;
		setChanged();
		notifyObservers();
	}

	public void createdFalse() {
		created = false;
	}

	public void editedTrue() {
		saved = false;
		edited = true;
		setChanged();
		notifyObservers();
	}

	public void editedFalse() {
		edited = false;
	}

	public boolean getCreated() {
		return created;
	}

	public boolean getEdited() {
		return edited;
	}

	public void addQuiz(QuestPoint p) {
		saved=false;
		quests.add(p);
	}

	public void addTreasureBox(TreasureBox b) {
		saved=false;
		boxes.add(b);
	}

	public void setQuiz() {

	}
	
	public void setQuiz(QuestPoint p,int index)
	{
		quests.set(index, p);
	}

	public QuestPoint getLastQuiz() {
		return quests.get(quests.size() - 1);
	}

	public TreasureBox getTreasureBox() {
		return null;
	}

	public ArrayList<QuestPoint> getQuizes() {
		return quests;
	}

	public ArrayList<String> getQuizesNames() {
		ArrayList names = new ArrayList();
		for (QuestPoint p : quests) {
			names.add(p.toString());
		}
		return names;
	}

	public ArrayList<TreasureBox> getTreasureBoxes() {
		return boxes;
	}

	public void createXml() {
		saved=true;
		XmlBuilder xml = new XmlBuilder(gameTitle);
		xml.resetId();
		zip = new ZipPacker("paczka.zip");
		xml.createIntro(introPics, introPics);
		xml.createOutro(outroPics, outroPics);
		for (int i = 0; i < quests.size(); i++) {
			FileTransfer(quests.get(i));
			QuestPoint tempQuest = quests.get(i);
			if (tempQuest.getQuestType() == QuestType.TEXTQUEST) {
				TextQuest quest = (TextQuest) tempQuest;
				xml.addQuizText(quest.getQuestName(), quest.getPicturePaths(),
						quest.getSoundPaths(), quest.getQuestDescription(),
						quest.getPreNote(), quest.getPostNote(),
						quest.getGoTo(), quest.getPoints(), quest.getDate(),
						quest.getQuestAnswer(), quest.getQuestTimeout(),
						quest.getWrong());
			} else if (tempQuest.getQuestType() == QuestType.FIELDQUEST) {
				FieldQuest quest = (FieldQuest) tempQuest;
				xml.addQuizGPS(quest.getQuestName(), quest.getPicturePaths(),
						quest.getSoundPaths(), quest.getQuestDescription(),
						quest.getPreNote(), quest.getPostNote(),
						quest.getGoTo(), quest.getPoints(), quest.getDate(),
						quest.getXCoordinate(), quest.getYCoordinate(),
						quest.getWidth(), quest.getHeight(),
						quest.getQuestTimeout(), quest.getWrong());
			} else if (tempQuest.getQuestType() == QuestType.DECISIONQUEST) {
				DecisionQuest quest = (DecisionQuest) tempQuest;
				xml.addQuizDecision(quest.getQuestName(),
						quest.getPicturePaths(), quest.getSoundPaths(),
						quest.getQuestDescription(), quest.getPreNote(),
						quest.getPostNote(), quest.getPoints(),
						quest.getDate(), quest.getGoToList(),
						quest.getQuestAnswer(), quest.getQuestTimeout(),
						quest.getWrong());
			} else if (tempQuest.getQuestType() == QuestType.CHOICEQUEST) {
				ChoiceQuest quest = (ChoiceQuest) tempQuest;
				xml.addQuizMofn(quest.getQuestName(), quest.getPicturePaths(),
						quest.getSoundPaths(), quest.getQuestDescription(),
						quest.getPreNote(), quest.getPostNote(),
						quest.getGoTo(), quest.getPoints(), quest.getDate(),
						quest.getQuestAnswer(), quest.getQuestAnswerCorrect(),
						quest.getQuestTimeout(), quest.getWrong());
			} else if (tempQuest.getQuestType() == QuestType.ORDERQUEST) {
				OrderQuest quest = (OrderQuest) tempQuest;
				xml.addQuizPermutation(quest.getQuestName(),
						quest.getPicturePaths(), quest.getSoundPaths(),
						quest.getQuestDescription(), quest.getPreNote(),
						quest.getPostNote(), quest.getGoTo(),
						quest.getPoints(), quest.getDate(), quest.getWrong(),
						quest.getQuestTimeout(), quest.getQuestAnswer());
			}
		}

		for (int i = 0; i < boxes.size(); i++) {
			TreasureBox box = boxes.get(i);
			xml.addTreasureBox(box.getxCoordinate(), box.getyCoordinate(),
					box.getWidth(), box.getHeight(), box.getNote());
		}

		try {
			xml.saveXml();
		} catch (TransformerException ex) {
			Logger.getLogger(Campaign.class.getName()).log(Level.SEVERE, null,
					ex);
		}
/*
		try {
			zip.addFile("temp"+File.pathSeparator+"Config.xml");
		} catch (IOException ex) {
			Logger.getLogger(NewQuizView.class.getName()).log(Level.SEVERE,
					null, ex);
		}*/
		File srcFile = new File("Config.xml");
		File destFolder = new File("temp");
		
		
		if(destFolder.exists())
		{
			try {
				FileUtils.copyFileToDirectory(srcFile, destFolder);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			srcFile.delete();
			PackageCoder.codeAllFilesInDirectoryExceptSound("temp");
			File [] files = destFolder.listFiles();
			for(int i=0;i<files.length;i++)
			{
				try {
					zip.addFile(files[i].getPath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				files[i].delete();
			}
			destFolder.delete();
		}
		
		zip.closeZip();
	}

	private void FileTransfer(QuestPoint newQuest) {
		
		File destFolder = new File("temp");
		
		if(!destFolder.exists())
		{
			destFolder.mkdir();
		}
		
		for (int i = 0; i < newQuest.getPicturePaths().size(); i++) {
			Path path = Paths.get(newQuest.getPicturePaths().get(i));
			File sourceFile = new File(path.toString());
			
			try {
				FileUtils.copyFileToDirectory(sourceFile, destFolder);
				//FileUtils.copyDirectory(sourceFile, destFolder);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			try {
				zip.addFile(newQuest.getPicturePaths().get(i));
			} catch (IOException ex) {
				Logger.getLogger(NewQuizView.class.getName()).log(Level.SEVERE,
						null, ex);
			}*/
		}

		for (int i = 0; i < newQuest.getSoundPaths().size(); i++) {
			Path path = Paths.get(newQuest.getSoundPaths().get(i));
			File sourceFile = new File(path.toString());
			try {
				FileUtils.copyFileToDirectory(sourceFile, destFolder);
				//FileUtils.copyDirectory(sourceFile, destFolder);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*	try {
				zip.addFile(newQuest.getSoundPaths().get(i));
			} catch (IOException ex) {
				Logger.getLogger(NewQuizView.class.getName()).log(Level.SEVERE,
						null, ex);
			}*/
		

	}
	
	public void loadXml(String file)
	{
		zipUnpacker = new ZipUnpacker(file);
		zipUnpacker.unZip();
		
		PackageCoder.decodeAllFilesInDirectory("temp");
		
		XmlLoader xml = new XmlLoader("temp"+File.separator+"Config.xml");
		xml.LoadXml(this);
		saved=true;
	}
	
	public void closeCampaign()
	{
		if(zipUnpacker!=null)
		{
			zipUnpacker.close();
		}
	}
	
	public boolean isSaved()
	{
		return saved;
	}
	public void setIntroPics(ArrayList<String> list)
	{
		introPics=list;
	}
	
	public void setOutroPics(ArrayList<String> list)
	{
		outroPics=list;
	}
	public void setIntroText(ArrayList<String> list)
	{
		introText=list;
	}
	public void setOutroText(ArrayList<String> list)
	{
		outroText=list;
	}
	public void setGameTitle(String title)
	{
		gameTitle=title;
	}
	public String getGameTitle() {
		return gameTitle;
	}
	public void setGameDate(String date) {
		this.date = date;
	}

	public String getGameDate() {
		return date;
	}
}
