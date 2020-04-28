package com.example.lyaho340hw1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
    }

    public void onDateClick(View view) {
        if (view == findViewById(R.id.button_date)) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            int startYear = c.get(YEAR) - 18;
            int currMonth = c.get(Calendar.MONTH);
            int currDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            TextView dateField = findViewById(R.id.in_date);
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
        TextView dateField = findViewById(R.id.in_date);
        String dateString = dateField.getText().toString();
        Date date;
        if (dateString.equals("")) {
            dateField.setError(getResources().getString(R.string.error_field_required));
            date = null;
        } else {
            Calendar birthday = Calendar.getInstance();
            date = new SimpleDateFormat("dd - MM - yyyy").parse(dateString);
            birthday.setTime(date);

            Calendar checkDate = Calendar.getInstance();
            checkDate.add(YEAR, -18); // 18 years ago today
            if (!birthday.before(checkDate)) {
                dateField.setError(getResources().getString(R.string.check_age));
                //dateField.setHint(new StringBuilder("").append(checkDate.get(Calendar.DAY_OF_MONTH))
                  //                      .append(" - ").append(checkDate.get(Calendar.MONTH))
                    //                    .append(" - ").append(checkDate.get(Calendar.YEAR))); }
                dateField.setText(getResources().getString(R.string.check_age)); }

        }


        if ((nameField.getError() == null) &&
                (emailField.getError() == null) &&
                (usernameField.getError() == null) &&
                (dateField.getError() == null)) {
            Intent intent = new Intent(FormActivity.this, FormSuccessActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            startActivity(intent);
            dateField.setText("");
            Bundle bundle = new Bundle();
            bundle.putString("NAME", name);
            nameField.setText("");
            bundle.putString("EMAIL", email);
            emailField.setText("");
            bundle.putString("USERNAME", username);
            usernameField.setText("");
            if (date != null) bundle.putSerializable("DATE", date);

            intent.putExtra("USER_DATA_BUNDLE", bundle);

        }
    }
}
