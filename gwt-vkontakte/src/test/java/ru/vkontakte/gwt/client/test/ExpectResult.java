package ru.vkontakte.gwt.client.test;

import static junit.framework.Assert.*;

public class ExpectResult<T> extends ExpectSuccess<T> {

	private T expectedResult;

	public ExpectResult(T expectedResult) {
		this.expectedResult = expectedResult;
	}
	
	public void onSuccess(T result) {
		assertEquals(expectedResult, result);
	}
}
