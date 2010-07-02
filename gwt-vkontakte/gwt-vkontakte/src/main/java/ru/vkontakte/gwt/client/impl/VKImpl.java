package ru.vkontakte.gwt.client.impl;

import ru.vkontakte.gwt.client.callback.ApiCallback;
import ru.vkontakte.gwt.client.callback.JavaScriptCallback;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class VKImpl {
	
	private boolean testMode;

	public void init(boolean testMode, AsyncCallback<Void> callback) {
		try {
			this.testMode = testMode;
			nativeInit(callback);
		} catch (JavaScriptException e) {
			callback.onFailure(e);
		}
	}

	private native void nativeInit(AsyncCallback<Void> callback) /*-{
		$wnd.VK.init(
			function() {
				callback.@com.google.gwt.user.client.rpc.AsyncCallback::onSuccess(Ljava/lang/Object;)(null);
			},		
			function() {
				callback.@com.google.gwt.user.client.rpc.AsyncCallback::onFailure(Ljava/lang/Throwable;)(null);
			});
	}-*/;

	public native void callMethod(String method, Object... params) /*-{
		$wnd.VK.callMethod.apply(null, [method].concat(params));		
	}-*/;

	public native void addCallback(String name, JavaScriptCallback callback) /*-{
		$wnd.VK.addCallback(name, function() {
			callback.@ru.vkontakte.gwt.client.callback.JavaScriptCallback::trigger([Lcom/google/gwt/core/client/JavaScriptObject;)(arguments);
		});		
	}-*/;
	

	public void api(String method, JSONObject param, final ApiCallback callback) {
		if (testMode) {
			if (param == null) param = new JSONObject();
			param.put("test_mode", new JSONNumber(1));
		}
		JavaScriptObject jsParam = param != null ? param.getJavaScriptObject() : null;
		nativeApi(method, jsParam, new JavaScriptCallback() {		
			public void trigger(JavaScriptObject[] params) {
				callback.trigger(new JSONObject(params[0]));
			}
		});
	}
	
	private native void nativeApi(String method, JavaScriptObject param, JavaScriptCallback callback) /*-{
		$wnd.VK.api(method, param, function() {
			callback.@ru.vkontakte.gwt.client.callback.JavaScriptCallback::trigger([Lcom/google/gwt/core/client/JavaScriptObject;)(arguments);
		});
	}-*/;

}
