package ru.vkontakte.gwt.client.model;

public enum Sex {
	NOT_SPECIFIED(0), FEMALE(1), MALE(2);

	private int sexId;

	private Sex(int id) {
		this.sexId = id;
	}

	public int getSexId() {
		return sexId;
	}

	public static Sex getById(int id) {
		for (Sex sex : Sex.values()) {
			if (sex.getSexId() == id)
				return sex;
		}
		return null;
	}
}
