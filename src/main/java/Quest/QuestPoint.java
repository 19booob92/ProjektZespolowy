package Quest;

import java.util.ArrayList;
import Quest.QuestType;;

public abstract class QuestPoint implements DescribeQuest{
	
	private ArrayList<String> PicturePaths;
	private ArrayList<String> SoundPaths;
	private String QuestName;
	private double QuestTimeout;
	
	private QuestType type = null;
	
	public QuestPoint(QuestType type)
	{
		this.type = type;
		QuestName = "";
		PicturePaths = new ArrayList<String>();
		PicturePaths.add("");
		SoundPaths = new ArrayList<String>();
		SoundPaths.add("");
		QuestTimeout = 10.0;
	}
		
	public QuestType getQuestType(){
		return type;
	}
	
	public void setQuestType(QuestType type){
		this.type = type;
	}
	
	public ArrayList<String> getPicturePaths() {
		return PicturePaths;
	}

	public double getQuestTimeout() {
		return QuestTimeout;
	}

	public void setQuestTimeout(double questTimeout) {
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
/*
	public String getQuestDescription() {
		return QuestDescription;
	}

	public void setQuestDescription(String questDescription) {
		QuestDescription = questDescription;
	}

	public String getQuestAnswer() {
		return QuestAnswer;
	}

	public void setQuestAnswer(String questAnswer) {
		QuestAnswer = questAnswer;
	}

	private String QuestDescription;
	private String QuestAnswer;
	*/
}
