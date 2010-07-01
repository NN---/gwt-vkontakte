package ru.vkontakte.gwt.client.model;

import java.util.Date;

import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONValue;

public class Activity {
	private Long id;
	private Date time;
	private String text;
	private Long userId;

	public Activity(JSONValue value) throws NoSuchJSONValueException {
		this(value, JSONUtil.tryGetLong(value, "uid"));
	}

	public Activity(JSONValue value, Long uid) throws NoSuchJSONValueException {
		id = JSONUtil.tryGetLong(value, "id");
		userId = uid;
		time = new Date(getTime(value));
		text = getText(value);
	}

	private String getText(JSONValue value) throws NoSuchJSONValueException {
		try {
			return JSONUtil.getString(value, "activity");
		} catch (NoSuchJSONValueException ex) {
			return JSONUtil.getString(value, "text");
		}
	}

	private Long getTime(JSONValue value) throws NoSuchJSONValueException {
		try {
			return JSONUtil.getLong(value, "time");
		} catch (NoSuchJSONValueException ex) {
			try {
				return JSONUtil.getLong(value, "created");
			} catch (NoSuchJSONValueException ex1) {
				return JSONUtil.getLong(value, "timestamp");
			}
		}
	}

	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public Date getTime() {
		return time;
	}

	public String getText() {
		return text;
	}
}
