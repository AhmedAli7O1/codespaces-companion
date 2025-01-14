package com.orbiktech.codespacescompanion;

import java.util.prefs.Preferences;

public class PreferencesTokenStorage {
    private static final Preferences preferences = Preferences.userNodeForPackage(PreferencesTokenStorage.class);
    private static final String TOKEN_KEY = "authToken";

    public static void saveToken(String token) {
        preferences.put(TOKEN_KEY, token);
    }

    public static String getToken() {
        return preferences.get(TOKEN_KEY, null);
    }

    public static void deleteToken() {
        preferences.remove(TOKEN_KEY);
    }
}
