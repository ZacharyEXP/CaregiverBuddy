package com.example.zacharyexp.myapplication;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ChoreRecyclerAdapter extends RecyclerView.Adapter<ChoreRecyclerViewHolder> {

    ArrayList<Chore> choreContainer = new ArrayList<>();

    //Data constructor from list
    public ChoreRecyclerAdapter(ArrayList<Chore> chores){
        this.choreContainer = chores;
    }

    //Creates ViewHolders
    @Override
    public ChoreRecyclerViewHolder onCreateViewHolder (ViewGroup viewGroup, int itemType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chore_card, viewGroup, false);
        return new ChoreRecyclerViewHolder(view);
    }

    //Fill the cell with objects' data
    public void onBindViewHolder (ChoreRecyclerViewHolder choreRecyclerViewHolder, int position){
        Chore chore = choreContainer.get(position);
        choreRecyclerViewHolder.bind(chore);
    }



    @Override
    public int getItemCount() {
        return choreContainer.size();
    }
}
