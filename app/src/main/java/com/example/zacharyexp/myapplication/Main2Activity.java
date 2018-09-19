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
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //String fileContents = "Hello World";
        //FileOutputStream outputStream

        try {
            //file = new file(File file = new File(context.getFilesDir(), filename);
            FileOutputStream outputStream = openFileOutput(FILENAME, Context.MODE_APPEND);
            //outputStream.write(fileContents.getBytes());
            System.out.println("Opened");
            //outputStream.close();
        } catch (Exception e) {
            File file = new File(FILENAME);
            System.out.println("Created");

            e.printStackTrace();
        }

        Button button2 = (Button)findViewById(R.id.button2);
        info = (EditText)findViewById(R.id.editText);
        name = (EditText)findViewById(R.id.editText2);
        age = (EditText)findViewById(R.id.editText3);

        button2.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        nameS = name.getText().toString();
                        ageS = age.getText().toString();
                        infoS = info.getText().toString();
                        savePatientInfo();
                        startPatientManagement(v);
                    }
                }
        );
    }

    public void startPatientManagement(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void savePatientInfo(){
        try {
            FileOutputStream outputStream = openFileOutput(FILENAME, Context.MODE_APPEND);
            outputStream.write((nameS + " | ").getBytes());
            outputStream.write((ageS + " | ").getBytes());
            outputStream.write((infoS + "\n").getBytes());
            System.out.println("Saved");
            outputStream.close();
        } catch (Exception e) {
            File file = new File(FILENAME);
            System.out.println("Created");

            e.printStackTrace();
        }

        read();
    }

    public void read() {
        try {
            FileInputStream fileInputStream= openFileInput(FILENAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String lines;
            while ((lines=bufferedReader.readLine())!=null) {
                stringBuffer.append(lines+"\n");
            }
            System.out.print(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
