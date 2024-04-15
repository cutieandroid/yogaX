package com.example.rob_lifefitnessdiet.screens;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
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

public class






login extends AppCompatActivity {
    private boolean passwordshowing=false;
    FirebaseAuth firebaseAuth;
    AlertDialog.Builder reset_alert;
    LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        firebaseAuth=FirebaseAuth.getInstance();
        reset_alert=new AlertDialog.Builder(login.this);
        final EditText usernameEt=findViewById(R.id.emailid);
        final EditText passwordEt=findViewById(R.id.password);
        final TextView signupbtn=findViewById(R.id.signup);
        final AppCompatButton signinbtn=findViewById(R.id.signinbutton);
        final ImageView showpassword=findViewById(R.id.showpass);
        final TextView forgotpassword=findViewById(R.id.forgotpasswordbtn);
        inflater=this.getLayoutInflater();


        showpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checking if password is showing or not
                if(passwordshowing)
                {
                    passwordshowing=false;
                    passwordEt.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    showpassword.setImageResource(R.drawable.showpassword);
                }
                else
                {
                    passwordshowing=true;

                    passwordEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    showpassword.setImageResource(R.drawable.hidepasswordicon);

                }

                //set the cursor at last of the text
                passwordEt.setSelection(passwordEt.length());

            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View views=inflater.inflate(R.layout.reset_dailog,null);

                reset_alert.setTitle("Reset Password")
                        .setMessage("Enter your registered email address to get password reset link")
                        .setPositiveButton("reset", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                 //validate the email address
                                EditText resetemail=views.findViewById(R.id.resetemaildailog);
                                if(resetemail.getText().toString().isEmpty()){
                                    resetemail.setError("Email field cannot be empty");
                                    return;
                                }
                                //send reset link

                                firebaseAuth.sendPasswordResetEmail(resetemail.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(login.this, "Reset password has been sent to your email address,make sure email address you entered is registered", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).setNegativeButton("Cancel",null)
                        .setView(views)
                        .create().show();


            }
        });


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, Register.class));
                overridePendingTransition(R.anim.slidefrombottom,R.anim.slidetobottom);
            }
        });
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=usernameEt.getText().toString();
                String Password=passwordEt.getText().toString();
                if(email.isEmpty()){
                    usernameEt.setError("Email field cannot be empty");
                    return;
                }
                if(Password.isEmpty()){
                    passwordEt.setError("Password field cannot be empty");
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(email,Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(login.this, emilyverification.class));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(login.this,emilyverification.class));
        }


    }
}
