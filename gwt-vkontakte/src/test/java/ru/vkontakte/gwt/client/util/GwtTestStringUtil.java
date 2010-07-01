package ru.vkontakte.gwt.client.util;

import ru.vkontakte.gwt.client.VKTestConstants;

import com.google.gwt.junit.client.GWTTestCase;

public class GwtTestStringUtil extends GWTTestCase {
	
	private static final String ESCAPED = "Hello &quot;TEXT&quot; &lt;You &amp; I&gt;";
	private static final String NOT_ESCAPED = "Hello \"TEXT\" <You & I>";

	public void testEscapeHTML() {
		assertEquals(ESCAPED, StringUtil.escapeHTML(NOT_ESCAPED));		
	}
	
	public void testUnescapeHTML() {
		assertEquals(NOT_ESCAPED, StringUtil.unescapeHTML(ESCAPED));				
	}

	@Override
	public String getModuleName() {
		return VKTestConstants.TEST_MODULE_NAME;
	}
}
