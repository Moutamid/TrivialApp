package com.moutamid.trivialapp;

import android.content.Context;

public class SharedPreferences {
    private static final String APP_PREFS_NAME = "TrivialAPP";
    private static final String COIN_PREFS_NAME = "COIN_PREFERENCE";

    private android.content.SharedPreferences mPreference;
    private android.content.SharedPreferences.Editor mPrefEditor;
    private Context context;

    public SharedPreferences(Context context) {
        this.mPreference = context.getSharedPreferences(APP_PREFS_NAME, Context.MODE_PRIVATE);
        this.mPrefEditor = mPreference.edit();
        this.context = context;
    }

    public void saveCoin(int coin){
        mPrefEditor.putInt(COIN_PREFS_NAME, coin);
        mPrefEditor.commit();
    }

    public int getCoin() {
        int c = mPreference.getInt(COIN_PREFS_NAME, 25);
        return c;
    }
}
