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
	public String getUserId() {
		return userId;
	}
	public Date getTime() {
		return time;
	}
	public String getText() {
		return text;
	}
}
