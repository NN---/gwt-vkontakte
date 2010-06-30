package ru.vkontakte.gwt.client.util;

import ru.vkontakte.gwt.client.VKTestConstants;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.junit.client.GWTTestCase;

public class GwtTestJSONUtil extends GWTTestCase {
	private String errorString = "{'error': {" + "'error_code': 4,"
			+ "'error_msg': 'Incorrect signature'" + " }}";


	private static String arrayString = "{\"response\":[15221,17836,19194]}";
	private Object[] expectedArray = new Object[] {15221, 17836, 19194};
	

	@Override
	public String getModuleName() {
		return VKTestConstants.TEST_MODULE_NAME;
	}

	public void testGetValue() {
		JSONValue value = JSONParser.parse(errorString);

		try {
			JSONValue error = JSONUtil.getValue(value, "error");
			assertTrue(error.isObject() != null);

			JSONValue code = JSONUtil.getValue(value, "error.error_code");
			assertTrue(code.isNumber().doubleValue() == 4);

			JSONValue message = JSONUtil.getValue(value, "error.error_msg");
			assertTrue(message.isString() != null);
			assertEquals("Incorrect signature", message.isString().stringValue());
		} catch (NoSuchJSONValueException ex) {
			fail();
		}

		try {
			JSONUtil.getValue(value, "error.not_existing.value");
			fail();
		} catch (NoSuchJSONValueException ex) {
		}
	}

	public void testGetInteger() {
		JSONValue value = JSONParser.parse(errorString);

		try {
			assertEquals(new Integer(4), JSONUtil.getInteger(value, "error.error_code"));
		} catch (NoSuchJSONValueException ex) {
			fail();
		}

		try {
			JSONUtil.getInteger(value, "error.error_msg");
			fail();
		} catch (NoSuchJSONValueException ex) {
		}

		try {
			assertNull(JSONUtil.getInteger(value, "error.not_existing.value"));
			fail();
		} catch (NoSuchJSONValueException ex) {
		}
	}

	public void testGetLong() {
		JSONValue value = JSONParser.parse(errorString);

		try {
			assertEquals(new Long(4), JSONUtil.getLong(value, "error.error_code"));
		} catch (NoSuchJSONValueException ex) {
			fail();
		}

		try {
			JSONUtil.getLong(value, "error.error_msg");
			fail();
		} catch (NoSuchJSONValueException ex) {
		}

		try {
			assertNull(JSONUtil.getLong(value, "error.not_existing.value"));
			fail();
		} catch (NoSuchJSONValueException ex) {
		}
	}	
	
	public void testGetString() {
		JSONValue value = JSONParser.parse(errorString);
		
		try {
			assertEquals("Incorrect signature", JSONUtil.getString(value, "error.error_msg"));
		} catch (NoSuchJSONValueException ex) {
			fail();
		}
		
		try {
			assertNull(JSONUtil.getString(value, "error.error_code"));
			fail();
		} catch (NoSuchJSONValueException ex) {
		}
		
		try {
			assertNull(JSONUtil.getString(value, "error.not_existing.value"));
			fail();
		} catch (NoSuchJSONValueException ex) {
		}
	}

	public void testGetArray() {
		JSONValue value = JSONParser.parse(arrayString);
		
		try {
			JSONArray array = JSONUtil.getArray(value, "response");
			assertEquals(expectedArray.length, array.size());
			for (int i=0; i<array.size(); i++) {
				assertEquals(expectedArray[i], JSONUtil.getInteger(array.get(i), ""));
			}
		} catch (NoSuchJSONValueException ex) {
			fail();
		}
		
		try {
			assertNull(JSONUtil.getArray(value, "error.error_code"));
			fail();
		} catch (NoSuchJSONValueException ex) {
		}
		
		try {
			assertNull(JSONUtil.getArray(value, "error.not_existing.value"));
			fail();
		} catch (NoSuchJSONValueException ex) {
		}
	}
	
	public void testHasValue() {
		JSONValue value = JSONParser.parse(errorString);
		assertTrue(JSONUtil.hasValue(value, "error.error_msg"));
		assertTrue(JSONUtil.hasValue(value, "error.error_code"));
		assertFalse(JSONUtil.hasValue(value, "error.not_existing.value"));
	}
}
