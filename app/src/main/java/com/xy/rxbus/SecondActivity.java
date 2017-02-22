package com.xy.rxbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xy.rxbus.rxbus.MessageEvent;
import com.xy.rxbus.rxbus.RxBus;

/**
 * Created by zhouliancheng on 2017/2/22.
 */
public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button publishBtn = (Button) findViewById(R.id.btn_publish);
        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getDefault().post(new MessageEvent("发布消息。。。。"));
                finish();
            }
        });

        Button publishStickyBtn = (Button) findViewById(R.id.btn_publish_sticky);
        publishStickyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getDefault().postSticky(new MessageEvent("发布粘性消息。。。。。"));
                finish();
            }
        });
    }
}
