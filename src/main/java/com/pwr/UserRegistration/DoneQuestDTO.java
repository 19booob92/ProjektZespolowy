package com.pwr.UserRegistration;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DoneQuestDTO {

	private QuestDTO questDTO;
	private Integer extraPoints;
	private Date doneTime;

	public Integer getExtraPoints() {
		return extraPoints;
	}

	public void setExtraPoints(Integer extraPoints) {
		this.extraPoints = extraPoints;
	}

	public Date getDoneTime() {
		return doneTime;
	}

	public void setDoneTime(Date doneTime) {
		this.doneTime = doneTime;
	}

	public QuestDTO getQuestDTO() {
		return questDTO;
	}

	public void setQuestDTO(QuestDTO questDTO) {
		this.questDTO = questDTO;
	}

}