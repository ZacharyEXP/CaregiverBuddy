package com.govst.zacharyexp.caregiverbuddy.health;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.govst.zacharyexp.caregiverbuddy.R;

public class HealthRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView cardTitle;
    private TextView subtitleContent;
    private TextView startDateValue;
    private TextView endDateValue;
    public ImageButton deleteButton;

    //View for 1 cell
    public HealthRecyclerViewHolder(View v){
        super(v);

        //findViews

        cardTitle = (TextView) v.findViewById(R.id.card_title);
        subtitleContent = (TextView) v.findViewById(R.id.subtitle_content);
        startDateValue = (TextView) v.findViewById(R.id.start_date_card);
        endDateValue = (TextView)  v.findViewById(R.id.end_date_card);
        deleteButton = (ImageButton) v.findViewById(R.id.delete);
        deleteButton.setVisibility(View.GONE);
    }

    //Binder
    public void bind (Health health){
        cardTitle.setText(health.getTypeName());
        subtitleContent.setText(health.getDataEntry());
        startDateValue.setText(HealthTools.dateToStringValue(health.getStartDate()));
        //endDateValue.setText(HealthTools.dateToStringValue(health.getEndDate()));
    }

    public void enableDelete() {
        deleteButton.setVisibility(View.VISIBLE);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void disableDelete() {
        deleteButton.setVisibility(View.GONE);
    }
}
