package com.example.lyaho340hw1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FormSuccessActivity extends AppCompatActivity {

    private TextView confirmation;
    private StringBuilder confirmMessage;
    private static final String TAG = FormSuccessActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        confirmation = findViewById(R.id.confirmation);
        confirmMessage = new StringBuilder(getString(R.string.thanks_signup));

        Intent incomingIntent = getIntent();
        Bundle incomingState = incomingIntent.getExtras();
        String name = "Unknown";

        if (incomingState != null) {
            Bundle incomingExtras = incomingState.getBundle(Constants.KEY_USER_DATA);

            if (incomingExtras != null) {
                if (incomingExtras.containsKey(Constants.KEY_NAME)) {
                    name = incomingExtras.getString(Constants.KEY_NAME);
                }
            }
        }
        confirmation.setText(confirmMessage.append(" ").append(name).toString());
        Log.d(TAG, "onCreate invoked");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart invoked");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        confirmMessage = new StringBuilder(getString(R.string.thanks_signup));
        if (savedInstanceState.containsKey(Constants.KEY_NAME)) {
            confirmation.setText(confirmMessage.append(savedInstanceState.getString(Constants.KEY_NAME))); // test to see if this still has the name appended
        }
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
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy invoked");
    }
}
