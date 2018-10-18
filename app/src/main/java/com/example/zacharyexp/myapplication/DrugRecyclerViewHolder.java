package com.example.zacharyexp.myapplication;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class DrugRecyclerViewHolder extends RecyclerView.ViewHolder{

    private TextView cardTitle;
    private TextView subtitleContent;
    private TextView startDateValue;
    private TextView endDateValue;

    //View for 1 cell
    public DrugRecyclerViewHolder(View v){
        super(v);

        //findViews

        cardTitle = (TextView) v.findViewById(R.id.card_title);
        subtitleContent = (TextView) v.findViewById(R.id.subtitle_content);
        startDateValue = (TextView) v.findViewById(R.id.start_date_card);
        endDateValue = (TextView)  v.findViewById(R.id.end_date_card);
    }

    //Binder
    public void bind (Drug drug){
        cardTitle.setText(drug.getDrugName());
        subtitleContent.setText(drug.getRelativeTime() + " " + drug.getAbsoluteTime());
        startDateValue.setText(DrugTools.dateToStringValue(drug.getStartDate()));
        endDateValue.setText(DrugTools.dateToStringValue(drug.getEndDate()));
    }
}
