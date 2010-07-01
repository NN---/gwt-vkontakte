package ru.vkontakte.gwt.client.test;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.vkontakte.gwt.client.callback.ApiCallback;
import ru.vkontakte.gwt.client.callback.JavaScriptCallback;
import ru.vkontakte.gwt.client.impl.VKImpl;

import static junit.framework.Assert.*;

public class MockVKImpl extends VKImpl {
	private JSONValue apiResponse;
	private String expectedApiMethod;
	
	@Override
	public void api(String method, JSONObject param, ApiCallback callback) {
		if (expectedApiMethod != null)
			assertEquals(expectedApiMethod, method);		
		callback.trigger(apiResponse);
	}
	
	@Override
	public void addCallback(String name, JavaScriptCallback callback) {
	}

	@Override
	public void callMethod(String method, Object... params) {
	}

	@Override
	public void init(boolean testMode, AsyncCallback<Void> callback) {
	}

	public JSONValue getApiResponse() {
		return apiResponse;
	}

	public void setApiResponse(JSONValue apiResponse) {
		this.apiResponse = apiResponse;
	}

	public String getExpectedApiMethod() {
		return expectedApiMethod;
	}

	public void setExpectedApiMethod(String expectedApiMethod) {
		this.expectedApiMethod = expectedApiMethod;
	}	
}
