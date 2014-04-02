package Quest;

import java.util.ArrayList;


public abstract class QuestPoint  implements DescribeQuest{
	private ArrayList<String> PicturePaths;
	public double getQuestTimeout() {
		return QuestTimeout;
	}

	public void setQuestTimeout(double questTimeout) {
		QuestTimeout = questTimeout;
	}

	private ArrayList<String> SoundPaths;
	private double QuestTimeout;
	
	public QuestPoint()
	{
		PicturePaths = new ArrayList<String>();
		PicturePaths.add("");
		SoundPaths = new ArrayList<String>();
		SoundPaths.add("");
		QuestDescription = "";
		QuestAnswer = "";
		QuestTimeout = 0;
	}
	
	public ArrayList<String> getPicturePaths() {
		return PicturePaths;
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
	
}
