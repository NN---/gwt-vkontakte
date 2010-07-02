package ru.vkontakte.gwt.client.test;

import java.util.List;

import ru.vkontakte.gwt.client.model.Profile;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class ExpectNothing<T> implements AsyncCallback<List<Profile>> {

	public void onFailure(Throwable arg0) {
	}

	public void onSuccess(List<Profile> arg0) {
	}

}
