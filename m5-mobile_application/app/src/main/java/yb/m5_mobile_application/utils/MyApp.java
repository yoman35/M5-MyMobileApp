package yb.m5_mobile_application.utils;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.Locale;

public class MyApp extends Application {

    private static MyApp singleton;

    private MySharedPreferences sharedPreferences;

    public static MyApp getInstance(){
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
    public void onLowMemory() {
        super.onLowMemory();
        //TODO: test if application turns well on low memory (eg. Genymotion)
    }

    public MySharedPreferences getSP() {
        return this.sharedPreferences;
    }

    public String getPhoneCountry() {
        return Locale.getDefault().getDisplayCountry();
    }

}
