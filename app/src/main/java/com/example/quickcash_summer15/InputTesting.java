    package com.example.quickcash_summer15;

    public class InputTesting {

        public static String validateUsername(String username){
            if(username == null || username.trim().isEmpty()){
                return "Username cannot be empty";
            }
            return null;
        }

        public static String validateEmail(String email){
            if(email == null || email.trim().isEmpty()){
                return "Email cannot be empty";
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
    }
