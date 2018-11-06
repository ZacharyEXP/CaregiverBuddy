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

public class ChoreActivity extends AppCompatActivity implements ChoreRecyclerAdapter.OnItemClicked{
    //Recycler view references
    private RecyclerView mainRecycler;
    private RecyclerView.LayoutManager mainRecyclerLayout;
    private ChoreRecyclerAdapter mainAdapter;

    //chore containers
    static ArrayList<Chore> choresList = new ArrayList<>();
    //static ArrayList<Chore> areHappeningToday = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("appAction","=====SERVICE STARTS=====");
        Log.i("appAction","Launching choreActivity ...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore);
        choresList.clear();
        //areHappeningToday.clear();

        //Import chore container from file
        Log.i("appAction","Grabbing chores Array container");
        choresList = ChoreTools.readAnArray(getApplicationContext());
        Log.i("appAction","Chores container length : " + choresList.size());

        //Delete old elements
        //ChoreTools.deleteChoreBeforeADate(choresList, Calendar.getInstance());

        //Set areHappeningToday list with the chores which should be taken on this day by the user
        Log.i("appAction", "Setting areHappeningToday list from the chores of the stored file ...");

        //for (int i = 0; i < choresList.size(); i++){
            ///if (choresList.get(i).isHappeningToday()){
                //areHappeningToday.add(choresList.get(i));
            //}
        //}
        //Log.i("appAction", "areHappeningToday list size : " + areHappeningToday.size());


        //Importing Floating Action button
        FloatingActionButton newChoreButton = (FloatingActionButton) findViewById(R.id.new_chore_button);

        //Setting the newchoreButton click listener
        newChoreButton.setOnClickListener(
            new FloatingActionButton.OnClickListener(){
                public void onClick(View v){

                    //Setting the new activity
                    Intent i = new Intent (getApplicationContext(),NewChore.class);
                    startActivity(i);
                }
            }
        );

    }

    @Override
    protected void onResume() {
        super.onResume();

        choresList.clear();

        choresList = ChoreTools.readAnArray(getApplicationContext());
        //Sort list
        choresList = ChoreTools.choreContainerSorter(choresList);

        //--Description statement--
        StringBuilder stringBuilder = new StringBuilder();
        //for (Chore chore : areHappeningToday){
            //stringBuilder.append("'" + chore.getRelativeTimeDescriber() + "', ");
        //}
        Log.i("appAction", "List state AreHappeningToday : " + stringBuilder.toString());
        //-- --

        //Disable "nothing to show" TextView if the list is empty
        if (!choresList.isEmpty()){
            findViewById(R.id.text_nothing).setVisibility(View.GONE);
        }

        //Recycler view management & integration
        mainRecycler = (RecyclerView) findViewById(R.id.main_recycler);

        mainRecycler.setHasFixedSize(true);

        mainRecyclerLayout = new LinearLayoutManager(this);
        mainRecycler.setLayoutManager(mainRecyclerLayout);

        mainAdapter = new ChoreRecyclerAdapter(choresList);

        mainAdapter.setOnClick(ChoreActivity.this);

        mainRecycler.setAdapter(mainAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("appAction", "onDestroy list length : " + choresList.size());
        ChoreTools.writeAnArray(choresList, getApplicationContext());
        Log.i("appAction", "ArrayList saved !");
    }

    @Override
    public void onItemClick(int position) {
        choresList.remove(position);
        mainAdapter = new ChoreRecyclerAdapter(choresList);
        mainAdapter.setOnClick(ChoreActivity.this);
        mainRecycler.setAdapter(mainAdapter);
    }
}
