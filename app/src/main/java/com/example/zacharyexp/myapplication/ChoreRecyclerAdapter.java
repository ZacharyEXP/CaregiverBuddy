package com.example.zacharyexp.myapplication;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ChoreRecyclerAdapter extends RecyclerView.Adapter<ChoreRecyclerViewHolder> {

    ArrayList<Chore> choreContainer = new ArrayList<>();
    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onItemClick(int position);
    }

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
        choreRecyclerViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //onClick.onItemClick(position);
                choreRecyclerViewHolder.enableDelete();
                choreRecyclerViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        choreRecyclerViewHolder.disableDelete();
                        onClick.onItemClick(position);
                    }
                });
                return false;
            }
        });
    }



    @Override
    public int getItemCount() {
        return choreContainer.size();
    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }
}
