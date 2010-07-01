package ru.vkontakte.gwt.sample.client;

import java.util.List;

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

		VKUser.getFriends(new AlertAsyncCallback<List<Long>>() {		
			public void onSuccess(List<Long> ids) {
				addRow("Friends count: ", Integer.toString(ids.size()));
			}
		});
	}

	private void addRow(String name, String result) {	
		int row = table.getRowCount();
		table.setText(row, 0, name);
		table.setText(row, 1, result);
	}
}
