package ru.vkontakte.gwt.client.callback;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ru.vkontakte.gwt.client.util.JSONUtil;

import com.google.gwt.json.client.JSONObject;

public class VKError {
	private Integer code;
	private String message;
	private Map<String, String> requestParams = new HashMap<String, String>();

	public static boolean isError(JSONObject object) {
		return object.containsKey("error");
	}

	public VKError(JSONObject object) {
		if (!isError(object))
			throw new RuntimeException();

		code = JSONUtil.getInteger(object, "error.error_code");
		message = JSONUtil.getString(object, "error.error_msg");

		if (code == null || message == null)
			throw new IllegalArgumentException(
					"Error should contain code and message");
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
