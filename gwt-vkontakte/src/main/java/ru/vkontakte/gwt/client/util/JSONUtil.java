package ru.vkontakte.gwt.client.util;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;


public final class JSONUtil {
	private JSONUtil() {		
	}
	
	public static JSONValue getValue(JSONValue value, String path) {
		if (path.isEmpty())
			return value;

		if (value == null)
			return null;
		
		JSONObject object = value.isObject();
		if (object == null)
			return null;
		
		int dotIndex = path.indexOf('.');
		if (dotIndex < 0) { 
			return object.get(path);
		} else {
			JSONValue child = object.get(path.substring(0, dotIndex));
			return getValue(child, path.substring(dotIndex + 1));
		}
	}
	
	public static Integer getInteger(JSONValue value, String path) {
		JSONValue result = getValue(value, path);
		
		if (result == null || result.isNumber() == null)
			return null;
		
		return (int) result.isNumber().doubleValue();
	}

	public static String getString(JSONValue value, String path) {
		JSONValue result = getValue(value, path);
		
		if (result == null || result.isString() == null)
			return null;
		
		return result.isString().stringValue();
	}
}
