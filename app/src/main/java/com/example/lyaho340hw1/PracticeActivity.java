package com.example.lyaho340hw1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PracticeActivity extends AppCompatActivity {

    private static final String TAG = PracticeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Log.d(TAG,"onCreate invoked");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume invoked");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause invoked");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop invoked");
    }
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Log.d(TAG,"onRestart invoked");
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy invoked");
    }

    public void goToFormActivity(View view) {
        Intent intent = new Intent(PracticeActivity.this, FormActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        startActivity(intent);
    }
}
