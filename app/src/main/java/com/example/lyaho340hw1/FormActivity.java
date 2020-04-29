package com.example.lyaho340hw1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.YEAR;

public class FormActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText emailField;
    private EditText usernameField;
    private TextView dateField;
    private static final String TAG = FormActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        nameField = findViewById(R.id.editText_name);
        emailField = findViewById(R.id.editText_email);
        usernameField = findViewById(R.id.editText_username);
        dateField = findViewById(R.id.textView_date);
        Log.d(TAG,"onCreate invoked");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart invoked");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState invoked");

        if (savedInstanceState.containsKey(Constants.KEY_NAME)) {
            nameField.setText(savedInstanceState.getString(Constants.KEY_NAME));
        }

        if (savedInstanceState.containsKey(Constants.KEY_EMAIL)) {
            emailField.setText(savedInstanceState.getString(Constants.KEY_EMAIL));
        }

        if (savedInstanceState.containsKey(Constants.KEY_USERNAME)) {
            usernameField.setText(savedInstanceState.getString(Constants.KEY_USERNAME));
        }

        if (savedInstanceState.containsKey(Constants.KEY_DOB_STRING)) {
            dateField.setText(savedInstanceState.getString(Constants.KEY_DOB_STRING));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume invoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        nameField.setText("");
        emailField.setText("");
        usernameField.setText("");
        dateField.setText(getResources().getString(R.string.date_of_birth));
        Log.d(TAG,"onResume invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause invoked");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "onSaveInstanceState invoked");
        outState.putString(Constants.KEY_NAME, nameField.getText().toString());
        outState.putString(Constants.KEY_EMAIL, emailField.getText().toString());
        outState.putString(Constants.KEY_USERNAME, usernameField.getText().toString());
        outState.putString(Constants.KEY_DOB_STRING, dateField.getText().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy invoked");
    }

    public void onDateClick(View view) {
        if (view == findViewById(R.id.button_date)) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            int startYear = c.get(YEAR) - Constants.AGE_OF_MAJORITY;
            int currMonth = c.get(Calendar.MONTH);
            int currDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            TextView dateField = findViewById(R.id.textView_date);
                            dateField.setError(null);
                            dateField.setText(
                                    ( new StringBuilder(String.format("%02d - %02d - %04d", dayOfMonth, monthOfYear + 1, year))).toString());

                        }
                    }, startYear, currMonth, currDay);
            datePickerDialog.show();
        }
    }

    public void submitForm(View view) throws ParseException {
        String name = nameField.getText().toString();
        if (name.equals("")) { nameField.setError(getResources().getString(R.string.error_field_required)); }
        String email = emailField.getText().toString();
        if (email.equals("")) { emailField.setError(getResources().getString(R.string.error_field_required)); }
        String username = usernameField.getText().toString();
        if (username.equals("")) { usernameField.setError(getResources().getString(R.string.error_field_required)); }
        String dateString = dateField.getText().toString();
        Date date;
        // DATE OF BIRTH VALIDATION
        if (dateString.equals("")) { // HASN'T BEEN ENTERED
            dateField.setError(getResources().getString(R.string.error_field_required));
            date = null;
        } else {
            Calendar birthday = Calendar.getInstance();
            date = new SimpleDateFormat("dd - MM - yyyy").parse(dateString);
            birthday.setTime(date);

            Calendar checkDate = Calendar.getInstance();
            checkDate.add(YEAR, -1 * Constants.AGE_OF_MAJORITY); // 18 years ago today
            if (!birthday.before(checkDate)) {
                dateField.setError(getResources().getString(R.string.check_age));
                dateField.setText(getResources().getString(R.string.check_age)); }

        }
        // IF NO ERRORS, START NEXT ACTIVITY
        if ((nameField.getError() == null) &&
                (emailField.getError() == null) &&
                (usernameField.getError() == null) &&
                (dateField.getError() == null)) {
            Intent intent = new Intent(FormActivity.this, FormSuccessActivity.class);
            intent.setAction(Intent.ACTION_VIEW);

            Bundle bundle = new Bundle();
            bundle.putString(Constants.KEY_NAME, name);
            bundle.putString(Constants.KEY_EMAIL, email);
            bundle.putString(Constants.KEY_USERNAME, username);
            if (date != null) {
                bundle.putSerializable(Constants.KEY_DOB, date);
                bundle.putString(Constants.KEY_DOB_STRING, dateString);
            }

            intent.putExtra(Constants.KEY_USER_DATA, bundle);
            startActivity(intent);
        }
    }
}
