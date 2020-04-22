package com.example.writerchainapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    public  Context context;
    public final String  PREF_FILE_NAME = "WRITERS_CHAIN";

    public PreferenceHelper(Context context) {
        this.context = context;
    }


 public SharedPreferences mPref= context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);

        public void setPrefString(String KEY, String value){
            SharedPreferences.Editor editor= mPref.edit();
            editor.putString(KEY, value);
            editor.commit();
        }

        public void setPrefInt(String KEY, int value){
            SharedPreferences.Editor editor= mPref.edit();
            editor.putInt(KEY, value);
            editor.commit();
        }

        public void  setPrefFloat(String KEY, float value){
            SharedPreferences.Editor editor= mPref.edit();
            editor.putFloat(KEY, value);
            editor.commit();
        }

        public void setPrefLong(String KEY, long value){
            SharedPreferences.Editor editor= mPref.edit();
            editor.putLong(KEY, value);
            editor.commit();
        }

         public void setPrefBoolean(String KEY, boolean status){
            SharedPreferences.Editor editor= mPref.edit();
            editor.putBoolean(KEY, status);
            editor.commit();
        }

         public String getPrefString(String KEY_NAME) {
            return mPref.getString(KEY_NAME, "");
        }

        public int getPrefInt(String KEY_NAME) {
            return mPref.getInt(KEY_NAME, 0);
        }

        public float getPrefFloat(String KEY_NAME) {
             return mPref.getFloat(KEY_NAME, 0.0f);
        }

        public long getPrefLong(String KEY_NAME) {
            return mPref.getLong(KEY_NAME, 0L);
        }

        public boolean getPrefBoolean(String KEY_NAME) {
            return mPref.getBoolean(KEY_NAME, false);
        }

        public void  clearPrefValue(String KEY){
            SharedPreferences.Editor editor= mPref.edit();
            editor.remove(KEY);
            editor.commit();
        }

        public void clearPrefs(){
            SharedPreferences.Editor editor= mPref.edit();
            editor.clear();
            editor.commit();
        }
}