package com.example.zacharyexp.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2Activity extends Activity {

    public static final String FILENAME = "patientlist.txt";
    public String nameS, ageS, infoS;
    public EditText info, name, age;
    FileManager fm = new FileManager();
    Boolean infoB = false;
    Boolean nameB = false;
    Boolean ageB = false;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button2 = (Button)findViewById(R.id.button2);
        info = (EditText)findViewById(R.id.editText);
        name = (EditText)findViewById(R.id.editText2);
        age = (EditText)findViewById(R.id.editText3);

        info.setOnClickListener(
                new EditText.OnClickListener(){
                    public void onClick(View v){
                        if(infoB == false) {
                            info.setText("");
                            infoB = true;
                        }
                    }
                }
        );

        name.setOnClickListener(
                new EditText.OnClickListener(){
                    public void onClick(View v){
                        if(nameB == false) {
                            name.setText("");
                            nameB = true;
                        }
                    }
                }
        );

        age.setOnClickListener(
                new EditText.OnClickListener(){
                    public void onClick(View v){
                        if(ageB == false) {
                            age.setText("");
                            ageB = true;
                        }
                    }
                }
        );

        button2.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        nameS = name.getText().toString();
                        ageS = age.getText().toString();
                        infoS = info.getText().toString();
                        fm.addEntry(nameS, ageS, infoS);
                        fm.save();
                        startPatientManagement(v);
                    }
                }
        );
    }

    public void startPatientManagement(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
