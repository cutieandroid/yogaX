package com.example.rob_lifefitnessdiet.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.rob_lifefitnessdiet.R;
import com.example.rob_lifefitnessdiet.adapters.DietItemAdapter;
import com.example.rob_lifefitnessdiet.model.Diet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class weightloss extends AppCompatActivity {

    Intent intent;
    String display;
    private RecyclerView recyclerViewweightgainbf,recyclerViewweightgainlun,recyclerViewweightgaindin;
    private ArrayList<Diet> dietitems;
    private DietItemAdapter recyclerimageadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightloss);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerViewweightgainbf=findViewById(R.id.recylerviewweightgainbf);
        recyclerViewweightgainlun=findViewById(R.id.recylerviewweightgainlun);
        recyclerViewweightgaindin=findViewById(R.id.recylerviewweightgaindin);
        recyclerViewweightgainbf.setLayoutManager(new LinearLayoutManager(weightloss.this,LinearLayoutManager.HORIZONTAL,false) )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       ;
        ;
        recyclerViewweightgainbf.setHasFixedSize(true);
        recyclerViewweightgainlun.setLayoutManager(new LinearLayoutManager(weightloss.this,LinearLayoutManager.HORIZONTAL,false) )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       ;
        recyclerViewweightgainlun.setHasFixedSize(true);
        recyclerViewweightgaindin.setLayoutManager(new LinearLayoutManager(weightloss.this,LinearLayoutManager.HORIZONTAL,false) )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       ;
        recyclerViewweightgaindin.setHasFixedSize(true);
        dietitems=new ArrayList<>();

        intent=getIntent();
        display=intent.getStringExtra("bmi");



        clearAll();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("yogadiet").child("weightgain").child("breakfast");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                clearAll();
                for(DataSnapshot snapshot: datasnapshot.getChildren()){

                    Diet diet= new Diet();
                    diet.setDietItem(snapshot.getValue().toString());
                    dietitems.add(diet);


                }

                recyclerimageadapter= new DietItemAdapter(getApplicationContext(),dietitems);
                recyclerViewweightgainbf.setAdapter(recyclerimageadapter);
                recyclerViewweightgainlun.setAdapter(recyclerimageadapter);
                recyclerViewweightgaindin.setAdapter(recyclerimageadapter);
                recyclerimageadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(weightloss.this, "Error:"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void clearAll() {
        if(dietitems!=null)
        {
            dietitems.clear();

            if(recyclerimageadapter != null)
            {
                recyclerimageadapter.notifyDataSetChanged();
            }
        }
        dietitems=new ArrayList<>();



    }
}