package com.govst.zacharyexp.caregiverbuddy.tbd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.govst.zacharyexp.caregiverbuddy.Patient;
import com.govst.zacharyexp.caregiverbuddy.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class BioFragment extends Fragment{
    Patient p;
    Context c;

    CardView cv;
    TextView personName;
    TextView personAge;
    ImageView personPhoto;
    RelativeLayout rl;

    View rootView;
    LayoutInflater in;

    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        in = inflater;
        rootView = in.inflate(R.layout.card_view_patient, container, false);

        FloatingActionButton fab = (FloatingActionButton)getActivity().findViewById(R.id.open_dialog);
        fab.setVisibility(View.GONE);

        c = getContext();
        p = new Patient(c, getActivity().getIntent().getIntExtra("PATIENT_ID", -1));

        cv = (CardView)rootView.findViewById(R.id.cvPatient);
        personName = (TextView)rootView.findViewById(R.id.person_name);
        personAge = (TextView)rootView.findViewById(R.id.person_age);
        personPhoto = (ImageView)rootView.findViewById(R.id.person_photo);
        rl = (RelativeLayout)rootView.findViewById(R.id.patLayout);

        personName.setText(p.getPatientName());
        personAge.setText(Integer.toString(p.getPatientAge()));
        //personPhoto.setImageResource(R.drawable.ic_launcher_background);
        //personPhoto.setImageResource(p.getPatientPicPath());

        try {
            personPhoto.setImageURI(Uri.parse(p.getPatientPicPath()));
            android.view.ViewGroup.LayoutParams layoutParams = personPhoto.getLayoutParams();
            layoutParams.width = 300;
            layoutParams.height = 300;
            personPhoto.setLayoutParams(layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
            personPhoto.setImageResource(R.drawable.ic_launcher_background);
        }

        return rootView;
    }
}
