package com.example.rob_lifefitnessdiet.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rob_lifefitnessdiet.R;
import com.example.rob_lifefitnessdiet.model.imagemmodel;
import com.example.rob_lifefitnessdiet.screens.aboutus;

import java.util.ArrayList;

public class Recyclerimageadapter extends RecyclerView.Adapter<Recyclerimageadapter.ViewHolder> {

    private Context contextl;
    private ArrayList<imagemmodel> imagemmodelArrayList;



    public Recyclerimageadapter(Context contextl, ArrayList<imagemmodel> imagemmodelArrayList) {
        this.contextl = contextl;
        this.imagemmodelArrayList = imagemmodelArrayList;
    }

    @NonNull
    @Override
    public Recyclerimageadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singleiamge,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //holder.contentview.setText(user.getContent());

        Glide.with(contextl).load(imagemmodelArrayList.get(position).getImageurl()).into(holder.imageviewforrv);
        holder.contentview.setText(imagemmodelArrayList.get(position).getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(view.getContext(), aboutus.class);
                i.putExtra("gif",imagemmodelArrayList.get(holder.getAdapterPosition()).getImageurl().toString());
                i.putExtra("title",imagemmodelArrayList.get(holder.getAdapterPosition()).getContent().toString());
                i.putExtra("step1",imagemmodelArrayList.get(holder.getAdapterPosition()).getStep1());
                i.putExtra("step2",imagemmodelArrayList.get(holder.getAdapterPosition()).getStep2());
                i.putExtra("step3",imagemmodelArrayList.get(holder.getAdapterPosition()).getStep3());
                view.getContext().startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return imagemmodelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

      ImageView imageviewforrv;
      TextView contentview;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imageviewforrv=itemView.findViewById(R.id.imageviewforrv);
            contentview=itemView.findViewById(R.id.Content);

        }
    }
}
