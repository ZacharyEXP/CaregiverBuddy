package com.example.zacharyexp.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class BioFragment extends Fragment{
    Patient p;
    Context c;

    CardView cv;
    TextView personName;
    TextView personAge;
    ImageView personPhoto;

    View rootView;
    LayoutInflater in;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        in = inflater;
        rootView = in.inflate(R.layout.card_view_patient, container, false);

        c = getContext();
        p = new Patient(c, getActivity().getIntent().getIntExtra("PATIENT_ID", -1));

        cv = (CardView)rootView.findViewById(R.id.cvPatient);
        personName = (TextView)rootView.findViewById(R.id.person_name);
        personAge = (TextView)rootView.findViewById(R.id.person_age);
        personPhoto = (ImageView)rootView.findViewById(R.id.person_photo);

        personName.setText(p.getPatientName());
        personAge.setText(Integer.toString(p.getPatientAge()));
        personPhoto.setImageResource(R.drawable.ic_launcher_background);
        //personPhoto.setImageResource(p.getPatientPicPath());

        return rootView;
    }

    public void removeView() {
        cv.removeView(rootView);
    }

    void openDialog() {

    }
}
