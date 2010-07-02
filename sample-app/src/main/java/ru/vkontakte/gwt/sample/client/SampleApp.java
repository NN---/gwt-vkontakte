package ru.vkontakte.gwt.sample.client;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ru.vkontakte.gwt.client.VKUser;
import ru.vkontakte.gwt.client.model.Case;
import ru.vkontakte.gwt.client.model.Profile;
import ru.vkontakte.gwt.client.model.ProfileFields;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

public class SampleApp implements EntryPoint {
	private FlexTable table;

	public void onModuleLoad() {
		table = new FlexTable();
		table.setWidth("100%");
		ScrollPanel panel = new ScrollPanel(table);
		panel.setHeight("200px");
		
		RootPanel.get("gwt-goes-here").add(panel);		
		
		addRow("Method", "Result");		

//		VK.init(true, new AlertAsyncCallback<Void>() {			
//			public void onSuccess(Void result) {
				getFriendIds();				
//			}
//		});
	}

	private void getFriendIds() {
		VKUser.getFriends(new AlertAsyncCallback<List<Long>>() {		
			public void onSuccess(List<Long> ids) {
				addRow("Friends count: ", Integer.toString(ids.size()));
				printFriendList(ids);
			}
		});
		
		
	}

	private void printFriendList(List<Long> ids) {
		VKUser.getProfiles(ids, ProfileFields.ALL, Case.NOM, new AlertAsyncCallback<List<Profile>>() {
			public void onSuccess(List<Profile> profiles) {
				Collections.sort(profiles, new Comparator<Profile>() {
					public int compare(Profile o1, Profile o2) {
						int lastnames = o1.getLastName().compareToIgnoreCase(o2.getLastName());
						return lastnames == 0 ? o1.getFirstName().compareToIgnoreCase(o2.getFirstName()) : lastnames;
					}					
				});
				for (Profile profile : profiles) {
					if (!isEmpty(profile.getHomePhone()) || !isEmpty(profile.getMobilePhone())) {
						addRow(profile.getLastName() + " " + profile.getFirstName(), 
								profile.getHomePhone() + " " + profile.getMobilePhone());
					}
				}
				
			}

		});
	}

	private boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	private void addRow(String name, String result) {	
		int row = table.getRowCount();
		table.setText(row, 0, name);
		table.setText(row, 1, result);
	}
}
