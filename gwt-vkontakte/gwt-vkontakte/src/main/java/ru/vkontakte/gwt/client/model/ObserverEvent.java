package ru.vkontakte.gwt.client.model;

public enum ObserverEvent {
	AUTH_LOGIN("auth.login"),
	AUTH_LOGOUT("auth.logout"),
	AUTH_SESSION_CHANGE("auth.sessionChange"),
	AUTH_STATUS_CHANGE("auth.statusChange");
	
	private String _eventName;
	
	ObserverEvent(String eventName) {
		this._eventName = eventName;
	}
	
	@Override
	public String toString() {
		return _eventName;
	}
}
