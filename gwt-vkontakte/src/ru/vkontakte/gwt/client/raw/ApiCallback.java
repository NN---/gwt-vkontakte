package ru.vkontakte.gwt.client.raw;

import com.google.gwt.json.client.JSONObject;

public interface ApiCallback {
	void trigger(JSONObject result); 
}
