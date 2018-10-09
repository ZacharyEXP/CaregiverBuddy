package com.example.zacharyexp.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewPatientActivity extends Activity {

    public static final String FILENAME = "patientlist.txt";
    public String nameS, ageS, infoS;
    public EditText info, name, age;
    Patient p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);

        Context c = getApplicationContext();

        p = new Patient(c);

        Button button2 = (Button)findViewById(R.id.button2);
        info = (EditText)findViewById(R.id.patient_desc);
        name = (EditText)findViewById(R.id.patient_name);
        age = (EditText)findViewById(R.id.patient_age);

        button2.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        nameS = name.getText().toString();
                        ageS = age.getText().toString();
                        infoS = info.getText().toString();

                        p.addListName(nameS);
                        p.setPatientName(nameS);
                        p.addListAge(ageS);
                        p.setPatientAge(Integer.parseInt(ageS));
                        p.addListPic(infoS);
                        p.setPatientPicPath(infoS);
                        p.setPatientDesc(infoS);
                        p.saveList();

                        startPatientManagement(v);
                    }
                }
        );
    }

    public void startPatientManagement(View view) {
        Intent intent = new Intent(this, PatientSelect.class);
        startActivity(intent);
    }
}
