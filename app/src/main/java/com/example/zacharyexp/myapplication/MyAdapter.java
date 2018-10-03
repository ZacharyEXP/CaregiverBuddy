package com.example.zacharyexp.myapplication;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        // Rename var to abstract
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        PersonViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvPatient);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
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

    MyAdapter(ArrayList<String> names, ArrayList<String> ages, ArrayList<String> pics){
        patientNames = names;
        patientAges = ages;
        patientPics = pics;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.patient_card_view, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.cv.setTag(i);
        personViewHolder.personName.setText(patientNames.get(i));
        personViewHolder.personAge.setText(patientAges.get(i));
        personViewHolder.personPhoto.setImageResource(R.drawable.ic_launcher_background);
        //personViewHolder.personPhoto.setImageResource(patientPics.get(i));
    }

    @Override
    public int getItemCount() {
        // Add for other types
        return patientNames.size();
    }
}

class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        // Rename var to abstract
        CardView cv;
        TextView taskName;
        TextView taskSt;
        TextView taskRecur;
        TextView taskFin;

        TaskViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvTask);
            taskName = (TextView)itemView.findViewById(R.id.task_desc);
            taskSt = (TextView)itemView.findViewById(R.id.task_start);
            taskRecur = (TextView) itemView.findViewById(R.id.task_recur);
            taskFin = (TextView)itemView.findViewById(R.id.task_done);
            System.out.println("TaskViewHolder init");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = (int) v.getTag();
                    System.out.println(position);
                }
            });
        }
    }

    ArrayList<String> taskDesc;
    ArrayList<String> taskStart;
    ArrayList<String> taskRecurrence;
    ArrayList<String> taskDone;

    TaskAdapter(ArrayList<String> descs, ArrayList<String> starts, ArrayList<String> recur, ArrayList<String> done){
        taskDesc = descs;
        taskStart = starts;
        taskRecurrence = recur;
        taskDone = done;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_card_view, viewGroup, false);
        TaskViewHolder pvh = new TaskViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder taskViewHolder, int i) {
        taskViewHolder.cv.setTag(i);
        taskViewHolder.taskName.setText(taskDesc.get(i));
        taskViewHolder.taskSt.setText(taskStart.get(i));
        taskViewHolder.taskRecur.setText(taskRecurrence.get(i));
        taskViewHolder.taskFin.setText(taskDone.get(i));
    }

    @Override
    public int getItemCount() {
        // Add for other types
        return taskDesc.size();
    }
}

class MedAdapter extends RecyclerView.Adapter<MedAdapter.MedViewHolder> {
    public static class MedViewHolder extends RecyclerView.ViewHolder {
        // Rename var to abstract
        CardView cv;
        TextView medName;
        TextView medDone;
        TextView medDay;
        TextView medAmount;
        ImageView medPic;

        MedViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvMed);
            medName = (TextView)itemView.findViewById(R.id.med_name);
            medDone = (TextView)itemView.findViewById(R.id.med_done);
            medDay = (TextView) itemView.findViewById(R.id.med_day);
            medAmount = (TextView)itemView.findViewById(R.id.med_amount);
            medPic = (ImageView)itemView.findViewById(R.id.med_photo);
            System.out.println("TaskViewHolder init");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = (int) v.getTag();
                    System.out.println(position);
                }
            });
        }
    }

    ArrayList<String> medNames;
    ArrayList<String> medsDone;
    ArrayList<String> medDays;
    ArrayList<String> medPicPaths;
    ArrayList<String> medAmounts;

    MedAdapter(ArrayList<String> names, ArrayList<String> done, ArrayList<String> days, ArrayList<String> pics, ArrayList<String> amount){
        medNames = names;
        medsDone = done;
        medDays = days;
        medPicPaths = pics;
        medAmounts = amount;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MedViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.med_card_view, viewGroup, false);
        MedViewHolder pvh = new MedViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(MedViewHolder medViewHolder, int i) {
        medViewHolder.cv.setTag(i);
        medViewHolder.medName.setText(medNames.get(i));
        medViewHolder.medDay.setText(medDays.get(i));
        medViewHolder.medAmount.setText(medAmounts.get(i));
        medViewHolder.medDone.setText(medsDone.get(i));
        medViewHolder.medPic.setImageResource(R.drawable.ic_launcher_background);
        //personViewHolder.personPhoto.setImageResource(medPicPaths.get(i));
    }

    @Override
    public int getItemCount() {
        // Add for other types
        return medNames.size();
    }
}