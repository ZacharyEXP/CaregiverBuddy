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

        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;
        Button personButton;

        PersonViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
            //personButton = (Button)itemView.findViewById(R.id.person_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    // item clicked
                    int position = (int) v.getTag();
                    //v.setBackgroundColor(999);
                    System.out.println(position);
                }
            });
        }
    }

    ArrayList<String> persons;

    MyAdapter(ArrayList<String> persons){
        this.persons = persons;
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
        personViewHolder.personName.setText(persons.get(i));
        personViewHolder.personAge.setText(persons.get(i));
        personViewHolder.personPhoto.setImageResource(R.drawable.ic_launcher_background);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}