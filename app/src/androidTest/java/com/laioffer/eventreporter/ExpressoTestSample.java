package com.laioffer.eventreporter;

/**
 * Created by Xiaoming Niu on 9/8/18.
 */
import android.support.test.espresso.action.ViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;



@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExpressoTestSample {
    private String mStringToBetyped;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void initValidString() {
        mStringToBetyped = "Espresso";
    }

    /**
     * Check if Login successfully, if yes, it should go to another EventActivity
     */
    @Test
    public void checkLoginSuccessful() {
        //Type username
        onView(withId(R.id.editTextLogin)).perform(ViewActions.clearText()).
                perform(ViewActions.typeText("1234"));

        //Type password
        onView(withId(R.id.editTextPassword)).perform(ViewActions.clearText()).
                perform(ViewActions.typeText("1234"));

        //Send intent and check intent
        onView(withId(R.id.submit)).perform(ViewActions.click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Check text
        onView(withId(R.id.Events)).check(matches(withText("Events")));
    }

    @Test
    public void checkShowToast() {
        //Type username
        onView(withId(R.id.editTextLogin)).perform(ViewActions.clearText()).
                perform(ViewActions.typeText("1234"));

        //Type password
        onView(withId(R.id.editTextPassword)).perform(ViewActions.clearText()).
                perform(ViewActions.typeText("1111"));

        //Send intent and check intent
        onView(withId(R.id.submit)).perform(ViewActions.click());

        //Check text
        onView(withId(R.id.Events)).check(matches(withText("Events")));
    }
}
