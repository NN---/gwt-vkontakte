package ru.vkontakte.gwt.client.util;

import ru.vkontakte.gwt.client.VK;

import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.junit.client.GWTTestCase;

public class GwtTestJSONUtil extends GWTTestCase {
	private String errorString = 
		"{'error': {" +
			"'error_code': 4," +
			"'error_msg': 'Incorrect signature'" +
		" }}";
	
	
	@Override
	public String getModuleName() {
		return VK.MODULE_NAME;
	}

	public void testGetValue() {		
		JSONValue value = JSONParser.parse(errorString);
		
		JSONValue error = JSONUtil.getValue(value, "error");
		assertTrue(error != null);
		assertTrue(error.isObject() != null);

		JSONValue code = JSONUtil.getValue(value, "error.error_code");
		assertTrue(code.isNumber() != null);
		assertTrue(code.isNumber().doubleValue() == 4);
		
		JSONValue message = JSONUtil.getValue(value, "error.error_msg");
		assertTrue(message.isString() != null);
		assertEquals("Incorrect signature", message.isString().stringValue());
		
		JSONValue notExisting = JSONUtil.getValue(value, "error.not_existing.value");
		assertTrue(notExisting == null);
	}
	
	public void testGetInteger() {
		JSONValue value = JSONParser.parse(errorString);
		assertEquals(new Integer(4), JSONUtil.getInteger(value, "error.error_code"));
		assertNull(JSONUtil.getInteger(value, "error.error_msg"));
		assertNull(JSONUtil.getInteger(value, "error.not_existing.value"));
	}

	public void testGetString() {
		JSONValue value = JSONParser.parse(errorString);
		assertEquals("Incorrect signature", JSONUtil.getString(value, "error.error_msg"));
		assertNull(JSONUtil.getString(value, "error.error_code"));
		assertNull(JSONUtil.getString(value, "error.not_existing.value"));
	}

}
