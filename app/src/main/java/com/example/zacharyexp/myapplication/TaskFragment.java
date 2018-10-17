package com.example.zacharyexp.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class TaskFragment extends Fragment {
    private RecyclerView rv;
    CustomListener cl;
    Patient p;
    Context c;

    LayoutInflater in;
    View rootView;
    View popupView;

    String radioRecur;

    public TaskFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        in = inflater;
        rootView = in.inflate(R.layout.fragment_drawer, container, false);

        c = getContext();
        p = new Patient(c, getActivity().getIntent().getIntExtra("PATIENT_ID", -1));

        FloatingActionButton fab = (FloatingActionButton)getActivity().findViewById(R.id.open_dialog);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        rv = (RecyclerView)rootView.findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());
        rv.setLayoutManager(llm);

        CustomListener cl  = (View c, int position) -> {

        };
        //rv.setHasFixedSize(true);

        ArrayList<String> taskDescs = p.getTaskDescs();
        ArrayList<String> taskStarts = p.getTaskStarts();
        ArrayList<String> taskDones = p.getTasksDone();
        ArrayList<String> taskRecurs= p.getTaskRecurrences();
        TaskAdapter mAdapter = new TaskAdapter(taskDescs, taskStarts, taskDones, taskRecurs, cl);
        rv.setAdapter(mAdapter);
        return rootView;
    }

    void openDialog() {
        // inflate the layout of the popup window
        popupView = in.inflate(R.layout.popup_task, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Date currentTime = Calendar.getInstance();


        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        EditText taskDesc = (EditText)popupView.findViewById(R.id.task_desc);
        EditText taskStart = (EditText)popupView.findViewById(R.id.task_start);
        //EditText taskRecur = (EditText)popupView.findViewById(R.id.task_recur);
        Button submit = (Button)popupView.findViewById(R.id.submit);
        RadioGroup taskRecur = (RadioGroup)popupView.findViewById(R.id.recur);
        RadioButton once = (RadioButton)popupView.findViewById(R.id.freq_once);
        RadioButton daily = (RadioButton)popupView.findViewById(R.id.freq_daily);
        RadioButton weekly = (RadioButton)popupView.findViewById(R.id.freq_weekly);

        submit.setOnClickListener(
                v -> {
                    if(once.isChecked() == true) {
                        radioRecur = "Once";
                    } else if(daily.isChecked() == true) {
                        radioRecur = "Daily";
                    } else if(weekly.isChecked() == true) {
                        radioRecur = "Weekly";
                    }
                    p.addTask(new Task(taskDesc.getText().toString(), taskStart.getText().toString(), radioRecur, "Not Done"));
                    p.save();

                    popupView.setVisibility(View.GONE);
                    update();
                }
        );
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.freq_once:
                if (checked) {
                    radioRecur = "Once";
                }
                    break;
            case R.id.freq_daily:
                if (checked) {
                    radioRecur = "Daily";
                }
                    break;
            case R.id.freq_weekly:
                if(checked) {
                    radioRecur = "Weekly";
                }
                break;
        }
    }

    void update() {
        ArrayList<String> taskDescs = p.getTaskDescs();
        ArrayList<String> taskStarts = p.getTaskStarts();
        ArrayList<String> taskDones = p.getTasksDone();
        ArrayList<String> taskRecurs= p.getTaskRecurrences();
        TaskAdapter mAdapter = new TaskAdapter(taskDescs, taskStarts, taskDones, taskRecurs, cl);
        rv.setAdapter(mAdapter);
    }

}