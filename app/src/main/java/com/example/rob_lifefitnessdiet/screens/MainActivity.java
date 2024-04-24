package com.example.rob_lifefitnessdiet.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.rob_lifefitnessdiet.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static final String channel_id = "fitnesstestchannel";
    private static final int not_id = 1;
    DrawerLayout drawerLayout;
    TextView emailaccount;


    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        auth = FirebaseAuth.getInstance();
        //Assign Variables
        drawerLayout = findViewById(R.id.drawer_layout);
        emailaccount = findViewById(R.id.emailfieldnavigation);
        final LottieAnimationView bmitest = findViewById(R.id.fitnesstest);
        final LottieAnimationView yogaexercises = findViewById(R.id.yogaexercises);
        final LottieAnimationView profile = findViewById(R.id.profile);
        final LottieAnimationView posedetect = findViewById(R.id.posedetect);
        posedetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String packageName = "com.google.mlkit.vision.demo";

                String activityClassName = "com.google.mlkit.vision.demo.java.CameraXLivePreviewActivity";

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setPackage(packageName);

                intent.setClassName(packageName, activityClassName);


                if (intent.resolveActivity(getPackageManager()) != null) {
                    // Open the other app
                    startActivity(intent);
                } else {
                    // The app is not installed on the device or the specific component is not found
                    // Handle this case accordingly
                    System.out.println("error");
                }

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, support.class));
            }
        });
        yogaexercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Yogas.class));
            }
        });

        bmitest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, com.example.rob_lifefitnessdiet.screens.bmitest.class));
            }
        });
        String currentusersemail = auth.getCurrentUser().getEmail();
        emailaccount.setText(currentusersemail);


        /******************************redirect to bmi test activity***********************************/



       /* final TextView verifytextview=findViewById(R.id.verifyemailtextview);
        final TextView verify=findViewById(R.id.verify);

        verifytextview.findViewById(R.id.verifyemailtextview);
        verify.findViewById(R.id.verify);

        if(!auth.getCurrentUser().isEmailVerified()){
            verifytextview.setVisibility(View.VISIBLE);
            verify.setVisibility(View.VISIBLE);
        }
        else {
            verifytextview.setVisibility(View.GONE);
            verify.setVisibility(View.GONE);
        }

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this, "Mail has been sent on your Registered email address", Toast.LENGTH_SHORT).show();
                        if(auth.getCurrentUser().isEmailVerified()){
                            verifytextview.setVisibility(View.GONE);
                            verify.setVisibility(View.GONE);
                        }

                    }
                });
            }
        });*/


        /*logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),login.class));

            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logoutmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), login.class));

        }
        return super.onOptionsItemSelected(item);
    }

    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        //open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view) {
        //close drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //closedrawer layout
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);


        }

    }

    public void ClickHome(View view) {
        //recreate activity
        recreate();

    }


    public void ClickWorkouts(View view) {
        redirectActivity(this, workouts.class);


    }

    public void ClickAboutUs(View view) {
        redirectActivity(this, aboutus.class);


    }

    public void clicksupport(View view) {
        redirectActivity(this, support.class);
    }

    public void redirectActivity(Activity activity, Class aclass) {
        Intent intent = new Intent(activity, aclass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public void clickbmitest(View view) {
        redirectActivity(this, bmitest.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}