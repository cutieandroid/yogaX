package com.example.rob_lifefitnessdiet.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rob_lifefitnessdiet.R;

public class bmiactivit extends AppCompatActivity {

    TextView bmidisplay,bmicategory,gender;
    Intent intent;
    ImageView imageView;
    String bmi;
    float bmi2;

    String height2;
    String weight2;
    float height,weight;
    RelativeLayout background;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivit);
        getSupportActionBar().hide();
        intent=getIntent();
        bmidisplay=findViewById(R.id.bmidisplay);
        bmicategory=findViewById(R.id.bmicategory);
        gender=findViewById(R.id.genderdisplay);

        height2=intent.getStringExtra("height");
        weight2=intent.getStringExtra("weight");

        height=Float.parseFloat(height2);
        weight=Float.parseFloat(weight2);

        height=height/100;
        bmi2=weight/( height*height);

        bmi=Float.toString(bmi2);
        if(bmi2<16){
            bmicategory.setText("Severe Thinness");

        }
        else if(bmi2<16.9 && bmi2>16){
            bmicategory.setText("Moderate thinness");

        }
        else if(bmi2<18.4 && bmi2>17){
            bmicategory.setText("Mild Thinness");
        }
        else if(bmi2<25 && bmi2>18.4)
        {
            bmicategory.setText("Normal");
        }
        else if(bmi2<29.4 && bmi2>25){
            bmicategory.setText("OverWeight");
        }
        else{
            bmicategory.setText("Obese class 1");
        }

        gender.setText(intent.getStringExtra("gender"));
       // bmidisplay.setText(bmi);

    }
}