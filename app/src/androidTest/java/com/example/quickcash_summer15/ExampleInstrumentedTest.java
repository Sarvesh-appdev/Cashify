package com.example.quickcash_summer15;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.quickcash_summer15", appContext.getPackageName());
    }

    @Test
    public void testEmptyEmailAndPassword() {
        // Click the submit button without entering email or password
        onView(withId(R.id.registerButton)).perform(click());

        // Check if email field shows an error
        onView(withId(R.id.emailBox))
                .check(matches(hasErrorText("Please enter an email")));

        // Check if password field shows an error
        onView(withId(R.id.passwordBox))
                .check(matches(hasErrorText("Please enter a password")));
    }

    @Test
    public void testInvalidPassword() {
        // Enter valid email but invalid password
        onView(withId(R.id.emailBox)).perform(typeText("test@example.com"), closeSoftKeyboard());
        onView(withId(R.id.passwordBox)).perform(typeText("pass"), closeSoftKeyboard());

        // Click the submit button
        onView(withId(R.id.registerButton)).perform(click());

        // Check if password field shows an error for invalid password
        onView(withId(R.id.passwordBox))
                .check(matches(hasErrorText("Password must be at least 8 characters long, contain uppercase, lowercase, digit, and special character.")));
    }

    @Test
    public void testValidEmailAndPassword() {
        // Enter valid email and valid password
        onView(withId(R.id.emailBox)).perform(typeText("test@example.com"), closeSoftKeyboard());
        onView(withId(R.id.passwordBox)).perform(typeText("Password@123"), closeSoftKeyboard());

        // Click the submit button
        onView(withId(R.id.registerButton)).perform(click());

        // Verify that no error messages are shown
        onView(withId(R.id.emailBox)).check(matches(withText("test@example.com")));
    }


}