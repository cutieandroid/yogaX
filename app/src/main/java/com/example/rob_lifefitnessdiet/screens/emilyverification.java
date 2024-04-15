package com.example.rob_lifefitnessdiet.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.rob_lifefitnessdiet.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
public class emilyverification extends AppCompatActivity {
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        auth=FirebaseAuth.getInstance();
       /* if(auth.getCurrentUser().isEmailVerified())
        {
            Intent intenetnew= new Intent(emilyverification.this,MainActivity.class);
            startActivity(intenetnew);
        }*/
        setContentView(R.layout.activity_emilyverification);

         final TextView logoutbtn=findViewById(R.id.logoutbtn);
         logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), login.class));

            }
        });
        final TextView verifytextview=findViewById(R.id.verifyemailtextview);
        final TextView verify=findViewById(R.id.verify);

        verifytextview.findViewById(R.id.verifyemailtextview);
        verify.findViewById(R.id.verify);



        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(emilyverification.this, "Mail has been sent on your Registered email address", Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(auth.getCurrentUser().isEmailVerified())
        {
            Intent intenetnew= new Intent(emilyverification.this, MainActivity.class);
            startActivity(intenetnew);
        }
    }



}

