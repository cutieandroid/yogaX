package com.example.rob_lifefitnessdiet.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rob_lifefitnessdiet.R;

public class bmitest extends AppCompatActivity {
    android.widget.Button proceed;
    MainActivity a;
    TextView currentheight;
    TextView currentweight,currntage;
    ImageView incrementage,incrementweight,decrementage,decrementweight;
    SeekBar sekkbarforeheight;
    RelativeLayout male,female;
    int intweight=55;
    int intage=18;
    int currentprogress;
    String intcurrentprogress="170";
    String typeofuser="0";
    String weight2="55";
    String age2="18";
    String bmistring;
    float bmi;
    float height;
    float weight;
    int age;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmitest);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        proceed=findViewById(R.id.Proceedbtn);
        getSupportActionBar().hide();
        currntage=findViewById(R.id.currentage);
        currentweight=findViewById(R.id.currentweight);
        incrementage=findViewById(R.id.incrementage);
        incrementweight=findViewById(R.id.incremententweight);
        decrementage=findViewById(R.id.decrementage);
        decrementweight=findViewById(R.id.decrementweight);
        currentheight=findViewById(R.id.CurrentHeight);
        sekkbarforeheight=findViewById(R.id.seekbarforheight);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);


        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));//sets background to focus theme because male is selected;
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalnotfoucus));//sets background to notfocus for female as female is not selected;
                typeofuser="Male";



            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalnotfoucus));//sets background to focus theme because male is selected;
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));//sets background to notfocus for female as female is not selected;
                typeofuser="Female";
            }
        });

        /*****************seekbar settings******************/

        sekkbarforeheight.setMax(300);
        sekkbarforeheight.setProgress(170);
        sekkbarforeheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                currentprogress=i;
                intcurrentprogress=String.valueOf(currentprogress);
                currentheight.setText(intcurrentprogress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        incrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               intage=intage+1;
               age2=String.valueOf(intage);
               currntage.setText(age2);

            }
        });

        incrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intweight=intweight+1;
                weight2=String.valueOf(intweight);
                currentweight.setText(weight2);

            }
        });

        decrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intage=intage-1;
                age2=String.valueOf(intage);
                currntage.setText(age2);

            }
        });

        decrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intweight=intweight-1;
                weight2=String.valueOf(intweight);
                currentweight.setText(weight2);

            }
        });






        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                height=Float.parseFloat(intcurrentprogress);
                height=height/100;
                weight=Float.parseFloat(weight2);
                age=Integer.parseInt(age2);
                bmi=weight/(height*height);
                //bmi=16;
                bmistring=String.valueOf(bmi);

               if(typeofuser.equals("0")){
                    Toast.makeText(bmitest.this, "Please select Gender", Toast.LENGTH_SHORT).show();
                }
                else if(intcurrentprogress.equals("0")){
                    Toast.makeText(bmitest.this, "Height cannot be zero", Toast.LENGTH_SHORT).show();

                }
                else if(intage==0 || intage<0 ){
                    Toast.makeText(bmitest.this, "Please enter a valid age", Toast.LENGTH_SHORT).show();
                }

                else if(intweight==0 || intweight<0 ){
                    Toast.makeText(bmitest.this, "Please enter a valid weight", Toast.LENGTH_SHORT).show();
                }


               else if(bmi<16.8 && age==15 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this, weightgain.class);
                   intent.putExtra("bmi",bmi);
                   startActivity(intent);
               }
               else if(bmi<18 && age==15 && typeofuser.equals("Female"))
               {
                   Intent intent=new Intent(bmitest.this,weightgain.class);
                   intent.putExtra("bmi",bmi);
                   startActivity(intent);
               }
               else if(bmi<23.2 && bmi>16.8&& age==15 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this, weightmaintain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<21.9 && bmi>18&& age==15 && typeofuser.equals("Female"))
               {
                   Intent intent=new Intent(bmitest.this,weightmaintain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<26.5 && bmi>23.2 && age==15 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this, weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<25.8 && bmi>21.9 && age==15 && typeofuser.equals("Female"))
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }

               else if(bmi>26.5 && age==15 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi>25.8 && age==15 && typeofuser.equals("Female"))
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<17.3 && age==16 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this,weightgain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<18.3 && age==16 && typeofuser.equals("Female"))
               {
                   Intent intent=new Intent(bmitest.this,weightgain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<24 && bmi >17.3 && age==16 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this,weightmaintain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<22.4 && bmi >18.3 && age==16 && typeofuser.equals("Female"))
               {
                   Intent intent=new Intent(bmitest.this,weightmaintain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<27.1 && bmi>24 && age==16 && typeofuser.equals("Male") )
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<26.5 && bmi>22.4 && age==16 && typeofuser.equals("Female") )
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi>27.1&& age==16 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi>26.5&& age==16 && typeofuser.equals("Female"))
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<18 && age==17 && typeofuser.equals("Male") )
               {
                   Intent intent=new Intent(bmitest.this,weightgain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }

               else if(bmi<18.7 && age==17 && typeofuser.equals("Female") )
               {
                   Intent intent=new Intent(bmitest.this,weightgain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<24.9 && bmi>18 && age==17 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this,weightmaintain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<22.8&& bmi>18.7 && age==17 && typeofuser.equals("Female"))
               {
                   Intent intent=new Intent(bmitest.this,weightmaintain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<27.9 &&bmi>24.9 && age==17 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<27 &&bmi>22.8 && age==17 && typeofuser.equals("Female"))
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi>27.9 && age==17 && typeofuser.equals("Male") )
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi>27 && age==17 && typeofuser.equals("Female") )
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<18.2 && age==18 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this,weightgain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<18.9 && age==18 && typeofuser.equals("Female"))
               {
                   Intent intent=new Intent(bmitest.this,weightgain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<25.4 && bmi>18.2 && age==18 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this,weightmaintain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<23 && bmi>18.9 && age==18 && typeofuser.equals("Female"))
               {
                   Intent intent=new Intent(bmitest.this,weightmaintain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<28.4 && bmi>25.4 && age==18 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<27.8 && bmi>23 && age==18 && typeofuser.equals("Female"))
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi>28.4 && age==18 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi>27.8 && age==18 && typeofuser.equals("Female"))
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<19 && age==19 && typeofuser.equals("Male") )
               {
                   Intent intent=new Intent(bmitest.this,weightgain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<19 && age==19 && typeofuser.equals("Female") )
               {
                   Intent intent=new Intent(bmitest.this,weightgain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<23.2 && bmi>19 && age==19 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this,weightmaintain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<24 && bmi>19 && age==19 && typeofuser.equals("Female"))
               {
                   Intent intent=new Intent(bmitest.this,weightmaintain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<29.2 && bmi>26.2 && age==19 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<28.2 && bmi>24 && age==19 && typeofuser.equals("Female"))
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi>29.2 && age==19 && typeofuser.equals("Male") )
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi>28.2 && age==19 && typeofuser.equals("Female") )
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<19.6 && age==20 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this,weightgain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<26.8 && bmi >19.6 && age==20&& typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this,weightmaintain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<29.9 && bmi>26.9 && age==20 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi>29.9 && age==20 && typeofuser.equals("Male"))
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<19.6 && age>20 && typeofuser.equals("Male") )
               {
                   Intent intent=new Intent(bmitest.this,weightgain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<19.6 && age>=20 && typeofuser.equals("Female") )
               {
                   Intent intent=new Intent(bmitest.this,weightgain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<26.8 && bmi >19.6 && age>20&& typeofuser.equals("Male") )
               {
                   Intent intent=new Intent(bmitest.this,weightmaintain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
               else if(bmi<24.8 && bmi >=19.6 && age>=20&& typeofuser.equals("Female") )
               {
                   Intent intent=new Intent(bmitest.this,weightmaintain.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }

               else if(bmi<29.9 && bmi>26.9 && age>20 && typeofuser.equals("Male") )
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
                else if(bmi<28.9 && bmi>24.8 && age>=20 && typeofuser.equals("Female") )
                {
                    Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                    startActivity(intent);
                }
               else if(bmi>29.9 && age>20 && typeofuser.equals("Male") )
               {
                   Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                   startActivity(intent);
               }
                else if(bmi>28.9 && age>=20 && typeofuser.equals("Female") )
                {
                    Intent intent=new Intent(bmitest.this,weightloss.class);intent.putExtra("bmi",bmistring);
                    startActivity(intent);
                }

              else{

                  /*  Intent intent=new Intent(bmitest.this,bmiactivit.class);

                    intent.putExtra("gender",typeofuser);
                    intent.putExtra("height",intcurrentprogress);
                    intent.putExtra("weight",weight2);
                    intent.putExtra("age",age2);





                    startActivity(intent);*/

                }





            }
        });

    }

}