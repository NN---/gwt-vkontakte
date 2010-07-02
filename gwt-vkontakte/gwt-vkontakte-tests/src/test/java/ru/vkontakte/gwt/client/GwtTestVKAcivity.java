package ru.vkontakte.gwt.client;

import java.util.Date;
import java.util.List;

import ru.vkontakte.gwt.client.callback.InvalidResponseException;
import ru.vkontakte.gwt.client.callback.VKErrorException;
import ru.vkontakte.gwt.client.model.Activity;
import ru.vkontakte.gwt.client.model.News;
import ru.vkontakte.gwt.client.test.ExpectFailure;
import ru.vkontakte.gwt.client.test.ExpectResult;
import ru.vkontakte.gwt.client.test.ExpectSuccess;

import com.google.gwt.json.client.JSONParser;

public class GwtTestVKAcivity extends AbstractVkApiTest {

	private static final String GET_RESPONSE = "{\"response\":{\"time\":1268215406,\"activity\":\"=)\",\"id\":160773101}}"; 
	private static final String HISTORY_RESPONSE = 
		"{\"response\":[" +
			"{\"id\":160773408,\"text\":\"&quot;.&quot;.\",\"created\":1268216019}," +
			"{\"id\":159895572,\"text\":\"When you lose small mind - you free your life.\",\"created\":1266422250}" +
		"]}";
	private static final String NEWS_RESPONSE = 
		"{\"response\":" +
			"[3446," +
				"{\"uid\":918388,\"timestamp\":1268211949,\"text\":\"Не верьте сказкам.\"}," +
				"{\"uid\":56338469,\"timestamp\":1268211801,\"text\":\"-Миром правят они.  -Кто они?  -Они это все те кто хочет быть ими...(с).\"}" +
			"]}";
	
	public void testGet() {
		mockVKImpl.setExpectedApiMethod("activity.get");

		mockVKImpl.setApiResponse(JSONParser.parse(GET_RESPONSE));				
		VKActivity.get(131124L, new ExpectSuccess<Activity>() {
			public void onSuccess(Activity activity) {
				assertEquals(new Long(160773101L), activity.getId());
				assertEquals(new Long(131124L), activity.getUserId());
				assertEquals(1268215406L, activity.getTime().getTime());
				assertEquals("=)", activity.getText());
			}
		});
		
		mockVKImpl.setApiResponse(JSONParser.parse(ERROR_RESPONSE));
		VKActivity.get(131124L, new ExpectFailure<Activity>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parse(INVAID_RESPONSE));
		VKActivity.get(131124L, new ExpectFailure<Activity>(InvalidResponseException.class));
	}
	
	public void testSet() {
		mockVKImpl.setExpectedApiMethod("activity.set");

		mockVKImpl.setApiResponse(JSONParser.parse(LONG_RESPONSE));				
		VKActivity.set("=)", new ExpectResult<Long>(EXPECTED_LONG));
		
		mockVKImpl.setApiResponse(JSONParser.parse(ERROR_RESPONSE));
		VKActivity.set("=)", new ExpectFailure<Long>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parse(INVAID_RESPONSE));
		VKActivity.set("=)", new ExpectFailure<Long>(InvalidResponseException.class));
	}
	
	public void testGetHistory() {
		mockVKImpl.setExpectedApiMethod("activity.getHistory");

		mockVKImpl.setApiResponse(JSONParser.parse(HISTORY_RESPONSE));				
		VKActivity.getHistory(new ExpectSuccess<List<Activity>>() {
			public void onSuccess(List<Activity> acivities) {
				assertEquals(2, acivities.size());
				Activity a = acivities.get(0);
				assertEquals(new Long(160773408L), a.getId());
				assertEquals("\".\".", a.getText());
				assertEquals(1268216019L, a.getTime().getTime());
			}
		});
		
		mockVKImpl.setApiResponse(JSONParser.parse(ERROR_RESPONSE));
		VKActivity.getHistory(new ExpectFailure<List<Activity>>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parse(INVAID_RESPONSE));
		VKActivity.getHistory(new ExpectFailure<List<Activity>>(InvalidResponseException.class));
	}

