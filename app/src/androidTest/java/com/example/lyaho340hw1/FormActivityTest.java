package com.example.lyaho340hw1;

import android.app.Activity;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class FormActivityTest {

    @Rule
    public ActivityScenarioRule<FormActivity> activityScenarioRule
            = new ActivityScenarioRule<>(FormActivity.class);

    @Test
    public void loadsViews() {
        onView(withId(R.id.editText_name))
                .check(matches(withHint(R.string.enter_name)));
        onView(withId(R.id.editText_email))
                .check(matches(withHint(R.string.enter_email)));
        onView(withId(R.id.editText_username))
                .check(matches(withHint(R.string.enter_username)));
        onView(withId(R.id.editText_occupation))
                .check(matches(withHint(R.string.enter_occupation)));
        onView(withId(R.id.editText_date))
                .check(matches(withHint(R.string.date_of_birth)));
        onView(withId(R.id.button_date))
                .check(matches(withText(R.string.select_date)));
    }

    @Test
    public void emptyFieldValidation() {
        onView(withId(R.id.button_submit_form)).perform(scrollTo(), click());
        try {
            onView(withId(R.id.editText_name)).check(matches(hasErrorText("required field")));
            onView(withId(R.id.editText_email)).check(matches(hasErrorText("required field")));
            onView(withId(R.id.editText_username)).check(matches(hasErrorText("required field")));
            onView(withId(R.id.editText_occupation)).check(matches(hasErrorText("required field")));
            onView(withId(R.id.editText_bio)).check(matches(hasErrorText("required field")));
            // for some reason this doesn't work with TextViews? -- yes, use getError() and setError() instead
            onView(withId(R.id.editText_date)).check(matches(hasErrorText("required field")));
        } catch (NoMatchingViewException e) {
            // copied your code from your ClickDemoActivityTest - not sure why we repeat same code in catch
            onView(withId(R.id.button_submit_form)).perform(scrollTo(), click());
            onView(withId(R.id.editText_name)).check(matches(hasErrorText("required field")));
            onView(withId(R.id.editText_email)).check(matches(hasErrorText("required field")));
            onView(withId(R.id.editText_username)).check(matches(hasErrorText("required field")));
            onView(withId(R.id.editText_occupation)).check(matches(hasErrorText("required field")));
            onView(withId(R.id.editText_bio)).check(matches(hasErrorText("required field")));
            onView(withId(R.id.editText_date)).check(matches(hasErrorText("required field")));
        }
    }

    @Test
    public void emailValidationCheck() {
        try {
            onView(withId(R.id.editText_name)).perform(click()).perform(typeText("testname"));
            onView(withId(R.id.editText_email)).perform(click()).perform(typeText("testemail"));
            onView(withId(R.id.editText_username)).perform(click()).perform(typeText("testusername"));
            onView(withId(R.id.editText_occupation)).perform(click()).perform(typeText("testoccupation"));
            onView(withId(R.id.editText_bio)).perform(click()).perform(typeText("testbio"));
            ViewActions.closeSoftKeyboard();
            onView(withId(R.id.date_picker))
                    .perform(PickerActions.setDate(1997, 5, 7));
            onView(withId(R.id.button_date)).perform(click());
            // upon submit, email field should get an error
            onView(withId(R.id.button_submit_form)).perform(scrollTo(), click());
            onView(withId(R.id.editText_email)).check(matches(hasErrorText("must enter valid email address")));
        } catch (NoMatchingViewException e) {
            onView(withId(R.id.date_picker))
                    .perform(PickerActions.setDate(1991, 6, 30));
            onView(withId(R.id.editText_name)).perform(click()).perform(typeText("test name"));
            onView(withId(R.id.editText_email)).perform(click()).perform(typeText("testemail"));
            onView(withId(R.id.editText_username)).perform(click()).perform(typeText("testusername"));
            onView(withId(R.id.editText_occupation)).perform(click()).perform(typeText("test occupation"));
            onView(withId(R.id.editText_bio)).perform(click()).perform(typeText("test bio"));
            // upon submit, email field should get an error
            onView(withId(R.id.button_submit_form)).perform(scrollTo(), click());
            onView(withId(R.id.editText_email)).check(matches(hasErrorText("must enter valid email address")));
        }
    }

    @Test
    public void dateValidationCheck() {
        try {
            onView(withId(R.id.editText_name)).perform(click()).perform(typeText("test name"));
            onView(withId(R.id.editText_email)).perform(click()).perform(typeText("test@test.com"));
            onView(withId(R.id.editText_username)).perform(click()).perform(typeText("testusername"));
            onView(withId(R.id.editText_occupation)).perform(click()).perform(typeText("test occupation"));
            onView(withId(R.id.editText_bio)).perform(click()).perform(typeText("test bio"));
            onView(withId(R.id.date_picker))
                    .perform(PickerActions.setDate(2010, 5, 7));
            onView(withId(R.id.button_date)).perform(click());
            // upon submit, date field should get an error
            onView(withId(R.id.button_submit_form)).perform(scrollTo(), click());
            onView(withId(R.id.editText_date)).check(matches(hasErrorText("must be 18 years or older")));
        } catch (NoMatchingViewException e) {
            onView(withId(R.id.editText_name)).perform(click()).perform(typeText("test name"));
            onView(withId(R.id.editText_email)).perform(click()).perform(typeText("larissa@unicorn.com"));
            onView(withId(R.id.editText_username)).perform(click()).perform(typeText("test username"));
            onView(withId(R.id.editText_occupation)).perform(click()).perform(typeText("test occupation"));
            onView(withId(R.id.editText_bio)).perform(click()).perform(typeText("test bio"));
            onView(withId(R.id.date_picker))
                    .perform(PickerActions.setDate(2010, 5, 7));
            onView(withId(R.id.button_date)).perform(click());
            // upon submit, date field should get an error
            onView(withId(R.id.button_submit_form)).perform(scrollTo(), click());
            onView(withId(R.id.editText_date)).check(matches(hasErrorText("must be 18 years or older")));
        }
    }

    @Test
    public void rotationSavesInformation() {
        Activity activity = TestUtils.getActivity();
        try {
            // SET VALUES
            onView(withId(R.id.editText_name)).perform(click()).perform(typeText("test name"));
            onView(withId(R.id.editText_email)).perform(click()).perform(typeText("unicorn@unicorn.com"));
            onView(withId(R.id.editText_username)).perform(click()).perform(typeText("testusername"));
            onView(withId(R.id.editText_occupation)).perform(click()).perform(typeText("test occupation"));
            onView(withId(R.id.editText_bio)).perform(click()).perform(typeText("test bio"));

            onView(withId(R.id.date_picker))
                    .perform(PickerActions.setDate(2010, 5, 7));
            onView(withId(R.id.button_date)).perform(click());
            // ROTATE
            TestUtils.rotateScreen(activity);
            // CHECK VALUES
            onView(withId(R.id.editText_name)).check(matches(withText("test name")));
            onView(withId(R.id.editText_email)).check(matches(withText("unicorn@unicorn.com")));
            onView(withId(R.id.editText_username)).check(matches(withText("testusername")));
            onView(withId(R.id.editText_occupation)).check(matches(withText("test occupation")));
            onView(withId(R.id.editText_bio)).check(matches(withText("test bio")));
            onView(withId(R.id.editText_date)).check(matches(withText("07 - 05 - 2010")));
            // ROTATE
            TestUtils.rotateScreen(activity);
        } catch (NoMatchingViewException e) {
            // SET VALUES
            onView(withId(R.id.editText_name)).perform(click()).perform(typeText("test name"));
            onView(withId(R.id.editText_email)).perform(click()).perform(typeText("unicorn@unicorn.com"));
            onView(withId(R.id.editText_username)).perform(click()).perform(typeText("testusername"));
            onView(withId(R.id.editText_occupation)).perform(click()).perform(typeText("test occupation"));
            onView(withId(R.id.editText_bio)).perform(click()).perform(typeText("test bio"));

            onView(withId(R.id.date_picker))
                    .perform(PickerActions.setDate(2010, 5, 7));
            onView(withId(R.id.button_date)).perform(click());
            // ROTATE
            TestUtils.rotateScreen(activity);
            // CHECK VALUES
            onView(withId(R.id.editText_name)).check(matches(withText("test name")));
            onView(withId(R.id.editText_email)).check(matches(withText("unicorn@unicorn.com")));
            onView(withId(R.id.editText_username)).check(matches(withText("testusername")));
            onView(withId(R.id.editText_occupation)).check(matches(withText("test occupation")));
            onView(withId(R.id.editText_bio)).check(matches(withText("test bio")));
            onView(withId(R.id.editText_date)).check(matches(withText("07 - 05 - 2010")));
            // ROTATE
            TestUtils.rotateScreen(activity);
        }
    }


}