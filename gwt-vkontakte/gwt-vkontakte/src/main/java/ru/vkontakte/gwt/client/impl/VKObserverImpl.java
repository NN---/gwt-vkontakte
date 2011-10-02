package ru.vkontakte.gwt.client.impl;

import ru.vkontakte.gwt.client.callback.ObserverEventHandler;

import com.google.gwt.core.client.JavaScriptObject;

public class VKObserverImpl {
	public void subscribe(String eventName, ObserverEventHandler handler) {
		
		nativeSubscribe(eventName, handler.getJavaScriptObject());
	}
	
	public void unsubscribe(String eventName, ObserverEventHandler handler) {
		
		nativeUnsubscribe(eventName, handler.getJavaScriptObject());
	}
	
	public native void nativeSubscribe(String eventName, JavaScriptObject handler) /*-{
		$wnd.VK.Observer.subscribe(eventName, handler);
	}-*/;
	
	public native void nativeUnsubscribe(String eventName, JavaScriptObject handler) /*-{
		$wnd.VK.Observer.unsubscribe(eventName, handler);		
	}-*/;
}
