package com.example.lyaho340hw1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FormSuccessActivity extends AppCompatActivity {

    private TextView usernameDisplay;
    private TextView nameDisplay;
    private TextView ageDisplay;
    private TextView bioDisplay;
    private TextView occupationDisplay;
    private static final String TAG = FormSuccessActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        usernameDisplay = findViewById(R.id.textView_username);
        nameDisplay = findViewById(R.id.textView_name);
        ageDisplay = findViewById(R.id.textView_age);
        bioDisplay = findViewById(R.id.textView_bio);
        occupationDisplay = findViewById(R.id.textView_occupation);

        Intent incomingIntent = getIntent();
        Bundle incomingState = incomingIntent.getExtras();

        if (incomingState != null) {
            Bundle incomingExtras = incomingState.getBundle(Constants.KEY_USER_DATA);

            if (incomingExtras != null) {
                if (incomingExtras.containsKey(Constants.KEY_USERNAME)) {
                    usernameDisplay.setText(incomingExtras.getString(Constants.KEY_USERNAME));
                }
                if (incomingExtras.containsKey(Constants.KEY_NAME)) {
                    nameDisplay.setText(incomingExtras.getString(Constants.KEY_NAME));
                }
                if (incomingExtras.containsKey(Constants.KEY_DOB)) {
                    Calendar today = Calendar.getInstance();
                    Date dob = (Date) incomingExtras.getSerializable(Constants.KEY_DOB);
                    assert dob != null;
                    Log.d(TAG, dob.toString());
                    long diffInMilliseconds = Math.abs(today.getTime().getTime() - dob.getTime());
                    long diff = TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
                    int age = (int) diff / 365;
                    ageDisplay.setText(Integer.toString(age));
                }
                if (incomingExtras.containsKey(Constants.KEY_BIO)) {
                    bioDisplay.setText(incomingExtras.getString(Constants.KEY_BIO));
                }
                if (incomingExtras.containsKey(Constants.KEY_OCCUPATION)) {
                    occupationDisplay.setText(incomingExtras.getString(Constants.KEY_OCCUPATION));
                }
            }
        }

        Log.d(TAG, "onCreate invoked");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart invoked");
    }



    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

        if (savedInstanceState.containsKey(Constants.KEY_USERNAME)) {
            usernameDisplay.setText(savedInstanceState.getString(Constants.KEY_USERNAME));
        }
        if (savedInstanceState.containsKey(Constants.KEY_NAME)) {
            nameDisplay.setText(savedInstanceState.getString(Constants.KEY_NAME));
        }
        if (savedInstanceState.containsKey(Constants.KEY_DOB_STRING)) {
            ageDisplay.setText(savedInstanceState.getString(Constants.KEY_DOB_STRING));
        }
        if (savedInstanceState.containsKey(Constants.KEY_BIO)) {
            bioDisplay.setText(savedInstanceState.getString(Constants.KEY_BIO));
        }
        if (savedInstanceState.containsKey(Constants.KEY_OCCUPATION)) {
            occupationDisplay.setText(savedInstanceState.getString(Constants.KEY_OCCUPATION));
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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "onSaveInstanceState invoked");
        outState.putString(Constants.KEY_USERNAME, usernameDisplay.getText().toString());
        outState.putString(Constants.KEY_NAME, nameDisplay.getText().toString());
        outState.putString(Constants.KEY_DOB_STRING, ageDisplay.getText().toString());
        outState.putString(Constants.KEY_BIO, bioDisplay.getText().toString());
        outState.putString(Constants.KEY_OCCUPATION, occupationDisplay.getText().toString());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy invoked");
    }
}
