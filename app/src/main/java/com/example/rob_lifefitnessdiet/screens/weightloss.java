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
    private ArrayList<Diet> dietitemslun;
    private ArrayList<Diet> dietitemsdin;
    private DietItemAdapter recyclerimageadapter,recyclerimageadapterlun,recyclerimageadapterdin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightloss);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerViewweightgainbf=findViewById(R.id.recylerviewweightgainbf);
        recyclerViewweightgainlun=findViewById(R.id.recylerviewweightgainlun);
        recyclerViewweightgaindin=findViewById(R.id.recylerviewweightgaindin);

        recyclerViewweightgainbf.setLayoutManager(new LinearLayoutManager(weightloss.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewweightgainbf.setHasFixedSize(true);
        recyclerViewweightgainlun.setLayoutManager(new LinearLayoutManager(weightloss.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewweightgainlun.setHasFixedSize(true);
        recyclerViewweightgaindin.setLayoutManager(new LinearLayoutManager(weightloss.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewweightgaindin.setHasFixedSize(true);

        dietitems=new ArrayList<>();
        dietitemslun=new ArrayList<>();
        dietitemsdin=new ArrayList<>();

        intent=getIntent();
        display=intent.getStringExtra("bmi");

        fetchDietItems("breakfast", recyclerViewweightgainbf, dietitems);
        fetchDietItems("lunch", recyclerViewweightgainlun, dietitemslun);
        fetchDietItems("dinner", recyclerViewweightgaindin, dietitemsdin);
    }

    private void fetchDietItems(String mealType, RecyclerView recyclerView, ArrayList<Diet> dietItems) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("yogadiet").child("weightloss").child(mealType);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dietItems.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Diet diet = new Diet();
                    diet.setDietItem(snapshot.getValue().toString());
                    dietItems.add(diet);
                }
                updateRecyclerView(recyclerView, dietItems);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(weightloss.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateRecyclerView(RecyclerView recyclerView, ArrayList<Diet> dietItems) {
        DietItemAdapter adapter = (DietItemAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new DietItemAdapter(getApplicationContext(), dietItems);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
