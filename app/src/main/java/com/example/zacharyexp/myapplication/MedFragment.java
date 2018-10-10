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

public class MedFragment extends Fragment {
    private RecyclerView rv;
    CustomListener cl;
    Patient p;
    Context c;

    public MedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drawer, container, false);

        c = getContext();
        p = new Patient(c, getActivity().getIntent().getIntExtra("PATIENT_ID", -1));

        rv = (RecyclerView)rootView.findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());
        rv.setLayoutManager(llm);

        //FloatingActionButton fab = findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});

        CustomListener cl  = (View c, int position) -> {

        };
        //rv.setHasFixedSize(true);

        ArrayList<String> medNames = p.getMedNames();
        ArrayList<String> medsDone = p.getMedsDone();
        ArrayList<String> medDays = p.getMedsDays();
        ArrayList<String> medPics= p.getMedPicPaths();
        ArrayList<String> medAmounts = p.getMedAmounts();
        MedAdapter mAdapter = new MedAdapter(medNames, medsDone, medDays, medPics, medAmounts, cl);
        rv.setAdapter(mAdapter);
        return rootView;
    }

}