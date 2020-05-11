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
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(targetContext, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_NAME, "testname");
        bundle.putString(Constants.KEY_EMAIL, "testemail");
        bundle.putString(Constants.KEY_USERNAME, "testusername");
        bundle.putString(Constants.KEY_OCCUPATION, "testoccupation");
        bundle.putString(Constants.KEY_BIO, "testbio");
        bundle.putSerializable(Constants.KEY_DOB, new Date());
        intent.putExtra(Constants.KEY_USER_DATA, bundle);
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
    public void swipes() throws InterruptedException {
//       try {
            // Swipe to Matches - NOT WORKING
            onView(withId(R.id.generic_avatar)).perform(swipeLeft());

            // Click on Matches Tab
            //onView(withText(R.string.tab_name_matches)).perform(click());

            // Check Matches
            onView(withId(R.id.hotdog_cat_matches))
                    .check(matches(withContentDescription(R.string.is_cat_with_hotdog)));
            // Swipe to Settings
            onView(withId(R.id.hotdog_cat_matches)).perform(swipeLeft());
            // Check Settings
            onView(withId(R.id.hotdog_cat_settings))
                    .check(matches(withContentDescription(R.string.is_cat_with_hotdog)));
            // Swipe back to Profile
            onView(withId(R.id.hotdog_cat_settings)).perform(swipeRight());
            onView(withId(R.id.hotdog_cat_matches)).perform(swipeRight());
            // Check Profile Image
            onView(withId(R.id.generic_avatar))
                    .check(matches(withContentDescription("generic avatar for profile picture")));
//        } catch (NoMatchingViewException e) {
//            Log.d("MainActivityTest", "swipe failed");
//        }
    }

    @Test
    public void rotationSavesInformation() {
        Activity activity = activityTestRule.getActivity();

        try {
            // don't currently have any state to save
            TestUtils.rotateScreen(activity);
            // rotate
//            onView(withId(R.id.textView_username))
//                    .check(matches(withText("testusername")));
            onView(withId(R.id.textView_name))
                    .check(matches(withText("testname")));
            onView(withId(R.id.textView_age))
                    .check(matches(withText("0")));
            onView(withId(R.id.textView_bio))
                    .check(matches(withText("testbio")));
//            onView(withId(R.id.textView_occupation))
//                    .check(matches(withText("testoccupation")));
            // rotate back
            TestUtils.rotateScreen(activity);
        } catch (NullPointerException e) {
            // don't currently have any state to save
            // rotate
            if (activity != null) TestUtils.rotateScreen(activity);
            // check data
            onView(withId(R.id.generic_avatar))
                    .check(matches(withContentDescription("generic avatar for profile picture")));
//            onView(withId(R.id.textView_username))
//                    .check(matches(withText("testusername")));
            onView(withId(R.id.textView_name))
                    .check(matches(withText("testname")));
            onView(withId(R.id.textView_age))
                    .check(matches(withText("0")));
            onView(withId(R.id.textView_bio))
                    .check(matches(withText("testbio")));
//            onView(withId(R.id.textView_occupation))
//                    .check(matches(withText("testoccupation")));
            // rotate back
            if (activity != null) TestUtils.rotateScreen(activity);
        }
    }

}