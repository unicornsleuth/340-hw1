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
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest  {

    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule
            = new ActivityScenarioRule<>(MainActivity.class);


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

   // @Test
    //public void checkIntent() {

    //}


}