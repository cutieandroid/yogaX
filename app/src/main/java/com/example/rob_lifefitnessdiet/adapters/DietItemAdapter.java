package com.example.rob_lifefitnessdiet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rob_lifefitnessdiet.R;
import com.example.rob_lifefitnessdiet.model.Diet;

import java.util.ArrayList;

public class DietItemAdapter extends RecyclerView.Adapter<DietItemAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Diet> dietItemArrayList;



    public DietItemAdapter(Context context, ArrayList<Diet> dietItemArrayList) {
        this.context= context;
        this.dietItemArrayList = dietItemArrayList;
    }

    @NonNull
    @Override
    public DietItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dietitem,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //holder.contentview.setText(user.getContent());
        holder.dietitemholder.setText(dietItemArrayList.get(position).getDietItem());
    }

    @Override
    public int getItemCount() {
        return dietItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dietitemholder;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
           dietitemholder=itemView.findViewById(R.id.dietitemholder);

        }
    }
}