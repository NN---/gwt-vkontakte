package ru.vkontakte.gwt.client.model;

import static ru.vkontakte.gwt.client.model.ProfileFields.*;
import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONValue;

public class Profile {
	private Long uid;
	private String firstName;
	private String lastName;
	private String nickname;
	private Sex sex;
	private String birthdate;
	private Long city;
	private Long country;
	private String timezone;
	private String photo;
	private String photoMedium;
	private String photoBig; 
	private String homePhone;
	private String mobilePhone; 
	private Boolean hasMobile;
	private String rate;
	private String university;
	private String universityName;
	private String faculty;
	private String facultyName;
	private String graduation;
	
	public Profile(JSONValue value) throws NoSuchJSONValueException {
		// Mandatory fields
		uid = JSONUtil.getLong(value, UID.getStringId());
		firstName = JSONUtil.getString(value, FIRST_NAME.getStringId());
		lastName = JSONUtil.getString(value, LAST_NAME.getStringId());
		
		// Optional fields
		nickname = JSONUtil.tryGetString(value, NICKNAME.getStringId());
		sex = JSONUtil.tryGetSex(value, SEX.getStringId());
		birthdate = JSONUtil.tryGetString(value, BIRTHDATE.getStringId());
		city = JSONUtil.tryGetLong(value, CITY.getStringId());
		country = JSONUtil.tryGetLong(value, COUNTRY.getStringId());
		timezone = JSONUtil.tryGetString(value, TIMEZONE.getStringId());
		photo = JSONUtil.tryGetString(value, PHOTO.getStringId());
		photoMedium = JSONUtil.tryGetString(value, PHOTO_MEDIUM.getStringId());
		photoBig = JSONUtil.tryGetString(value, PHOTO_BIG.getStringId());
		homePhone = JSONUtil.tryGetString(value, HOME_PHONE);
		mobilePhone = JSONUtil.tryGetString(value, MOBILE_PHONE);
		hasMobile = JSONUtil.tryGetBoolean(value, HAS_MOBILE.getStringId());
		rate = JSONUtil.tryGetString(value, RATE.getStringId());
		university = JSONUtil.tryGetString(value, UNIVERSITY);
		universityName = JSONUtil.tryGetString(value, UNIVERSITY_NAME);
		faculty = JSONUtil.tryGetString(value, FACULTY);
		facultyName = JSONUtil.tryGetString(value, FACULTY_NAME);
		graduation = JSONUtil.tryGetString(value, GRADUATION);
	}

	public Long getUid() {
		return uid;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getNickname() {
		return nickname;
	}

	public Sex getSex() {
		return sex;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public Long getCity() {
		return city;
	}

	public Long getCountry() {
		return country;
	}

	public String getTimezone() {
		return timezone;
	}

	public String getPhoto() {
		return photo;
	}

	public String getPhotoMedium() {
		return photoMedium;
	}

	public String getPhotoBig() {
		return photoBig;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public Boolean hasMobile() {
		return hasMobile;
	}

	public String getRate() {
		return rate;
	}

	public String getUniversity() {
		return university;
	}

	public String getUniversityName() {
		return universityName;
	}

	public String getFaculty() {
		return faculty;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public String getGraduation() {
		return graduation;
	}
}
