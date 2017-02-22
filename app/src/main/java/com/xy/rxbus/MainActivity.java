package com.xy.rxbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xy.rxbus.rxbus.MessageEvent;
import com.xy.rxbus.rxbus.RxBus;
import com.xy.rxbus.rxbus.RxBusSubscriber;
import com.xy.rxbus.rxbus.RxSubscriptions;

import rx.Subscription;

public class MainActivity extends AppCompatActivity {

    private Subscription sub;
    private TextView subscriberTv;
    private TextView subscriberStickyTv;
    private Subscription subSticky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enterBtn = (Button) findViewById(R.id.btn_enter);
        subscriberTv = (TextView) findViewById(R.id.tv_subscriber);
        subscriberStickyTv = (TextView) findViewById(R.id.tv_subscriber_sticky);
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        RxSubscriptions.remove(sub);
        sub = RxBus.getDefault().toObservable(MessageEvent.class)
                .subscribe(new RxBusSubscriber<MessageEvent>() {
                    @Override
                    protected void onEvent(MessageEvent messageEvent) {
                        if (messageEvent != null) {
                            subscriberTv.setText(messageEvent.getMsg());
                        }
                    }
                });
        RxSubscriptions.add(sub);


        RxSubscriptions.remove(subSticky);
        subSticky = RxBus.getDefault().toObservableSticky(MessageEvent.class)
                .subscribe(new RxBusSubscriber<MessageEvent>() {
                    @Override
                    protected void onEvent(MessageEvent messageEvent) {
                        if (messageEvent != null) {
                            subscriberStickyTv.setText(messageEvent.getMsg());
                        }
                    }
                });
        RxSubscriptions.add(subSticky);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxSubscriptions.remove(sub);
        RxSubscriptions.remove(subSticky);
    }
}
