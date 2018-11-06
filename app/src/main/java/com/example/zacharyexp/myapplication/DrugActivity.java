package com.example.zacharyexp.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class DrugActivity extends AppCompatActivity implements DrugRecyclerAdapter.OnItemClicked{
    //Recycler view references
    private RecyclerView mainRecycler;
    private RecyclerView.LayoutManager mainRecyclerLayout;
    private DrugRecyclerAdapter mainAdapter;

    //Drug containers
    static ArrayList<Drug> drugsList = new ArrayList<>();
    //static ArrayList<Drug> areHappeningToday = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("appAction","=====SERVICE STARTS=====");
        Log.i("appAction","Launching DrugActivity ...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);
        drugsList.clear();
        //areHappeningToday.clear();

        //Import drug container from file
        Log.i("appAction","Grabbing drugs Array container");
        drugsList = DrugTools.readAnArray(getApplicationContext());
        Log.i("appAction","Drugs container length : " + drugsList.size());

        //Delete old elements
        //DrugTools.deleteDrugBeforeADate(drugsList, Calendar.getInstance());

        //Set areHappeningToday list with the drugs which should be taken on this day by the user
        Log.i("appAction", "Setting areHappeningToday list from the drugs of the stored file ...");

        //for (int i = 0; i < drugsList.size(); i++){
            //if (drugsList.get(i).isHappeningToday()){
                //areHappeningToday.add(drugsList.get(i));
            //}
        //}
        //Log.i("appAction", "areHappeningToday list size : " + areHappeningToday.size());
        System.out.println("Test Cre");


        //Importing Floating Action button
        FloatingActionButton newDrugButton = (FloatingActionButton) findViewById(R.id.new_drug_button);

        //Setting the newDrugButton click listener
        newDrugButton.setOnClickListener(
            new FloatingActionButton.OnClickListener(){
                public void onClick(View v){

                    //Setting the new activity
                    Intent i = new Intent (getApplicationContext(),NewDrug.class);
                    startActivity(i);
                }
            }
        );

    }

    @Override
    protected void onResume() {
        super.onResume();

        drugsList.clear();
        //areHappeningToday.clear();

        //Import drug container from file
        drugsList = DrugTools.readAnArray(getApplicationContext());

        //for (int i = 0; i < drugsList.size(); i++){
            //if (drugsList.get(i).isHappeningToday()){
                //areHappeningToday.add(drugsList.get(i));
            //}
        //}
        //Sort list
        drugsList = DrugTools.drugContainerSorter(drugsList);
        System.out.println("Test Res");

        //--Description statement--
        StringBuilder stringBuilder = new StringBuilder();
        for (Drug drug : drugsList){
            stringBuilder.append("'" + drug.getRelativeTimeDescriber() + "', ");
        }
        Log.i("appAction", "List state AreHappeningToday : " + stringBuilder.toString());
        //-- --

        //Disable "nothing to show" TextView if the list is empty
        if (!drugsList.isEmpty()){
            findViewById(R.id.text_nothing).setVisibility(View.GONE);
        }

        //Recycler view management & integration
        mainRecycler = (RecyclerView) findViewById(R.id.main_recycler);
        mainRecycler.setHasFixedSize(true);

        mainRecyclerLayout = new LinearLayoutManager(this);

        mainRecycler.setLayoutManager(mainRecyclerLayout);
        mainAdapter = new DrugRecyclerAdapter(drugsList);

        mainAdapter.setOnClick(DrugActivity.this);

        mainRecycler.setAdapter(mainAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("appAction", "onDestroy list length : " + drugsList.size());
        DrugTools.writeAnArray(drugsList, getApplicationContext());
        Log.i("appAction", "ArrayList saved !");
    }

    @Override
    public void onItemClick(int position) {
        drugsList.remove(position);
        mainAdapter = new DrugRecyclerAdapter(drugsList);
        mainAdapter.setOnClick(DrugActivity.this);
        mainRecycler.setAdapter(mainAdapter);
    }
}
