package com.example.rob_lifefitnessdiet.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.rob_lifefitnessdiet.R;
import com.example.rob_lifefitnessdiet.adapters.Recyclerimageadapter;
import com.example.rob_lifefitnessdiet.model.imagemmodel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class legsworkouts extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<imagemmodel> imagemmodelArrayList;
    private Recyclerimageadapter recyclerimageadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legsworkouts);
        recyclerView=findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(new GridLayoutManager(legsworkouts.this,1) )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       ;
        recyclerView.setHasFixedSize(true);
        imagemmodelArrayList=new ArrayList<>();

        clearAll();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("legs");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                clearAll();
                for(DataSnapshot snapshot: datasnapshot.getChildren()){
                    imagemmodel imagemodel= new imagemmodel();
                    imagemodel.setImageurl(snapshot.child("img1").getValue().toString());
                    imagemodel.setContent(snapshot.child("string1").getValue().toString());


                    // imagemodel = snapshot.getValue(imagemmodel.class);


                    imagemmodelArrayList.add(imagemodel);
                }

                recyclerimageadapter=new Recyclerimageadapter(getApplicationContext(),imagemmodelArrayList);
                recyclerView.setAdapter(recyclerimageadapter);
                recyclerimageadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(legsworkouts.this, "Error:"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void clearAll() {
        if(imagemmodelArrayList!=null)
        {
            imagemmodelArrayList.clear();

            if(recyclerimageadapter!=null)
            {
                recyclerimageadapter.notifyDataSetChanged();
            }
        }
        imagemmodelArrayList=new ArrayList<>();



    }
}