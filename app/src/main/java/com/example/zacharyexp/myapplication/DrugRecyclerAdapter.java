package com.example.zacharyexp.myapplication;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;


public class DrugRecyclerAdapter extends RecyclerView.Adapter<DrugRecyclerViewHolder> {

    ArrayList<Drug> drugContainer = new ArrayList<>();

    //Data constructor from list
    public DrugRecyclerAdapter(ArrayList<Drug> drugs){
        this.drugContainer = drugs;
    }

    //Creates ViewHolders
    @Override
    public DrugRecyclerViewHolder onCreateViewHolder (ViewGroup viewGroup, int itemType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drug_card, viewGroup, false);
        return new DrugRecyclerViewHolder(view);
    }

    //Fill the cell with objects' data
    public void onBindViewHolder (DrugRecyclerViewHolder drugRecyclerViewHolder, int position){
        Drug drug = drugContainer.get(position);
        drugRecyclerViewHolder.bind(drug);
    }



    @Override
    public int getItemCount() {
        return drugContainer.size();
    }
}
