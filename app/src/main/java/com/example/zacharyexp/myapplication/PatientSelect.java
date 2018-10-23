package com.example.zacharyexp.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class PatientSelect extends Activity {
    Patient p; //= new Patient();
    Context c;

    String name;
    int pos, id;

    private RecyclerView rv;
    MyAdapter mAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_select);

        //CustomListener listener = (getContext(), position)

        // Creates a recycler view containing the names, ages, and pictures of all patients
        rv = (RecyclerView)findViewById(R.id.rv);

        RecyclerView.LayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        c = getApplicationContext();
        p = new Patient(c);

        CustomListener listener = (View c, int position) -> {
            pos = position;
            name = checkName(pos);
            id = p.getIdFromName(name);
            startPatientHome(c, id);
        };

        ArrayList<String> names = p.getListNames();
        ArrayList<String> ages = p.getListAges();
        ArrayList<String> pics = p.getListPics();

        Button testbutton = (Button)findViewById(R.id.testbutton);

        System.out.println(names);

        mAdapter = new MyAdapter(names, ages, pics, listener);
        rv.setAdapter(mAdapter);

        testbutton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        startPatientInfo(v);
                    }
                }
        );
    }

    public void startPatientInfo(View view) {
        Intent intent = new Intent(this, NewPatientActivity.class);
        startActivity(intent);
    }

    public void startPatientHome(View view, int id) {
        //Intent intent = new Intent(this, MainScreen.class);
        Intent intent = new Intent(this, MainSelect.class);
        intent.putExtra("PATIENT_ID", id);
        startActivity(intent);
    }

    public String checkName() {
        return mAdapter.getName();
    }

    public String checkName(int pos) {
        return mAdapter.getName(pos);
    }
}
