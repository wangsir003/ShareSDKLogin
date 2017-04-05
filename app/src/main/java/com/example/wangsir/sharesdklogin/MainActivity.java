package com.example.wangsir.sharesdklogin;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import cn.smssdk.DefaultOnSendMessageHandler;
import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class MainActivity extends AppCompatActivity {

    private EditText etNumber;
    private Button button;
    private EditText etNum;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int time = (int) msg.obj;
            Log.d("dddddd", time + "");
            if (time == -1) {
                button.setText("60S");
                button.setClickable(true);
                TIME_COUNT = 60;
            } else {
                button.setText(time + "S");
                coculateTime(--TIME_COUNT);
            }
        }
    };
    private EventHandler eventHandler = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    Log.i("MainActivity", "验证码提交成功");
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    Log.i("MainActivity", "验证码获取成功");
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                    Log.i("MainActivity", "返回国家列表成功");
                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNumber = (EditText) findViewById(R.id.et_number);
        button = (Button) findViewById(R.id.btn_getrisgster);
        etNum = (EditText) findViewById(R.id.et_num);
        SMSSDK.initSDK(this, "1c97795e90ba0", "2365cbca8679e2b91fb99102f044a0dd");

        //跳转到有界面的注册，
        initRegister();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }

    private int TIME_COUNT = 5;
    private boolean isTiming = false;

    public void click(View view) {
        button.setClickable(false);
        SMSSDK.getVerificationCode("+86", "13161069743");
        //注册监听
        SMSSDK.registerEventHandler(eventHandler);
        //onSendMessageHandler.onSendMessage(Coun)
//        DefaultOnSendMessageHandler defaultOnSendMessageHandler = new DefaultOnSendMessageHandler();
//        defaultOnSendMessageHandler.onSendMessage("13161069743","86");
        coculateTime(TIME_COUNT);

        //SMSSDK.getVerificationCode("86", "13161069743");


    }

    private void coculateTime(final int second) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("second", second + "");
                if (second > 0) {
                    Message msg = Message.obtain();
                    msg.obj = second;
                    handler.sendMessage(msg);
                } else {
                    Message msg = Message.obtain();
                    msg.obj = -1;
                    handler.sendMessage(msg);
                }
            }
        }, 1000);


    }


    private void initRegister() {
        SMSSDK.initSDK(this, "1c97795e90ba0", "2365cbca8679e2b91fb99102f044a0dd");
        //打开注册页面
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");

                    // 提交用户信息（此方法可以不调用）
                    // registerUser(country, phone);
                }
            }
        });
        registerPage.show(this);
    }
}
