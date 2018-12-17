package com.govst.zacharyexp.caregiverbuddy.drug;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.govst.zacharyexp.caregiverbuddy.R;

public class DrugRecyclerViewHolder extends RecyclerView.ViewHolder{

    private TextView cardTitle;
    private TextView subtitleContent;
    private TextView startDateValue;
    private TextView endDateValue;
    public ImageButton deleteButton;

    //View for 1 cell
    public DrugRecyclerViewHolder(View v){
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
    public void bind (Drug drug){
        cardTitle.setText(drug.getDrugName());
        subtitleContent.setText(drug.getRelativeTime() + " " + drug.getAbsoluteTime());
        startDateValue.setText(DrugTools.dateToStringValue(drug.getStartDate()));
        endDateValue.setText(DrugTools.dateToStringValue(drug.getEndDate()));
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
