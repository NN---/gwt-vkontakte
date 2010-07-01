package ru.vkontakte.gwt.client;

public interface VKTestConstants {
	String TEST_MODULE_NAME = "ru.vkontakte.gwt.VKTest";
	
	String INVAID_RESPONSE = "{\"somefield\":\"somevalue\"}";
	String ERROR_RESPONSE = "{'error': {" + "'error_code': 4,"
			+ "'error_msg': 'Incorrect signature'" + " }}";

	String TRUE_RESPONSE = "{\"response\":\"1\"}";
	String FALSE_RESPONSE = "{\"response\":\"0\"}";

	String LONG_RESPONSE = "{\"response\":350}";
	Long EXPECTED_LONG = 350L;
	
	String INTEGER_RESPONSE = "{\"response\":15}";
	Integer EXPECTED_INTEGER = 15;
	
	String LONG_ARRAY_RESPONSE = "{\"response\":[15221,17836,19194]}";
	Long[] EXPECTED_LONG_ARRAY = new Long[] {15221L, 17836L, 19194L};
}
