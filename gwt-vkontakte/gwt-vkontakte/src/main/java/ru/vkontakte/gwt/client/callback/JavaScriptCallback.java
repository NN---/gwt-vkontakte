package ru.vkontakte.gwt.client.callback;

import com.google.gwt.core.client.JavaScriptObject;

public interface JavaScriptCallback {
	void trigger(JavaScriptObject[] params); 
}
