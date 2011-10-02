package ru.vkontakte.gwt.client.model;

import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public class LoginStatus {

	private Session session;
	private String status;
	
	public LoginStatus(JSONValue value) throws NoSuchJSONValueException {

		JSONObject response = value.isObject();
		
		if(response == null) {
			throw new NoSuchJSONValueException();
		}
		
		JSONValue s = JSONUtil.getValue(response, "session");
		if(s != null && s.isObject() != null) {
			session = new Session(s.isObject());
		}
		
		status = JSONUtil.getString(value, "status");
	}

	public Session getSession() {
		return session;
	}

	public String getStatus() {
		return status;
	}
	
	public boolean isAuthenticated() {
		return session != null;
	}
}
