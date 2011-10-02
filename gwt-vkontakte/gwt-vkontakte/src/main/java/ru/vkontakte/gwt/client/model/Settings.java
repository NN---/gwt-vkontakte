package ru.vkontakte.gwt.client.model;

import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONValue;

public class Settings {
	
	//+1	Пользователь разрешил отправлять ему уведомления.
	public static final Long NOTIFICATIONS = 1L;
	//+2	Доступ к друзьям.
	public static final Long FRIENDS_ACCESS = 2L;
	//+4	Доступ к фотографиям.
	public static final Long PHOTOS_ACCESS = 4L;
	//+8	Доступ к аудиозаписям.
	public static final Long AUDIO_ACCESS = 8L;
	//+16	Доступ к видеозаписям.
	public static final Long VIDEO_ACCESS = 16L;
	//+32	Доступ к предложениям.
	public static final Long OFFERS_ACCESS = 32L;
	//+64	Доступ к вопросам.
	public static final Long QUESTIONS_ACCESS = 64L;
	//+128	Доступ к wiki-страницам.
	public static final Long WIKI_ACCESS = 128L;
	//+256	Добавление ссылки на приложение в меню слева.
	public static final Long MENU_LINK = 256L;
	//+512	Добавление ссылки на приложение для быстрой публикации на стенах пользователей.
	public static final Long QUICK_WALL_LINK = 512L;
	//+1024	Доступ к статусам пользователя.
	public static final Long STATUS_ACCESS = 1024L;
	//+2048	Доступ заметкам пользователя.
	public static final Long NOTES_ACCESS = 2048L;
	//+4096	(для Desktop-приложений) Доступ к расширенным методам работы с сообщениями.
	public static final Long MESSAGES_ACCESS = 4096L;
	//+8192	Доступ к обычным и расширенным методам работы со стеной.
	public static final Long WALL_ACCESS = 8192L;
	//+32768	Доступ к функциям для работы с рекламным кабинетом.
	public static final Long ADS_ACCESS = 32768L;
	//+131072	Доступ к документам пользователя.
	public static final Long DOCUMENTS_ACCESS = 131072L;
	//+262144	Доступ к группам пользователя.
	public static final Long GROUPS_ACCESS = 262144L;
	

	private Long _value;

	public Settings(JSONValue value) throws NoSuchJSONValueException {
		this._value = JSONUtil.getLong(value);
	}

	public Settings(Long... settings) {
		_value = 0L;
		for(Long setting : settings) {
			_value += setting;
		}
	}
	
	public boolean isEnabled(Long setting) {
		return (_value & setting) == setting;
	}
	
	public Long getValue() {
		return _value;
	}
}
