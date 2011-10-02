package ru.vkontakte.gwt.client.impl;

import ru.vkontakte.gwt.client.callback.ApiCallback;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;

public class VKImpl {
	
	private boolean testMode = true;

	public void init(int apiId) {

		JSONObject options = new JSONObject();
		options.put("apiId", new JSONNumber(apiId));
			
		nativeInit(options.getJavaScriptObject());
	}
	
	private native void nativeInit(JavaScriptObject options) /*-{
		$wnd.VK.init(options);
	}-*/;
	
	public void api(String method, JSONObject param, ApiCallback callback) {
		if (testMode) {
			if (param == null) param = new JSONObject();
			param.put("test_mode", new JSONNumber(1));
		}
		JavaScriptObject jsParam = param != null ? param.getJavaScriptObject() : null;
		
		nativeApi(method, jsParam, callback.getJavaScriptObject());
	}
	
	private native void nativeApi(String method, JavaScriptObject param, JavaScriptObject callback) /*-{
		$wnd.VK.api(method, param, callback);
	}-*/;

}
