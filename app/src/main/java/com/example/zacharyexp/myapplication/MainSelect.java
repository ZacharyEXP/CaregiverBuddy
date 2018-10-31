package com.example.zacharyexp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainSelect extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBio(View v){
        int id = getIntent().getIntExtra("PATIENT_ID", -1);
        Intent intent = new Intent(this, BioActivity.class);
        intent.putExtra("PATIENT_ID", id);
        System.out.println("#" + id);
        startActivity(intent);
    }

    public void openHealth(View v) {
        Intent intent = new Intent(this, HealthActivity.class);
        startActivity(intent);
    }

    public void openMedicine(View v) {
        Intent intent = new Intent(this, DrugActivity.class);
        startActivity(intent);
    }

    public void openTasks(View v) {
        Intent intent = new Intent(this, ChoreActivity.class);
        startActivity(intent);
    }

    public void openCalendar(View v) {
        Intent intent = new Intent(this, NewCalendar.class);
        startActivity(intent);
    }
}
