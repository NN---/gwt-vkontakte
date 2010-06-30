package ru.vkontakte.gwt.client.model;

import java.util.Arrays;
import java.util.List;

public class ProfileFields implements HasStringId {
	public static final ProfileFields UID = new ProfileFields("uid");
	public static final ProfileFields FIRST_NAME = new ProfileFields("first_name"); 
	public static final ProfileFields LAST_NAME = new ProfileFields("last_name"); 
	public static final ProfileFields NICKNAME = new ProfileFields("nickname"); 
	public static final ProfileFields SEX = new ProfileFields("sex"); 
	public static final ProfileFields BIRTHDATE = new ProfileFields("bdate"); 
	public static final ProfileFields CITY = new ProfileFields("city"); 
	public static final ProfileFields COUNTRY = new ProfileFields("country"); 
	public static final ProfileFields TIMEZONE = new ProfileFields("timezone"); 
	public static final ProfileFields PHOTO = new ProfileFields("photo"); 
	public static final ProfileFields PHOTO_MEDIUM = new ProfileFields("photo_medium"); 
	public static final ProfileFields PHOTO_BIG = new ProfileFields("photo_big"); 
	public static final ProfileFields HAS_MOBILE = new ProfileFields("has_mobile"); 
	public static final ProfileFields RATE = new ProfileFields("rate"); 
	public static final ProfileFields CONTACTS = new ProfileFields("contacts"); 
	public static final ProfileFields EDUCATION = new ProfileFields("education");

	// CONTACTS subfields
	public static final String HOME_PHONE = "home_phone"; 
	public static final String MOBILE_PHONE = "mobile_phone"; 
	
	// EDUCATION subfields
	public static final String UNIVERSITY = "university";
	public static final String UNIVERSITY_NAME = "university_name";
	public static final String FACULTY = "faculty";
	public static final String FACULTY_NAME = "faculty_name";
	public static final String GRADUATION = "graduation";
	
	public static final List<ProfileFields> ALL = Arrays.asList(new ProfileFields[] { UID,
			FIRST_NAME, LAST_NAME, NICKNAME, SEX, BIRTHDATE, CITY, COUNTRY, TIMEZONE, PHOTO,
			PHOTO_MEDIUM, PHOTO_BIG, HAS_MOBILE, RATE, CONTACTS, EDUCATION });

	private String fieldId;
	
	private ProfileFields(String fieldName) {
		this.fieldId = fieldName;
	}
		
	public String getStringId() {
		return fieldId;
	}
}
