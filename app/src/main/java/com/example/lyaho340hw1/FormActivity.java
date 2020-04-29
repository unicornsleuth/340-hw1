package com.example.lyaho340hw1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.YEAR;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Log.d(Constants.LIFECYCLE,"onCreate invoked");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Constants.LIFECYCLE,"onStart invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Constants.LIFECYCLE, "onResume invoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        EditText nameField = findViewById(R.id.editText_name);
        nameField.setText("");
        EditText emailField = findViewById(R.id.editText_email);
        emailField.setText("");
        EditText usernameField = findViewById(R.id.editText_username);
        usernameField.setText("");
        TextView dateField = findViewById(R.id.textView_date);
        dateField.setText(getResources().getString(R.string.date_of_birth));
        Log.d(Constants.LIFECYCLE,"onResume invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Constants.LIFECYCLE,"onPause invoked");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Constants.LIFECYCLE,"onStop invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Constants.LIFECYCLE,"onDestroy invoked");
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
                                    new StringBuilder().append(String.format("%02d - %02d - %04d", dayOfMonth, monthOfYear + 1, year)).toString());

                        }
                    }, startYear, currMonth, currDay);
            datePickerDialog.show();
        }
    }

    public void submitForm(View view) throws ParseException {
        EditText nameField = findViewById(R.id.editText_name);
        String name = nameField.getText().toString();
        if (name.equals("")) { nameField.setError(getResources().getString(R.string.error_field_required)); }
        EditText emailField = findViewById(R.id.editText_email);
        String email = emailField.getText().toString();
        if (email.equals("")) { emailField.setError(getResources().getString(R.string.error_field_required)); }
        EditText usernameField = findViewById(R.id.editText_username);
        String username = usernameField.getText().toString();
        if (username.equals("")) { usernameField.setError(getResources().getString(R.string.error_field_required)); }
        TextView dateField = findViewById(R.id.textView_date);
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
            startActivity(intent);
            dateField.setText("");
            Bundle bundle = new Bundle();
            bundle.putString(Constants.KEY_NAME, name);
            nameField.setText("");
            bundle.putString(Constants.KEY_EMAIL, email);
            emailField.setText("");
            bundle.putString(Constants.KEY_USERNAME, username);
            usernameField.setText("");
            if (date != null) bundle.putSerializable(Constants.KEY_DOB, date);

            intent.putExtra(Constants.KEY_USER_DATA, bundle);

        }
    }
}
