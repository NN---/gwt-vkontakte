package ru.vkontakte.gwt.client.model;

import java.util.Date;

public class Status {
	private Long id;
	private Date time;
	private String text;
	private String userId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}		
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
