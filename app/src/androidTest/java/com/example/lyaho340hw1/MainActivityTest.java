package com.example.lyaho340hw1;

import android.app.Activity;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest  {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void hasImageOnScreen() {
        onView(withId(R.id.hotdog_cat))
                .check(matches(withContentDescription(R.string.is_cat_with_hotdog)));
    }

    @Test
    public void hasButtonOnScreen() {
        onView(withId(R.id.form_button))
                .check(matches(withText(R.string.go_to_form_button)));
    }

    @Test
    public void rotationSavesInformation() {
        Activity activity = activityTestRule.getActivity();
        try {
            // don't currently have any state to save
            // rotate
            TestUtils.rotateScreen(activity);
            // rotate back
            TestUtils.rotateScreen(activity);
        } catch (NullPointerException e) {
            // don't currently have any state to save
            // rotate
            if (activity != null) TestUtils.rotateScreen(activity);
            // check data

            // rotate back
            if (activity != null) TestUtils.rotateScreen(activity);
        }
    }


}