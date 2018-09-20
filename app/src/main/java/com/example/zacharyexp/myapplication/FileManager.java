package com.example.zacharyexp.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;

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
    final static String path = Environment.getDataDirectory().getAbsolutePath() + "/data/com.example.zacharyexp.myapplication/files/";
    final static String TAG = FileManager.class.getName();
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> ages = new ArrayList<String>();
    ArrayList<String> descriptions = new ArrayList<String>();
    File file = new File(path + FILENAME);

    public FileManager() {
        int idCount = 1;

        String[] data;

        try {
            FileInputStream fileInputStream = new FileInputStream (new File(path + FILENAME));
            System.out.println("It works");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String lines;

            while ((lines= bufferedReader.readLine())!=null) {
                data = lines.split(" \\| ");
                stringBuffer.delete(0, stringBuffer.length());

                System.out.println("Test 1");

                for(int i = 0; i < data.length; i++) {
                    switch(i % 3) {
                        case 0:
                            names.add(data[i]);
                            break;
                        case 1:
                            ages.add(data[i]);
                            break;
                        case 2:
                            descriptions.add(data[i]);
                            System.out.println("Test 2");
                            break;
                    }
                }
            }

            System.out.println(names);
            System.out.println(ages);
            System.out.println(descriptions);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEntry(String newName, String newAge, String newDesc) {
        names.add(newName);
        ages.add(newAge);
        descriptions.add(newDesc);
    }

    public void save() {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            for(int i = 0; i < names.size(); i++) {
                outputStream.write((names.get(i) + " | ").getBytes());
                outputStream.write((ages.get(i) + " | ").getBytes());
                outputStream.write((descriptions.get(i) + "\n").getBytes());
                System.out.println("Saved");
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName(int id) {
        return names.get(id);
    }

    public String getAge(int id) {
        return ages.get(id);
    }

    public String getDesc(int id) {
        return descriptions.get(id);
    }

    //Add methods for getting ID, dates from calendar, medications, removing people, etc.
}
