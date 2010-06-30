package ru.vkontakte.gwt.client.callback;

import java.util.ArrayList;
import java.util.List;

import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class ListResponseCallbackWrapper<T> extends AsyncCallbackWrapper<List<T>> {

	public ListResponseCallbackWrapper(AsyncCallback<List<T>> callback) {
		super(callback);
	}

	@Override
	protected List<T> parseResponse(JSONValue result) throws NoSuchJSONValueException {
		JSONArray array = JSONUtil.getArray(result, "response");
		ArrayList<T> resultArray = new ArrayList<T>(array.size());
		for (int i = 0; i < array.size(); i++) {
			resultArray.add(convertValue(array.get(i)));
		}
		return resultArray;
	}
	protected abstract T convertValue(JSONValue value) throws NoSuchJSONValueException;
}
