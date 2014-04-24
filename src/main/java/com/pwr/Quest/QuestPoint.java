package com.pwr.Quest;

import java.util.ArrayList;

import com.pwr.Quest.QuestType;

;

public abstract class QuestPoint implements DescribeQuest {

	private ArrayList<String> PicturePaths;
	private ArrayList<String> SoundPaths;
        private ArrayList<String> questDescription;
	private String QuestName;
	private int QuestTimeout;
        private int points;
        private String preNote;
        private String postNote;
        private String date;
        private String wrong;
	private QuestType type = null;

	public QuestPoint(QuestType type) {
		this.type = type;
		QuestName = "";
		PicturePaths = new ArrayList<String>();
		PicturePaths.add("");
		SoundPaths = new ArrayList<String>();
		SoundPaths.add("");
                questDescription = new ArrayList<String>();
		QuestTimeout = 0;
                date="10-03-2014 14:33";
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

	public String ToString() {
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

    public ArrayList<String> getQuestDescription() {
        return questDescription;
    }

    public void setQuestDescription(ArrayList<String> paragraph) {
        this.questDescription = paragraph;
    }
        
    public void setGoTo(String goTo) {
    	
    }
}
