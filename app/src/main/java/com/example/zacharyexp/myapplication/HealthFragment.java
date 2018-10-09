package com.example.zacharyexp.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class HealthFragment extends Fragment {
    private RecyclerView rv;
    CustomListener cl;

    public HealthFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drawer, container, false);

        CustomListener cl  = (View c, int position) -> {

        };

        rv = (RecyclerView)rootView.findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());
        rv.setLayoutManager(llm);

        ArrayList<String> persons = new ArrayList<>(Arrays.asList("London", "Tokyo", "New York", "London2", "Tokyo2", "New York2", "London3", "Tokyo3"));
        ArrayList<String> ages = new ArrayList<>(Arrays.asList("11", "22", "33", "44", "55", "66", "77", "88"));
        MedAdapter mAdapter = new MedAdapter(persons, ages, persons, ages, persons, cl);
        rv.setAdapter(mAdapter);
        return rootView;
    }

}