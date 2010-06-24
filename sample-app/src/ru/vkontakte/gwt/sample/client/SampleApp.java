package ru.vkontakte.gwt.sample.client;

import ru.vkontakte.gwt.client.VK;
import ru.vkontakte.gwt.client.VKUser;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;

public class SampleApp implements EntryPoint {
	private FlexTable table;

	public void onModuleLoad() {
		VK.init(new AlertAsyncCallback<Void>() {			
			public void onSuccess(Void result) {
				buildUI();				
			}
		});
	}

	private void buildUI() {
		table = new FlexTable();
		addRow("Method", "Result");		
		RootPanel.get("gwt-goes-here").add(table);		

		VKUser.isAppUser(new AlertAsyncCallback<Boolean>() {		
			public void onSuccess(Boolean result) {
				addRow("isAppUser", result.toString());		
			}
		});
	}

	private void addRow(String name, String result) {	
		int row = table.getRowCount();
		table.setText(row, 0, name);
		table.setText(row, 1, result);
	}
}
