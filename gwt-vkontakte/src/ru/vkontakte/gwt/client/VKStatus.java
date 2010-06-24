package ru.vkontakte.gwt.client;

import java.util.Date;

import ru.vkontakte.gwt.client.model.Status;

/**
 * 	Личный статус
 */
public class VKStatus {
	/**
	 * возвращает текущий статус пользователя. 
	 */
	public static Status get(Long uid){
		return null;
	}
	
	/**
	 * устанавливает статус текущего пользователя.
	 */
	public static void set(String status){
	}
	
	/**
	 * возвращает историю статусов.
	 */
	public static Status[] getHistory() {
		return null;		
	}
	
	/** 
	 * удаляет элемент истории статусов у текущего пользователя.
	 */
	public static void deleteHistoryItem(Long statusId) {		
	}
	
	/**
	 * возвращает обновления статусов друзей.
	 */
	public static Status[] getNews(Date fromDate) {
		return null;				
	}

	/**
	 * возвращает обновления статусов друзей.
	 */
	public static Status[] getNews(Long offset, Long count) {
		return null;				
	}
}
