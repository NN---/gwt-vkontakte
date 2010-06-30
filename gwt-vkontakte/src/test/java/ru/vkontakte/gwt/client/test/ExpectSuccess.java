package ru.vkontakte.gwt.client.test;

import static junit.framework.Assert.*;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class ExpectSuccess<T> implements AsyncCallback<T> {

	public void onFailure(Throwable t) {
		t.printStackTrace();
		fail();
	}

}