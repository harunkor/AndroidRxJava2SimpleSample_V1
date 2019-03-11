package com.harunkor.androidrxjavasimplesample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button3=(Button) findViewById(R.id.button3);
        button4=(Button) findViewById(R.id.button4);
        button5=(Button) findViewById(R.id.button5);
        button6=(Button) findViewById(R.id.button6);


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goActivity(CompletableObserverActivity.class);


            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goActivity(CompositeDisposableActivity.class);


            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goActivity(FlowableActivity.class);


            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goActivity(SingleObserverActivity.class);



            }
        });





    }




    private void goActivity( Class activity )
    {
       Intent intent=new Intent(MainActivity.this,activity);
       startActivity(intent);

    }



}
