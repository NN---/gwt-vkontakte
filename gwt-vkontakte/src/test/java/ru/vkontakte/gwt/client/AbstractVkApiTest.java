package ru.vkontakte.gwt.client;

import ru.vkontakte.gwt.client.impl.VKImpl;
import ru.vkontakte.gwt.client.test.MockVKImpl;

import com.google.gwt.junit.client.GWTTestCase;

public abstract class AbstractVkApiTest extends GWTTestCase {

	private VKImpl originalVKImpl;
	protected MockVKImpl mockVKImpl;

	@Override
	protected void gwtSetUp() throws Exception {
		originalVKImpl = VK.getImpl();
		mockVKImpl = new MockVKImpl();
		VK.setImpl(mockVKImpl);
		super.gwtSetUp();
	}
	
	@Override
	protected void gwtTearDown() throws Exception {
		super.gwtTearDown();
		VK.setImpl(originalVKImpl);
	}
	
	@Override
	public String getModuleName() {
		return VKTestConstants.TEST_MODULE_NAME;
	}
}
