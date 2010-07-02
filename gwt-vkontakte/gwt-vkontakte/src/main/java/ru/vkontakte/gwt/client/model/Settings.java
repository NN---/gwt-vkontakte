package ru.vkontakte.gwt.client.model;

import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONValue;

public class Settings {
	// +1 – пользователь разрешил отправлять ему уведомления.
	public static final Long NOTIFICATIONS = 1L;
	// +2 – доступ к друзьям.
	public static final Long FRIENDS_ACCESS = 1L;
	// +4 – доступ к фотографиям.
	public static final Long PHOTOS_ACCESS = 1L;
	// +8 – доступ к аудиозаписям.
	public static final Long AUDIO_ACCESS = 1L;
	// +16 – доступ к видеозаписям.
	public static final Long VIDEO_ACCESS = 1L;
	// +32 – доступ к предложениям.
	public static final Long OFFERS_ACCESS = 1L;
	// +64 – доступ к вопросам.
	public static final Long QUESTIONS_ACCESS = 1L;
	// +128 – доступ к wiki-страницам.
	public static final Long WIKI_ACCESS = 1L;
	// +256 – добавление ссылки на приложение в меню слева.
	public static final Long MENU_LINK = 1L;
	// +512 – добавление ссылки на приложение для быстрой публикации на стенах пользователей.
	public static final Long QUICK_WALL_LINK = 1L;
	// +1024 – доступ к статусам пользователя.
	public static final Long STATUS_ACCESS = 1L;
	// +2048 – доступ заметкам пользователя.
	public static final Long NOTES_ACCESS = 1L;

	private Long value;

	public Settings(JSONValue value) throws NoSuchJSONValueException {
		this.value = JSONUtil.getLong(value);
	}

	public boolean isEnabled(Long settings) {
		return (value & settings) == settings;
	}
	
	public Long getValue() {
		return value;
	}
}
