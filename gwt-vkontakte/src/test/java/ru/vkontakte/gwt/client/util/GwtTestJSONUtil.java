package ru.vkontakte.gwt.client.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import ru.vkontakte.gwt.client.VKTestConstants;
import ru.vkontakte.gwt.client.model.ProfileFields;
import ru.vkontakte.gwt.client.model.Sex;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.junit.client.GWTTestCase;

public class GwtTestJSONUtil extends GWTTestCase {
	private String errorString = "{'error': {" + "'error_code': 4,"
			+ "'error_msg': 'Incorrect signature'" + " }}";


	private static String arrayString = "{\"response\":[15221,17836,19194]}";
	private Object[] expectedArray = new Object[] {15221, 17836, 19194};

	private static String booleanTest = "{\"trueValue\": 1,\"falseValue\": 0}";

	private static String sexNotSpecifiedString = "{\"response\": 0}";
	private static String sexFemaleString = "{\"response\": 1}";
	private static String sexMaleString = "{\"response\": 2}";

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
	
	public void testGetBoolean() {
		JSONValue value = JSONParser.parse(booleanTest);
		try {
			assertTrue(JSONUtil.getBoolean(value, "trueValue"));
			assertFalse(JSONUtil.getBoolean(value, "falseValue"));
		} catch (NoSuchJSONValueException e) {
			fail();
		}
		
		try {
			JSONUtil.getBoolean(value, "undefinedValue");
			fail();
		} catch (NoSuchJSONValueException e) {
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
	
	public void testJoinAsStrings() {
		assertEquals("15221,17836,19194", JSONUtil.joinAsStrings(Arrays.asList(expectedArray), ",")
				.stringValue());
	}

	public void testJoinStringIds() {
		List<ProfileFields> fields = new LinkedList<ProfileFields>();
		fields.add(ProfileFields.FIRST_NAME);
		fields.add(ProfileFields.LAST_NAME);
		fields.add(ProfileFields.COUNTRY);
		assertEquals(
				ProfileFields.FIRST_NAME.getStringId() + ","
						+ ProfileFields.LAST_NAME.getStringId() + ","
						+ ProfileFields.COUNTRY.getStringId(), JSONUtil.joinStringIds(fields, ",")
						.stringValue());
	}

	public void testTryGetString() {
		JSONValue value = JSONParser.parse(errorString);
		
		assertEquals("Incorrect signature", JSONUtil.tryGetString(value, "error.error_msg"));
		assertNull(JSONUtil.tryGetString(value, "error.error_code"));
		assertNull(JSONUtil.tryGetString(value, "error.not_existing.value"));
	}

	public void testTryGetSex() {
		JSONValue ns = JSONParser.parse(sexNotSpecifiedString);
		assertEquals(Sex.NOT_SPECIFIED, JSONUtil.tryGetSex(ns, "response"));
		
		JSONValue male = JSONParser.parse(sexMaleString);
		assertEquals(Sex.MALE, JSONUtil.tryGetSex(male, "response"));

		JSONValue female = JSONParser.parse(sexFemaleString);
		assertEquals(Sex.FEMALE, JSONUtil.tryGetSex(female, "response"));
	}
	
	public void testTryGetInteger() {
		JSONValue value = JSONParser.parse(errorString);
		
		assertNull(JSONUtil.tryGetInteger(value, "error.error_msg"));
		assertEquals(new Integer(4), JSONUtil.tryGetInteger(value, "error.error_code"));
		assertNull(JSONUtil.tryGetInteger(value, "error.not_existing.value"));
	}

	public void testTryGetLong() {
		JSONValue value = JSONParser.parse(errorString);
		
		assertNull(JSONUtil.tryGetLong(value, "error.error_msg"));
		assertEquals(new Long(4), JSONUtil.tryGetLong(value, "error.error_code"));
		assertNull(JSONUtil.tryGetLong(value, "error.not_existing.value"));
	}
	
	public void testTryGetBoolean() {
		JSONValue value = JSONParser.parse(booleanTest);
		assertTrue(JSONUtil.tryGetBoolean(value, "trueValue"));
		assertFalse(JSONUtil.tryGetBoolean(value, "falseValue"));
		assertNull(JSONUtil.tryGetBoolean(value, "undefinedValue"));
	}

}
