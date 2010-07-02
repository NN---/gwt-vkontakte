package ru.vkontakte.gwt.client.model;

import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONValue;

public class City {
	private Long id;
	private String name;
	
	public City(JSONValue value) throws NoSuchJSONValueException {
		id = JSONUtil.getLong(value, "cid");
		name = JSONUtil.getString(value, "name");
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
