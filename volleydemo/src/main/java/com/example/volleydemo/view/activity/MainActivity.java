package com.example.volleydemo.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volleydemo.R;
import com.example.volleydemo.utils.HttpUtils;
import com.example.volleydemo.utils.impl.VolleyInterface;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button viewById = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.tv);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCodeWeb();
            }
        });
    }

    private void loadCodeWeb() {
        HttpUtils.doGet(getApplicationContext(), "http://www.baidu.com", "GET",
                new VolleyInterface(getApplicationContext(),
                VolleyInterface.mListener, VolleyInterface.mErrorListener) {
            @Override
            public void onMySuccess(String result) {
                Log.i("onmyload_success:", Thread.currentThread().getName());
                Log.i("onmyload_success:", result.toString());
                tv.setText(result);
            }

            @Override
            public void onMyError(VolleyError volleyError) {
                Log.i("onmyload_error:", Thread.currentThread().getName());

            }
        });
    }
}
