package ru.vkontakte.gwt.client;


import ru.vkontakte.gwt.client.callback.ApiCallback;
import ru.vkontakte.gwt.client.callback.ObserverEventHandler;
import ru.vkontakte.gwt.client.impl.VKAuthImpl;
import ru.vkontakte.gwt.client.impl.VKImpl;
import ru.vkontakte.gwt.client.impl.VKObserverImpl;
import ru.vkontakte.gwt.client.model.ObserverEvent;
import ru.vkontakte.gwt.client.model.Settings;

import com.google.gwt.json.client.JSONObject;

public class VK {
	private static VKImpl impl;
	
	private VK() {
		// Instantiation of this class is not allowed
		throw new AssertionError();
	}
	
	public static void init(int apiId) {
		getImpl().init(apiId);
	}
	
	public static void api(String method, JSONObject param, final ApiCallback callback) {
		getImpl().api(method, param, callback);
	}

	static VKImpl getImpl() {
		if (impl == null) impl = new VKImpl();
		return impl;
	}

	static void setImpl(VKImpl impl) {
		VK.impl = impl;
	}
	
	public static class Auth {
		private static VKAuthImpl authImpl;
		
		public static void login(ApiCallback callback) {
			login(callback, null);
		}
		
		public static void login(ApiCallback callback, Settings settings) {
			getImpl().login(callback, settings);
		}
		
		/**
		 * В текущей реализации (на 15.09.2011) openAPI этот метод просто вызывает revokeGrants.
		 * Несмотря на это в официальной документации значится:
		 * В случае успеха завершает сессию пользователя внутри платформы Open API и выполняет выход пользователя из ВКонтакте.
		 * Данный метод генерирует событие auth.logout, на которое Вы можете подписаться, используя методы наблюдения за событиями, предоставляемые объектом VK.Observer.
		 */
		public static void logout() {
			logout(null);
		}
		
		/**
		 * В текущей реализации (на 15.09.2011) openAPI этот метод просто вызывает revokeGrants
		 * Несмотря на это в официальной документации значится:
		 * В случае успеха завершает сессию пользователя внутри платформы Open API и выполняет выход пользователя из ВКонтакте.
		 * Данный метод генерирует событие auth.logout, на которое Вы можете подписаться, используя методы наблюдения за событиями, предоставляемые объектом VK.Observer.
		 */
		public static void logout(ApiCallback callback) {
			getImpl().logout(callback);
		}
			
		public static void revokeGrants(ApiCallback callback) {
			getImpl().revokeGrants(callback);
		}
		
		public static void getLoginStatus(ApiCallback callback) {
			getImpl().getLoginStatus(callback);
		}
		
		static VKAuthImpl getImpl() {
			if (authImpl == null) authImpl = new VKAuthImpl();
			return authImpl;
		}
	}
	
	public static class Observer {
		private static VKObserverImpl observerImpl;
		
		public static void subscribe(ObserverEvent event, ObserverEventHandler callback) {
			getImpl().subscribe(event.toString(), callback);
		}
		
		public static void unsubscribe(ObserverEvent event, ObserverEventHandler callback) {
			getImpl().unsubscribe(event.toString(), callback);
		}
		
		static VKObserverImpl getImpl() {
			if (observerImpl == null) observerImpl = new VKObserverImpl();
			return observerImpl;
		}
	}
}
