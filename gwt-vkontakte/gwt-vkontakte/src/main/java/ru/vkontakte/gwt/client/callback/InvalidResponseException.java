package ru.vkontakte.gwt.client.callback;


public class InvalidResponseException extends Exception {	
	private static final long serialVersionUID = 1L;

	public InvalidResponseException() {
	}

	public InvalidResponseException(Throwable t) {
		super(t);
	}	
}
