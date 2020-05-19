package com.example.knjizara.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class CurrentTabSP {

    SharedPreferences pref;
    SharedPreferences.Editor prefsEditor;
    Context context;

    public CurrentTabSP(Context context) {

        this.context = context;

        pref = context.getSharedPreferences("com.example.knjizara", MODE_PRIVATE);

        prefsEditor = pref.edit();
    }

    public void setCT(int ct) {
        prefsEditor.putInt("currentTab",ct);
        prefsEditor.apply();
    }
    public int getCT() {
        int ct = pref.getInt("currentTab",0);
        return ct;
    }
}