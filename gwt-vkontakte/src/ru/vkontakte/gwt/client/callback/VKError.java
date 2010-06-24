package ru.vkontakte.gwt.client.callback;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class VKError {
	private int code;
	private String message;
	private Map<String, String> requestParams = new HashMap<String, String>();

	public static boolean isError(JSONObject object) {
		return object.containsKey("error");
	}
	
	public VKError(JSONObject object) {
		if (!isError(object)) 
			throw new RuntimeException();
		
		JSONObject error = (JSONObject) object.get("error");
		JSONNumber jsonErrorCode = (JSONNumber) error.get("error_code");
		code = (int) jsonErrorCode.doubleValue();
		
		JSONString jsonErrorMessage = (JSONString) error.get("error_msg");
		message = jsonErrorMessage.stringValue();
	}	

	
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Map<String, String> getRequestParams() {
		return Collections.unmodifiableMap(requestParams);
	}
}
