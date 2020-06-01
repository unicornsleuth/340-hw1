package com.example.lyaho340hw1;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class SettingsFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void setUp() {
        onView(withText(R.string.tab_name_settings)).perform(click());
    }

    @Test
    public void saveSettingsButton() {
        onView(withId(R.id.editText_max_distance)).perform(click()).perform(typeText("42"));
        // scroll to minAge, input
        onView(withId(R.id.editText_min_age)).perform(scrollTo())
                .perform(click()).perform(typeText("25"));
        // maxAge, input
        onView(withId(R.id.editText_max_age)).perform(scrollTo())
                .perform(click()).perform(typeText("45"));
        // scroll to button, click
        onView(withId(R.id.button_save_settings)).perform(scrollTo()).perform(click());
        // check toast
    }

    @Test
    public void spinnerChange() {
        // scroll to gender, switch opt 2
        // scroll to lookingForGender, switch opt 2
        // scroll to privacy, switch opt 2
        // scroll to save button, click
        // scroll to gender, switch opt 3
        // scroll to lookingForGender, switch opt 3
        // scroll to save button, click
        // scroll to gender, switch opt 4
        // scroll to lookingForGender, switch opt 4
        // scroll to save button, click
    }
}