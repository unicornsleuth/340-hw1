package com.example.lyaho340hw1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class FormSuccessActivityTest {

    @Rule
    public ActivityScenarioRule<FormSuccessActivity> activityScenarioRule
            = new ActivityScenarioRule<>(FormSuccessActivity.class);

//    @Rule
//    public IntentsTestRule<FormSuccessActivity> intentsTestRule =
//            new IntentsTestRule<>(FormSuccessActivity.class);

    // this doesn't work : (, should have a @Before on it to set incoming state

    @Before
    public void setUp() {
        //Activity activity = TestUtils.getActivity();
        //FormSuccessActivity activity = Robolectric.setupActivity(FormSuccessActivity.class);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_NAME, "testname");
        intent.putExtra(Constants.KEY_USER_DATA, bundle);
       //activity.setIntent(intent);
    }

    @Test
    public void loadsViews() {
        onView(withId(R.id.generic_avatar))
                .check(matches(withContentDescription("generic avatar for profile picture")));
        onView(withId(R.id.textView_username))
                .check(matches(withContentDescription("displays user\'s username")));
        onView(withId(R.id.textView_name))
                .check(matches(withContentDescription("displays user's name")));
        onView(withId(R.id.textView_age))
                .check(matches(withContentDescription("displays user's age")));
        onView(withId(R.id.textView_bio))
                .check(matches(withContentDescription("displays user's bio")));
        onView(withId(R.id.textView_occupation))
                .check(matches(withContentDescription("displays user\'s occupation")));
    }

    @Test
    public void rotationSavesInformation() {
        Activity activity = TestUtils.getActivity();

        try {
            // don't currently have any state to save
            // rotate
            onView(withId(R.id.generic_avatar))
                    .check(matches(withContentDescription("generic avatar for profile picture")));
            onView(withId(R.id.textView_username))
                    .check(matches(withContentDescription("displays user\'s username")));
            onView(withId(R.id.textView_name))
                    .check(matches(withContentDescription("displays user's name")));
            onView(withId(R.id.textView_age))
                    .check(matches(withContentDescription("displays user's age")));
            onView(withId(R.id.textView_bio))
                    .check(matches(withContentDescription("displays user's bio")));
            onView(withId(R.id.textView_occupation))
                    .check(matches(withContentDescription("displays user\'s occupation")));
            // rotate back
        } catch (NoMatchingViewException e) {
            // don't currently have any state to save
            // rotate
            onView(withId(R.id.generic_avatar))
                    .check(matches(withContentDescription("generic avatar for profile picture")));
            onView(withId(R.id.textView_username))
                    .check(matches(withContentDescription("displays user\'s username")));
            onView(withId(R.id.textView_name))
                    .check(matches(withContentDescription("displays user's name")));
            onView(withId(R.id.textView_age))
                    .check(matches(withContentDescription("displays user's age")));
            onView(withId(R.id.textView_bio))
                    .check(matches(withContentDescription("displays user's bio")));
            onView(withId(R.id.textView_occupation))
                    .check(matches(withContentDescription("displays user\'s occupation")));
            // rotate back
        }
    }

    @Test
    public void usesStateInOnCreate() {

    }

}