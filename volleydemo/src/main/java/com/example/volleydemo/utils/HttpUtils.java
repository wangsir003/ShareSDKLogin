package com.example.volleydemo.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;
import com.example.volleydemo.MyApplication;
import com.example.volleydemo.utils.impl.VolleyInterface;

import java.util.Map;

/**
 * 使用Volley访问Http请求管理的工具类
 * Created by wangsir on 2017/4/1.
 */

public class HttpUtils {
    public static StringRequest stringRequest;

    /**
     * 封装get，post请求
     * @param context //上下文
     * @param url
     * @param tag
     * @param vif
     */
    public static void doGet(Context context, String url, String tag, VolleyInterface vif){
        Log.e("url",url);
        //获取全局请请求队列，并把基于Tag标签的请求全部取消，防止重复请求
        //MyApplication.getRequestQueue().cancelAll(tag);
        Log.i("urlurlurl:",MyApplication.getRequestQueue().toString());
        //实例化StringQequest
        stringRequest = new StringRequest(Method.GET,url,vif.loadingListener(),vif.errorListener());
        //设置标签
        stringRequest.setTag(tag);
        MyApplication.getRequestQueue().add(stringRequest);

        //启动
        MyApplication.getRequestQueue().start();

    }


    public static void doPost(Context context, String url, String tag, final Map<String,String> params, VolleyInterface vif){
        Log.e("url",url);
        //获取全局请请求队列，并把基于Tag标签的请求全部取消，防止重复请求
        MyApplication.getRequestQueue().cancelAll(tag);
        //实例化StringQequest
        stringRequest = new StringRequest(Method.POST,url,vif.loadingListener(),vif.errorListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        //设置标签
        stringRequest.setTag(tag);
        MyApplication.getRequestQueue().add(stringRequest);

        //启动
        MyApplication.getRequestQueue().start();

    }






}
