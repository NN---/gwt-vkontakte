package ru.vkontakte.gwt.client.callback;

import ru.vkontakte.gwt.client.VKTestConstants;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.junit.client.GWTTestCase;

public class GwtTestVKError extends GWTTestCase {

	public void testParseJSON() throws Exception {
		JSONObject object = (JSONObject) JSONParser.parseStrict(VKTestConstants.ERROR_RESPONSE);
		VKError error = new VKError(object);
		assertEquals(4, error.getCode());
		assertEquals("Incorrect signature", error.getMessage());
	}

	@Override
	public String getModuleName() {
		return VKTestConstants.TEST_MODULE_NAME;
	}
}
