package ru.vkontakte.gwt.client.callback;

import ru.vkontakte.gwt.client.VK;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.junit.client.GWTTestCase;

public class VKErrorTest extends GWTTestCase {
	private String errorString = 
		"{'error': {" +
			"'error_code': 4," +
			"'error_msg': 'Incorrect signature'" +
		" }}";
	
	public void testParseJSON() throws Exception {
		JSONObject object = (JSONObject) JSONParser.parse(errorString);
		VKError error = new VKError(object);
		assertEquals(4, error.getCode());
		assertEquals("Incorrect signature", error.getMessage());
	}

	@Override
	public String getModuleName() {
		return VK.MODULE_NAME;
	}
}
