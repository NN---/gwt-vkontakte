package ru.vkontakte.gwt.client.test;

import static junit.framework.Assert.*;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class ExpectListResult<T> implements AsyncCallback<List<T>> {

	private T[] expectedResult;

	public ExpectListResult(T... expectedResult) {
		this.expectedResult = expectedResult;
	}
	
	public void onFailure(Throwable t) {
		fail();
	}

	public void onSuccess(List<T> result) {
		assertEquals(expectedResult.length, result.size());

		int i=0;
		Iterator<T> it = result.iterator();
		while (it.hasNext()) {
			assertEquals(expectedResult[i], it.next());
			i++;
		}
	}
}
