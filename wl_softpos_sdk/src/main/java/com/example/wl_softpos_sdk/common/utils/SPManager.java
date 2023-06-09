package com.example.wl_softpos_sdk.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.example.wl_softpos_sdk.common.base.App;

import java.io.IOException;
import java.security.GeneralSecurityException;



/**
 * Created by Ganesh on 23/11/17.
 */
public class SPManager {
    private static SPManager myManager;
    private SharedPreferences s;

    String masterKeyAlias;
    private static final String TAG = Context.class.getSimpleName();



    private SPManager(Context context) {
        try {
            masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            s = EncryptedSharedPreferences.create("PreferencesFilename",
                    masterKeyAlias,
                    App.getContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);

        } catch (GeneralSecurityException e) {
            Utils.getInstance().log(TAG,""+e.toString());
        } catch (IOException e) {
            Utils.getInstance().log(TAG,""+e.toString());
        } catch (Exception e) {
            Utils.getInstance().log(TAG,""+e.toString());
        }
    }

    public static SPManager getInstance() {
        if (myManager == null) {
            synchronized (SPManager.class) {
                myManager = new SPManager(App.getContext());
            }
        }
        return myManager;
    }

    private void saveString(String key, String data) {
        SharedPreferences.Editor editor = s.edit();
        editor.putString(key, data);
        editor.apply();
    }

    private void saveInt(String key, int data) {
        SharedPreferences.Editor editor = s.edit();
        editor.putInt(key, data);
        editor.apply();
    }

    private void saveBoolean(String key, boolean data) {
        SharedPreferences.Editor editor = s.edit();
        editor.putBoolean(key, data);
        editor.apply();
    }

    private void delete(String key) {
        SharedPreferences.Editor editor = s.edit();
        editor.remove(key);
        editor.apply();
    }

    private String retrieveString(String key) {
        return s.getString(key, null);
    }

    private int retrieveInt(String key) {
        return s.getInt(key, 0);
    }

    private boolean retrieveBool(String key) {
        return s.getBoolean(key, false);
    }

    private boolean retrieveBool(String key, boolean defaultValue) {
        return s.getBoolean(key, defaultValue);
    }


    public void logout() {
        SharedPreferences.Editor editor = s.edit();
        editor.clear();
        editor.apply();
    }


}