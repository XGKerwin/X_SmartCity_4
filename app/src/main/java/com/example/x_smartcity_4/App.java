package com.example.x_smartcity_4;

import android.app.Application;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/30  15:09
 */
public class App extends Application {
    private static String Userid;

    public static String getUserid() {
        return Userid;
    }

    public static void setUserid(String userid) {
        Userid = userid;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
