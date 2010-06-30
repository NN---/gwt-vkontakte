package ru.vkontakte.gwt.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ru.vkontakte.gwt.client.callback.InvalidResponseException;
import ru.vkontakte.gwt.client.callback.VKErrorException;
import ru.vkontakte.gwt.client.model.Case;
import ru.vkontakte.gwt.client.model.Group;
import ru.vkontakte.gwt.client.model.Profile;
import ru.vkontakte.gwt.client.model.ProfileFields;
import ru.vkontakte.gwt.client.model.Settings;
import ru.vkontakte.gwt.client.test.ExpectFailure;
import ru.vkontakte.gwt.client.test.ExpectListResult;
import ru.vkontakte.gwt.client.test.ExpectNothing;
import ru.vkontakte.gwt.client.test.ExpectResult;
import ru.vkontakte.gwt.client.test.ExpectSuccess;

import com.google.gwt.json.client.JSONParser;

public class GwtTestVKUser extends AbstractVkApiTest {
	private static String INVAID_RESPONSE = "{\"somefield\":\"somevalue\"}";
	private String ERROR_RESPONSE = "{'error': {" + "'error_code': 4,"
			+ "'error_msg': 'Incorrect signature'" + " }}";

	private static String IS_APP_USER_TRUE_RESPONSE = "{\"response\":\"1\"}";
	private static String IS_APP_USER_FALSE_RESPONSE = "{\"response\":\"0\"}";

	private Long[] uids = new Long[] {1L, 6492L};
	private static String GET_PROFILES_RESPONSE =
		"{\"response\":[{\"uid\":\"1\",\"first_name\":\"Павел\",\"last_name\":\"Дуров\"," +
			"\"photo\":\"http:\\/\\/cs109.vkontakte.ru\\/u00001\\/c_df2abf56.jpg\"}," +
			"{\"uid\":\"6492\",\"first_name\":\"Andrew\",\"last_name\":\"Rogozov\"," +
			"\"photo\":\"http:\\/\\/cs537.vkontakte.ru\\/u06492\\/c_28629f1d.jpg\"}]}";
	
	private static String GET_USER_BALANCE_RESPONSE = "{\"response\":350}";
	private static String GET_USER_SETTINGS_RESPONSE = "{\"response\":15}";
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
		mockVKImpl.setExpectedApiMethod("getProfiles");

		mockVKImpl.setApiResponse(JSONParser.parse(GET_PROFILES_RESPONSE));
		VKUser.getProfiles(Arrays.asList(uids), ProfileFields.ALL, Case.NOM, new ExpectSuccess<List<Profile>>() {			
			public void onSuccess(List<Profile> profiles) {
				assertEquals(profiles.size(), 2);
				Profile profile = profiles.get(1);
				assertEquals(new Long(6492L), profile.getUid());
				assertEquals("Andrew", profile.getFirstName());
				assertEquals("Rogozov", profile.getLastName());
				assertEquals("http://cs537.vkontakte.ru/u06492/c_28629f1d.jpg", profile.getPhoto());
			}
		});

		VKUser.getProfiles(Arrays.asList(uids), ProfileFields.ALL, null, new ExpectSuccess<List<Profile>>() {			
			public void onSuccess(List<Profile> profiles) {
			}
		});

		VKUser.getProfiles(Arrays.asList(uids), null, Case.NOM, new ExpectSuccess<List<Profile>>() {			
			public void onSuccess(List<Profile> profiles) {
			}
		});

		try {
			VKUser.getProfiles(null, ProfileFields.ALL, null, new ExpectNothing<List<Profile>>());
			fail();
		} catch (IllegalArgumentException ex) {
			// expected
		} catch (Throwable t) {
			fail();
		}
		
		try {
			List<Long> emptyList = Collections.emptyList();
			VKUser.getProfiles(emptyList, ProfileFields.ALL, null, new ExpectNothing<List<Profile>>());
			fail();
		} catch (IllegalArgumentException ex) {			
		}

		mockVKImpl.setApiResponse(JSONParser.parse(ERROR_RESPONSE));
		VKUser.getProfiles(Arrays.asList(uids), ProfileFields.ALL, Case.NOM, new ExpectFailure<List<Profile>>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parse(INVAID_RESPONSE));
		VKUser.getProfiles(Arrays.asList(uids), ProfileFields.ALL, Case.NOM, new ExpectFailure<List<Profile>>(InvalidResponseException.class));		
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
		mockVKImpl.setExpectedApiMethod("getUserSettings");

		mockVKImpl.setApiResponse(JSONParser.parse(GET_USER_SETTINGS_RESPONSE));
		VKUser.getUserSettings(new ExpectSuccess<Settings>() {
			public void onSuccess(Settings settings) {
				assertEquals(new Long(15L), settings.getValue());
			}
		});

		mockVKImpl.setApiResponse(JSONParser.parse(ERROR_RESPONSE));
		VKUser.getUserSettings(new ExpectFailure<Settings>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parse(INVAID_RESPONSE));
		VKUser.getUserSettings(new ExpectFailure<Settings>(InvalidResponseException.class));
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
