package ru.vkontakte.gwt.client.callback;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;

public abstract class ObserverEventHandler implements JavaScriptCallback<JavaScriptObject> {

	private JavaScriptObject jsHandler;
	
	public final void trigger(JavaScriptObject arguments) {
		trigger(new JSONArray(arguments));
	}
	
	public abstract void trigger(JSONArray arguments);
	
	public JavaScriptObject getJavaScriptObject() {
		if(jsHandler == null) {
			jsHandler = getJavaScriptObject(this);
		}
		
		return jsHandler;
	}
	

	// на самом деле arguments - недомассив, но при попытке преобразовать его или Array.prototype.slice.call(arguments) в JavaScriptObject[]
	// выдается ошибка ClassCastException(только  в DevMode'е, скомпилированная версия работает) и метод trigger() не вызывается.
	// В связи с этим преобразование в массив осуществляется средствами java внутри trigger(JavaScriptObject arguments);
	private native JavaScriptObject getJavaScriptObject(ObserverEventHandler handler) /*-{
		return function() {
			handler.@ru.vkontakte.gwt.client.callback.ObserverEventHandler::trigger(Lcom/google/gwt/core/client/JavaScriptObject;)(arguments);
		}
	}-*/;
}
