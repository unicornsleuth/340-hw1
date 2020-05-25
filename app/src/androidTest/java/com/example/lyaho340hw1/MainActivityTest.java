package com.example.lyaho340hw1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.test.espresso.IdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class, true, false);

    private MainActivity mainActivity = null;
    private IdlingResource idlingResource;

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

//    @Before
//    public void registerIdlingResource() {
//        mainActivity = activityTestRule.getActivity();
//        //idlingResource = mainActivity.getIdlingResource();
//        idlingResource = EspressoTestingIdlingResource.getIdlingResource();
//        IdlingRegistry.getInstance().register(idlingResource);
//    }

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
    public void tabsLoadViews() throws InterruptedException {
        // Click on Matches Tab
        onView(withText(R.string.tab_name_matches)).perform(click());
        Thread.sleep(15000);
        //onView(allOf(withId(R.id.match_picture), isDisplayingAtLeast(30))).perform(scrollTo());
        // Check Matches
        onView(allOf(withId(R.id.match_picture), isDisplayingAtLeast(30)))
                .check(matches(withContentDescription(R.string.match_picture)));
        onView(allOf(withId(R.id.match_name), isDisplayed()))
                .check(matches(withContentDescription(R.string.match_name)));
        onView(allOf(withId(R.id.like_button), isDisplayed()))
                .check(matches(anyOf(withContentDescription(R.string.liked), withContentDescription(R.string.not_liked))));

        // Click on Settings tab
        onView(withText(R.string.tab_name_settings)).perform(click());

        // Check Settings
        onView(withId(R.id.hotdog_cat_settings))
               .check(matches(withContentDescription(R.string.is_cat_with_hotdog)));

        // Click on Profile tab
        onView(withText(R.string.tab_name_profile)).perform(click());

        // Check Profile Image
        onView(withId(R.id.generic_avatar))
                .check(matches(withContentDescription("generic avatar for profile picture")));
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


//    @After
//    public void unregisterIdlingResource() {
//        if (idlingResource != null) {
//            IdlingRegistry.getInstance().unregister(idlingResource);
//        }
//    }

}