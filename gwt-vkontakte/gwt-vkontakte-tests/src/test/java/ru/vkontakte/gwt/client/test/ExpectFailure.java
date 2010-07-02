package ru.vkontakte.gwt.client.test;

import com.google.gwt.user.client.rpc.AsyncCallback;
import static junit.framework.Assert.*;

public class ExpectFailure<T> implements AsyncCallback<T> {

	private Class<? extends Throwable> expectedFailure;

	public ExpectFailure(Class<? extends Throwable> expectedFailure) {
		this.expectedFailure = expectedFailure;
	}
	
	public void onFailure(Throwable ex) {
		assertEquals(expectedFailure, ex.getClass());
	}

	public void onSuccess(T result) {
		fail();
	}

}
