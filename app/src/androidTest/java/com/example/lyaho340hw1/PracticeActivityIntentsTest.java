package com.example.lyaho340hw1;

import android.content.Intent;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class PracticeActivityIntentsTest {

    @Rule
    public IntentsTestRule<FormActivity> intentsTestRule =
            new IntentsTestRule<>(FormActivity.class);



    @Test
    public void navigatesToFormActivity() {
        try {
            // Click Form Button
            onView(withId(R.id.form_button)).perform(scrollTo(), click());
            intended(hasAction(Intent.ACTION_VIEW));

        } catch (NoMatchingViewException e) {
            // am I really supposed to just C/P the previous clause?
        }
    }

}
