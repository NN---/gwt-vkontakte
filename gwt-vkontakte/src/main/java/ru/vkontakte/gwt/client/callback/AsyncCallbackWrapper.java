package ru.vkontakte.gwt.client.callback;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AsyncCallbackWrapper<T> implements ApiCallback {
	private AsyncCallback<T> callback;

	public AsyncCallbackWrapper(AsyncCallback<T> callback) {
		this.callback = callback;
	}
	
	public void trigger(JSONObject result) {
		if (VKError.isError(result)) {
			VKError error = new VKError(result);
			callback.onFailure(new VKErrorException(error));
		} else {
			callback.onSuccess(parseResult(result));
		}
	}

	protected abstract T parseResult(JSONObject result);
}
