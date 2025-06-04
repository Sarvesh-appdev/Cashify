package com.example.quickcash_summer15;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views
        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        submitButton = findViewById(R.id.submit);

        // Set click listener on submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get input values from EditTexts
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                boolean hasError = false;

                // Validate input
                if (TextUtils.isEmpty(email)) {
                    emailEditText.setError("Please enter an email");
                    hasError = true;
                }

                if (TextUtils.isEmpty(password)) {
                    passwordEditText.setError("Please enter a password");
                    hasError = true;
                }

                // Validate password using PasswordValidation class
                else if (!InputTesting.isValidPassword(password)) {
                    passwordEditText.setError("Password must be at least 8 characters long, " +
                            "contain uppercase, lowercase, digit, and special character.");
                    hasError = true;
                }
                if (hasError) return;

                // If everything is valid, display success message (this could be replaced with actual logic)
                Toast.makeText(MainActivity.this, "Form Submitted Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}