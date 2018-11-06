package com.example.zacharyexp.myapplication;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ChoreRecyclerViewHolder extends RecyclerView.ViewHolder{

    private TextView cardTitle;
    private TextView subtitleContent;
    private TextView startDateValue;
    private TextView endDateValue;
    public ImageButton deleteButton;

    //View for 1 cell
    public ChoreRecyclerViewHolder(View v){
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
    public void bind (Chore chore){
        cardTitle.setText(chore.getChoreName());
        subtitleContent.setText(chore.getRelativeTime() + " " + chore.getAbsoluteTime());
        startDateValue.setText(DrugTools.dateToStringValue(chore.getStartDate()));
        endDateValue.setText(DrugTools.dateToStringValue(chore.getEndDate()));
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
