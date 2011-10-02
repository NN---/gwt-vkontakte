package ru.vkontakte.gwt.client.model;

import ru.vkontakte.gwt.client.util.JSONUtil;
import ru.vkontakte.gwt.client.util.NoSuchJSONValueException;

import com.google.gwt.json.client.JSONValue;

public class Session {
	
	private final Long mid;
	private final String sid;
	private final String secret;
	private final Long expire;
	private final String sig;
	private final User user;
	
	public Session(JSONValue session) throws NoSuchJSONValueException {

		if(session.isObject() == null) {
			throw new NoSuchJSONValueException();
		}
		
		mid = JSONUtil.getLong(session, "mid");
		sid = JSONUtil.getString(session, "sid");
		secret = JSONUtil.getString(session, "sid");
		expire = JSONUtil.getLong(session, "expire");
		sig = JSONUtil.getString(session, "sig");
		
		if(session.isObject().containsKey("user")) {
			user = new User(JSONUtil.getValue(session, "user"));
		} else {
			user = null;
		}
	}

	public Long getExpire() {
		return expire;
	}

	public Long getMid() {
		return mid;
	}

	public String getSid() {
		return sid;
	}

	public String getSecret() {
		return secret;
	}

	public String getSig() {
		return sig;
	}

	public User getUser() {
		return user;
	}
	
	public static class User {
		private final Long id;
		private final String href;
		private final String domain;
		private final String firstName;
		private final String lastName;
		private final String nickName;
		
		public User(JSONValue user) throws NoSuchJSONValueException {
			if(user.isObject() == null) {
				throw new NoSuchJSONValueException();
			}
			
			id = JSONUtil.getLong(user, "id");
			href = JSONUtil.getString(user, "href");
			domain = JSONUtil.getString(user, "domain");
			firstName = JSONUtil.getString(user, "first_name");
			lastName = JSONUtil.getString(user, "last_name");
			nickName = JSONUtil.getString(user, "nickname");
		}

		public Long getId() {
			return id;
		}

		public String getHref() {
			return href;
		}

		public String getDomain() {
			return domain;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public String getNickName() {
			return nickName;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(getFirstName());
			
			if(getNickName() != null && getNickName().length() > 0) {
				sb.append(" (")
				  .append(getNickName())
				  .append(") ");
			}
			
			sb.append(" ")
			  .append(getLastName());
			 
			return sb.toString(); 
		}
	}
}
