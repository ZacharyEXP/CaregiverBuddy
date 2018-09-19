package com.example.zacharyexp.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileManager extends Activity {
    public static final String FILENAME = "patientlist.txt";

    public FileManager() {
        //ArrayList<String> data = new ArrayList<String>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> ages = new ArrayList<String>();
        ArrayList<String> descriptions = new ArrayList<String>();

        String[] data;

        try {
            File file = new File(FILENAME);
            String path = file.getCanonicalPath();
            System.out.println(path);
            FileInputStream fileInputStream = openFileInput(FILENAME);
            System.out.println("Test 1");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String lines;

            while ((lines= bufferedReader.readLine())!=null) {
                data = lines.split(" \\| ");
                //data.add(lines);
                stringBuffer.delete(0, stringBuffer.length());

                System.out.println("Test 1");

                for(int i = 0; i < data.length - 1; i++) {
                    switch(i % 3) {
                        case 0:
                            names.add(data[i]);
                        case 1:
                            ages.add(data[i]);
                        case 2:
                            descriptions.add(data[i]);
                            System.out.println("Test 2");
                    }
                }
            }

            System.out.println("Opened Manager");
            System.out.println(names);
            System.out.println(ages);
            System.out.println(descriptions);
            //System.out.println(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEntry(String entry) {

    }

    //public String
}
