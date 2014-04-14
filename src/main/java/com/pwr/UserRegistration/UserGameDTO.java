package com.pwr.UserRegistration;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@XmlRootElement
@JsonAutoDetect
public class UserGameDTO implements Serializable{
	
	private String user;
	private int points;
	private Date endTime;
	private List<DoneQuestDTO> setDoneQuestDTO;
	
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String[] toArray() {
		return new String [] {String.valueOf(points), endTime.toString()} ;
	}
	public List<DoneQuestDTO> getSetDoneQuestDTO() {
		return setDoneQuestDTO;
	}
	public void setSetDoneQuestDTO(List<DoneQuestDTO> setDoneQuestDTO) {
		this.setDoneQuestDTO = setDoneQuestDTO;
	}
}
