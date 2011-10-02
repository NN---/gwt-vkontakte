package ru.vkontakte.gwt.client.callback;

import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AsyncCallbackWrapper<T> extends ApiCallback {
	private AsyncCallback<T> callback;

	public AsyncCallbackWrapper(AsyncCallback<T> callback) {
		this.callback = callback;
	}

	public void trigger(JSONValue result) {
		try {
			if (VKError.isError(result)) {
				VKError error = new VKError(result);
				callback.onFailure(new VKErrorException(error));
			} else {
				JSONValue response = JSONUtil.getValue(result, "response");
				callback.onSuccess(parseResponse(response));
			}
		} catch (NoSuchJSONValueException t) {
			callback.onFailure(new InvalidResponseException(t));
		} catch (Throwable t) {
			callback.onFailure(t);
		}
	}

	protected abstract T parseResponse(JSONValue response) throws NoSuchJSONValueException;
}
