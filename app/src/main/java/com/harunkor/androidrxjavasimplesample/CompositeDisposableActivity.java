package com.harunkor.androidrxjavasimplesample;


import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class CompositeDisposableActivity extends AppCompatActivity {

    private  Button starttaskbutton;
    private  Button stoptaskbutton;
    private TextView txtview;
    private ProgressBar progressBar;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compositedisposable);
        starttaskbutton=(Button) findViewById(R.id.button);
        stoptaskbutton=(Button) findViewById(R.id.button2);
        txtview=(TextView) findViewById(R.id.textView);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.GONE);
        getSupportActionBar().setTitle("CompositeDisposableActivity - Rxjava2");

        starttaskbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomeWork();
            }
        });


        stoptaskbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compositeDisposable.dispose();
                if(compositeDisposable.isDisposed())
                {
                    progressBar.setVisibility(ProgressBar.GONE);
                }
            }
        });








    }


    void doSomeWork() {
        compositeDisposable=new CompositeDisposable();
        compositeDisposable.add(sampleObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    protected void onStart() {
                        progressBar.setVisibility(ProgressBar.VISIBLE);

                        super.onStart();
                    }

                    @Override
                    public void onComplete() {
                        txtview.append(" TAMAMLANDI.");
                        txtview.append("\n");
                        Log.d("TAG", " TAMAMLANDI.");
                        progressBar.setVisibility(ProgressBar.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        txtview.append(" HATA : " + e.getMessage());
                        txtview.append("\n");
                        Log.d("TAG", " HATA : " + e.getMessage());
                    }

                    @Override
                    public void onNext(String value) {
                        txtview.append(" BIRSONRAKIISLEM : DEGER : " + value);
                        txtview.append("\n");
                        Log.d("TAG", " BIRSONRAKIISLEM DEGER : " + value);
                    }
                }));
    }




    static Observable<String> sampleObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() {

                SystemClock.sleep(5000);

                return Observable.just("islem1", "islem2", "islem3", "islem4", "islem5");
            }
        });
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }






}
