package ru.vkontakte.gwt.client;

import java.util.List;

import ru.vkontakte.gwt.client.callback.AsyncCallbackWrapper;
import ru.vkontakte.gwt.client.callback.BooleanResponseCallbackWrapper;
import ru.vkontakte.gwt.client.callback.ListResponseCallbackWrapper;
import ru.vkontakte.gwt.client.callback.LongListResponseCallbackWrapper;
import ru.vkontakte.gwt.client.callback.LongResponseCallbackWrapper;
import ru.vkontakte.gwt.client.model.Case;
import ru.vkontakte.gwt.client.model.Group;
import ru.vkontakte.gwt.client.model.Profile;
import ru.vkontakte.gwt.client.model.ProfileFields;
import ru.vkontakte.gwt.client.model.Settings;
import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Пользователи
 */
public class VKUser {

	/**
	 * возвращает установил пользователь данное приложение или нет.
	 */
	public static void isAppUser(final AsyncCallback<Boolean> callback) {
		VK.api("isAppUser", null, new BooleanResponseCallbackWrapper(callback));
	}

	/**
	 * возвращает расширенную информацию о пользователях.
	 */
	public static void getProfiles(List<Long> uids, List<ProfileFields> fields, Case nameCase,
			AsyncCallback<List<Profile>> callback) {
		if (uids == null || uids.isEmpty())
			throw new IllegalArgumentException();
		
		JSONObject params = new JSONObject();
		params.put("uids", JSONUtil.joinAsStrings(uids, ","));

		if (fields != null)
			params.put("fields", JSONUtil.joinStringIds(fields, ","));

		if (nameCase != null)
			params.put("name_case", new JSONString(nameCase.getCaseId()));

		VK.api("getProfiles", params, new ListResponseCallbackWrapper<Profile>(callback) {
			@Override
			protected Profile convertValue(JSONValue value) throws NoSuchJSONValueException {
				return new Profile(value);
			}
		});
	}

	/**
	 * возвращает список id друзей текущего пользователя
	 */
	public static void getFriends(AsyncCallback<List<Long>> callback) {
		VK.api("getFriends", null, new LongListResponseCallbackWrapper(callback));
	}

	/**
	 * возвращает список id друзей текущего пользователя, которые установили данное приложение.
	 */
	public static void getAppFriends(AsyncCallback<List<Long>> callback) {
		VK.api("getAppFriends", null, new LongListResponseCallbackWrapper(callback));
	}

	/**
	 * возвращает баланс текущего пользователя в данном приложении.
	 */
	public static void getUserBalance(AsyncCallback<Long> callback) {
		VK.api("getUserBalance", null, new LongResponseCallbackWrapper(callback));
	}

	/**
	 * возвращает настройки приложения текущего пользователя.
	 */
	public static void getUserSettings(AsyncCallback<Settings> callback) {
		VK.api("getUserSettings", null, new AsyncCallbackWrapper<Settings>(callback) {
			@Override
			protected Settings parseResponse(JSONValue response) throws NoSuchJSONValueException {
				return new Settings(response);
			}
		});
	}

	/**
	 * возвращает список id групп, в которых состоит текущий пользователь.
	 */
	public static void getGroups(AsyncCallback<List<Long>> callback) {
		VK.api("getGroups", null, new LongListResponseCallbackWrapper(callback));
	}

	/**
	 * возвращает базовую информацию о группах, в которых состоит текущий пользователь.
	 */
	public static void getGroupsFull(AsyncCallback<List<Group>> callback) {
		VK.api("getGroupsFull", null, new ListResponseCallbackWrapper<Group>(callback) {
			@Override
			protected Group convertValue(JSONValue value) throws NoSuchJSONValueException {
				return new Group(value);
			}
		});
	}
}
