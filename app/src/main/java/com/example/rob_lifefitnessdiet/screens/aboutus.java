package com.example.rob_lifefitnessdiet.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rob_lifefitnessdiet.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class aboutus extends AppCompatActivity {
    private static final long TIMER_DURATION = 2 * 60 * 1000; // 2 minutes in milliseconds
    private TextView timerTextView,title,step1,step2,step3;
    private TextView startButton;
    private CountDownTimer countDownTimer;
    ImageView imageviewforrv;
    private boolean isTimerRunning = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        getSupportActionBar().hide();
        Intent i= getIntent();


        timerTextView = findViewById(R.id.timerTextView);
        timerTextView.setText("02:00");
        title = findViewById(R.id.Content);
        imageviewforrv=findViewById(R.id.imageviewforrv);
        step1 = findViewById(R.id.step1);
        step2 = findViewById(R.id.step2);
        step3 = findViewById(R.id.step3);
        startButton = findViewById(R.id.startButton);
        Glide.with(aboutus.this).load(i.getStringExtra("gif")).into(imageviewforrv);
        step1.setText(i.getStringExtra("step1"));
        step2.setText(i.getStringExtra("step2"));
        step3.setText(i.getStringExtra("step3"));
        title.setText(i.getStringExtra("title"));


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the timer when the button is clicked
                if (!isTimerRunning) {
                    startTimer();
                    startButton.setText("Stop");
                } else {
                    stopTimer();
                    startButton.setText("Start");
                }
            }
        });
    }

    private void startTimer() {
        // Cancel any previous timer
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        // Create and start a new countdown timer
        countDownTimer = new CountDownTimer(TIMER_DURATION, 1000) {
            public void onTick(long millisUntilFinished) {
                // Update the TextView with the remaining time
                long minutes = millisUntilFinished / 60000;
                long seconds = (millisUntilFinished % 60000) / 1000;
                timerTextView.setText(String.format("%02d:%02d", minutes, seconds));
            }

            public void onFinish() {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                String userId = currentUser.getUid();
                // Display a message or perform an action when the timer finishes
                startButton.setText("Start");
                timerTextView.setText("Time's up!");
                isTimerRunning = false;
                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            double currentLifeIncreased = dataSnapshot.child("lifeincreased").getValue(Double.class);
                            double newLifeIncreased = currentLifeIncreased + 0.05;
                            userRef.child("lifeincreased").setValue(newLifeIncreased)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // Successfully updated the lifeincreased field
                                            startButton.setText("Start");
                                            timerTextView.setText("Time's up!");
                                            isTimerRunning = false;
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Failed to update the lifeincreased field
                                            Toast.makeText(aboutus.this, "Failed to update lifeincreased field.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            // User document does not exist
                            Toast.makeText(aboutus.this, "User document does not exist.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle database error
                        Toast.makeText(aboutus.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }.start();
        isTimerRunning = true;
    }
    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            startButton.setText("Start"); // Reset button text to "Start" when timer is stopped
            isTimerRunning = false; // Reset timer running flag
        }
    }
}