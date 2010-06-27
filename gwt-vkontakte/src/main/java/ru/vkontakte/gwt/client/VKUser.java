package ru.vkontakte.gwt.client;

import ru.vkontakte.gwt.client.callback.AsyncCallbackWrapper;
import ru.vkontakte.gwt.client.model.Case;
import ru.vkontakte.gwt.client.model.Group;
import ru.vkontakte.gwt.client.model.Profile;
import ru.vkontakte.gwt.client.model.ProfileFields;
import ru.vkontakte.gwt.client.model.Settings;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *  Пользователи 
 */
public class VKUser {
	
	/**
	 * возвращает установил пользователь данное приложение или нет.
	 */
	public static void isAppUser(final AsyncCallback<Boolean> callback) {
		VK.api("isAppUser", null, new AsyncCallbackWrapper<Boolean>(callback) {
			@Override
			protected Boolean parseResult(JSONObject result) {				
				JSONString response = (JSONString) result.get("response");
				return Integer.parseInt(response.stringValue()) > 0;
			}
		});
	}
	
	/**
	 * возвращает расширенную информацию о пользователях.
	 */
	public static Profile getProfiles(Long[] uids, ProfileFields[] fields, Case nameCase) {
		return null;
	}
	
	/**
	 * возвращает список id друзей текущего пользователя
	 */
	public static Long[] getFriends() {
		return null;
	}
	
	/**
	 * возвращает список id друзей текущего пользователя, которые установили данное приложение.
	 */
	public static Long[] getAppFriends() {
		return null;
	}
	
	/**
	 * возвращает баланс текущего пользователя в данном приложении.
	 */
	public static Long getUserBalance() {
		return null;
	}
	
	/**
	 * возвращает настройки приложения текущего пользователя.
	 */	
	public static Settings getUserSettings() {
		return null;
	}
	
	/**
	 * возвращает список id групп, в которых состоит текущий пользователь.
	 */
	public Long[] getGroups() { 
		return null;
	}
	
	/**
	 * возвращает базовую информацию о группах, в которых состоит текущий пользователь.
	 */
	public Group[] getGroupsFull() {
		return null;
	}
}
