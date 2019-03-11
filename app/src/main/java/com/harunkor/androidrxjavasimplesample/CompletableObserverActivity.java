package com.harunkor.androidrxjavasimplesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class CompletableObserverActivity extends AppCompatActivity {

    private  Button starttaskbutton;
    private TextView txtview;
    private ProgressBar progressBar;
    private static final String TAG="TAG";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completable_observer);
        starttaskbutton=(Button) findViewById(R.id.button);
        txtview=(TextView) findViewById(R.id.textView);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.GONE);
        getSupportActionBar().setTitle("CompletableObserverActivity - Rxjava2");


        starttaskbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomeWork();
            }
        });







    }

    private void doSomeWork() {
        Completable completable = Completable.timer(5000, TimeUnit.MILLISECONDS);

        completable
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getCompletableObserver());






    }





    private CompletableObserver getCompletableObserver() {
        return new CompletableObserver() {


            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
                progressBar.setVisibility(ProgressBar.VISIBLE);
            }

            @Override
            public void onComplete() {
                txtview.append(" onComplete");
                txtview.append("\n");
                Log.d(TAG, " onComplete");
                progressBar.setVisibility(ProgressBar.GONE);
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
