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
 		closed = JSONUtil.getBoolean(value, "is_closed");
 	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhoto() {
		return photo;
	}

	public boolean isClosed() {
		return closed;
	}	
}
