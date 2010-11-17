package ru.vkontakte.gwt.client;

import java.util.ArrayList;
import java.util.List;

import ru.vkontakte.gwt.client.callback.InvalidResponseException;
import ru.vkontakte.gwt.client.callback.VKErrorException;
import ru.vkontakte.gwt.client.model.City;
import ru.vkontakte.gwt.client.model.Country;
import ru.vkontakte.gwt.client.test.ExpectFailure;
import ru.vkontakte.gwt.client.test.ExpectSuccess;

import com.google.gwt.json.client.JSONParser;

public class GwtTestVKGeo extends AbstractVkApiTest {
	private static final String RESPONSE = "{\"response\":" +
			"[{\"cid\":\"1\",\"name\":\"name1\"}," +
			"{\"cid\":\"2\",\"name\":\"name2\"}]}";
	
	public void testGetCities() throws Exception {
		mockVKImpl.setExpectedApiMethod("getCities");

		ArrayList<Long> cityIds = new ArrayList<Long>();
		cityIds.add(1L);
		cityIds.add(2L);

		mockVKImpl.setApiResponse(JSONParser.parseStrict(RESPONSE));						
		VKGeo.getCities(cityIds, new ExpectSuccess<List<City>>() {
			public void onSuccess(List<City> cities) {
				assertEquals(2, cities.size());
				City city = cities.get(1);
				assertEquals(new Long(2L), city.getId());
				assertEquals("name2", city.getName());
			}
		});
		
		mockVKImpl.setApiResponse(JSONParser.parseStrict(ERROR_RESPONSE));
		VKGeo.getCities(cityIds, new ExpectFailure<List<City>>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(INVAID_RESPONSE));
		VKGeo.getCities(cityIds, new ExpectFailure<List<City>>(InvalidResponseException.class));
	}
	
	public void testGetCountries() throws Exception {
		mockVKImpl.setExpectedApiMethod("getCountries");

		ArrayList<Long> countryIds = new ArrayList<Long>();
		countryIds.add(1L);
		countryIds.add(2L);

		mockVKImpl.setApiResponse(JSONParser.parseStrict(RESPONSE));						
		VKGeo.getCountries(countryIds, new ExpectSuccess<List<Country>>() {
			public void onSuccess(List<Country> countries) {
				assertEquals(2, countries.size());
				Country country = countries.get(1);
				assertEquals(new Long(2L), country.getId());
				assertEquals("name2", country.getName());
			}
		});
		
		mockVKImpl.setApiResponse(JSONParser.parseStrict(ERROR_RESPONSE));
		VKGeo.getCountries(countryIds, new ExpectFailure<List<Country>>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parseStrict(INVAID_RESPONSE));
		VKGeo.getCountries(countryIds, new ExpectFailure<List<Country>>(InvalidResponseException.class));		
	}

}
