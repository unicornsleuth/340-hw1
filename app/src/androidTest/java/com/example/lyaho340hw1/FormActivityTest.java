package com.example.lyaho340hw1;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class FormActivityTest {

    @Rule
    public ActivityScenarioRule<FormActivity> activityTestRule
            = new ActivityScenarioRule<>(FormActivity.class);

    @Test
    public void loadsViews() {
        onView(withId(R.id.editText_name))
                .check(matches(withHint(R.string.enter_name)));
        onView(withId(R.id.editText_email))
                .check(matches(withHint(R.string.enter_email)));
        onView(withId(R.id.editText_username))
                .check(matches(withHint(R.string.enter_username)));
        onView(withId(R.id.textView_date))
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
            // for some reason this doesn't work with TextViews
            // onView(withId(R.id.textView_date)).check(matches(hasErrorText("required field")));
        } catch (NoMatchingViewException e) {
            // copied your code from your ClickDemoActivityTest - not sure why we repeat same code in catch
            onView(withId(R.id.button_submit_form)).perform(scrollTo(), click());
            onView(withId(R.id.editText_name)).check(matches(hasErrorText("required field")));
            onView(withId(R.id.editText_email)).check(matches(hasErrorText("required field")));
            onView(withId(R.id.editText_username)).check(matches(hasErrorText("required field")));
            //onView(withId(R.id.textView_date)).check(matches(hasErrorText("required field")));
        }
    }
}