package com.example.quickcash_summer15;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button submitButton;
    private Spinner roleSpinner;

    FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views
        emailEditText = findViewById(R.id.emailBox);
        passwordEditText = findViewById(R.id.passwordBox);
        submitButton = findViewById(R.id.registerButton);
        roleSpinner = findViewById(R.id.roleSpinner);

        Auth = FirebaseAuth.getInstance();

        loadRoleSpinner();

        // Set click listener on submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get input values from EditTexts
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String selectedRole = roleSpinner.getSelectedItem().toString();

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
                Auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = Auth.getCurrentUser();
                                if (user != null) {
                                    String uid = user.getUid();

                                    // Save to Firebase Realtime Database
                                    DatabaseReference dbRef = FirebaseDatabase.getInstance()
                                            .getReference("Users").child(uid);

                                    Map<String, Object> userData = new HashMap<>();
                                    userData.put("email", email);
                                    userData.put("role", selectedRole);

                                    dbRef.setValue(userData).addOnCompleteListener(dbTask -> {
                                        if (dbTask.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                                            // Navigate to next screen or login page here if needed
                                        } else {
                                            Toast.makeText(getApplicationContext(),
                                                    "Database Error: " + dbTask.getException().getMessage(),
                                                   Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Auth Error: " + task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                 });

                // If everything is valid, display success message (this could be replaced with actual logic)
                Toast.makeText(MainActivity.this, "Form Submitted Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadRoleSpinner() {
        Spinner roleSpinner = findViewById(R.id.roleSpinner);
        List<String> roles = new ArrayList<>();
        roles.add("Employee");
        roles.add("Employer");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, roles);
        spinnerAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        roleSpinner.setAdapter(spinnerAdapter);
    }
}