	public void testGetHistoryById() {
		mockVKImpl.setExpectedApiMethod("activity.getHistory");

		mockVKImpl.setApiResponse(JSONParser.parse(HISTORY_RESPONSE));				
		VKActivity.getHistory(111L, new ExpectSuccess<List<Activity>>() {
			public void onSuccess(List<Activity> acivities) {
				assertEquals(2, acivities.size());
				Activity a = acivities.get(0);
				assertEquals(new Long(160773408L), a.getId());
				assertEquals("\".\".", a.getText());
				assertEquals(1268216019L, a.getTime().getTime());
				assertEquals(new Long(111L), a.getUserId());
			}
		});
		
		mockVKImpl.setApiResponse(JSONParser.parse(ERROR_RESPONSE));
		VKActivity.getHistory(111L, new ExpectFailure<List<Activity>>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parse(INVAID_RESPONSE));
		VKActivity.getHistory(111L, new ExpectFailure<List<Activity>>(InvalidResponseException.class));
	}

	public void testDeleteHistoryItem() {
		mockVKImpl.setExpectedApiMethod("activity.deleteHistoryItem");

		mockVKImpl.setApiResponse(JSONParser.parse(TRUE_RESPONSE));				
		VKActivity.deleteHistoryItem(111L, new ExpectResult<Boolean>(Boolean.TRUE));
		
		mockVKImpl.setApiResponse(JSONParser.parse(ERROR_RESPONSE));
		VKActivity.deleteHistoryItem(111L, new ExpectFailure<Boolean>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parse(INVAID_RESPONSE));
		VKActivity.deleteHistoryItem(111L, new ExpectFailure<Boolean>(InvalidResponseException.class));
	}
	
	public void testGetNewsTimestamp() {
		mockVKImpl.setExpectedApiMethod("activity.getNews");

		mockVKImpl.setApiResponse(JSONParser.parse(NEWS_RESPONSE));				
		VKActivity.getNews(new Date(), new ExpectSuccess<News>() {
			public void onSuccess(News news) {
				assertEquals(new Long(3446L), news.getCount());

				List<Activity> acivities = news.getActivities();
				assertEquals(2, acivities.size());
				
				Activity a = acivities.get(0);
				assertEquals("Не верьте сказкам.", a.getText());
				assertEquals(1268211949L, a.getTime().getTime());
				assertEquals(new Long(918388L), a.getUserId());
			}
		});
		
		mockVKImpl.setApiResponse(JSONParser.parse(ERROR_RESPONSE));
		VKActivity.getNews(new Date(), new ExpectFailure<News>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parse(INVAID_RESPONSE));
		VKActivity.getNews(new Date(), new ExpectFailure<News>(InvalidResponseException.class));
	}	

	public void testGetNewsOffsetCount() {
		mockVKImpl.setExpectedApiMethod("activity.getNews");

		mockVKImpl.setApiResponse(JSONParser.parse(NEWS_RESPONSE));				
		VKActivity.getNews(111L, 2L, new ExpectSuccess<News>() {
			public void onSuccess(News news) {
				assertEquals(new Long(3446L), news.getCount());

				List<Activity> acivities = news.getActivities();
				assertEquals(2, acivities.size());
				
				Activity a = acivities.get(0);
				assertEquals("Не верьте сказкам.", a.getText());
				assertEquals(1268211949L, a.getTime().getTime());
				assertEquals(new Long(918388L), a.getUserId());
			}
		});
		
		mockVKImpl.setApiResponse(JSONParser.parse(ERROR_RESPONSE));
		VKActivity.getNews(111L, 2L, new ExpectFailure<News>(VKErrorException.class));

		mockVKImpl.setApiResponse(JSONParser.parse(INVAID_RESPONSE));
		VKActivity.getNews(111L, 2L, new ExpectFailure<News>(InvalidResponseException.class));
	}	

}
