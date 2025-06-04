    package com.example.quickcash_summer15;

    import android.content.Context;
    import android.widget.Toast;

    public class InputTesting {

        public static String validateUsername(String username){
            if(username == null || username.trim().isEmpty()){
                return "Username cannot be empty";
            }
            return null;
        }

        public static String validateEmail(Context context, String email){
            if(email == null || email.trim().isEmpty()){
                Toast.makeText(context, "Email cannot be empty", Toast.LENGTH_SHORT).show();
            }
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9+_.-]+\\.[A-Za-z]{2,6}$";
            if(!email.matches(emailRegex)){
                return "Invalid email format";
            }
            return null;
        }

        public static String validatePassword(String password){
            if(password == null || password.trim().isEmpty()){
                return "Password cannot be empty";
            }else if(password.length() < 6){
                return "Password must be at least 6 characters";
            }
            return null;
        }

        public static boolean isValidPassword(String password) {
            if (password == null || password.isEmpty()) {
                return false;
            }
            if (password.length() < 8) {
                return false; // Minimum length of 8 characters
            }
            if (!password.matches(".*[A-Z].*")) {
                return false; // At least one uppercase letter
            }
            if (!password.matches(".*[a-z].*")) {
                return false; // At least one lowercase letter
            }
            if (!password.matches(".*\\d.*")) {
                return false; // At least one digit
            }
            if (!password.matches(".*[@#$%^&+=].*")) {
                return false; // At least one special character
            }
            return true;
        }

    }
