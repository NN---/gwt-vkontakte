package ru.vkontakte.gwt.client.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ru.vkontakte.gwt.client.model.HasStringId;
import ru.vkontakte.gwt.client.model.Sex;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public final class JSONUtil {
	private JSONUtil() {
	}

	public static JSONValue getValue(JSONValue value, String path) throws NoSuchJSONValueException {
		if (path == null || path.length() == 0)
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

	public static Integer getInteger(JSONValue value) throws NoSuchJSONValueException {
		return getInteger(value, null);
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

	public static Long getLong(JSONValue value) throws NoSuchJSONValueException {
		return getLong(value, null);
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

	public static Boolean getBoolean(JSONValue value) throws NoSuchJSONValueException {
		return getBoolean(value, null);
	}

	public static Boolean getBoolean(JSONValue value, String path) throws NoSuchJSONValueException {
		return getInteger(value, path) > 0;
	}

	public static String getString(JSONValue value) throws NoSuchJSONValueException {
		return getString(value, null);
	}
	
	public static String getString(JSONValue value, String path) throws NoSuchJSONValueException {
		JSONValue result = getValue(value, path);

		if (result.isString() != null) {
			String stringValue = result.isString().stringValue();
			return StringUtil.unescapeHTML(stringValue);
		}

		throw new NoSuchJSONValueException();
	}

	public static JSONArray getArray(JSONValue value) throws NoSuchJSONValueException {
		return getArray(value, null);
	}

	public static <T> List<T> convertToList(JSONValue value, JSONConverter<T> converter) throws NoSuchJSONValueException {
		JSONArray array = JSONUtil.getArray(value);
		ArrayList<T> resultArray = new ArrayList<T>(array.size());
		for (int i = 0; i < array.size(); i++) {
			resultArray.add(converter.convert(array.get(i)));
		}
		return resultArray;	
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

	public static <T> JSONString joinAsStrings(List<T> list, String delimiter) {
		Iterator<T> it = list.iterator();
		StringBuilder builder = new StringBuilder();
		while (it.hasNext()) {
			builder.append(it.next().toString());
			if (it.hasNext())
				builder.append(delimiter);
		}
		return new JSONString(builder.toString());
	}

	public static <T extends HasStringId> JSONString joinStringIds(List<T> list, String delimiter) {
		Iterator<T> it = list.iterator();
		StringBuilder builder = new StringBuilder();
		while (it.hasNext()) {
			builder.append(it.next().getStringId());
			if (it.hasNext())
				builder.append(delimiter);
		}
		return new JSONString(builder.toString());
	}

	public static String tryGetString(JSONValue value, String path) {
		try {
			return getString(value, path);
		} catch (NoSuchJSONValueException e) {
			return null;
		}
	}

	public static Sex tryGetSex(JSONValue value, String path) {
		try {
			return Sex.getById(getInteger(value, path));
		} catch (NoSuchJSONValueException e) {
		}
		return null;
	}

	public static Long tryGetLong(JSONValue value, String path) {		
		try {
			return getLong(value, path);
		} catch (NoSuchJSONValueException e) {
		}
		return null;
	}

	public static Integer tryGetInteger(JSONValue value, String path) {
		try {
			return getInteger(value, path);
		} catch (NoSuchJSONValueException e) {
		}
		return null;
	}

	public static Boolean tryGetBoolean(JSONValue value, String path) {
		try {
			return getBoolean(value, path);
		} catch (NoSuchJSONValueException e) {
		}
		
		return null;
	}
}
