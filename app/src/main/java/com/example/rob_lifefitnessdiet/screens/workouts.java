package com.example.rob_lifefitnessdiet.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rob_lifefitnessdiet.R;

public class workouts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);
        final ImageView chestlogo=findViewById(R.id.chestlogo);
        final TextView chestlogotextview=findViewById(R.id.chestlogotextview);
        final TextView armslogotextview=findViewById(R.id.armmslogotextview);
        final ImageView armslogo=findViewById(R.id.armmslogo);
        final ImageView abslogo=findViewById(R.id.abslogo);
        final TextView abslogotextview=findViewById(R.id.abslogotextview);
        final ImageView legslogo=findViewById(R.id.legslogo);
        final TextView legslogotextview=findViewById(R.id.legslogotextview);

        armslogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(workouts.this, armworkouts.class);
                startActivity(intent);
            }
        });

    }
    public void openchest(View view)
    {
        Intent intent= new Intent(workouts.this, Yogas.class);
        startActivity(intent);
    }
    /*public void openarms(View view)
    {
        Intent intent= new Intent(workouts.this,bicepworkouts.class);
        startActivity(intent);
    }*/
    public void openabs(View view)
    {
        Intent intent= new Intent(workouts.this, absworkouts.class);
        startActivity(intent);
    }
    public void openlegs(View view)
    {
        Intent intent= new Intent(workouts.this, legsworkouts.class);
        startActivity(intent);
    }
    public void openshoulders(View view)
    {
        Intent intent= new Intent(workouts.this, Shoulderworkout.class);
        startActivity(intent);
    }
    public void opencardio(View view)
    {
        Intent intent= new Intent(workouts.this, cardio.class);
        startActivity(intent);
    }
    public void openback(View view)
    {
        Intent intent= new Intent(workouts.this, backworkout.class);
        startActivity(intent);
    }



}