package com.example.lyaho340hw1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ProfileFragment extends Fragment {

    private TextView usernameDisplay;
    private TextView nameDisplay;
    private TextView ageDisplay;
    private TextView bioDisplay;
    private TextView occupationDisplay;
    private static final String TAG = ProfileFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        usernameDisplay = rootView.findViewById(R.id.textView_username);
        nameDisplay = rootView.findViewById(R.id.textView_name);
        ageDisplay = rootView.findViewById(R.id.textView_age);
        bioDisplay = rootView.findViewById(R.id.textView_bio);
        occupationDisplay = rootView.findViewById(R.id.textView_occupation);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity rootView = getActivity();

         Intent incomingIntent = rootView.getIntent();
         Bundle incomingState = incomingIntent.getExtras();

        if (incomingState != null) {
            Bundle incomingExtras = incomingState.getBundle(Constants.KEY_USER_DATA);

            if (incomingExtras != null) {
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
            }
        }
        Log.d(TAG, "onActivityCreated invoked");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "onSaveInstanceState invoked");
        // outState.putString(Constants.KEY_USERNAME, usernameDisplay.getText().toString());
        outState.putString(Constants.KEY_NAME, nameDisplay.getText().toString());
        outState.putString(Constants.KEY_DOB_STRING, ageDisplay.getText().toString());
        outState.putString(Constants.KEY_BIO, bioDisplay.getText().toString());
        // outState.putString(Constants.KEY_OCCUPATION, occupationDisplay.getText().toString());

    }
}
