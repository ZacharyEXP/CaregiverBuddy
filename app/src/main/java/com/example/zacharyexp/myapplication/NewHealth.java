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

    int healthType;
    String typeName;

    EditText hr, sys, dia, glu, food, weight, sleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_health);

        //Importing all components from UI
        //final EditText healthName = (EditText) findViewById(R.id.edit_name_health);
        final TextView healthName = (TextView) findViewById(R.id.edit_name_health);
        //final EditText typeName = (EditText) findViewById(R.id.edit_type_name);

        //final Button buttonStartDate = (Button) findViewById(R.id.button_start_date);
        //final Button buttonEndDate = (Button) findViewById(R.id.button_end_date);

        //final EditText timesPerFrequency = (EditText) findViewById(R.id.edit_frequency);
        //final Spinner frequencySelector = (Spinner) findViewById(R.id.spinner_frequency_selector);

        //final Spinner absoluteTime = (Spinner) findViewById(R.id.spinner_time2);
        //final Spinner relativeTime = (Spinner) findViewById(R.id.spinner_time1);

        FloatingActionButton okButton = (FloatingActionButton) findViewById(R.id.fab_ok);
        LinearLayout l_layout = (LinearLayout) findViewById(R.id.health_var);

        healthType = getIntent().getIntExtra("HEALTH_TYPE", -1);


        //Initialise the current date
        Calendar currentCalendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String format = dateFormat.format(currentCalendar.getTime());

        //Setting current date to the button's text
        //buttonStartDate.setText(format);
        //buttonEndDate.setText(format);

        switch (healthType) {
            case 0:
                l_layout.removeAllViews();
                typeName = "Heart Rate";
                healthName.setText(typeName);

                hr = new EditText(getApplicationContext());
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
                typeName = "Blood Pressure";
                healthName.setText(typeName);

                sys = new EditText(getApplicationContext());
                sys.setInputType(InputType.TYPE_CLASS_NUMBER);
                sys.setGravity(Gravity.CENTER);
                sys.setWidth(150);
                sys.setHint("Sys");

                TextView sep = new TextView(getApplicationContext());
                sep.setGravity(Gravity.CENTER);
                sep.setText("/");

                dia = new EditText(getApplicationContext());
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
                typeName = "Glucose";
                healthName.setText(typeName);

                glu = new EditText(getApplicationContext());
                glu.setInputType(InputType.TYPE_CLASS_NUMBER);
                glu.setWidth(170);
                glu.setGravity(Gravity.LEFT);

                TextView mgdl = new TextView(getApplicationContext());
                mgdl.setGravity(Gravity.CENTER);
                mgdl.setText("mg/dL");

                l_layout.addView(glu);
                l_layout.addView(mgdl);
                break;
            case 3:
                l_layout.removeAllViews();
                typeName = "Food";
                healthName.setText(typeName);

                food = new EditText(getApplicationContext());
                food.setInputType(InputType.TYPE_CLASS_NUMBER);
                food.setWidth(170);
                food.setGravity(Gravity.LEFT);

                TextView cal = new TextView(getApplicationContext());
                cal.setGravity(Gravity.CENTER);
                cal.setText("Calories");

                l_layout.addView(food);
                l_layout.addView(cal);
                break;
            case 4:
                l_layout.removeAllViews();
                typeName = "Weight";
                healthName.setText(typeName);

                weight = new EditText(getApplicationContext());
                weight.setInputType(InputType.TYPE_CLASS_NUMBER);
                weight.setWidth(170);
                weight.setGravity(Gravity.LEFT);

                TextView lbs = new TextView(getApplicationContext());
                lbs.setGravity(Gravity.CENTER);
                lbs.setText("lbs");

                l_layout.addView(weight);
                l_layout.addView(lbs);
                break;
            case 5:
                l_layout.removeAllViews();
                typeName = "Sleep";
                healthName.setText(typeName);

                sleep = new EditText(getApplicationContext());
                sleep.setInputType(InputType.TYPE_CLASS_NUMBER);
                sleep.setWidth(170);
                sleep.setGravity(Gravity.LEFT);

                TextView hours = new TextView(getApplicationContext());
                hours.setGravity(Gravity.CENTER);
                hours.setText("hours of sleep");

                l_layout.addView(sleep);
                l_layout.addView(hours);
                break;
    }
        //Buttons listeners
        /*buttonStartDate.setOnClickListener(
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
        );*/


        //okButton action
        okButton.setOnClickListener(
                new FloatingActionButton.OnClickListener(){
                    public void onClick (View v){
                        //Getting the values of the user
                        //final Calendar startDay = HealthTools.stringConverterToDate(buttonStartDate.getText().toString());
                        //final Calendar endDay = HealthTools.stringConverterToDate(buttonEndDate.getText().toString());


                        //Creating new Health object with values selected by user
                        Health health = new Health();
                        health.setHealthName(healthName.getText().toString());
                        health.setHealthType(healthType);
                        health.setTypeName(typeName);
                        switch (healthType){
                            case 0:
                                health.setDataEntry(hr.getText().toString() + " BPM");
                                break;
                            case 1:
                                health.setDataEntry(sys.getText().toString() + "/" + dia.getText().toString());
                                break;
                            case 2:
                                health.setDataEntry(glu.getText().toString() + " mg/dL");
                                break;
                            case 3:
                                health.setDataEntry(food.getText().toString() + " Calories");
                                break;
                            case 4:
                                health.setDataEntry(weight.getText().toString() + " lbs");
                                break;
                            case 5:
                                health.setDataEntry(sleep.getText().toString() + " hours of sleep");
                                break;
                        }

                        health.setStartDate(Calendar.getInstance());
                        health.setEndDate(Calendar.getInstance());
                        //health.setTimesPerFrequency(Integer.parseInt(timesPerFrequency.getText().toString()));
                       // health.setFrequency(frequencySelector.getSelectedItem().toString());
                        //health.setAbsoluteTime(absoluteTime.getSelectedItem().toString());
                        //health.setRelativeTime(relativeTime.getSelectedItem().toString());
                        //health.setRelativeTimeDescriber(HealthTools.relativeTimeToInt(health));
                        Log.i("appAction","Creating new Health object");
                        Log.i("appAction",health.toString());

                        //Adding this new object to container
                        HealthActivity.healthList.add(health);
                        Log.i("appAction","List length : " + HealthActivity.healthList.size());

                        //Write ArrayList container to the internal storage
                        HealthTools.writeAnArray(HealthActivity.healthList, getApplicationContext());

                        //Determine if the new health has to be taken on this da

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
