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

public class HealthActivity extends AppCompatActivity implements HealthRecyclerAdapter.OnItemClicked{
    //Recycler view references
    private RecyclerView mainRecycler;
    private RecyclerView.LayoutManager mainRecyclerLayout;
    private HealthRecyclerAdapter mainAdapter;

    //health containers
    static ArrayList<Health> healthList = new ArrayList<>();

    int healthType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("appAction","=====SERVICE STARTS=====");
        Log.i("appAction","Launching healthActivity ...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        healthType = getIntent().getIntExtra("HEALTH_TYPE", -1);

        //Import health container from file
        Log.i("appAction","Grabbing ealth Array container");
        healthList = HealthTools.readAnArray(getApplicationContext());
        Log.i("appAction","Health container length : " + healthList.size());

        //Delete old elements
        //HealthTools.deleteHealthBeforeADate(healthsList, Calendar.getInstance());

        //Set areHappeningToday list with the healths which should be taken on this day by the user
        Log.i("appAction", "Setting areHappeningToday list from the healths of the stored file ...");



        //Importing Floating Action button
        FloatingActionButton newHealthButton = (FloatingActionButton) findViewById(R.id.new_health_button);

        //Setting the newhealthButton click listener
        newHealthButton.setOnClickListener(
                new FloatingActionButton.OnClickListener(){
                    public void onClick(View v){

                        //Setting the new activity
                        Intent i = new Intent (getApplicationContext(),NewHealth.class);
                        i.putExtra("HEALTH_TYPE", healthType);
                        startActivity(i);
                    }
                }
        );

    }

    @Override
    protected void onResume() {
        super.onResume();

        //Sort list
        healthList = HealthTools.healthContainerSorter(healthList);

        //-- --

        //Disable "nothing to show" TextView if the list is empty
        if (!healthList.isEmpty()){
            findViewById(R.id.text_nothing).setVisibility(View.GONE);
        }

        //Recycler view management & integration
        mainRecycler = (RecyclerView) findViewById(R.id.main_recycler);

        mainRecycler.setHasFixedSize(true);

        mainRecyclerLayout = new LinearLayoutManager(this);
        mainRecycler.setLayoutManager(mainRecyclerLayout);

        mainAdapter = new HealthRecyclerAdapter(healthList, healthType);

        mainAdapter.setOnClick(HealthActivity.this);

        mainRecycler.setAdapter(mainAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("appAction", "onDestroy list length : " + healthList.size());
        HealthTools.writeAnArray(healthList, getApplicationContext());
        Log.i("appAction", "ArrayList saved !");
    }

    @Override
    public void onItemClick(int position) {
        healthList.remove(position);
        mainAdapter = new HealthRecyclerAdapter(healthList, healthType);
        mainAdapter.setOnClick(HealthActivity.this);
        mainRecycler.setAdapter(mainAdapter);
    }
}