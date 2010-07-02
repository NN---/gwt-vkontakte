package ru.vkontakte.gwt.client.callback;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONValue;

public class VKError {
	private Integer code;
	private String message;
	private Map<String, String> requestParams = new HashMap<String, String>();

	public static boolean isError(JSONValue object) {
		return JSONUtil.hasValue(object, "error");
	}

	public VKError(JSONValue object) throws NoSuchJSONValueException {
		if (!isError(object))
			throw new RuntimeException();

		code = JSONUtil.getInteger(object, "error.error_code");
		message = JSONUtil.getString(object, "error.error_msg");
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
