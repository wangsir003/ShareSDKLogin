package com.example.volleydemo.utils.impl;

import android.content.Context;

import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;

/**
 * Created by wangsir on 2017/4/1.
 */

public abstract class VolleyInterface {
    /**
     * 上下文
     */
    public Context mContext;

    /**
     * 请求成功的监听
     */
    public static Listener<String> mListener;

    /**
     * 请求失败的监听
     */
    public static ErrorListener mErrorListener;

    public VolleyInterface(Context mContext, Listener<String> mListener,ErrorListener mErrorListener) {
        this.mContext = mContext;
        this.mListener = mListener;
        this.mErrorListener = mErrorListener;
    }

    public abstract void onMySuccess(String result);

    public abstract void onMyError(VolleyError volleyError);

    public Listener<String> loadingListener(){
        mListener = new Listener<String>() {
            @Override
            public void onResponse(String s) {
                onMySuccess(s);
            }
        };
        return mListener;
    }
    public ErrorListener errorListener(){
        mErrorListener = new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onMyError(volleyError);
            }
        };
        return mErrorListener;
    }

}
