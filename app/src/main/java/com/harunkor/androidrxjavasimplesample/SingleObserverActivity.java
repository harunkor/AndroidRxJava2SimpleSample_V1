package com.harunkor.androidrxjavasimplesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class SingleObserverActivity extends AppCompatActivity {

    private  Button starttaskbutton;
    private  Button stoptaskbutton;
    private TextView txtview;
    private ProgressBar progressBar;
    private static final String TAG="TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_observer);
        starttaskbutton=(Button) findViewById(R.id.button);
        txtview=(TextView) findViewById(R.id.textView);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.GONE);
        getSupportActionBar().setTitle("SingleObserverActivity - Rxjava2");



        starttaskbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomeWork();
            }
        });




    }



    private void doSomeWork() {

        Single.just("harunkor.com").subscribe(getSingleObserver());

    }


    private SingleObserver<String> getSingleObserver() {
        return new SingleObserver<String>() {


            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
                progressBar.setVisibility(ProgressBar.VISIBLE);
            }

            @Override
            public void onSuccess(String value) {
                txtview.append(" onNext : value : " + value);
                txtview.append("\n");
                Log.d(TAG, " onNext value : " + value);

               // progressBar.setVisibility(ProgressBar.GONE);
            }

            @Override
            public void onError(Throwable e) {
                txtview.append(" onError : " + e.getMessage());
                txtview.append("\n");
                Log.d(TAG, " onError : " + e.getMessage());
            }
        };
    }









}
