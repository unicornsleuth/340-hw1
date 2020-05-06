package com.example.lyaho340hw1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class FormSuccessActivityTest {

    @Rule
    public ActivityTestRule<FormSuccessActivity> activityTestRule
            = new ActivityTestRule<>(FormSuccessActivity.class, true, false);

    @Before
    public void setUp() {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(targetContext, FormSuccessActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_NAME, "testname");
        bundle.putString(Constants.KEY_EMAIL, "testemail");
        bundle.putString(Constants.KEY_USERNAME, "testusername");
        bundle.putString(Constants.KEY_OCCUPATION, "testoccupation");
        bundle.putString(Constants.KEY_BIO, "testbio");
        bundle.putSerializable(Constants.KEY_DOB, new Date());
        intent.putExtra(Constants.KEY_USER_DATA, bundle);
        //activity.setIntent(intent);
        activityTestRule.launchActivity(intent);
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
        Activity activity = activityTestRule.getActivity();

        try {
            // don't currently have any state to save
            TestUtils.rotateScreen(activity);
            // rotate
            onView(withId(R.id.textView_username))
                    .check(matches(withText("testusername")));
            onView(withId(R.id.textView_name))
                    .check(matches(withText("testname")));
            onView(withId(R.id.textView_age))
                    .check(matches(withText("0")));
            onView(withId(R.id.textView_bio))
                    .check(matches(withText("testbio")));
            onView(withId(R.id.textView_occupation))
                    .check(matches(withText("testoccupation")));
            // rotate back
            TestUtils.rotateScreen(activity);
        } catch (NullPointerException e) {
            // don't currently have any state to save
            // rotate
            if (activity != null) TestUtils.rotateScreen(activity);
            // check data
            onView(withId(R.id.generic_avatar))
                    .check(matches(withContentDescription("generic avatar for profile picture")));
            onView(withId(R.id.textView_username))
                    .check(matches(withText("testusername")));
            onView(withId(R.id.textView_name))
                    .check(matches(withText("testname")));
            onView(withId(R.id.textView_age))
                    .check(matches(withText("0")));
            onView(withId(R.id.textView_bio))
                    .check(matches(withText("testbio")));
            onView(withId(R.id.textView_occupation))
                    .check(matches(withText("testoccupation")));
            // rotate back
            if (activity != null) TestUtils.rotateScreen(activity);
        }
    }

    @Test
    public void usesStateInOnCreate() {

    }

}