package ru.vkontakte.gwt.client.callback;

import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class LongResponseCallbackWrapper extends AsyncCallbackWrapper<Long> {

	public LongResponseCallbackWrapper(AsyncCallback<Long> callback) {
		super(callback);
	}

	@Override
	protected Long parseResponse(JSONValue result) throws NoSuchJSONValueException {
		return JSONUtil.getLong(result, "response");
	}
}
