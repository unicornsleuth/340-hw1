package com.example.lyaho340hw1;

import android.content.Intent;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.core.AllOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class FormActivityIntentsTest {

    @Rule
    public IntentsTestRule<FormActivity> intentsTestRule =
            new IntentsTestRule<>(FormActivity.class);



    @Test
    public void sendsInformation() {
        try {
            // SET VALUES
            onView(withId(R.id.editText_name)).perform(click()).perform(typeText("test name"));
            onView(withId(R.id.editText_email)).perform(click()).perform(typeText("test@test.com"));
            onView(withId(R.id.editText_username)).perform(click()).perform(typeText("testusername"));
            onView(withId(R.id.editText_occupation)).perform(click()).perform(typeText("test occupation"));
            onView(withId(R.id.editText_bio)).perform(click()).perform(typeText("test bio"));
            closeSoftKeyboard();
            onView(withId(R.id.date_picker))
                    .perform(PickerActions.setDate(1990, 5, 7));
            onView(withId(R.id.button_date)).perform(scrollTo(), click());
            // SUBMIT - CHECK INTENT
            //Intents.init();
            onView(withId(R.id.button_submit_form)).perform(scrollTo(), click());
            intended(AllOf.allOf(
                    hasComponent(FormSuccessActivity.class.getName()),
                    hasAction(Intent.ACTION_VIEW),
                    hasExtraWithKey(Constants.KEY_USER_DATA)));

//            onView(withId(R.id.editText_name)).check(matches(withText("test name")));
//            onView(withId(R.id.editText_email)).check(matches(withText("unicorn@unicorn.com")));
//            onView(withId(R.id.editText_username)).check(matches(withText("testusername")));
//            onView(withId(R.id.editText_occupation)).check(matches(withText("test occupation")));
//            onView(withId(R.id.editText_bio)).check(matches(withText("test bio")));
//            onView(withId(R.id.editText_date)).check(matches(withText("07 - 05 - 2010")));

        } catch (NoMatchingViewException e) {
        // am I really supposed to just C/P the previous clause?
        }
    }

}
