package com.govst.zacharyexp.caregiverbuddy;

import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.PersonViewHolder> {
    public class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Rename var to abstract
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        CustomListener cl;

        PersonViewHolder(final View itemView, CustomListener listener) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvPatient);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
            cl = listener;
            System.out.println("PersonViewHolder init");

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try {
                cl.onClick(view, getAdapterPosition());
                System.out.println(getAdapterPosition());
                setName((String)personName.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    ArrayList<String> patientNames;
    ArrayList<String> patientAges;
    ArrayList<String> patientPics;

    int index = -1;
    static String name;

    CustomListener cl;

    MyAdapter(ArrayList<String> names, ArrayList<String> ages, ArrayList<String> pics, CustomListener li){
        patientNames = names;
        patientAges = ages;
        patientPics = pics;
        cl = li;
    }

    public static void setName(String s) {name = s;}

    public String getName() {return name;}

    public String getName(int index) {return patientNames.get(index);}

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_patient, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v, cl);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.cv.setTag(i);
        System.out.println(i);
        personViewHolder.personName.setText(patientNames.get(i));
        personViewHolder.personAge.setText(patientAges.get(i));
        try {
            personViewHolder.personPhoto.setImageURI(Uri.parse(patientPics.get(i)));
            android.view.ViewGroup.LayoutParams layoutParams = personViewHolder.personPhoto.getLayoutParams();
            layoutParams.width = 300;
            layoutParams.height = 300;
            personViewHolder.personPhoto.setLayoutParams(layoutParams);
            //medViewHolder.medPic.setHeight(30);
            //medViewHolder.medPic.setMaxWidth(30);
            //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
            //iv.setLayoutParams(layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
            personViewHolder.personPhoto.setImageResource(R.drawable.ic_launcher_background);
        }
        //personViewHolder.personPhoto.setImageResource(patientPics.get(i));
    }

    @Override
    public int getItemCount() {
        // Add for other types
        return patientNames.size();
    }
}

class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Rename var to abstract
        CardView cv;
        TextView taskName;
        TextView taskSt;
        TextView taskRecur;
        TextView taskFin;

        CustomListener cl;

        TaskViewHolder(final View itemView, CustomListener listener) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvTask);
            taskName = (TextView)itemView.findViewById(R.id.task_desc);
            taskSt = (TextView)itemView.findViewById(R.id.task_start);
            taskRecur = (TextView) itemView.findViewById(R.id.task_recur);
            taskFin = (TextView)itemView.findViewById(R.id.task_done);
            cl = listener;
            System.out.println("TaskViewHolder init");

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try {
                cl.onClick(view, getAdapterPosition());
                System.out.println(getAdapterPosition());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    ArrayList<String> taskDesc;
    ArrayList<String> taskStart;
    ArrayList<String> taskRecurrence;
    ArrayList<String> taskDone;

    CustomListener cl;

    TaskAdapter(ArrayList<String> descs, ArrayList<String> starts, ArrayList<String> recur, ArrayList<String> done, CustomListener li){
        taskDesc = descs;
        taskStart = starts;
        taskRecurrence = recur;
        taskDone = done;
        cl = li;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_task, viewGroup, false);
        TaskViewHolder pvh = new TaskViewHolder(v, cl);
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
    public class MedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Rename var to abstract
        CardView cv;
        TextView medName;
        TextView medDone;
        TextView medDay;
        TextView medAmount;
        ImageView medPic;

        CustomListener cl;

        MedViewHolder(final View itemView, CustomListener listener) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvMed);
            medName = (TextView)itemView.findViewById(R.id.med_name);
            medDone = (TextView)itemView.findViewById(R.id.med_done);
            medDay = (TextView) itemView.findViewById(R.id.med_day);
            medAmount = (TextView)itemView.findViewById(R.id.med_amount);
            medPic = (ImageView)itemView.findViewById(R.id.med_photo);
            cl = listener;
            System.out.println("MedViewHolder init");

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try {
                cl.onClick(view, getAdapterPosition());
                System.out.println(getAdapterPosition());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    ArrayList<String> medNames;
    ArrayList<String> medsDone;
    ArrayList<String> medDays;
    ArrayList<String> medPicPaths;
    ArrayList<String> medAmounts;

    CustomListener cl;

    MedAdapter(ArrayList<String> names, ArrayList<String> done, ArrayList<String> days, ArrayList<String> pics, ArrayList<String> amount, CustomListener li){
        medNames = names;
        medsDone = done;
        medDays = days;
        medPicPaths = pics;
        medAmounts = amount;
        cl = li;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MedViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_med, viewGroup, false);
        MedViewHolder pvh = new MedViewHolder(v, cl);
        return pvh;
    }

    @Override
    public void onBindViewHolder(MedViewHolder medViewHolder, int i) {
        medViewHolder.cv.setTag(i);
        medViewHolder.medName.setText(medNames.get(i));
        medViewHolder.medDay.setText(medDays.get(i));
        medViewHolder.medAmount.setText(medAmounts.get(i));
        medViewHolder.medDone.setText(medsDone.get(i));
        //medViewHolder.medPic.setImageResource(R.drawable.ic_launcher_background);
        try {
            medViewHolder.medPic.setImageURI(Uri.parse(medPicPaths.get(i)));
            android.view.ViewGroup.LayoutParams layoutParams = medViewHolder.medPic.getLayoutParams();
            layoutParams.width = 300;
            layoutParams.height = 300;
            medViewHolder.medPic.setLayoutParams(layoutParams);
            //medViewHolder.medPic.setHeight(30);
            //medViewHolder.medPic.setMaxWidth(30);
            //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
            //iv.setLayoutParams(layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
            medViewHolder.medPic.setImageResource(R.drawable.ic_launcher_background);
        }
    }

    @Override
    public int getItemCount() {
        // Add for other types
        return medNames.size();
    }
}