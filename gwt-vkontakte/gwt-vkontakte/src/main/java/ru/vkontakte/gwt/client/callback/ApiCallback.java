package ru.vkontakte.gwt.client.callback;

import com.google.gwt.json.client.JSONValue;

public interface ApiCallback {
	void trigger(JSONValue result); 
}
