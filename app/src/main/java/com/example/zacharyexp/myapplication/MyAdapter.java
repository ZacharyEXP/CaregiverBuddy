package com.example.zacharyexp.myapplication;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        // Rename var to abstract
        CardView cv;
        TextView personName;
        TextView personAge;
        TextView recur;
        TextView done;
        ImageView personPhoto;
        //Button personButton;

        PersonViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            switch(type) {

            }
            personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
            //personButton = (Button)itemView.findViewById(R.id.person_button);
            System.out.println("PersonViewHolder init");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = (int) v.getTag();
                    System.out.println(position);
                }
            });
        }
    }

    ArrayList<String> patientNames;
    ArrayList<String> patientAges;
    ArrayList<String> patientPics;
    ArrayList<String> taskDesc;
    ArrayList<String> taskStart;
    ArrayList<String> taskRecurrence;
    ArrayList<String> taskDone;
    ArrayList<String> medName;
    ArrayList<String> medDone;
    ArrayList<String> medDays;
    ArrayList<String> medPicPath;
    ArrayList<String> medAmount;
    int type;

    MyAdapter() {
        type = 0;
    }

    MyAdapter(ArrayList<String> names, ArrayList<String> ages, ArrayList<String> pics){
        patientNames = names;
        patientAges = ages;
        patientPics = pics;
        type = 1;
    }

    MyAdapter(ArrayList<String> descs, ArrayList<String> starts, ArrayList<String> recur, ArrayList<String> done){
        taskDesc = descs;
        taskStart = starts;
        taskRecurrence = recur;
        taskDone = done;
        type = 2;
    }

    MyAdapter(ArrayList<String> names, ArrayList<String> done, ArrayList<String> days, ArrayList<String> pics, ArrayList<String> amount){
        medName = names;
        medDone = done;
        medDays = days;
        medPicPath = pics;
        medAmount = amount;
        type = 3;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_card_view, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.cv.setTag(i);
        switch(type) {
            case 0:
                break;
            case 1:
                personViewHolder.personName.setText(patientNames.get(i));
                personViewHolder.personAge.setText(patientAges.get(i));
                personViewHolder.personPhoto.setImageResource(R.drawable.ic_launcher_background);
                //personViewHolder.personPhoto.setImageResource(patientPics.get(i));
                break;
            case 2:
                personViewHolder.personName.setText(patientNames.get(i));
                personViewHolder.personAge.setText(patientAges.get(i));
                personViewHolder.personPhoto.setImageResource(R.drawable.ic_launcher_background);
                //personViewHolder.personPhoto.setImageResource(patientPics.get(i));
                break;
            case 3:
                personViewHolder.personName.setText(patientNames.get(i));
                personViewHolder.personAge.setText(patientAges.get(i));
                personViewHolder.personPhoto.setImageResource(R.drawable.ic_launcher_background);
                //personViewHolder.personPhoto.setImageResource(patientPics.get(i));
                break;
        }
    }

    @Override
    public int getItemCount() {
        // Add for other types
        return patientNames.size();
    }
}

class MedAdapter extends MyAdapter {

}