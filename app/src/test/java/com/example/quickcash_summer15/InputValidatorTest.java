package com.example.quickcash_summer15;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputValidatorTest {

    public void testUsername(){
        assertEquals("Username cannot be empty", InputTesting.validateUsername(""));
        assertEquals("Username cannot be empty",InputTesting.validateUsername(""));
        assertNull(InputTesting.validateUsername("Alex"));
    }

    public void testEmail(){
        assertEquals("Email cannot be empty", InputTesting.validateEmail(""));
        assertEquals("Invalid email format",InputTesting.validateEmail("alex@"));
        assertEquals("Invalid email format", InputTesting.validateEmail("invalid.com"));
        assertNull(InputTesting.validateEmail("alex@example.com"));
    }


    public void testPassword(){
        assertEquals("Password cannot be empty",InputTesting.validatePassword(""));
        assertEquals("Password must be at least 6 characters", InputTesting.validatePassword("123"));
        assertNull(InputTesting.validatePassword("strongpass123"));
    }

}
