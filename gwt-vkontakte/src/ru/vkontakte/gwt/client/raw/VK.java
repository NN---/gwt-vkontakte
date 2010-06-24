package ru.vkontakte.gwt.client.raw;


import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class VK {
	private static boolean testMode;
	
	private VK() {
		// Instantiation of this class is not allowed
	}
	
	public static void init(AsyncCallback<Void> callback) {
		init(callback, false);
	}
	
	public static void init(AsyncCallback<Void> callback, boolean testMode) {
		try {
			VK.testMode = testMode;
			nativeInit(callback);
		} catch (JavaScriptException e) {
			callback.onFailure(e);
		}
	}

	private static native void nativeInit(AsyncCallback<Void> callback) /*-{
		$wnd.VK.init(
			function() {
				callback.@com.google.gwt.user.client.rpc.AsyncCallback::onSuccess(Ljava/lang/Object;)(null);
			},		
			function() {
				callback.@com.google.gwt.user.client.rpc.AsyncCallback::onFailure(Ljava/lang/Throwable;)(null);
			});
	}-*/;

	public static native void callMethod(String method, Object... params) /*-{
		$wnd.VK.callMethod.apply(null, [method].concat(params));		
	}-*/;

	public static native void addCallback(String name, JavaScriptCallback callback) /*-{
		$wnd.VK.addCallback(name, function() {
			callback.@ru.vkontakte.gwt.client.raw.JavaScriptCallback::trigger([Lcom/google/gwt/core/client/JavaScriptObject;)(arguments);
		});		
	}-*/;
	

	public static void api(String method, JSONObject param, final ApiCallback callback) {
		if (testMode) {
			if (param == null) param = new JSONObject();
			param.put("test_mode", new JSONNumber(1));
		}
		nativeApi(method, param.getJavaScriptObject(), new JavaScriptCallback() {		
			public void trigger(JavaScriptObject[] params) {
				callback.trigger(new JSONObject(params[0]));
			}
		});
	}
	
	private static native void nativeApi(String method, JavaScriptObject param, JavaScriptCallback callback) /*-{
		$wnd.VK.api(method, param, function() {
			callback.@ru.vkontakte.gwt.client.raw.JavaScriptCallback::trigger([Lcom/google/gwt/core/client/JavaScriptObject;)(arguments);
		});
	}-*/;
}
