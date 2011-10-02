package ru.vkontakte.gwt.client.test;

import static junit.framework.Assert.assertEquals;
import ru.vkontakte.gwt.client.callback.ApiCallback;
import ru.vkontakte.gwt.client.impl.VKImpl;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

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
	public void init(int apiId) {
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
