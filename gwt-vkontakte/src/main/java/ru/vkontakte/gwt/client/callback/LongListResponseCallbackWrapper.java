package ru.vkontakte.gwt.client.callback;

import java.util.List;

import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class LongListResponseCallbackWrapper extends ListResponseCallbackWrapper<Long> {

	public LongListResponseCallbackWrapper(AsyncCallback<List<Long>> callback) {
		super(callback);
	}

	protected Long convertValue(JSONValue value) throws NoSuchJSONValueException {
		return JSONUtil.getLong(value, "");
	}
}
