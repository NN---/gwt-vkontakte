# About project

This is a further development of the original project: http://code.google.com/p/gwt-vkontakte

# Usage

```java
	VK.init([YOUR_APP_ID]);

	VK.Auth.getLoginStatus(new AuthInfoCallback());

    private class AuthInfoCallback extends ApiCallback {

        public void trigger(JSONValue result) {
            LoginStatus loginStatus = new LoginStatus(result);

            if (loginStatus.isAuthenticated()) {
                // Do stuff
            } else {
                // Do auth
                VK.Auth.login(AuthInfoCallback.this, new Settings(Settings.FRIENDS_ACCESS));
            }
        }
    }
```