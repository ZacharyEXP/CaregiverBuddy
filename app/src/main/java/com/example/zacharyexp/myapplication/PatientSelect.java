package com.example.zacharyexp.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class PatientSelect extends Activity {
    Patient p; //= new Patient();
    Context c;

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_select);

        // Creates a recycler view containing the names, ages, and pictures of all patients
        rv = (RecyclerView)findViewById(R.id.rv);

        RecyclerView.LayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        c = getApplicationContext();
        p = new Patient(c);

        ArrayList<String> names = p.getListNames();
        ArrayList<String> ages = p.getListAges();
        ArrayList<String> pics = p.getListPics();

        Button testbutton = (Button)findViewById(R.id.testbutton);

        System.out.println(names);

        MyAdapter mAdapter = new MyAdapter(names, ages, pics);
        rv.setAdapter(mAdapter);

        rv.setOnClickListener(
                new RecyclerView.OnClickListener() {
                    @Override public void onClick(View v) {
                        int position = (int) v.getTag();
                        //String t = .getName();//(String)v.getTag();
                        System.out.println(position);
                        startPatientHome(v, 2);
                    }
                }
        );


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
        Intent intent = new Intent(this, MainScreen.class);
        intent.putExtra("PATIENT_ID", id);
        startActivity(intent);
    }
}
