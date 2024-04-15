package com.example.rob_lifefitnessdiet.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rob_lifefitnessdiet.R;

public class aboutus extends AppCompatActivity {
    private static final long TIMER_DURATION = 2 * 60 * 1000; // 2 minutes in milliseconds
    private TextView timerTextView,title,step1,step2,step3;
    private TextView startButton;
    private CountDownTimer countDownTimer;
    ImageView imageviewforrv;

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
                startTimer();
                startButton.setVisibility(View.GONE);
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
                // Display a message or perform an action when the timer finishes
                timerTextView.setText("Time's up!");
            }
        }.start();
    }
}