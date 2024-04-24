package com.example.rob_lifefitnessdiet.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class weightgain extends AppCompatActivity {

    private RecyclerView recyclerViewweightgainbf, recyclerViewweightgainlun, recyclerViewweightgaindin;
    private ArrayList<Diet> dietitemsbf, dietitemslun, dietitemsdin;
    private DietItemAdapter recyclerimageadapterbf, recyclerimageadapterlun, recyclerimageadapterdin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightgain);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerViewweightgainbf = findViewById(R.id.recylerviewweightgainbf);
        recyclerViewweightgainlun = findViewById(R.id.recylerviewweightgainlun);
        recyclerViewweightgaindin = findViewById(R.id.recylerviewweightgaindin);

        recyclerViewweightgainbf.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewweightgainlun.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewweightgaindin.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        dietitemsbf = new ArrayList<>();
        dietitemslun = new ArrayList<>();
        dietitemsdin = new ArrayList<>();

        fetchDietItems("weightgain", "breakfast", recyclerViewweightgainbf, dietitemsbf);
        fetchDietItems("weightgain", "lunch", recyclerViewweightgainlun, dietitemslun);
        fetchDietItems("weightgain", "dinner", recyclerViewweightgaindin, dietitemsdin);
    }

    private void fetchDietItems(String category, String mealType, RecyclerView recyclerView, ArrayList<Diet> dietItems) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("yogadiet").child(category).child(mealType);
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
                Toast.makeText(weightgain.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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
