package com.example.lyaho340hw1;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class FormSuccessActivityTest {

    @Rule
    public ActivityScenarioRule<FormSuccessActivity> activityTestRule
            = new ActivityScenarioRule<>(FormSuccessActivity.class);

    // I'm not sure why this is failing - it's exactly the same as the one on MainActivityTest, which works
    @Test
    public void loadsViews() {
        onView(withId(R.id.confirmation))
                .check(matches(withText("Thanks for Signing Up Unknown")));
    }

}