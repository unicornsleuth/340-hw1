package com.example.lyaho340hw1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    public TimePicker reminderTimePicker;
    public String reminderTimeString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

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

        return view;
    }

}
