package com.example.lyaho340hw1;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }

    public void onDateClick(View view) {
        if (view == findViewById(R.id.button_date)) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            int currYear = c.get(Calendar.YEAR);
            int currMonth = c.get(Calendar.MONTH);
            int currDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            EditText txtDate = findViewById(R.id.in_date);
                            txtDate.setText(
                                    new StringBuilder().append(dayOfMonth)
                                                        .append("-")
                                                        .append(monthOfYear + 1)
                                                        .append("-")
                                                        .append(year).toString());
                        }
                    }, currYear, currMonth, currDay);
            datePickerDialog.show();
        }
    }
}
