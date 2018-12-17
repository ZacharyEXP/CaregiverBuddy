package com.govst.zacharyexp.caregiverbuddy.chore;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.govst.zacharyexp.caregiverbuddy.DatePickerFragment;
import com.govst.zacharyexp.caregiverbuddy.R;
import com.govst.zacharyexp.caregiverbuddy.chore.Chore;
import com.govst.zacharyexp.caregiverbuddy.chore.ChoreActivity;
import com.govst.zacharyexp.caregiverbuddy.chore.ChoreTools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ChoreNew extends AppCompatActivity {

    ArrayList<Chore> ch = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chore);

        ch.clear();

        ch = ChoreTools.readAnArray(getApplicationContext());

        //Importing all components from UI
        final EditText choreName = (EditText) findViewById(R.id.edit_name_chore);
        final EditText typeName = (EditText) findViewById(R.id.edit_type_name);

        final Button buttonStartDate = (Button) findViewById(R.id.button_start_date);
        final Button buttonEndDate = (Button) findViewById(R.id.button_end_date);

        final EditText timesPerFrequency = (EditText) findViewById(R.id.edit_frequency);
        final Spinner frequencySelector = (Spinner) findViewById(R.id.spinner_frequency_selector);

        final Spinner absoluteTime = (Spinner) findViewById(R.id.spinner_time2);
        final Spinner relativeTime = (Spinner) findViewById(R.id.spinner_time1);

        FloatingActionButton okButton = (FloatingActionButton) findViewById(R.id.fab_ok);


        //Initialise the current date
        Calendar currentCalendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String format = dateFormat.format(currentCalendar.getTime());

        //Setting current date to the button's text
        buttonStartDate.setText(format);
        buttonEndDate.setText(format);

        //Buttons listeners
        buttonStartDate.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        //set marker text
                        buttonStartDate.setText("");

                        //Show the Date picker
                        showDatePickerDialog(v);
                    }
                }
        );

        buttonEndDate.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        //set marker text
                        buttonEndDate.setText("");

                        //Show the Date picker
                        showDatePickerDialog(v);

                    }
                }
        );


        //okButton action
        okButton.setOnClickListener(
                new FloatingActionButton.OnClickListener(){
                    public void onClick (View v){
                        //Getting the values of the user
                        final Calendar startDay = ChoreTools.stringConverterToDate(buttonStartDate.getText().toString());
                        final Calendar endDay = ChoreTools.stringConverterToDate(buttonEndDate.getText().toString());


                        //Creating new chore object with values selected by user
                        Chore chore = new Chore();
                        chore.setChoreName(choreName.getText().toString());
                        chore.setTypeName(typeName.getText().toString());
                        chore.setStartDate(startDay);
                        chore.setEndDate(endDay);
                        chore.setTimesPerFrequency(Integer.parseInt(timesPerFrequency.getText().toString()));
                        chore.setFrequency(frequencySelector.getSelectedItem().toString());
                        chore.setAbsoluteTime(absoluteTime.getSelectedItem().toString());
                        chore.setRelativeTime(relativeTime.getSelectedItem().toString());
                        chore.setRelativeTimeDescriber(ChoreTools.relativeTimeToInt(chore));
                        Log.i("appAction","Creating new Chore object");
                        Log.i("appAction",chore.toString());

                        //Adding this new object to container
                        ch.add(chore);
                        ChoreActivity.choresList.add(chore);
                        Log.i("appAction","List length : " + ChoreActivity.choresList.size());

                        //Write ArrayList container to the internal storage
                        ChoreTools.writeAnArray(ch, getApplicationContext());

                        //Determine if the new chore has to be taken on this day
                        //if (chore.isHappeningToday()){
                            //Log.i("appAction","Chore has to be taken today !");
                            //ChoreActivity.areHappeningToday.add(chore);
                        //}

                        //Close activity
                        Log.i("appAction","Close new chore activity intent");
                        finish();
                    }
                }
        );

    }


    //Method which shows the date picker fragment dialog
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
        int anInt = newFragment.getId();

    }

}
