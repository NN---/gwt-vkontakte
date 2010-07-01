package ru.vkontakte.gwt.client.util;

import com.google.gwt.json.client.JSONValue;

public interface JSONConverter<T> {
	T convert(JSONValue jsonValue) throws NoSuchJSONValueException;
}
