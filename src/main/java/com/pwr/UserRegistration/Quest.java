package com.pwr.UserRegistration;


import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import Utils.JsonDateSerializer;

@XmlRootElement
public class Quest {

		private int defaultPoints;
		private Date defaultTime;
		private String name;

		
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
		@JsonSerialize(using = JsonDateSerializer.class)
		public Date getDefaultTime() {
			return defaultTime;
		}
		public void setDefaultTime(Date defaultTime) {
			this.defaultTime = defaultTime;
		}
		
	}
