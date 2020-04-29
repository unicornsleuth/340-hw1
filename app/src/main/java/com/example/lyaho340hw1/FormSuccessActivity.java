package com.example.lyaho340hw1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class FormSuccessActivity extends AppCompatActivity {

    private Bundle incomingState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        Intent incomingIntent = getIntent();
        incomingState = incomingIntent.getExtras();

        if (savedInstanceState != null) {
            // recover stuff from orientation switch
        }
        Log.d(Constants.LIFECYCLE,"onCreate invoked");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Constants.LIFECYCLE,"onStart invoked");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Constants.LIFECYCLE,"onResume invoked");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Constants.LIFECYCLE,"onPause invoked");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Constants.LIFECYCLE,"onStop invoked");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Constants.LIFECYCLE,"onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Constants.LIFECYCLE,"onDestroy invoked");
    }
}
