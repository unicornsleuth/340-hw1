package com.example.lyaho340hw1;


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

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityNoSetupTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void emptySetUp() {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(targetContext, MainActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtra(Constants.KEY_USER_DATA, bundle);
        activityTestRule.launchActivity(intent);
    }

    @Test
    public void loadsPlaceHolderValues() {
        onView(withId(R.id.textView_username))
                .check(matches(withText("placeholder username")));
        onView(withId(R.id.textView_name))
                .check(matches(withText("placeholder name")));
        onView(withId(R.id.textView_age))
                .check(matches(withText("-1")));
        onView(withId(R.id.textView_bio))
                .check(matches(withContentDescription("placeholder bio")));
        onView(withId(R.id.textView_occupation))
                .check(matches(withContentDescription("placeholder occupation")));
    }
}
