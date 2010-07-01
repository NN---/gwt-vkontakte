package ru.vkontakte.gwt.client;


import ru.vkontakte.gwt.client.callback.ApiCallback;
import ru.vkontakte.gwt.client.callback.JavaScriptCallback;
import ru.vkontakte.gwt.client.impl.VKImpl;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class VK {
	private static VKImpl impl;
	
	private VK() {
		// Instantiation of this class is not allowed
	}
	
	public static void init(AsyncCallback<Void> callback) {
		init(false, callback);
	}
	
	public static void init(boolean testMode, AsyncCallback<Void> callback) {
		getImpl().init(testMode, callback);
	}

	
	public static void callMethod(String method, Object... params) {
		getImpl().callMethod(method, params);
	}

	public static void addCallback(String name, JavaScriptCallback callback) {
		getImpl().addCallback(name, callback);	
	}
	
	public static void api(String method, JSONObject param, final ApiCallback callback) {
		getImpl().api(method, param, callback);
	}

	static VKImpl getImpl() {
		if (impl == null) impl = new VKImpl();
		return impl;
	}

	static void setImpl(VKImpl impl) {
		VK.impl = impl;
	}
}
