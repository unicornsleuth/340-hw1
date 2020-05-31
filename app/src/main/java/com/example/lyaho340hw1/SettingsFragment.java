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

import java.util.List;

public class SettingsFragment extends Fragment {

    public EditText maxDistance;
    public TimePicker reminderTimePicker;
    public String reminderTimeString;
    public Spinner gender;
    public Spinner lookingForGender;
    public Spinner accountPrivacy;
    public EditText minAge;
    public EditText maxAge;
    public Button saveSettingsButton;
    private UserSettings userSettings;

    private UserSettingsViewModel vm;
    private static final String TAG = SettingsFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        Context context = getContext();
        userSettings = new UserSettings();
        vm = new ViewModelProvider(this).get(UserSettingsViewModel.class);
        vm.getAllUserSettings().observe(getViewLifecycleOwner(), new Observer<List<UserSettings>>() {
           @Override
           public void onChanged(@Nullable final List<UserSettings> userSettings) {
               // Update the cached copy of the settings
               // Not sure what to do here - I guess I should have a UserSettings object that I set to this?
               // I should just send it the current UserSettings
           }
        });

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
                StringBuilder reminderTime = new StringBuilder(reminderHour)
                        .append(":").append(reminderMinute);
                reminderTimeString = reminderTime.toString();
            }
        });

        saveSettingsButton = view.findViewById(R.id.button_save_settings);
//        saveSettingsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onSaveSettingsSubmit(v);
//            }
//        });

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
                    UserSettings settingsFromAppDatabase = vm.findSettingsByEmail(email);
                    if (settingsFromAppDatabase != null) {
                        // Load state
                        userSettings = settingsFromAppDatabase;

                        maxDistance.setText(userSettings.getMaxDistance());

                        if (!userSettings.getReminderTime().equals("")) { reminderTimeString = userSettings.getReminderTime(); }
                        int hour = Integer.parseInt(reminderTimeString.substring(0, 1));
                        reminderTimePicker.setHour(hour);
                        int minute = Integer.parseInt(reminderTimeString.substring(3, 4));
                        reminderTimePicker.setMinute(minute);

                        int genderPosition = 0;
                        if (userSettings.getGender().equals(
                            getResources().getStringArray(R.array.genders)[0])) { genderPosition = 0; }
                        else if (userSettings.getGender().equals(
                            getResources().getStringArray(R.array.genders)[1])) { genderPosition = 1; }
                        else if (userSettings.getGender().equals(
                                getResources().getStringArray(R.array.genders)[2])) { genderPosition = 2; }
                        else if (userSettings.getGender().equals(
                                getResources().getStringArray(R.array.genders)[3])) { genderPosition = 3; }
                        gender.setSelection(genderPosition);

                        int lookingForGenderPosition = 0;
                        if (userSettings.getLookingForGender().equals(
                                getResources().getStringArray(R.array.genders)[0])) { lookingForGenderPosition = 0; }
                        else if (userSettings.getLookingForGender().equals(
                                getResources().getStringArray(R.array.genders)[1])) { lookingForGenderPosition = 1; }
                        else if (userSettings.getLookingForGender().equals(
                                getResources().getStringArray(R.array.genders)[2])) { lookingForGenderPosition = 2; }
                        else if (userSettings.getLookingForGender().equals(
                                getResources().getStringArray(R.array.genders)[3])) { lookingForGenderPosition = 3; }
                        gender.setSelection(lookingForGenderPosition);

                        int privacyPosition;
                        if (userSettings.getPrivateAccount()) { privacyPosition = 0; }
                        else { privacyPosition = 1; }
                        accountPrivacy.setSelection(privacyPosition);

                        minAge.setText(userSettings.getMinAge());
                        maxAge.setText(userSettings.getMaxAge());
                    }
                    else { userSettings.setEmail(email); }
                }
            }
        }
        Log.d(TAG, "onActivityCreated invoked");
    }

    public void onSaveSettingsSubmit(View view) {
        if (view == getActivity().findViewById(R.id.button_save_settings)) {
                // Max Distance Validation - Must be >1
            int maxDist = Integer.parseInt(maxDistance.getText().toString());
            if (maxDist < 1) { maxDistance.setError(getString(R.string.max_distance_error)); }
            // Min Age Validation - Must be >= 18
            int minAgeVal = Integer.parseInt(minAge.getText().toString());
            if (minAgeVal < 18) { minAge.setError(getString(R.string.min_age_error)); }
            // Max Age Validation - Must be >= min age
            int maxAgeVal = Integer.parseInt(maxAge.getText().toString());
            if (maxAgeVal < minAgeVal) { maxAge.setError(getString(R.string.max_age_error)); }
            // If no errors, save to current userSettings object, submit
            if ((maxDistance.getError() == null) &&
                    (minAge.getError() == null) &&
                    (maxAge.getError() == null)) {

                // update userSettings for this user
                userSettings.setMaxDistance(maxDist);
                userSettings.setMinAge(minAgeVal);
                userSettings.setMaxAge(maxAgeVal);
                userSettings.setGender(gender.getSelectedItem().toString());
                userSettings.setLookingForGender(lookingForGender.getSelectedItem().toString());
                boolean accountIsPrivate;
                if (accountPrivacy.getSelectedItem().toString().equals(getResources().getStringArray(R.array.account_privacy)[0])) {
                    accountIsPrivate = true;
                } else { accountIsPrivate = false; }
                userSettings.setPrivateAccount(accountIsPrivate);
                userSettings.setReminderTime(reminderTimeString);

                // update the database
                vm.insert(userSettings);

                // toast
                Toast toast = Toast.makeText(getContext(), "Settings Saved", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

}
