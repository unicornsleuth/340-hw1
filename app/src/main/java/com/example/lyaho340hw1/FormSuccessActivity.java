package com.example.lyaho340hw1;

import android.content.Intent;
import android.os.Bundle;

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
    }
}
