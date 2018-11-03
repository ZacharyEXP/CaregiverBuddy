package com.example.zacharyexp.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.TypeInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewHealth extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_health);

        //Importing all components from UI
        //final EditText healthName = (EditText) findViewById(R.id.edit_name_health);
        final Spinner healthName = (Spinner) findViewById(R.id.edit_name_health);
        final EditText typeName = (EditText) findViewById(R.id.edit_type_name);

        final Button buttonStartDate = (Button) findViewById(R.id.button_start_date);
        final Button buttonEndDate = (Button) findViewById(R.id.button_end_date);

        final EditText timesPerFrequency = (EditText) findViewById(R.id.edit_frequency);
        final Spinner frequencySelector = (Spinner) findViewById(R.id.spinner_frequency_selector);

        final Spinner absoluteTime = (Spinner) findViewById(R.id.spinner_time2);
        final Spinner relativeTime = (Spinner) findViewById(R.id.spinner_time1);

        FloatingActionButton okButton = (FloatingActionButton) findViewById(R.id.fab_ok);
        LinearLayout l_layout = (LinearLayout) findViewById(R.id.health_var);


        //Initialise the current date
        Calendar currentCalendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String format = dateFormat.format(currentCalendar.getTime());

        //Setting current date to the button's text
        buttonStartDate.setText(format);
        buttonEndDate.setText(format);

        healthName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        l_layout.removeAllViews();

                        EditText hr = new EditText(getApplicationContext());
                        hr.setInputType(InputType.TYPE_CLASS_NUMBER);
                        hr.setWidth(170);
                        hr.setGravity(Gravity.LEFT);

                        TextView bpm = new TextView(getApplicationContext());
                        bpm.setGravity(Gravity.CENTER);
                        bpm.setText("BPM");

                        l_layout.addView(hr);
                        l_layout.addView(bpm);
                        break;
                    case 1:
                        l_layout.removeAllViews();

                        EditText sys = new EditText(getApplicationContext());
                        sys.setInputType(InputType.TYPE_CLASS_NUMBER);
                        sys.setGravity(Gravity.CENTER);
                        sys.setWidth(150);
                        sys.setHint("Sys");

                        TextView sep = new TextView(getApplicationContext());
                        sep.setGravity(Gravity.CENTER);
                        sep.setText("/");

                        EditText dia = new EditText(getApplicationContext());
                        dia.setInputType(InputType.TYPE_CLASS_NUMBER);
                        dia.setGravity(Gravity.CENTER);
                        dia.setWidth(150);
                        dia.setHint("Dia");

                        l_layout.addView(sys);
                        l_layout.addView(sep);
                        l_layout.addView(dia);
                        break;
                    case 2:
                        l_layout.removeAllViews();

                        EditText glu = new EditText(getApplicationContext());
                        glu.setInputType(InputType.TYPE_CLASS_NUMBER);
                        glu.setWidth(170);
                        glu.setGravity(Gravity.LEFT);

                        TextView mgdl = new TextView(getApplicationContext());
                        mgdl.setGravity(Gravity.CENTER);
                        mgdl.setText("mg/dL");

                        l_layout.addView(glu);
                        l_layout.addView(mgdl);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });
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
                        final Calendar startDay = HealthTools.stringConverterToDate(buttonStartDate.getText().toString());
                        final Calendar endDay = HealthTools.stringConverterToDate(buttonEndDate.getText().toString());


                        //Creating new Health object with values selected by user
                        Health health = new Health();
                        health.setHealthName(healthName.getSelectedItem().toString());
                        health.setTypeName(typeName.getText().toString());
                        health.setStartDate(startDay);
                        health.setEndDate(endDay);
                        health.setTimesPerFrequency(Integer.parseInt(timesPerFrequency.getText().toString()));
                        health.setFrequency(frequencySelector.getSelectedItem().toString());
                        health.setAbsoluteTime(absoluteTime.getSelectedItem().toString());
                        health.setRelativeTime(relativeTime.getSelectedItem().toString());
                        health.setRelativeTimeDescriber(HealthTools.relativeTimeToInt(health));
                        Log.i("appAction","Creating new Health object");
                        Log.i("appAction",health.toString());

                        //Adding this new object to container
                        HealthActivity.healthList.add(health);
                        Log.i("appAction","List length : " + HealthActivity.healthList.size());

                        //Write ArrayList container to the internal storage
                        HealthTools.writeAnArray(HealthActivity.healthList, getApplicationContext());

                        //Determine if the new health has to be taken on this day
                        if (health.isHappeningToday()){
                            Log.i("appAction","Health has to be taken today !");
                            HealthActivity.areHappeningToday.add(health);
                        }

                        //Close activity
                        Log.i("appAction","Close new health activity intent");
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
