package com.example.volleydemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by wangsir on 2017/4/1.
 */

public class MyApplication extends Application {
    private static Context context;
    private static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static Context getAppContext() {
        return context;
    }
    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
