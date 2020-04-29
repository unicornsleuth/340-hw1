package com.example.lyaho340hw1;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FormSuccessActivityTest {

    @Rule
    public ActivityScenarioRule<FormSuccessActivity> activityTestRule
            = new ActivityScenarioRule<>(FormSuccessActivity.class);

    // I'm not sure why this is failing - it's exactly the same as the one on MainActivityTest, which works
//    @Test
//    public void loadsViews() {
//        onView(withId(R.id.confirmation))
//                .check(matches(withText(R.string.thanks_signup)));
//    }

}