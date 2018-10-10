package com.example.zacharyexp.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskFragment extends Fragment {
    private RecyclerView rv;
    CustomListener cl;
    Patient p;
    Context c;

    public TaskFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drawer, container, false);

        c = getContext();
        p = new Patient(c, getActivity().getIntent().getIntExtra("PATIENT_ID", -1));

        rv = (RecyclerView)rootView.findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());
        rv.setLayoutManager(llm);

        CustomListener cl  = (View c, int position) -> {

        };
        //rv.setHasFixedSize(true);

        ArrayList<String> taskDescs = p.getTaskDescs();
        ArrayList<String> taskStarts = p.getTaskStarts();
        ArrayList<String> tasksDone = p.getTasksDone();
        ArrayList<String> taskRecurs = p.getTaskRecurrences();
        TaskAdapter mAdapter = new TaskAdapter(taskDescs, taskStarts, tasksDone, taskRecurs, cl);
        rv.setAdapter(mAdapter);
        return rootView;
    }

}