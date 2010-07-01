package ru.vkontakte.gwt.client.util;

public class StringUtil {
	public static String escapeHTML(String str) {
		return str.replaceAll("&", "&amp;")
				.replaceAll(">", "&gt;")
				.replaceAll("<", "&lt;")
				.replaceAll("\"", "&quot;");
	}

	public static String unescapeHTML(String str) {
		return str.replaceAll("&amp;", "&")
				.replaceAll("&gt;", ">")
				.replaceAll("&lt;", "<")
				.replaceAll("&quot;", "\"");
	}
}
