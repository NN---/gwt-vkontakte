package ru.vkontakte.gwt.client.callback;

import java.util.List;

import ru.vkontakte.gwt.client.util.JSONConverter;
import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class ListResponseCallbackWrapper<T> extends AsyncCallbackWrapper<List<T>> {

	public ListResponseCallbackWrapper(AsyncCallback<List<T>> callback) {
		super(callback);
	}

	@Override
	protected List<T> parseResponse(JSONValue response) throws NoSuchJSONValueException {
		return JSONUtil.convertToList(response, new JSONConverter<T>() {
			public T convert(JSONValue jsonValue) throws NoSuchJSONValueException {
				return convertValue(jsonValue);
			}		
		});
	}
	protected abstract T convertValue(JSONValue value) throws NoSuchJSONValueException;
}
