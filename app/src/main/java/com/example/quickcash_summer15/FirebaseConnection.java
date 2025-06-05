package com.example.quickcash_summer15;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseConnection {
    private FirebaseDatabase firebaseDatabase;

    private DatabaseReference dbref;

    private static final String FIREBASE_URL = "https://quickcash-summer15-default-rtdb.firebaseio.com/";

    private TextView textView;

    private void connectFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance(FIREBASE_URL);
        dbref = firebaseDatabase.getReference("message");
    }

    private void writeToFirebase() {
        dbref.setValue("Hello  3130");
    }

    private void listentoDataChange() {

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String readValue = snapshot.getValue(String.class);
                textView.setText("Success:" + readValue);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}

