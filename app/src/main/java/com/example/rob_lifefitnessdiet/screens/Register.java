package com.example.rob_lifefitnessdiet.screens;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rob_lifefitnessdiet.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private boolean regpasswordshowing=false;
    private boolean regcnfrmpasswordshowing=true;
    FirebaseAuth fauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        final EditText regusernameEt=findViewById(R.id.regusername);

        final EditText regemailEt=findViewById(R.id.regemailid);
        final EditText regpassEt=findViewById(R.id.regpassword);
        final EditText regcnfrmpassEt=findViewById(R.id.regcnfrmpassword);
        final AppCompatButton regsignup=findViewById(R.id.regsignupbutton);
        final TextView siginpage=findViewById(R.id.regsignin);

        final ImageView regshowpassword=findViewById(R.id.regshowpass);
        final ImageView regshowcnfrmpassword=findViewById(R.id.regcnfrmshowpass);

        fauth=FirebaseAuth.getInstance();
        //show and hide password functionality for password
        regshowpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(regpasswordshowing)
                {
                    regpasswordshowing=false;
                    regpassEt.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    regshowpassword.setImageResource(R.drawable.showpassword);
                }
                else
                {
                    regpasswordshowing=true;

                    regpassEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    regshowpassword.setImageResource(R.drawable.hidepasswordicon);

                }

                //set the cursor at last of the text
                regpassEt.setSelection(regpassEt.length());

            }


        });



        //show and hide password functionality for confirm password
        regshowcnfrmpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(regcnfrmpasswordshowing)
                {
                    regcnfrmpasswordshowing=false;
                    regcnfrmpassEt.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    regshowcnfrmpassword.setImageResource(R.drawable.showpassword);
                }
                else
                {
                    regcnfrmpasswordshowing=true;

                    regcnfrmpassEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    regshowcnfrmpassword.setImageResource(R.drawable.hidepasswordicon);

                }

                //set the cursor at last of the text
                regcnfrmpassEt.setSelection(regcnfrmpassEt.length());

            }
        });



        //function of sign up button
        regsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname=regusernameEt.getText().toString();
                String emailid=regemailEt.getText().toString();
                String password=regpassEt.getText().toString();
                String confirmpassword=regcnfrmpassEt.getText().toString();

                if(fullname.isEmpty())
                {
                    regusernameEt.setError("Full name is required");
                    return;

                }
                if(emailid.isEmpty())
                {
                    regemailEt.setError("Email id is required");
                    return;

                }
                if(password.isEmpty())
                {
                    regpassEt.setError("Password is Required");
                    return;
                }
                if(confirmpassword.isEmpty())
                {
                    regcnfrmpassEt.setError("confirm password is required");
                    return;
                }
                if(!password.equals(confirmpassword)){
                    regcnfrmpassEt.setError("password didn't match :(");
                    return;
                }

                fauth.createUserWithEmailAndPassword(emailid,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {


                        fauth.signInWithEmailAndPassword(emailid,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(getApplicationContext(), emilyverification.class));
                                finish();
                            }
                        });


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

        //function of sign in button
        siginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, login.class));
                overridePendingTransition(R.anim.slidefrombottom,R.anim.slidetobottom);

            }
        });



    }
}