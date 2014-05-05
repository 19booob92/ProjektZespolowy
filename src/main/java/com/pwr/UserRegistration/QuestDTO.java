package com.pwr.UserRegistration;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QuestDTO {

	private int id;
	private int defaultPoints;
	private Date defaultTime;
	private String name = "NAME"; //TODO

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDefaultPoints() {
		return defaultPoints;
	}
	public void setDefaultPoints(int defaultPoints) {
		this.defaultPoints = defaultPoints;
	}
	public Date getDefaultTime() {
		return defaultTime;
	}
	public void setDefaultTime(Date defaultTime) {
		this.defaultTime = defaultTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String [] toArray() {
		return new String [] {String.valueOf(id), String.valueOf(defaultPoints), defaultTime.toString(), name};
	}
	
}
