package ru.vkontakte.gwt.client.model;

import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONValue;

public class Group {
	private Long id;
	private String name;
	private String photo;
 	private boolean closed;
	
 	public Group(JSONValue value) throws NoSuchJSONValueException {
 		id = JSONUtil.getLong(value, "gid");
 		name = JSONUtil.getString(value, "name");
 		photo = JSONUtil.getString(value, "photo");
 		closed = JSONUtil.getInteger(value, "is_closed") > 0;
 	}
 	
 	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public boolean isClosed() {
		return closed;
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
	} 	
}
