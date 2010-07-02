package ru.vkontakte.gwt.client;

import java.util.Date;
import java.util.List;

import ru.vkontakte.gwt.client.callback.AsyncCallbackWrapper;
import ru.vkontakte.gwt.client.callback.BooleanResponseCallbackWrapper;
import ru.vkontakte.gwt.client.callback.ListResponseCallbackWrapper;
import ru.vkontakte.gwt.client.callback.LongResponseCallbackWrapper;
import ru.vkontakte.gwt.client.model.Activity;
import ru.vkontakte.gwt.client.model.News;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 	Личный статус
 */
public class VKActivity {
	/**
	 * возвращает текущий статус пользователя. 
	 */
	public static void get(final Long uid, AsyncCallback<Activity> callback) {
		VK.api("activity.get", null, new AsyncCallbackWrapper<Activity>(callback) {
			@Override
			protected Activity parseResponse(JSONValue response) throws NoSuchJSONValueException {
				return new Activity(response, uid);
			}
		});
	}
	
	/**
	 * устанавливает статус текущего пользователя.
	 */
	public static void set(String text, AsyncCallback<Long> callback) {
		JSONObject json = new JSONObject();
		json.put("text", new JSONString(text));
		VK.api("activity.set", json, new LongResponseCallbackWrapper(callback));

	}
	
	/**
	 * возвращает историю статусов.
	 */
	public static void getHistory(AsyncCallback<List<Activity>> callback) {
		VK.api("activity.getHistory", null, new ListResponseCallbackWrapper<Activity>(callback) {
			@Override
			protected Activity convertValue(JSONValue value) throws NoSuchJSONValueException {
				return new Activity(value);
			}			
		});		
	}

	/**
	 * возвращает историю статусов.
	 */
	public static void getHistory(final long uid, AsyncCallback<List<Activity>> callback) {
		JSONObject json = new JSONObject();
		json.put("uid", new JSONNumber(uid));
		VK.api("activity.getHistory", json, new ListResponseCallbackWrapper<Activity>(callback) {
			@Override
			protected Activity convertValue(JSONValue value) throws NoSuchJSONValueException {
				return new Activity(value, uid);
			}			
		});			
	}
	
	/** 
	 * удаляет элемент истории статусов у текущего пользователя.
	 */
	public static void deleteHistoryItem(Long activityId, AsyncCallback<Boolean> callback) {		
		JSONObject json = new JSONObject();
		json.put("aid", new JSONNumber(activityId));
		VK.api("activity.deleteHistoryItem", json, new BooleanResponseCallbackWrapper(callback));				
	}
	
	/**
	 * возвращает обновления статусов друзей.
	 */
	public static void getNews(Date fromDate, AsyncCallback<News> callback) {
		JSONObject json = new JSONObject();
		json.put("timestamp", new JSONNumber(fromDate.getTime()));
		VK.api("activity.getNews", json, new AsyncCallbackWrapper<News>(callback) {
			@Override
			protected News parseResponse(JSONValue response) throws NoSuchJSONValueException {
				return new News(response);
			}
		});				
	}

	/**
	 * возвращает обновления статусов друзей.
	 */
	public static void getNews(Long offset, Long count, AsyncCallback<News> callback) {
		JSONObject json = new JSONObject();
		json.put("offset", new JSONNumber(offset));
		json.put("count", new JSONNumber(count));
		VK.api("activity.getNews", json, new AsyncCallbackWrapper<News>(callback) {
			@Override
			protected News parseResponse(JSONValue result) throws NoSuchJSONValueException {
				return new News(result);
			}
		});				
	}
}
