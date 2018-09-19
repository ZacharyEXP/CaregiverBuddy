package com.example.zacharyexp.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    public static final String FILENAME = "patientlist.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileManager fm = new FileManager();

        Button testbutton = (Button)findViewById(R.id.testbutton);
        ListView patientInfoL = (ListView)findViewById(R.id.patientList);
        ArrayList<String> data = new ArrayList<String>();

        try {
            //File file = new File(FILENAME);
            //String path = file.getAbsolutePath();
            //System.out.println(path);
            FileInputStream fileInputStream= openFileInput(FILENAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String lines;
            while ((lines= bufferedReader.readLine())!=null) {
                //System.out.println(lines);
                data.add(lines);
                stringBuffer.delete(0, stringBuffer.length());

            }
            System.out.println(data);
            //patientInfo.setText(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                R.layout.activity_list_item, R.id.textview, data);

        patientInfoL.setAdapter(adapter1);

        patientInfoL.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                String value = (String)adapter.getItemAtPosition(position);
                System.out.println(value);
                // assuming string and if you want to get the value on click of list item
                // do what you intend to do on click of listview row
            }
        });

        testbutton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        //TextView testview = (TextView)findViewById(R.id.testview);
                        //testview.setText("GG");
                        startPatientInfo(v);
                    }
                }
        );
    }

    public void startPatientInfo(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
