package com.harunkor.androidrxjavasimplesample;


import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import io.reactivex.Flowable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

public class FlowableActivity extends AppCompatActivity {

    private Button starttaskbutton;
    private TextView txtview;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowable);
        starttaskbutton=(Button) findViewById(R.id.button);
        txtview=(TextView) findViewById(R.id.textView);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.GONE);
        getSupportActionBar().setTitle("FlowableActivity - Rxjava2");


        starttaskbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomeWork();

            }
        });





    }



    private void doSomeWork() {


        Flowable<Integer> observable = Flowable.just(1, 2, 3, 4,5,6,7,8,9,10);

        observable.reduce(50, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2) {

                return t1 + t2;
                //50+1
                //51+2
                //53+3
                //56+4
                //....

            }
        }).subscribe(getObserver());

    }


    private SingleObserver<Integer> getObserver() {


        return new SingleObserver<Integer>() {


            @Override
            public void onSubscribe(Disposable d) {
                progressBar.setVisibility(ProgressBar.VISIBLE);
                Log.d("TAG", " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onSuccess(Integer value) {
                txtview.append(" onSuccess : value : " + value);
                txtview.append("\n");
                Log.d("TAG", " onSuccess : value : " + value);

              // progressBar.setVisibility(ProgressBar.GONE);
            }

            @Override
            public void onError(Throwable e) {
                txtview.append(" onError : " + e.getMessage());
                txtview.append("\n");
                Log.d("TAG", " onError : " + e.getMessage());
            }
        };
    }





}

