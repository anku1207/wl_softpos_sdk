package com.example.wl_softpos_sdk.common.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;
import android.view.WindowManager;

import com.example.wl_softpos_sdk.common.utils.Environment;

import java.lang.ref.WeakReference;

public class App extends MultiDexApplication {
    private static WeakReference<App> mInstance = new WeakReference<>(null);
    public static Environment environment;


    public static Context getContext() {
        return mInstance.get().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init(this);
        setupActivityListener();

    }

    private static synchronized void init(App app){
        mInstance.clear();
        mInstance = new WeakReference<>(app);
        environment = Environment.ENVIRONMENT_NORMAL;

    }
    private void setupActivityListener() {
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);            }
            @Override
            public void onActivityStarted(Activity activity) {
            }
            @Override
            public void onActivityResumed(Activity activity) {

            }
            @Override
            public void onActivityPaused(Activity activity) {

            }
            @Override
            public void onActivityStopped(Activity activity) {
            }
            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }
            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });
    }

}
