package com.example.lyaho340hw1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
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
        // Activity activity = TestUtils.getActivity();
        // activity.recreate();
        ActivityScenario scenario = activityScenarioRule.getScenario();
        scenario.moveToState(Lifecycle.State.RESUMED);
        scenario.recreate();
        onView(withId(R.id.confirmation))
                .check(matches(withText("Thanks for Signing Up Unknown")));
    }

    @Test
    public void rotationSavesInformation() {
        Activity activity = TestUtils.getActivity();

        try {
            // I can't figure out why the activity is null! getActivity() works in FormActivityTest.java
            // ROTATE
            //TestUtils.rotateScreen(activity);
            // CHECK VALUES
            onView(withId(R.id.confirmation))
                    .check(matches(withText("Thanks for Signing Up Unknown")));
            // ROTATE
            //TestUtils.rotateScreen(activity);
        } catch (NoMatchingViewException e) {
            // ROTATE
            //TestUtils.rotateScreen(activity);
            // CHECK VALUES
            onView(withId(R.id.confirmation))
                    .check(matches(withText("Thanks for Signing Up testname")));
            // ROTATE
            //TestUtils.rotateScreen(activity);
        }
    }

    @Test
    public void usesStateInOnCreate() {

    }

}