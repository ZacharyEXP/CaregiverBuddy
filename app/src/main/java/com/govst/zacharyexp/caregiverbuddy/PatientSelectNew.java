package com.govst.zacharyexp.caregiverbuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;


import com.govst.zacharyexp.caregiverbuddy.biography.BioEdit;
import com.govst.zacharyexp.caregiverbuddy.biography.NewPatientActivity;
import com.govst.zacharyexp.caregiverbuddy.library.EuclidActivity;
import com.govst.zacharyexp.caregiverbuddy.library.EuclidListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientSelectNew extends EuclidActivity {
    Patient p; //= new Patient();
    Context c;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        c = getApplicationContext();
        p = new Patient(c);
        super.onCreate(savedInstanceState);

        mButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PatientSelectNew.this, "Oh hi!", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton newPatientButton = findViewById(R.id.new_patient);

        //Setting the newhealthButton click listener
        newPatientButton.setOnClickListener(
                new FloatingActionButton.OnClickListener(){
                    public void onClick(View v){

                        //Setting the new activity
                        Intent i = new Intent (getApplicationContext(), BioEdit.class);
                        startActivity(i);
                    }
                }
        );
    }

    @Override
    protected BaseAdapter getAdapter() {
        Map<String, Object> profileMap;
        List<Map<String, Object>> profilesList = new ArrayList<>();

        System.out.println("Test D");

        ArrayList<String> avatars = new ArrayList<>();
        for (String s : p.getListPics()) {
            System.out.println("Test B");
            avatars.add(s);
        }
        ArrayList<String> names = new ArrayList<>();
        for (String s : p.getListNames()) {
            System.out.println("Test C");
            names.add(s);
        }
        ArrayList<String> ages = new ArrayList<>();
        for (String s : p.getListAges()) {
            System.out.println("Test C");
            ages.add(s);
        }

        for (int i = 0; i < avatars.size(); i++) {
            profileMap = new HashMap<>();
            profileMap.put(EuclidListAdapter.KEY_AVATAR, avatars.get(i));
            profileMap.put(EuclidListAdapter.KEY_NAME, names.get(i));
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, "Age " + ages.get(i));
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, getString(R.string.lorem_ipsum_long));
            profilesList.add(profileMap);
        }

        return new EuclidListAdapter(this, R.layout.list_item, profilesList);
    }

}
