package com.xtkj.paopaoxiche.application;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

public class BaseApplication extends Application {

    private static Context context;

    private static String versionName;

    public static Context getContext() {
        return context;
    }

    public static String getVersionName() {
        return versionName;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        try {
            versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
