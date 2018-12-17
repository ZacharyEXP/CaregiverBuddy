package com.govst.zacharyexp.caregiverbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.govst.zacharyexp.caregiverbuddy.biography.BioNewActivity;
import com.govst.zacharyexp.caregiverbuddy.braingame.QuizMainActivity;
import com.govst.zacharyexp.caregiverbuddy.calendar.NewCalendar;
import com.govst.zacharyexp.caregiverbuddy.chore.ChoreActivity;
import com.govst.zacharyexp.caregiverbuddy.drug.DrugActivity;
import com.govst.zacharyexp.caregiverbuddy.health.HealthSelectActivity;

public class MainSelect extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBio(View v){
        int id = getIntent().getIntExtra("PATIENT_ID", -1);
        Intent intent = new Intent(this, BioNewActivity.class);
        intent.putExtra("PATIENT_ID", id);
        System.out.println("#" + id);
        startActivity(intent);
    }

    public void openHealth(View v) {
        Intent intent = new Intent(this, HealthSelectActivity.class);
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

    public void openGame(View v) {
        Intent intent = new Intent(this, QuizMainActivity.class);
        startActivity(intent);
    }
}
