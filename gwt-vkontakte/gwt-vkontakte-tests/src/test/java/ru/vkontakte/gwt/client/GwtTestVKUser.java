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
	private Long[] uids = new Long[] {1L, 6492L};
	private static String GET_PROFILES_RESPONSE =
		"{\"response\":[{\"uid\":\"1\",\"first_name\":\"Павел\",\"last_name\":\"Дуров\"," +
			"\"photo\":\"http:\\/\\/cs109.vkontakte.ru\\/u00001\\/c_df2abf56.jpg\"}," +
			"{\"uid\":\"6492\",\"first_name\":\"Andrew\",\"last_name\":\"Rogozov\"," +
			"\"photo\":\"http:\\/\\/cs537.vkontakte.ru\\/u06492\\/c_28629f1d.jpg\"}]}";
	
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

		mockVKImpl.setApiResponse(JSONParser.parseStrict(TRUE_RESPONSE));
		VKUser.isAppUser(new ExpectResult<Boolean>(true));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(FALSE_RESPONSE));
		VKUser.isAppUser(new ExpectResult<Boolean>(false));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(ERROR_RESPONSE));
		VKUser.isAppUser(new ExpectFailure<Boolean>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(INVAID_RESPONSE));
		VKUser.isAppUser(new ExpectFailure<Boolean>(InvalidResponseException.class));
	}

	public void testGetProfiles() {
		mockVKImpl.setExpectedApiMethod("getProfiles");

		mockVKImpl.setApiResponse(JSONParser.parseStrict(GET_PROFILES_RESPONSE));
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

		mockVKImpl.setApiResponse(JSONParser.parseStrict(ERROR_RESPONSE));
		VKUser.getProfiles(Arrays.asList(uids), ProfileFields.ALL, Case.NOM, new ExpectFailure<List<Profile>>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(INVAID_RESPONSE));
		VKUser.getProfiles(Arrays.asList(uids), ProfileFields.ALL, Case.NOM, new ExpectFailure<List<Profile>>(InvalidResponseException.class));		
	}
	
	public void testGetFriends() {
		mockVKImpl.setExpectedApiMethod("getFriends");

		mockVKImpl.setApiResponse(JSONParser.parseStrict(LONG_ARRAY_RESPONSE));
		VKUser.getFriends(new ExpectListResult<Long>(EXPECTED_LONG_ARRAY));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(ERROR_RESPONSE));
		VKUser.getFriends(new ExpectFailure<List<Long>>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(INVAID_RESPONSE));
		VKUser.getFriends(new ExpectFailure<List<Long>>(InvalidResponseException.class));
	}
	
	public void testGetAppFriends() {
		mockVKImpl.setExpectedApiMethod("getAppFriends");

		mockVKImpl.setApiResponse(JSONParser.parseStrict(LONG_ARRAY_RESPONSE));
		VKUser.getAppFriends(new ExpectListResult<Long>(EXPECTED_LONG_ARRAY));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(ERROR_RESPONSE));
		VKUser.getAppFriends(new ExpectFailure<List<Long>>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(INVAID_RESPONSE));
		VKUser.getAppFriends(new ExpectFailure<List<Long>>(InvalidResponseException.class));
	}
	
	public void testGetUserBalance() {
		mockVKImpl.setExpectedApiMethod("getUserBalance");

		mockVKImpl.setApiResponse(JSONParser.parseStrict(LONG_RESPONSE));
		VKUser.getUserBalance(new ExpectResult<Long>(EXPECTED_LONG));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(ERROR_RESPONSE));
		VKUser.getUserBalance(new ExpectFailure<Long>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(INVAID_RESPONSE));
		VKUser.getUserBalance(new ExpectFailure<Long>(InvalidResponseException.class));
	}

	public void testGetUserSettings() {
		mockVKImpl.setExpectedApiMethod("getUserSettings");

		mockVKImpl.setApiResponse(JSONParser.parseStrict(LONG_RESPONSE));
		VKUser.getUserSettings(new ExpectSuccess<Settings>() {
			public void onSuccess(Settings settings) {
				assertEquals(EXPECTED_LONG, settings.getValue());
			}
		});

		mockVKImpl.setApiResponse(JSONParser.parseStrict(ERROR_RESPONSE));
		VKUser.getUserSettings(new ExpectFailure<Settings>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(INVAID_RESPONSE));
		VKUser.getUserSettings(new ExpectFailure<Settings>(InvalidResponseException.class));
	}

	public void testGetGroups() {
		mockVKImpl.setExpectedApiMethod("getGroups");

		mockVKImpl.setApiResponse(JSONParser.parseStrict(LONG_ARRAY_RESPONSE));
		VKUser.getGroups(new ExpectListResult<Long>(EXPECTED_LONG_ARRAY));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(ERROR_RESPONSE));
		VKUser.getGroups(new ExpectFailure<List<Long>>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(INVAID_RESPONSE));
		VKUser.getGroups(new ExpectFailure<List<Long>>(InvalidResponseException.class));		
	}
	
	public void testGetGroupsFull() {
		mockVKImpl.setExpectedApiMethod("getGroupsFull");

		mockVKImpl.setApiResponse(JSONParser.parseStrict(GET_GROUPS_FULL_RESPONSE));
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

		mockVKImpl.setApiResponse(JSONParser.parseStrict(ERROR_RESPONSE));
		VKUser.getGroupsFull(new ExpectFailure<List<Group>>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(INVAID_RESPONSE));
		VKUser.getGroupsFull(new ExpectFailure<List<Group>>(InvalidResponseException.class));		
	}
}
