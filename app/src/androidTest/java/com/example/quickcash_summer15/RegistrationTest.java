package com.example.quickcash_summer15;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RegistrationTest {

    private static final String APP_PACKAGE = "com.example.quickcash_summer15"; // Your app's package name
    private static final int LAUNCH_TIMEOUT = 5000;
    private UiDevice device;

    @Before
    public void setUp() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(getInstrumentation());

        // Launch the app
        Context context = getInstrumentation().getContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(APP_PACKAGE);
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear previous instances
            context.startActivity(intent);
        }

        // Wait for the app to launch
        device.wait(Until.hasObject(By.pkg(APP_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void testSignInFlow() throws Exception {
        // Enter name
        UiObject nameField = device.findObject(new UiSelector().resourceId(APP_PACKAGE + ":id/name"));
        assertTrue("Name field not found", nameField.exists());
        nameField.setText("Test User");

        // Enter email
        UiObject emailField = device.findObject(new UiSelector().resourceId(APP_PACKAGE + ":id/emailBox"));
        assertTrue("Email field not found", emailField.exists());
        emailField.setText("testuser@example.com");

        // Enter password
        UiObject passwordField = device.findObject(new UiSelector().resourceId(APP_PACKAGE + ":id/passwordBox"));
        assertTrue("Password field not found", passwordField.exists());
        passwordField.setText("TestPassword123");

        // Click the "Register" button
        UiObject registerButton = device.findObject(new UiSelector().resourceId(APP_PACKAGE + ":id/registerButton"));
        assertTrue("Register button not found", registerButton.exists());
        registerButton.click();
    }

}
