package com.govst.zacharyexp.caregiverbuddy.drug;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.govst.zacharyexp.caregiverbuddy.R;

import java.util.ArrayList;


public class DrugRecyclerAdapter extends RecyclerView.Adapter<DrugRecyclerViewHolder> {

    ArrayList<Drug> drugContainer = new ArrayList<>();
    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onItemClick(int position);
    }


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
        drugRecyclerViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //onClick.onItemClick(position);
                drugRecyclerViewHolder.enableDelete();
                drugRecyclerViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drugRecyclerViewHolder.disableDelete();
                        onClick.onItemClick(position);
                    }
                });
                return false;
            }
        });
    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }


    @Override
    public int getItemCount() {
        return drugContainer.size();
    }
}
