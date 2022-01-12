package com.example.testbts.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Constructor
    public Session(Context context){
        this._context = context;
        pref = _context.getSharedPreferences("session", Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createSession(String access_token){
        // Storing login value as TRUE
        editor.putString("token", "Bearer "+access_token);

        // commit changes
        editor.commit();
    }

    // get Token
    public String getToken(){
        return pref.getString("token", "");
    }

}
