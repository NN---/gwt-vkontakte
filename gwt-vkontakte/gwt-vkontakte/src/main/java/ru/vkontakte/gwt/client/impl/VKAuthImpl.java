package ru.vkontakte.gwt.client.impl;

import ru.vkontakte.gwt.client.callback.ApiCallback;
import ru.vkontakte.gwt.client.model.Settings;

import com.google.gwt.core.client.JavaScriptObject;

public class VKAuthImpl {
	
	public void login(ApiCallback callback, Settings settings) {
		
		if(settings == null) {
			nativeLogin(callback.getJavaScriptObject(), null);
		} else {
			nativeLogin(callback.getJavaScriptObject(), settings.getValue());
		}
	}

	private native void nativeLogin(JavaScriptObject jsCallback, Long settings) /*-{
		$wnd.VK.Auth.login(jsCallback, settings);
	}-*/;
	
	public void getLoginStatus(ApiCallback callback) {	
		nativeGetLoginStatus(callback.getJavaScriptObject());
	}
	
	private native void nativeGetLoginStatus(JavaScriptObject jsCallback) /*-{
		$wnd.VK.Auth.getLoginStatus(jsCallback);
	}-*/;

	public void logout(ApiCallback callback) {
		if(callback != null) {
			nativeLogout(callback.getJavaScriptObject());
		} else {
			nativeLogout(null);
		}
	}
	
	private native void nativeLogout(JavaScriptObject jsCallback) /*-{
		$wnd.VK.Auth.logout(jsCallback);
	}-*/;
	
	public void revokeGrants(ApiCallback callback) {		
		nativeRevokeGrants(callback.getJavaScriptObject());
	}
	
	private native void nativeRevokeGrants(JavaScriptObject jsCallback) /*-{
		$wnd.VK.Auth.revokeGrants(jsCallback);
	}-*/;
}
