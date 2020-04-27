package com.example.lyaho340hw1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public void submitForm(View view) throws ParseException {
        EditText nameField = findViewById(R.id.editText_name);
        String name = nameField.getText().toString();
        EditText emailField = findViewById(R.id.editText_email);
        String email = emailField.getText().toString();
        EditText usernameField = findViewById(R.id.editText_username);
        String username = usernameField.getText().toString();
        EditText dateField = findViewById(R.id.in_date);
        String dateString = dateField.toString();
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);

        Intent intent = new Intent(FormActivity.this, FormSuccessActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        // LARISSA TRY USING A BUNDLE HERE - THIS MAY BE THE PROBLEM ??
        intent.putExtra("name", name)
                .putExtra("email", email)
                .putExtra("username", username)
                .putExtra("date", date);
        startActivity(intent);
    }
}
