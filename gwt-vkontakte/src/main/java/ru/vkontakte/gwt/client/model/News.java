package ru.vkontakte.gwt.client.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONValue;

public class News {
	private Long count;
	private List<Activity> activities;
	
	public News(JSONValue value) throws NoSuchJSONValueException {
		JSONArray array = JSONUtil.getArray(value);
		if (array.size() == 0)
			throw new NoSuchJSONValueException();

		activities = new ArrayList<Activity>(array.size()-1);
		count = JSONUtil.getLong(array.get(0));
		for (int i=1; i<array.size(); i++) {
			activities.add(new Activity(array.get(i)));
		}
	}
	public Long getCount() {
		return count;
	}
	public List<Activity> getActivities() {
		return Collections.unmodifiableList(activities);
	}
	
	
}
