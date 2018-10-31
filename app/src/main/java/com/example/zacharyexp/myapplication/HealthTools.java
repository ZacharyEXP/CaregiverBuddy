package com.example.zacharyexp.myapplication;


import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/*
 * A class containing all tools for converting, processing values ...
 */
public class HealthTools {

    //Text button converter from string to calendar
    public static Calendar stringConverterToDate (String buttonText){


        Calendar calendar = Calendar.getInstance();

        //processing the buttonText String using a StringBuilder
        calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(new StringBuilder().append(buttonText.charAt(0))
                .append(buttonText.charAt(1))
                .toString()));
        calendar.set(Calendar.MONTH, Integer.parseInt(new StringBuilder().append(buttonText.charAt(3))
                .append(buttonText.charAt(4))
                .toString()) - 1);
        calendar.set(Calendar.YEAR, Integer.parseInt(new StringBuilder().append(buttonText.charAt(6))
                .append(buttonText.charAt(7))
                .append(buttonText.charAt(8))
                .append(buttonText.charAt(9))
                .toString()));
        Log.i("appAction","stringConverterToDate method converts : " + buttonText + " to : " + calendar.toString());
        return calendar;
    }


    //***Files writers and readers***
    //Writer
    public static void writeAnArray(ArrayList arrayList, Context context){
        Log.i("appAction","Writing Health container ... ");
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("health", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);
            objectOutputStream.close();
        }catch (Exception e){
            Log.i("appAction","An error occurred !");
            Log.e("appAction", Log.getStackTraceString(e));
        }
    }

    //Reader
    public static ArrayList<Health> readAnArray(Context context){
        Log.i("appAction","Reading Health container ...");
        ArrayList tempContainer = new ArrayList();
        try {
            FileInputStream fileInputStream = context.openFileInput("health");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            tempContainer = (ArrayList) objectInputStream.readObject();
            objectInputStream.close();
        }catch(Exception e){
            Log.i("appAction","No file found !");
            Log.e("appAction", Log.getStackTraceString(e));
        }
        return tempContainer;
    }


    //Get date in a unique integer => YEAR + MONTH + DAY OF THE MONTH
    public static int dateToInteger(Calendar cal){
        Log.i("appAction", "Calendar : YEAR = " + cal.get(Calendar.YEAR) + " MONTH = " + cal.get(Calendar.MONTH) + " DAY_OF_MONTH = " + cal.get(Calendar.DAY_OF_MONTH));

        StringBuilder processString = new StringBuilder();
        processString.append(cal.get(Calendar.YEAR));

        if (cal.get(Calendar.MONTH) < 10){
            processString.append(0);
        }
        processString.append(cal.get(Calendar.MONTH));

        if (cal.get(Calendar.DAY_OF_MONTH) < 10){
            processString.append(0);
        }
        processString.append(cal.get(Calendar.DAY_OF_MONTH));

        int result = Integer.parseInt(processString.toString());

        Log.i("appAction","Calendar converted to : " + result);
        return result;
    }

    //Compute relativeTimeDescriber value
    public static int relativeTimeToInt (Health health){
        Log.i("appAction","Determine relative time int describer ...");

        int result = 0;
        String absoluteTime = health.getAbsoluteTime();
        String relativeTime = health.getRelativeTime();

        switch (absoluteTime){
            case "Breakfast":
                result = 1;
                break;
            case "Lunch":
                result = 4;
                break;
            case "Dinner":
                result = 7;
                break;
        }

        switch (relativeTime){
            case "Before":
                result = result - 1;
                break;
            case "After":
                result = result + 1;
        }

        Log.i("appAction","Relative time describer value : " + result);
        return result;
    }

    //Sort a health container with the relative time
    public static ArrayList<Health> healthContainerSorter (ArrayList<Health> mainArray){
        Log.i("appAction","Sort health container by relative time ");
        ArrayList<Health> result = new ArrayList<>();

        StringBuilder before = new StringBuilder();
        StringBuilder after = new StringBuilder();

        //--Description statement--
        for (Health health : mainArray){
            before.append("'" + health.getRelativeTimeDescriber() + "', ");
        }

        Log.i("appAction", "List state BEFORE : " + before.toString());


        //Case the array is empty
        if (mainArray.isEmpty()){
            Log.i("appAction","Health container is Empty !");
        }else{

            Health minObj = mainArray.get(0);
            while (mainArray.size() != 0) {
                for (Health health : mainArray) {
                    if (health.getRelativeTimeDescriber() <= minObj.getRelativeTimeDescriber()) {
                        minObj = health;
                    }
                }
                result.add(minObj);
                mainArray.remove(minObj);
                minObj = new Health();
                minObj.setRelativeTimeDescriber(10);
            }

            //Have an unknown issue => There is always one object left after processing
            if (mainArray.size() >= 1){
                Log.i("appAction", "-!- SORTING ISSUE -!-");
                Log.i("appAction", "Main array size : " + mainArray.size());
                Log.i("appAction", "Result array size : " + result.size());
                result.add(mainArray.get(0));
            }
        }

        //--Description statement--
        for (Health health : result){
            after.append("'" + health.getRelativeTimeDescriber() + "', ");
        }

        Log.i("appAction", "List state AFTER : " + after.toString());

        return result;
    }

    //Delete every health object which is under a specific date
    public static void deleteHealthBeforeADate (ArrayList<Health> health, Calendar maximumDate){
        Log.i("appAction","Delete older health objects ... ");
        int initialSize = health.size();

        ArrayList<Health> haveToBeDestroyed = new ArrayList<>();

        for (Health h : health){
            if (h.getEndDate().compareTo(maximumDate) <= 0){
                haveToBeDestroyed.add(h);
            }
        }
        for (Health h : haveToBeDestroyed){
            health.remove(h);
        }

        Log.i("appAction","Deleted objects : " + (initialSize - health.size()));
    }

    //convert a calendar to an hexadecimal value
    public static String dateToStringValue(Calendar calendar){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(calendar.getTime());
    }

}