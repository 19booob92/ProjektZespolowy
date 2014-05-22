package com.pwr.Quest;

import java.util.ArrayList;

import com.pwr.Graph.QuizDataObject;

public abstract class QuestPoint implements DescribeQuest {

	private ArrayList<String> PicturePaths;
	private ArrayList<Boolean> pictureInventoryList;
	
	private ArrayList<String> SoundPaths;
	private ArrayList<Boolean> soundInventoryList;
	private int soundNarration;
	private String paragraph;
	private String QuestName;
	private int QuestTimeout;
	private int points;
	private static int id = 1;
	private String preNote;
	private int questId;
	private String postNote;
	private String date;
	private String wrong;
	private QuestType type = null;

	public QuestPoint(QuestType type) {
		this.type = type;
		QuestName = "";
		questId = id;
		PicturePaths = new ArrayList<String>();
		PicturePaths.add("");
		SoundPaths = new ArrayList<String>();
		SoundPaths.add("");
		paragraph="";
		soundInventoryList = new ArrayList();
		pictureInventoryList = new ArrayList();
		soundNarration=-1;
		QuestTimeout = 0;
		date = "10-03-1410 14:33";
		//id++;
	}
	
	public void setParagraph(String paragraph)
	{
		this.paragraph=paragraph;
	}
	
	public String getParagraph()
	{
		return paragraph;
	}
	
	public ArrayList<Boolean> getPictureInventoryList()
	{
		return pictureInventoryList;
	}
	
	public void setPictureInventoryList(ArrayList<Boolean> pictureInventoryList)
	{
		this.pictureInventoryList=pictureInventoryList;
	}
	
	public ArrayList<Boolean> getSoundInventoryList()
	{
		return soundInventoryList;
	}
	
	public void setSoundInventoryList( ArrayList<Boolean> soundInventoryList)
	{
		this.soundInventoryList=soundInventoryList;
	}
	public int soundNarration()
	{
		return soundNarration;
	}
	public void setSoundNarration(int soundNarration)
	{
		this.soundNarration=soundNarration;
	}
	
	public int getId() {
		return questId;
	}

	public void setId(String id) {
		questId = Integer.parseInt(id);
	}
	
	public static void incrementId() {
		id++;
	}

	public QuestType getQuestType() {
		return type;
	}

	public String getWrong() {
		return wrong;
	}

	public void setWrong(String wrong) {
		this.wrong = wrong;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPreNote() {
		return preNote;
	}

	public void setPreNote(String preNote) {
		this.preNote = preNote;
	}

	public String getPostNote() {
		return postNote;
	}

	public void setPostNote(String postNote) {
		this.postNote = postNote;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void setQuestType(QuestType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return QuestName;
	}

	public ArrayList<String> getPicturePaths() {
		return PicturePaths;
	}

	public int getQuestTimeout() {
		return QuestTimeout;
	}

	public void setQuestTimeout(int questTimeout) {
		QuestTimeout = questTimeout;
	}

	public void setPicturePaths(ArrayList<String> picturePaths) {
		PicturePaths = picturePaths;
	}

	public ArrayList<String> getSoundPaths() {
		return SoundPaths;
	}

	public void setSoundPaths(ArrayList<String> soundPaths) {
		SoundPaths = soundPaths;
	}

	public void setQuestName(String qName) {
		QuestName = qName;
	}

	public String getQuestName() {
		return QuestName;
	}

	public abstract void setGoTo(String goTo); 
		//this.goTo = goTo;
	
	public abstract String getGoTo() ;
	public abstract ArrayList<String> getGoToList();
	public QuizDataObject convert() {
		//MessageDigest md;
		//String id = "";
		QuizDataObject quizDTO;
		//try {
			//md = MessageDigest.getInstance("MD5");
			//byte[] questNameBytes = QuestName.getBytes();
			//byte[] digested = md.digest(questNameBytes);
			//id = digested.toString();
			
		//} catch (NoSuchAlgorithmException e) {
			//e.printStackTrace();
		//}
		if (this.type == QuestType.DECISIONQUEST) {
			String [] arr = getGoToList().toArray(new String[getGoToList().size()]);
			quizDTO = new QuizDataObject(QuestName, arr,wrong,Integer.toString(questId));			
			return quizDTO;
		} else {
			quizDTO = new QuizDataObject(QuestName, new String[]{getGoTo()},wrong,Integer.toString(questId));
			return quizDTO;
		}
	}
}
