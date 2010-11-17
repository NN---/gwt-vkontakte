package ru.vkontakte.gwt.client.model;

import ru.vkontakte.gwt.client.VKTestConstants;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONParser;
import com.google.gwt.junit.client.GWTTestCase;

public class GwtTestProfile extends GWTTestCase{
	
	private String json = "{" +
			"uid:1, " +
			"first_name:\"Name\", " +
			"last_name:\"Surname\", " +
			"nickname:\"Nick\", " +
			"sex:0, " +
			"bdate:\"21.9\", " +
			"city:2, " +
			"country:3, " +
			"timezone:\"TZ\", " +
			"photo:\"url\", " +
			"photo_medium:\"urlm\", " +
			"photo_big:\"urlb\", " +
			"home_phone:\"666\", " +
			"mobile_phone:\"777\", " +
			"has_mobile:1, " +
			"rate:\"XXX\", " +
			"university:\"NSU\", " +
			"university_name:\"NSU name\", " +
			"faculty:\"Faculty\", " +
			"faculty_name:\"FName\", " +
			"graduation:\"Graduation\"}";
	
	private String noFirstName = "{" +
			"last_name:\"Surname\", " +
			"nickname:\"Nick\"}";

	public void testParseProfile() {
		try {
			Profile p = new Profile(JSONParser.parseStrict(json));
			assertEquals(p.getUid(), new Long(1L));
			assertEquals(p.getFirstName(), "Name");
			assertEquals(p.getLastName(), "Surname");
			assertEquals(p.getNickname(), "Nick");
			assertEquals(p.getSex(), Sex.NOT_SPECIFIED);
			assertEquals(p.getBirthdate(), "21.9");
			assertEquals(p.getCity(), new Long(2L));
			assertEquals(p.getCountry(), new Long(3L));
			assertEquals(p.getTimezone(), "TZ");
			assertEquals(p.getPhoto(), "url");
			assertEquals(p.getPhotoBig(), "urlb");
			assertEquals(p.getPhotoMedium(), "urlm");
			assertEquals(p.getHomePhone(), "666");
			assertEquals(p.getMobilePhone(), "777");
			assertEquals(p.hasMobile(), Boolean.TRUE);
			assertEquals(p.getRate(), "XXX");
			assertEquals(p.getUniversity(), "NSU");
			assertEquals(p.getUniversityName(), "NSU name");
			assertEquals(p.getFaculty(), "Faculty");
			assertEquals(p.getFacultyName(), "FName");
			assertEquals(p.getGraduation(), "Graduation");				
		} catch (NoSuchJSONValueException e) {
			fail();
		}
		
		try {
			new Profile(JSONParser.parseStrict(noFirstName));
			fail();
		} catch (NoSuchJSONValueException e) {
			// expected
		}		
	}

	@Override
	public String getModuleName() {
	
		return VKTestConstants.TEST_MODULE_NAME;
	}
}
