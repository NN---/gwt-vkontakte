package ru.vkontakte.gwt.client.callback;

import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class BooleanResponseCallbackWrapper extends AsyncCallbackWrapper<Boolean> {

	public BooleanResponseCallbackWrapper(AsyncCallback<Boolean> callback) {
		super(callback);
	}

	@Override
	protected Boolean parseResponse(JSONValue response) throws NoSuchJSONValueException {
		return JSONUtil.getBoolean(response);
	}
}
