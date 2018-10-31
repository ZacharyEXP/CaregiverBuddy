package com.example.zacharyexp.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class HealthRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView cardTitle;
    private TextView subtitleContent;
    private TextView startDateValue;
    private TextView endDateValue;

    //View for 1 cell
    public HealthRecyclerViewHolder(View v){
        super(v);

        //findViews

        cardTitle = (TextView) v.findViewById(R.id.card_title);
        subtitleContent = (TextView) v.findViewById(R.id.subtitle_content);
        startDateValue = (TextView) v.findViewById(R.id.start_date_card);
        endDateValue = (TextView)  v.findViewById(R.id.end_date_card);
    }

    //Binder
    public void bind (Health health){
        cardTitle.setText(health.getHealthName());
        subtitleContent.setText(health.getRelativeTime() + " " + health.getAbsoluteTime());
        startDateValue.setText(HealthTools.dateToStringValue(health.getStartDate()));
        endDateValue.setText(HealthTools.dateToStringValue(health.getEndDate()));
    }
}
