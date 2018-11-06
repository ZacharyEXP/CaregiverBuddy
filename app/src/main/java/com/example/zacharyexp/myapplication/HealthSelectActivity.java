package com.example.zacharyexp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HealthSelectActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_select);
    }

    public void openHeartrate(View v){
        Intent intent = new Intent(this, HealthActivity.class);
        intent.putExtra("HEALTH_TYPE", 0);
        startActivity(intent);
    }

    public void openBloodpressure(View v){
        Intent intent = new Intent(this, HealthActivity.class);
        intent.putExtra("HEALTH_TYPE", 1);
        startActivity(intent);
    }

    public void openGlucose(View v){
        Intent intent = new Intent(this, HealthActivity.class);
        intent.putExtra("HEALTH_TYPE", 2);
        startActivity(intent);
    }

    public void openFood(View v){
        Intent intent = new Intent(this, HealthActivity.class);
        intent.putExtra("HEALTH_TYPE", 3);
        startActivity(intent);
    }

    public void openWeight(View v){
        Intent intent = new Intent(this, HealthActivity.class);
        intent.putExtra("HEALTH_TYPE", 4);
        startActivity(intent);
    }

    public void openSleep(View v){
        Intent intent = new Intent(this, HealthActivity.class);
        intent.putExtra("HEALTH_TYPE", 5);
        startActivity(intent);
    }
}
