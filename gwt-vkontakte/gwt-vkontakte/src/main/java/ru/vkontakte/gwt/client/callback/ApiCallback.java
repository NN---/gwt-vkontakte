package ru.vkontakte.gwt.client.callback;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public abstract class ApiCallback implements JavaScriptCallback<JavaScriptObject> {
	private JavaScriptObject jsHandler;
	
	public abstract void trigger(JSONValue result);
	
	public final void trigger(JavaScriptObject response) {
		trigger(new JSONObject(response));
	}
	
	
	public JavaScriptObject getJavaScriptObject() {
		if(jsHandler == null) {
			jsHandler = getJavaScriptObject(this);
		}
		
		return jsHandler;
	}
	
	private native JavaScriptObject getJavaScriptObject(ApiCallback callback) /*-{
		return function(response) {
			callback.@ru.vkontakte.gwt.client.callback.ApiCallback::trigger(Lcom/google/gwt/core/client/JavaScriptObject;)(response);
		}
	}-*/;
}
