package com.example.zacharyexp.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.Inflater;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MedFragment extends Fragment {
    private RecyclerView rv;
    CustomListener cl;
    Patient p;
    Context c;

    LayoutInflater in;

    public MedFragment() {
    }

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        in = inflater;
        View rootView = in.inflate(R.layout.fragment_drawer, container, false);

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

        ArrayList<String> medNames = p.getMedNames();
        ArrayList<String> medsDone = p.getMedsDone();
        ArrayList<String> medDays = p.getMedsDays();
        ArrayList<String> medPics= p.getMedPicPaths();
        ArrayList<String> medAmounts = p.getMedAmounts();
        MedAdapter mAdapter = new MedAdapter(medNames, medsDone, medDays, medPics, medAmounts, cl);
        rv.setAdapter(mAdapter);
        return rootView;
    }

    void openDialog() {
        // inflate the layout of the popup window
        View popupView = in.inflate(R.layout.popup_med, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        EditText medName = (EditText)popupView.findViewById(R.id.med_name);
        EditText medAmount = (EditText)popupView.findViewById(R.id.med_amount);
        EditText medDays = (EditText)popupView.findViewById(R.id.med_days);
        Button submit = (Button)popupView.findViewById(R.id.submit);

        submit.setOnClickListener(
                v -> {
                    p.addMedName(medName.getText().toString());
                    p.addMedAmount(medAmount.getText().toString());
                    p.addMedDays(medDays.getText().toString());
                    p.addMedDone("No");
                    p.addMedPicPath("Default");
                    p.save();

                    popupView.setVisibility(View.GONE);
                }
        );
    }
}