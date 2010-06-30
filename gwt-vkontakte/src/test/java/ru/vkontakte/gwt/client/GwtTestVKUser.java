package ru.vkontakte.gwt.client;

import java.util.List;

import ru.vkontakte.gwt.client.VKUser;
import ru.vkontakte.gwt.client.callback.InvalidResponseException;
import ru.vkontakte.gwt.client.callback.VKErrorException;
import ru.vkontakte.gwt.client.model.Group;
import ru.vkontakte.gwt.client.test.ExpectFailure;
import ru.vkontakte.gwt.client.test.ExpectListResult;
import ru.vkontakte.gwt.client.test.ExpectResult;
import ru.vkontakte.gwt.client.test.ExpectSuccess;

import com.google.gwt.json.client.JSONParser;

public class GwtTestVKUser extends AbstractVkApiTest {
	private static String INVAID_RESPONSE = "{\"somefield\":\"somevalue\"}";
	private String ERROR_RESPONSE = "{'error': {" + "'error_code': 4,"
			+ "'error_msg': 'Incorrect signature'" + " }}";

	private static String IS_APP_USER_TRUE_RESPONSE = "{\"response\":\"1\"}";
	private static String IS_APP_USER_FALSE_RESPONSE = "{\"response\":\"0\"}";

	private static String GET_USER_BALANCE_RESPONSE = "{\"response\":350}";
	private static String GET_FRIENDS_RESPONSE = "{\"response\":[15221,17836,19194]}";
	private static String GET_APP_FRIENDS_RESPONSE = "{\"response\":[15221,17836,19194]}";
	private static final String GET_GROUPS_RESPONSE = "{\"response\":[1,55,103,354]}";
	private static final String GET_GROUPS_FULL_RESPONSE = 
		"{\"response\":[" +
			"{\"gid\":1,\"name\":\"FLASH API\"," +
			 	"\"photo\":\"http:\\/\\/cs1202.vkontakte.ru\\/g00001\\/b_7cc2919b.jpg\",\"is_closed\":\"0\"}," +
			"{\"gid\":55,\"name\":\"Вики ВКонтакте\","+
				"\"photo\":\"http:\\/\\/vkontakte.ru\\/images\\/question_100.gif\",\"is_closed\":\"0\"}," +
			"{\"gid\":103,\"name\":\"East\"," +
				"\"photo\":\"http:\\/\\/vkontakte.ru\\/images\\/question_100.gif\",\"is_closed\":\"0\"}," +
			"{\"gid\":354,\"name\":\"ВК\"," +
				"\"photo\":\"http:\\/\\/cs80.vkontakte.ru\\/g00354\\/b_a2b5680.jpg\",\"is_closed\":\"1\"}," +
			"{\"gid\":400,\"name\":\"VKontakte Coders\"," +
				"\"photo\":\"http:\\/\\/vkontakte.ru\\/images\\/question_100.gif\",\"is_closed\":\"1\"}" +
		"]}";

	public void testIsAppUser() {
		mockVKImpl.setExpectedApiMethod("isAppUser");

		mockVKImpl.setApiResponse(JSONParser.parse(IS_APP_USER_TRUE_RESPONSE));
		VKUser.isAppUser(new ExpectResult<Boolean>(true));

		mockVKImpl.setApiResponse(JSONParser.parse(IS_APP_USER_FALSE_RESPONSE));
		VKUser.isAppUser(new ExpectResult<Boolean>(false));

		mockVKImpl.setApiResponse(JSONParser.parse(ERROR_RESPONSE));
		VKUser.isAppUser(new ExpectFailure<Boolean>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parse(INVAID_RESPONSE));
		VKUser.isAppUser(new ExpectFailure<Boolean>(InvalidResponseException.class));
	}

	public void testGetProfiles() {
		fail();
	}
	
	public void testGetFriends() {
		mockVKImpl.setExpectedApiMethod("getFriends");

		mockVKImpl.setApiResponse(JSONParser.parse(GET_FRIENDS_RESPONSE));
		VKUser.getFriends(new ExpectListResult<Long>(15221L,17836L,19194L));

		mockVKImpl.setApiResponse(JSONParser.parse(ERROR_RESPONSE));
		VKUser.getFriends(new ExpectFailure<List<Long>>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parse(INVAID_RESPONSE));
		VKUser.getFriends(new ExpectFailure<List<Long>>(InvalidResponseException.class));
	}
	
	public void testGetAppFriends() {
		mockVKImpl.setExpectedApiMethod("getAppFriends");

		mockVKImpl.setApiResponse(JSONParser.parse(GET_APP_FRIENDS_RESPONSE));
		VKUser.getAppFriends(new ExpectListResult<Long>(15221L,17836L,19194L));

		mockVKImpl.setApiResponse(JSONParser.parse(ERROR_RESPONSE));
		VKUser.getAppFriends(new ExpectFailure<List<Long>>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parse(INVAID_RESPONSE));
		VKUser.getAppFriends(new ExpectFailure<List<Long>>(InvalidResponseException.class));
	}
	
	public void testGetUserBalance() {
		mockVKImpl.setExpectedApiMethod("getUserBalance");

		mockVKImpl.setApiResponse(JSONParser.parse(GET_USER_BALANCE_RESPONSE));
		VKUser.getUserBalance(new ExpectResult<Long>(350L));

		mockVKImpl.setApiResponse(JSONParser.parse(ERROR_RESPONSE));
		VKUser.getUserBalance(new ExpectFailure<Long>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parse(INVAID_RESPONSE));
		VKUser.getUserBalance(new ExpectFailure<Long>(InvalidResponseException.class));
	}

	public void testGetUserSettings() {
		fail();
	}

	public void testGetGroups() {
		mockVKImpl.setExpectedApiMethod("getGroups");

		mockVKImpl.setApiResponse(JSONParser.parse(GET_GROUPS_RESPONSE));
		VKUser.getGroups(new ExpectListResult<Long>(1L,55L,103L,354L));

		mockVKImpl.setApiResponse(JSONParser.parse(ERROR_RESPONSE));
		VKUser.getGroups(new ExpectFailure<List<Long>>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parse(INVAID_RESPONSE));
		VKUser.getGroups(new ExpectFailure<List<Long>>(InvalidResponseException.class));		
	}
	
	public void testGetGroupsFull() {
		mockVKImpl.setExpectedApiMethod("getGroupsFull");

		mockVKImpl.setApiResponse(JSONParser.parse(GET_GROUPS_FULL_RESPONSE));
		VKUser.getGroupsFull(new ExpectSuccess<List<Group>>() {			
			public void onSuccess(List<Group> groups) {
				assertEquals(groups.size(), 5);
				Group group = groups.get(2);
				assertEquals(new Long(103L), group.getId());
				assertEquals("East", group.getName());
				assertEquals("http://vkontakte.ru/images/question_100.gif", group.getPhoto());
				assertEquals(false, group.isClosed());				
			}
		});

		mockVKImpl.setApiResponse(JSONParser.parse(ERROR_RESPONSE));
		VKUser.getGroupsFull(new ExpectFailure<List<Group>>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parse(INVAID_RESPONSE));
		VKUser.getGroupsFull(new ExpectFailure<List<Group>>(InvalidResponseException.class));		
	}
}
