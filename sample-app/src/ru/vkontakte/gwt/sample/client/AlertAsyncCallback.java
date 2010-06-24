package ru.vkontakte.gwt.sample.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AlertAsyncCallback<T> implements AsyncCallback<T> {
	public void onFailure(Throwable caught) {
		if (caught == null) {
			Window.alert("Unknown failure");			
		} else {
			Window.alert(caught.getMessage());
		}
	}
}
