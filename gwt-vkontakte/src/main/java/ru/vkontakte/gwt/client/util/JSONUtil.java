package ru.vkontakte.gwt.client.util;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public final class JSONUtil {
	private JSONUtil() {
	}

	public static JSONValue getValue(JSONValue value, String path) throws NoSuchJSONValueException {
		if (path.length() == 0)
			return value;

		if (value == null)
			throw new NoSuchJSONValueException();

		JSONObject object = value.isObject();
		if (object == null)
			throw new NoSuchJSONValueException();

		int dotIndex = path.indexOf('.');
		if (dotIndex < 0) {
			JSONValue jsonValue = object.get(path);
			if (jsonValue == null)
				throw new NoSuchJSONValueException();			
			return jsonValue;
		} else {
			JSONValue child = object.get(path.substring(0, dotIndex));
			return getValue(child, path.substring(dotIndex + 1));
		}
	}

	public static Integer getInteger(JSONValue value, String path) throws NoSuchJSONValueException {
		JSONValue result = getValue(value, path);

		if (result.isNumber() != null)
			return (int) result.isNumber().doubleValue();

		if (result.isString() != null) {
			try {
				return Integer.parseInt(result.isString().stringValue());
			} catch (NumberFormatException ex) {
			}
		}

		throw new NoSuchJSONValueException();
	}

	public static Long getLong(JSONValue value, String path) throws NoSuchJSONValueException {
		JSONValue result = getValue(value, path);

		if (result.isNumber() != null)
			return (long) result.isNumber().doubleValue();

		if (result.isString() != null) {
			try {
				return Long.parseLong(result.isString().stringValue());
			} catch (NumberFormatException ex) {
			}
		}

		throw new NoSuchJSONValueException();
	}

	public static String getString(JSONValue value, String path) throws NoSuchJSONValueException {
		JSONValue result = getValue(value, path);

		if (result.isString() != null)
			return result.isString().stringValue();

		throw new NoSuchJSONValueException();
	}

	public static JSONArray getArray(JSONValue value, String path) throws NoSuchJSONValueException {
		JSONValue arrayValue = getValue(value, path);

		if (arrayValue.isArray() != null)
			return arrayValue.isArray();

		throw new NoSuchJSONValueException();
	}

	public static boolean hasValue(JSONValue value, String path) {
		try {
			getValue(value, path);
			return true;
		} catch (NoSuchJSONValueException e) {
			return false;
		}
	}
}
