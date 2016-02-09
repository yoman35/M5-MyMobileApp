package yb.m5_mobile_application.utils;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;

public class MyApp extends Application {

    private static MyApp singleton;

    private MySharedPreferences sharedPreferences;

    public MyApp getInstance(){
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        this.sharedPreferences = new MySharedPreferences();
        SharedPreferences sp = getSharedPreferences(MySharedPreferences.SP_KEY, MODE_PRIVATE);
        this.sharedPreferences.setSP(sp);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //Called by the system when the device configuration
        //changes while your component is running
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //This is called when the overall system is running low on memory,
        //and would like actively running processes to tighten their belts
    }

    public MySharedPreferences getSP() {
        return this.sharedPreferences;
    }

}
