package com.pwr.Quest;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.FileUtils;




import com.pwr.Graph.QuizDataObject;
import com.pwr.Package.PackageCoder;
import com.pwr.Package.XmlBuilder;
import com.pwr.Package.XmlLoader;
import com.pwr.Package.ZipPacker;
import com.pwr.Package.ZipUnpacker;
import com.pwr.QuestView.NewQuizView;

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
	private boolean deleted = false;

	private String gameTitle = "";
	private String date = "";
	private String background1 = "";
	private String background2 = "";
	private String background3 = "";
	private String logo = "";
	private String button = "";
	private String pathToFile;
	
	public Campaign() {
		quests = new ArrayList();
		boxes = new ArrayList();
		introPics = new ArrayList();
		outroPics = new ArrayList();
		introText = new ArrayList();
		outroText = new ArrayList();
	}
	
	public void clearCampaign()
	{
		quests.clear();
		boxes = new ArrayList();
		introPics = new ArrayList();
		outroPics = new ArrayList();
		introText = new ArrayList();
		outroText = new ArrayList();
		created = false;
		edited = false;
		saved = true;
		deleted = false;

		gameTitle = "";
		date = "";
		background1 = "";
		background2 = "";
		background3 = "";
		logo = "";
		button = "";
		QuestPoint.resetId();
	}

	public List<QuizDataObject> convertQuiz() {
		List<QuizDataObject> quizDTOs = new ArrayList<>();
		for (QuestPoint q : quests) {
			quizDTOs.add(q.convert());
		}
		return quizDTOs;
	}

	public void deleteTrue() {
		deleted = true;
		setChanged();
		notifyObservers();
	}

	public void deleteFalse() {
		deleted = false;
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

	public boolean getDeleted() {
		return deleted;
	}

	public boolean getCreated() {
		return created;
	}

	public boolean getEdited() {
		return edited;
	}

	public void removeQuiz(QuestPoint q) {
		deleted = true;
		quests.remove(q);
	}

	public void addQuiz(QuestPoint p) {
		QuestPoint.incrementId();
		saved = false;
		quests.add(p);
	}

	public void addTreasureBox(TreasureBox b) {
		saved = false;
		boxes.add(b);
	}

	public void setQuiz() {

	}

	public void setQuiz(QuestPoint p, int index) {
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
	public QuestPoint getQuizById(int argId)
	{
		QuestPoint qP=null;
		
		for(int n=0;n<quests.size();n++)
			if(quests.get(n).getId()==argId)
				return quests.get(n);
		return null;
	}
	public ArrayList<String> getQuizesNames() {
		ArrayList names = new ArrayList();
		for (QuestPoint p : quests) {
			names.add(p.getQuestName());
		}
		return names;
	}

	public ArrayList<TreasureBox> getTreasureBoxes() {
		return boxes;
	}

	public boolean createXml() {
		pathToFile = selectFile();
		if(pathToFile!=null)
		{
		saved = true;
		XmlBuilder xml = new XmlBuilder(gameTitle);
		xml.resetId();
		
		zip = new ZipPacker(pathToFile);
		xml.createIntro(introPics, introText);
		xml.createOutro(outroPics, outroText);
		for (int i = 0; i < quests.size(); i++) {
			FileTransfer(quests.get(i));
			QuestPoint tempQuest = quests.get(i);
			if (tempQuest.getQuestType() == QuestType.TEXTQUEST) {
				TextQuest quest = (TextQuest) tempQuest;
				xml.addQuizText(quest.getQuestName(),quest.getId(), quest.getPicturePaths(),
						quest.getPictureInventoryList(), quest.getSoundPaths(),
						quest.getSoundInventoryList(), quest.getSoundNarration(),
						quest.getParagraph(), quest.getPreNote(),
						quest.getPostNote(), quest.getGoTo(),
						quest.getPoints(), quest.getDate(),
						quest.getQuestAnswer(), quest.getQuestTimeout(),
						quest.getWrong());
			} else if (tempQuest.getQuestType() == QuestType.FIELDQUEST) {
				FieldQuest quest = (FieldQuest) tempQuest;
				xml.addQuizGPS(quest.getQuestName(),quest.getId(), quest.getPicturePaths(),
						quest.getPictureInventoryList(), quest.getSoundPaths(),
						quest.getSoundInventoryList(), quest.getSoundNarration(),
						quest.getParagraph(), quest.getPreNote(),
						quest.getPostNote(), quest.getGoTo(),
						quest.getPoints(), quest.getDate(),
						quest.getXCoordinate(), quest.getYCoordinate(),
						//quest.getWidth(), quest.getHeight(),
						quest.getX2Coordinate(), quest.getY2Coordinate(),
						quest.getQuestTimeout(), quest.getWrong());
			} else if (tempQuest.getQuestType() == QuestType.DECISIONQUEST) {
				DecisionQuest quest = (DecisionQuest) tempQuest;
				xml.addQuizDecision(quest.getQuestName(),quest.getId(),
						quest.getPicturePaths(),
						quest.getPictureInventoryList(), quest.getSoundPaths(),
						quest.getSoundInventoryList(), quest.getSoundNarration(),
						quest.getParagraph(), quest.getPreNote(),
						quest.getPostNote(), quest.getPoints(),
						quest.getDate(), quest.getGoToList(),
						quest.getQuestAnswer(), quest.getQuestTimeout(),
						quest.getWrong());
			} else if (tempQuest.getQuestType() == QuestType.CHOICEQUEST) {
				ChoiceQuest quest = (ChoiceQuest) tempQuest;
				xml.addQuizMofn(quest.getQuestName(),quest.getId(), quest.getPicturePaths(),
						quest.getPictureInventoryList(), quest.getSoundPaths(),
						quest.getSoundInventoryList(), quest.getSoundNarration(),
						quest.getParagraph(), quest.getPreNote(),
						quest.getPostNote(), quest.getGoTo(),
						quest.getPoints(), quest.getDate(),
						quest.getQuestAnswer(), quest.getQuestAnswerCorrect(),
						quest.getQuestTimeout(), quest.getWrong());
			} else if (tempQuest.getQuestType() == QuestType.ORDERQUEST) {
				OrderQuest quest = (OrderQuest) tempQuest;
				xml.addQuizPermutation(quest.getQuestName(),quest.getId(),
						quest.getPicturePaths(),
						quest.getPictureInventoryList(), quest.getSoundPaths(),
						quest.getSoundInventoryList(), quest.getSoundNarration(),
						quest.getParagraph(), quest.getPreNote(),
						quest.getPostNote(), quest.getGoTo(),
						quest.getPoints(), quest.getDate(), quest.getWrong(),
						quest.getQuestTimeout(), quest.getQuestAnswer());
			}
		}
		xml.addSettings(this.date, background1, background2, background3, logo,
				button);
		for (int i = 0; i < boxes.size(); i++) {
			TreasureBox box = boxes.get(i);
			xml.addTreasureBox(box.getxCoordinate(), box.getyCoordinate(),
					//box.getWidth(), box.getHeight(), 
					box.getX2Coordinate(),box.getY2Coordinate(),box.getNote());
		}

		try {
			xml.saveXml();
		} catch (TransformerException ex) {
			Logger.getLogger(Campaign.class.getName()).log(Level.SEVERE, null,
					ex);
		}

		File srcFile = new File("Config.xml");
		File destFolder = new File("temp");

		if (destFolder.exists()) {
			try {
				FileUtils.copyFileToDirectory(srcFile, destFolder);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			for (int i = 0; i < introPics.size(); i++) {
				srcFile = new File(introPics.get(i));
				Path path = Paths.get(introPics.get(i));
				try {
					if (!path.getParent().toString().equals("temp"))
						FileUtils.copyFileToDirectory(srcFile, destFolder);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			for (int i = 0; i < outroPics.size(); i++) {
				srcFile = new File(outroPics.get(i));
				Path path = Paths.get(outroPics.get(i));
				try {
					if (!path.getParent().toString().equals("temp"))
						FileUtils.copyFileToDirectory(srcFile, destFolder);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// srcFile.delete();
			 //PackageCoder.codeAllFilesInDirectoryExceptSound("temp");
			PackageCoder.codeFile("temp"+File.separatorChar+"Config.xml");
			File[] files = destFolder.listFiles();
			for (int i = 0; i < files.length; i++) {
				try {
					zip.addFile(files[i].getPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
				// files[i].delete();
			}
			// destFolder.delete();
		}

		zip.closeZip();
		return true;
		}
		return false;
	}

	private String selectFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setSelectedFile(new File(gameTitle + ".zip"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.zip", "zip");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showOpenDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().getAbsolutePath();
		}
		return null;
		
	}

	private void FileTransfer(QuestPoint newQuest) {

		File destFolder = new File("temp");

		if (!destFolder.exists()) {
			destFolder.mkdir();
		}

		for (int i = 0; i < newQuest.getPicturePaths().size(); i++) {
			Path path = Paths.get(newQuest.getPicturePaths().get(i));
			File sourceFile = new File(path.toString());

			try {
				if (!path.getParent().toString().equals("temp"))
					FileUtils.copyFileToDirectory(sourceFile, destFolder);
				// FileUtils.copyDirectory(sourceFile, destFolder);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < newQuest.getSoundPaths().size(); i++) {
			Path path = Paths.get(newQuest.getSoundPaths().get(i));
			File sourceFile = new File(path.toString());
			try {
				if (!path.getParent().toString().equals("temp"))
					FileUtils.copyFileToDirectory(sourceFile, destFolder);
				// FileUtils.copyDirectory(sourceFile, destFolder);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			if (!background1.equals("")) {
				Path path = Paths.get(background1);
				File sourceFile = new File(path.toString());
				if (!path.getParent().toString().equals("temp"))
					FileUtils.copyFileToDirectory(sourceFile, destFolder);
			}
			if (!background2.equals("")) {
				Path path = Paths.get(background2);
				File sourceFile = new File(path.toString());
				if (!path.getParent().toString().equals("temp"))
					FileUtils.copyFileToDirectory(sourceFile, destFolder);
			}
			if (!background3.equals("")) {
				Path path = Paths.get(background3);
				File sourceFile = new File(path.toString());
				if (!path.getParent().toString().equals("temp"))
					FileUtils.copyFileToDirectory(sourceFile, destFolder);
			}
			if (!logo.equals("")) {
				Path path = Paths.get(logo);
				File sourceFile = new File(path.toString());
				if (!path.getParent().toString().equals("temp"))
					FileUtils.copyFileToDirectory(sourceFile, destFolder);
			}
			if (!button.equals("")) {
				Path path = Paths.get(button);
				File sourceFile = new File(path.toString());
				if (!path.getParent().toString().equals("temp"))
					FileUtils.copyFileToDirectory(sourceFile, destFolder);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean loadXml(String file) {
		if (!file.equals("")) {
			zipUnpacker = new ZipUnpacker(file);
			zipUnpacker.unZip();
			 //PackageCoder.decodeAllFilesInDirectory("temp");
			PackageCoder.decodeFile("temp"+File.separatorChar+"Config.xml");

			XmlLoader xml = new XmlLoader("temp" + File.separator
					+ "Config.xml");
			xml.LoadXml(this);
			saved = true;
			return true;
		}
		return false;
	}

	public void closeCampaign() {
		if (zipUnpacker != null) {
			zipUnpacker.close();
		}
	}

	public boolean isSaved() {
		return saved;
	}

	public void setIntroPics(ArrayList<String> list) {
		introPics = list;
	}

	public void setOutroPics(ArrayList<String> list) {
		outroPics = list;
	}

	public void setIntroText(ArrayList<String> list) {
		introText = list;
	}

	public void setOutroText(ArrayList<String> list) {
		outroText = list;
	}

	public ArrayList<String> getOutroText() {
		return outroText;
	}

	public ArrayList<String> getIntroText() {
		return introText;
	}

	public void setGameTitle(String title) {
		gameTitle = title;
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

	public ArrayList<String> getIntroPics() {
		return introPics;
	}

	public ArrayList<String> getOutroPics() {
		return outroPics;
	}

	public void putTreasureBox(TreasureBox box, int index) {
		this.boxes.set(index, box);
	}

	public TreasureBox getTreasureBox(int index) {
		return boxes.get(index);
	}
	
	public void removeTreasureBox(int index)
	{
		boxes.remove(index);
	}

	public void setSettings(String background1, String background2,
			String background3, String logo, String button) {
		this.background1 = background1;
		this.background2 = background2;
		this.background3 = background3;
		this.logo = logo;
		this.button = button;
	}

	public void setSettings(String background1, String background2,
			String background3, String logo, String button, String date,
			String title) {
		setSettings(background1, background2, background3, logo, button);
		this.gameTitle = title;
		this.date = date;
	}

	public ArrayList<String> getSettings() {
		ArrayList<String> list = new ArrayList();
		list.add(background1);
		list.add(background2);
		list.add(background3);
		list.add(logo);
		list.add(button);
		return list;
	}

	public void setQuizById(int quizIndex, QuestPoint qp) {
		for(int n=0;n<quests.size();n++)
			if(quests.get(n).getId()==quizIndex)
			{
				quests.set(n,qp);
				return;
			}
	}

}
