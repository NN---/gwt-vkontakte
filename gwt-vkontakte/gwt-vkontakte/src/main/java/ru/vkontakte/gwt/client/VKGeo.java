package ru.vkontakte.gwt.client;

import java.util.List;

import ru.vkontakte.gwt.client.callback.ListResponseCallbackWrapper;
import ru.vkontakte.gwt.client.model.City;
import ru.vkontakte.gwt.client.model.Country;
import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Географические объекты 
 */	
public class VKGeo {
	/**
	 * возвращает информацию о городах по их id.
	 */
	public static void getCities(List<Long> cityIds, AsyncCallback<List<City>> callback) {
		JSONObject params = new JSONObject();
		params.put("cids", JSONUtil.joinAsStrings(cityIds, ","));
		VK.api("getCities", params, new ListResponseCallbackWrapper<City>(callback) {
			@Override
			protected City convertValue(JSONValue value) throws NoSuchJSONValueException {
				return new City(value);
			}
		});			
	}
	
	/** 
	 * возвращает информацию о странах по их id.
	 */
	public static void getCountries (List<Long> countryIds, AsyncCallback<List<Country>> callback) {
		JSONObject params = new JSONObject();
		params.put("cids", JSONUtil.joinAsStrings(countryIds, ","));
		VK.api("getCountries", params, new ListResponseCallbackWrapper<Country>(callback) {
			@Override
			protected Country convertValue(JSONValue value) throws NoSuchJSONValueException {
				return new Country(value);
			}
		});					
	}
}
