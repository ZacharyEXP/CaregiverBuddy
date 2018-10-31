package com.example.zacharyexp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;

/*
L.A.Y. : Look After Yourself

Project by Florent Philippe : https://github.com/florentphilippe
Started on March, 7th 2017

 */

public class HealthActivity extends AppCompatActivity {
    //Recycler view references
    private RecyclerView mainRecycler;
    private RecyclerView.LayoutManager mainRecyclerLayout;

    //health containers
    static ArrayList<Health> healthList = new ArrayList<>();
    static ArrayList<Health> areHappeningToday = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("appAction","=====SERVICE STARTS=====");
        Log.i("appAction","Launching healthActivity ...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        //Import health container from file
        Log.i("appAction","Grabbing ealth Array container");
        healthList = HealthTools.readAnArray(getApplicationContext());
        Log.i("appAction","Health container length : " + healthList.size());

        //Delete old elements
        //HealthTools.deleteHealthBeforeADate(healthsList, Calendar.getInstance());

        //Set areHappeningToday list with the healths which should be taken on this day by the user
        Log.i("appAction", "Setting areHappeningToday list from the healths of the stored file ...");

        for (int i = 0; i < healthList.size(); i++){
            if (healthList.get(i).isHappeningToday()){
                areHappeningToday.add(healthList.get(i));
            }
        }
        Log.i("appAction", "areHappeningToday list size : " + areHappeningToday.size());


        //Importing Floating Action button
        FloatingActionButton newHealthButton = (FloatingActionButton) findViewById(R.id.new_health_button);

        //Setting the newhealthButton click listener
        newHealthButton.setOnClickListener(
                new FloatingActionButton.OnClickListener(){
                    public void onClick(View v){

                        //Setting the new activity
                        Intent i = new Intent (getApplicationContext(),NewHealth.class);
                        startActivity(i);
                    }
                }
        );

    }

    @Override
    protected void onResume() {
        super.onResume();

        //Sort list
        areHappeningToday = HealthTools.healthContainerSorter(areHappeningToday);

        //--Description statement--
        StringBuilder stringBuilder = new StringBuilder();
        for (Health health : areHappeningToday){
            stringBuilder.append("'" + health.getRelativeTimeDescriber() + "', ");
        }
        Log.i("appAction", "List state AreHappeningToday : " + stringBuilder.toString());
        //-- --

        //Disable "nothing to show" TextView if the list is empty
        if (!areHappeningToday.isEmpty()){
            findViewById(R.id.text_nothing).setVisibility(View.GONE);
        }

        //Recycler view management & integration
        mainRecycler = (RecyclerView) findViewById(R.id.main_recycler);

        mainRecycler.setHasFixedSize(true);

        mainRecyclerLayout = new LinearLayoutManager(this);
        mainRecycler.setLayoutManager(mainRecyclerLayout);

        mainRecycler.setAdapter(new HealthRecyclerAdapter(areHappeningToday));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("appAction", "onDestroy list length : " + healthList.size());
        HealthTools.writeAnArray(healthList, getApplicationContext());
        Log.i("appAction", "ArrayList saved !");
    }
}