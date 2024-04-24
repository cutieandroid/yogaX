package com.example.rob_lifefitnessdiet.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rob_lifefitnessdiet.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class support extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        // Initialize Firebase Authentication
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        // Check if a user is currently signed in
        if (currentUser != null) {
            // Get the user's UID
            String userId = currentUser.getUid();

            // Initialize Firebase Realtime Database reference
            DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

            // Read user data from Realtime Database
            usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Get user information from dataSnapshot
                        String username = dataSnapshot.child("fullname").getValue(String.class);
                        double lifeincrease = dataSnapshot.child("lifeincreased").getValue(Double.class);

                        // Display user information in TextViews
                        TextView usernameTextView = findViewById(R.id.username);
                        usernameTextView.setText(username);

                        TextView lifeincreaseTextView = findViewById(R.id.lifeincrease);
                        lifeincreaseTextView.setText("Life Increased by " + lifeincrease+" years");
                    } else {
                        Toast.makeText(support.this, "User data not found.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(support.this, "Failed to retrieve user data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // No user is currently signed in
            Toast.makeText(support.this, "No user is currently signed in.", Toast.LENGTH_SHORT).show();
        }
    }
}
