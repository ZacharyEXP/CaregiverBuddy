package com.example.zacharyexp.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;


public class HealthRecyclerAdapter extends RecyclerView.Adapter<HealthRecyclerViewHolder> {

    ArrayList<Health> healthContainer = new ArrayList<>();

    //Data constructor from list
    public HealthRecyclerAdapter(ArrayList<Health> health){
        this.healthContainer = health;
    }

    //Creates ViewHolders
    @Override
    public HealthRecyclerViewHolder onCreateViewHolder (ViewGroup viewGroup, int itemType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.health_card, viewGroup, false);
        return new HealthRecyclerViewHolder(view);
    }

    //Fill the cell with objects' data
    public void onBindViewHolder (HealthRecyclerViewHolder healthRecyclerViewHolder, int position){
        Health health = healthContainer.get(position);
        healthRecyclerViewHolder.bind(health);
    }



    @Override
    public int getItemCount() {
        return healthContainer.size();
    }
}