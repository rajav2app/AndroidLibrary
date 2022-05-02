package com.example.androidlibrary;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Pref";
    public static final String KEY_THEAM = "light_mode";
    public static final String KEY_MUTE_STATE ="MUTE_STATE";
    public static final String KEY_PUMP_NAME ="PUMP_NAME";
    public static final String KEY_LOG_TRANSFER_MODE ="LOG_TRANSFER_MODE";

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public boolean isButtonMute(){
        return pref.getBoolean(KEY_MUTE_STATE, false);
    }
    public void saveMuteState(boolean mutestate) {
        editor.putBoolean(KEY_MUTE_STATE, mutestate);
        editor.commit();
    }


    public void savelightmode(boolean theamID) {
        editor.putBoolean(KEY_THEAM, theamID);
        editor.commit();
    }

    public void savepumpName(String pumpname) {
        editor.putString(KEY_PUMP_NAME, pumpname);
        editor.commit();
    }

    public String getKeyPumpName(){
        return  pref.getString(KEY_PUMP_NAME,"");
    }

    public boolean isLightModeOn() {
        return pref.getBoolean(KEY_THEAM, false);
    }
    public void clearallData() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }


}

