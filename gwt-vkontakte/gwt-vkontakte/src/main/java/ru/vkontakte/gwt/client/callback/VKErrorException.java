package ru.vkontakte.gwt.client.callback;

public class VKErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private VKError error;

	public VKErrorException(VKError error) {
		super(error.getMessage());
		this.error = error;
	}

	public VKError getError() {
		return error;
	}	
}
