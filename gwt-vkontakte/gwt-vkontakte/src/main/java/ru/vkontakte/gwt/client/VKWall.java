package ru.vkontakte.gwt.client;

import ru.vkontakte.gwt.client.callback.AsyncCallbackWrapper;
import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *	Стена
 */	
public class VKWall {
	/**
	 * возвращает адрес сервера для загрузки фотографии на стену.
	 */
	public static void getPhotoUploadServer(AsyncCallback<String> callback) {
		VK.api("wall.getPhotoUploadServer", null, new AsyncCallbackWrapper<String>(callback){
			@Override
			protected String parseResponse(JSONValue response) throws NoSuchJSONValueException {
				return JSONUtil.getString(response, "upload_url");
			}			
		});
	}

	/**
	 * сохраняет запись на стене пользователя.
	 */
	public static void savePost(long userId, long photoId, String message, AsyncCallback<String> callback) {
		savePost(userId, photoId, message, null, callback);
	}
	
	/**
	 * сохраняет запись на стене пользователя.
	 */
	public static void savePost(long userId, long photoId, String message, String postId, AsyncCallback<String> callback) {
		JSONObject params = new JSONObject();
		params.put("wall_id", new JSONNumber(userId));
		params.put("photo_id", new JSONNumber(photoId));
		params.put("message", new JSONString(message));
		if (postId != null)
			params.put("post_id", new JSONString(postId));

		VK.api("wall.savePost", params, new AsyncCallbackWrapper<String>(callback) {
			@Override
			protected String parseResponse(JSONValue response) throws NoSuchJSONValueException {
				return JSONUtil.getString(response, "post_hash");
			}
		});
	}

}
