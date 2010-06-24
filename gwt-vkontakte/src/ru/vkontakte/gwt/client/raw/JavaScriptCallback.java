package ru.vkontakte.gwt.client.raw;

import com.google.gwt.core.client.JavaScriptObject;

public interface JavaScriptCallback {
	void trigger(JavaScriptObject[] params); 
}
