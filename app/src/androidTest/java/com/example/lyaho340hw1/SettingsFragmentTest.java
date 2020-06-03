package com.example.lyaho340hw1;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class SettingsFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void setUp() {
        onView(withText(R.string.tab_name_settings)).perform(click());
    }

    @Test
    public void saveSettingsButton() {
        onView(withId(R.id.editText_max_distance)).perform(click()).perform(typeText("42"));
        // scroll to minAge, input
        onView(withId(R.id.editText_min_age)).perform(scrollTo())
                .perform(click()).perform(typeText("25"));
        // maxAge, input
        onView(withId(R.id.editText_max_age)).perform(scrollTo())
                .perform(click()).perform(typeText("45"));
        // scroll to button, click
        onView(withId(R.id.button_save_settings)).perform(scrollTo()).perform(click());
        // check toast
    }

    @Test
    public void spinnerChange() {
        onView(withId(R.id.editText_max_distance)).perform(click()).perform(typeText("42"));
        // scroll to minAge, input
        onView(withId(R.id.editText_min_age)).perform(scrollTo())
                .perform(click()).perform(typeText("25"));
        // maxAge, input
        onView(withId(R.id.editText_max_age)).perform(scrollTo())
                .perform(click()).perform(typeText("45"));

        Context context = activityTestRule.getActivity().getApplicationContext();
        // scroll to gender, switch opt 2
        String[] genders = context.getResources().getStringArray(R.array.genders);
        onView(withId(R.id.spinner_gender)).perform(scrollTo()).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(genders[1]))).perform(click());
        onView(withId(R.id.spinner_gender)).check(matches(withSpinnerText(containsString(genders[1]))));
        // scroll to lookingForGender, switch opt 2
        onView(withId(R.id.spinner_looking_for_gender)).perform(scrollTo()).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(genders[1]))).perform(click());
        onView(withId(R.id.spinner_looking_for_gender)).check(matches(withSpinnerText(containsString(genders[1]))));
        // scroll to privacy, switch opt 2
        String[] privacy = context.getResources().getStringArray(R.array.account_privacy);
        onView(withId(R.id.spinner_account_privacy)).perform(scrollTo()).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(privacy[1]))).perform(click());
        onView(withId(R.id.spinner_account_privacy)).check(matches(withSpinnerText(containsString(privacy[1]))));
        // scroll to save button, click
        onView(withId(R.id.button_save_settings)).perform(scrollTo()).perform(click());
    }
}