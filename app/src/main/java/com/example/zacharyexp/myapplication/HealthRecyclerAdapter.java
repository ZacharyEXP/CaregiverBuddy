package com.example.zacharyexp.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;


public class HealthRecyclerAdapter extends RecyclerView.Adapter<HealthRecyclerViewHolder> {

    ArrayList<Health> healthContainer = new ArrayList<>();
    private OnItemClicked onClick;
    int healthType;

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    //Data constructor from list
    public HealthRecyclerAdapter(ArrayList<Health> health){
        this.healthContainer = health;
    }

    public HealthRecyclerAdapter(ArrayList<Health> health, int type){
        this.healthContainer = health;
        this.healthType = type;
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
        if(health.getHealthType() == healthType) {
            healthRecyclerViewHolder.bind(health);
        } else {
            ViewGroup.LayoutParams invisible = new ViewGroup.LayoutParams(0, 0);
            //invisible.set
            healthRecyclerViewHolder.itemView.setVisibility(View.GONE);
            healthRecyclerViewHolder.itemView.setLayoutParams(invisible);
        }
        healthRecyclerViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //onClick.onItemClick(position);
                healthRecyclerViewHolder.enableDelete();
                healthRecyclerViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        healthRecyclerViewHolder.disableDelete();
                        onClick.onItemClick(position);
                    }
                });
                return false;
            }
        });
        healthRecyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onClick.onItemClick(position);
                System.out.println(position);
            }
        });
    }



    @Override
    public int getItemCount() {
        return healthContainer.size();
    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }
}