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

import com.govst.zacharyexp.caregiverbuddy.library.EuclidActivity;
import com.govst.zacharyexp.caregiverbuddy.library.EuclidActivity2;
import com.govst.zacharyexp.caregiverbuddy.library.EuclidListAdapter;
import com.govst.zacharyexp.caregiverbuddy.library.EuclidState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oleksii Shliama on 1/27/15.
 */
public class BioNewActivity extends EuclidActivity2 {
    Patient p; //= new Patient();
    Context c;
    int id;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        c = getApplicationContext();
        id = getIntent().getIntExtra("PATIENT_ID", -1);
        p = new Patient(c, id);
        super.onCreate(savedInstanceState);

        System.out.println("Test A");

        //super.mState = EuclidState.Opening;
        Map<String, Object> profileMap;
        profileMap = new HashMap<>();
        profileMap.put(EuclidListAdapter.KEY_AVATAR, p.getPatientPicPath());
        profileMap.put(EuclidListAdapter.KEY_NAME, p.getPatientName());
        profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, "Age " + p.getPatientAge());
        profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, p.getPatientDesc());
        //showProfileDetails(profileMap, getCurrentFocus());

        FloatingActionButton newPatientButton = (FloatingActionButton) findViewById(R.id.new_patient);

        //Setting the newhealthButton click listener
        newPatientButton.setOnClickListener(
                new FloatingActionButton.OnClickListener(){
                    public void onClick(View v){

                        //Setting the new activity
                        Intent i = new Intent (getApplicationContext(),NewPatientActivity.class);
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

        /*ArrayList<String> avatars = new ArrayList<>();
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
        }*/

            profileMap = new HashMap<>();
            profileMap.put(EuclidListAdapter.KEY_AVATAR, p.getPatientPicPath());
            profileMap.put(EuclidListAdapter.KEY_NAME, p.getPatientName());
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, "Age " + p.getPatientAge());
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, p.getPatientDesc());
            profilesList.add(profileMap);

        return new EuclidListAdapter(this, R.layout.list_item, profilesList);
    }

}
