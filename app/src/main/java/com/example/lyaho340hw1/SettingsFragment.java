package com.example.lyaho340hw1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class SettingsFragment extends Fragment {

    private EditText maxDistance;
    private TimePicker reminderTimePicker;
    private String reminderTimeString;
    private Spinner gender;
    private Spinner lookingForGender;
    private Spinner accountPrivacy;
    private EditText minAge;
    private EditText maxAge;
    private Button saveSettingsButton;
    private UserSettings userSettings = new UserSettings();

    private UserSettingsViewModel vm;
    private static final String TAG = SettingsFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        Context context = getContext();
        userSettings = new UserSettings();
        vm = new ViewModelProvider(this).get(UserSettingsViewModel.class);

        maxDistance = view.findViewById(R.id.editText_max_distance);

        gender = view.findViewById(R.id.spinner_gender);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(context,
                R.array.genders, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        gender.setAdapter(genderAdapter);

        lookingForGender = view.findViewById(R.id.spinner_looking_for_gender);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> lookingForGenderAdapter = ArrayAdapter.createFromResource(context,
                R.array.genders, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        lookingForGenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        lookingForGender.setAdapter(lookingForGenderAdapter);

        accountPrivacy = view.findViewById(R.id.spinner_account_privacy);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> accountPrivacyAdapter = ArrayAdapter.createFromResource(context,
                R.array.account_privacy, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        accountPrivacyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        accountPrivacy.setAdapter(accountPrivacyAdapter);

        minAge = view.findViewById(R.id.editText_min_age);
        maxAge = view.findViewById(R.id.editText_max_age);

        reminderTimePicker = view.findViewById(R.id.reminder_time_picker);
        reminderTimePicker.setIs24HourView(true);
        reminderTimePicker.setHour(12);
        reminderTimePicker.setMinute(0);
        reminderTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String reminderHour = String.format("%02d", hourOfDay);
                String reminderMinute = String.format("%02d", minute);
                reminderTimeString = reminderHour + ":" + reminderMinute;
            }
        });

        saveSettingsButton = view.findViewById(R.id.button_save_settings);
        saveSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveSettingsSubmit(v);
            }
        });

        Log.d(TAG, "onCreateView invoked");
        return view;
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
                if (incomingExtras.containsKey(Constants.KEY_EMAIL)) {
                    String email = incomingExtras.getString(Constants.KEY_EMAIL);
                    vm.findSettingsByEmail(email).observe(getViewLifecycleOwner(), new Observer<UserSettings>() {
                        @Override
                        public void onChanged(@Nullable final UserSettings userSettingsFromDb) {
                            // Update the cached copy of the settings
                            userSettings = userSettingsFromDb;
                            if (userSettings == null) {
                                userSettings = new UserSettings();
                                userSettings.setEmail(email);
                            }
                            Log.e(TAG, "from database, gender is " + userSettings.getGender());
                            loadSettingsIntoForm();
                        }
                    });
                }
            }
            Log.d(TAG, "onActivityCreated invoked");
        }
    }

    public void loadSettingsIntoForm() {
        if (userSettings == null) {
            userSettings = new UserSettings();
        }
        maxDistance.setText(Integer.toString(userSettings.getMaxDistance()));

        if (!userSettings.getReminderTime().equals("")) { reminderTimeString = userSettings.getReminderTime(); }
        int hour = Integer.parseInt(reminderTimeString.substring(0, 2));
        reminderTimePicker.setHour(hour);
        int minute = Integer.parseInt(reminderTimeString.substring(3, 4));
        reminderTimePicker.setMinute(minute);

        Log.e("loading gender as:", userSettings.getGender());
        int genderPosition = 0;
        String currentGender = userSettings.getGender();
        String gender1 = getResources().getStringArray(R.array.genders)[1];
        String gender2 = getResources().getStringArray(R.array.genders)[2];
        String gender3 = getResources().getStringArray(R.array.genders)[3];

        if (currentGender.equals(gender1)) { genderPosition = 1; }
        else if (currentGender.equals(gender2)) { genderPosition = 2; }
        else if (currentGender.equals(gender3)) { genderPosition = 3; }
        gender.setSelection(genderPosition);

        Log.e("loading looking for gender as:", userSettings.getLookingForGender());
        int lookingForGenderPosition = 0;
        String currentLookingForGender = userSettings.getLookingForGender();
        if (currentLookingForGender.equals(gender1)) { lookingForGenderPosition = 1; }
        else if (currentLookingForGender.equals(gender2)) { lookingForGenderPosition = 2; }
        else if (currentLookingForGender.equals(gender3)) { lookingForGenderPosition = 3; }
        lookingForGender.setSelection(lookingForGenderPosition);

        int privacyPosition;
        if (userSettings.getPrivateAccount()) { privacyPosition = 0; }
        else { privacyPosition = 1; }
        accountPrivacy.setSelection(privacyPosition);

        if ((Integer) userSettings.getMinAge() != null && userSettings.getMinAge() != 0) {
            minAge.setText(Integer.toString(userSettings.getMinAge()));
        }
        if ((Integer) userSettings.getMaxAge() != null && userSettings.getMaxAge() != 0) {
            maxAge.setText(Integer.toString(userSettings.getMaxAge()));
        }
        Log.d(TAG, "loadSettingsIntoForm() invoked");
    }

    private void onSaveSettingsSubmit(View view) {
        if (view == getActivity().findViewById(R.id.button_save_settings)) {
                // Max Distance Validation - Must be >1
            int maxDist = Integer.parseInt(maxDistance.getText().toString());
            if (maxDist < 1) { maxDistance.setError(getString(R.string.max_distance_error)); }
            // Min Age Validation - Must be >= 18
            String minAgeString = minAge.getText().toString();
            int minAgeVal = 18;
            if (minAgeString.equals("")) { minAge.setError(getString(R.string.error_field_required)); }
            else {
                minAgeVal = Integer.parseInt(minAgeString);
                if (minAgeVal < 18) {
                    minAge.setError(getString(R.string.min_age_error));
                }
            }
            // Max Age Validation - Must be >= min age
            String maxAgeString = maxAge.getText().toString();
            int maxAgeVal = 99;
            if (maxAgeString.equals("")) { maxAge.setError(getString(R.string.error_field_required)); }
            else {
                maxAgeVal = Integer.parseInt(maxAgeString);
                if (maxAgeVal < minAgeVal) {
                    maxAge.setError(getString(R.string.max_age_error));
                }
            }
            // If no errors, save to current userSettings object, submit
            if ((maxDistance.getError() == null) &&
                    (minAge.getError() == null) &&
                    (maxAge.getError() == null)) {

                // update userSettings for this user
                userSettings.setMaxDistance(maxDist);
                userSettings.setMinAge(minAgeVal);
                userSettings.setMaxAge(maxAgeVal);
                Log.e("saving gender as:", gender.getSelectedItem().toString());
                userSettings.setGender(gender.getSelectedItem().toString());

                Log.e("saving lookingForGender as:", lookingForGender.getSelectedItem().toString());
                userSettings.setLookingForGender(lookingForGender.getSelectedItem().toString());
                boolean accountIsPrivate = accountPrivacy.getSelectedItem().toString()
                        .equals(getResources().getStringArray(R.array.account_privacy)[0]);
                userSettings.setPrivateAccount(accountIsPrivate);
                userSettings.setReminderTime(reminderTimeString);

                // update the database
                vm.insert(userSettings);
                loadSettingsIntoForm();

                // toast
                Toast toast = Toast.makeText(getContext(), "Settings Saved", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        // implement parcelable in UserSettings
//        outState.putParcelable("User Settings", userSettings);
//        Log.d(TAG, "onSaveInstanceState invoked");
//    }

    //    @Override
//    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        Log.d(TAG, "onRestoreInstanceState invoked");
//        // implement parcelable in UserSettings
//        userSettings = savedInstanceState.getParcelable("User Settings");
//        Log.d(TAG, "onRestoreInstanceState invoked");
//    }

}
