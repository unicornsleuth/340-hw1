package com.example.lyaho340hw1;

import android.app.Activity;
import android.widget.ImageView;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EspressoTestingIdlingResource implements IdlingResource {

    private static final String RESOURCE = "GLOBAL";
    private ResourceCallback resourceCallback;
    private boolean isIdle;

    private static CountingIdlingResource mCountingIdlingResource =
            new CountingIdlingResource(RESOURCE);

    public static void increment() {
        mCountingIdlingResource.increment();
    }

    public static void decrement() {
        mCountingIdlingResource.decrement();
    }

    public static IdlingResource getIdlingResource() {
        return mCountingIdlingResource;
    }

    @Override
    public String getName() {
        return EspressoTestingIdlingResource.class.getName();
    }

    public boolean isIdleNow() {
        Activity currentActivity = TestUtils.getActivity();
        if (isIdle) return true;
        if (currentActivity == null) return false;

        ImageView img = (ImageView) currentActivity.findViewById(R.id.match_picture);

        isIdle = img == null;
        if (isIdle) {
            resourceCallback.onTransitionToIdle();
        }
        return isIdle;
    }

    @Override
    public void registerIdleTransitionCallback(
            ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }
}