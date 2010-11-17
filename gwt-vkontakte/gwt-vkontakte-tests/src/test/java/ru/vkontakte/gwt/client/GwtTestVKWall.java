package ru.vkontakte.gwt.client;

import ru.vkontakte.gwt.client.callback.InvalidResponseException;
import ru.vkontakte.gwt.client.callback.VKErrorException;
import ru.vkontakte.gwt.client.test.ExpectFailure;
import ru.vkontakte.gwt.client.test.ExpectSuccess;

import com.google.gwt.json.client.JSONParser;

public class GwtTestVKWall extends AbstractVkApiTest {
	
	private static final String EXPECTED_SERVER_URL = "http://cs9231.vkontakte.ru/upload.php" +
				"?act=do_add_posted&mid=6492&hash=284b5d004f5524e8b781cc9ddfb75de1" +
				"&rhash=5133711120e3156dbb8f4cb2069fb29f&swfupload=1";

	private static final String GET_PHOTO_UPLOAD_SERVER = "{\"response\":" +
				"{\"upload_url\":\"http:\\/\\/cs9231.vkontakte.ru\\/upload.php" +
					"?act=do_add_posted&mid=6492&hash=284b5d004f5524e8b781cc9ddfb75de1" +
					"&rhash=5133711120e3156dbb8f4cb2069fb29f&swfupload=1\"}}";

	private static final String EXPECTED_HASH = "264b5d004f5524e8c781cb9dafb75de1";

	private static final String SAVE_POST_RESPONSE = "{\"response\":" +
			"{\"post_hash\":\"" + EXPECTED_HASH + "\"," +
			"\"photo_src\":\"http:\\/\\/cs9231.vkontakte.ru\\/u06492\\/a_7b9c2b04.jpg\"}}";

	
	public void testGetPhotoUploadServer() throws Exception {
		mockVKImpl.setExpectedApiMethod("wall.getPhotoUploadServer");

		mockVKImpl.setApiResponse(JSONParser.parseStrict(GET_PHOTO_UPLOAD_SERVER));				
		VKWall.getPhotoUploadServer(new ExpectSuccess<String>() {
			public void onSuccess(String serverUrl) {
				assertEquals(EXPECTED_SERVER_URL, serverUrl);
			}
		});
		
		mockVKImpl.setApiResponse(JSONParser.parseStrict(ERROR_RESPONSE));
		VKWall.getPhotoUploadServer(new ExpectFailure<String>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(INVAID_RESPONSE));
		VKWall.getPhotoUploadServer(new ExpectFailure<String>(InvalidResponseException.class));
	}
	
	public void testSavePost() throws Exception {
		mockVKImpl.setExpectedApiMethod("wall.savePost");

		mockVKImpl.setApiResponse(JSONParser.parseStrict(SAVE_POST_RESPONSE));				
		VKWall.savePost(1L, 1L, "Text", new ExpectSuccess<String>() {
			public void onSuccess(String photoHash) {
				assertEquals(EXPECTED_HASH, photoHash);
			}
		});
		
		mockVKImpl.setApiResponse(JSONParser.parseStrict(ERROR_RESPONSE));
		VKWall.savePost(1L, 1L, "Text", new ExpectFailure<String>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(INVAID_RESPONSE));
		VKWall.savePost(1L, 1L, "Text", new ExpectFailure<String>(InvalidResponseException.class));		
	}
}
