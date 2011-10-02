package ru.vkontakte.gwt.client.callback;


public interface JavaScriptCallback<T> {
	void trigger(T params); 
}
