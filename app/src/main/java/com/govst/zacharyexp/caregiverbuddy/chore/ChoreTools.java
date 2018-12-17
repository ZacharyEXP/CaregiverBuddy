package com.govst.zacharyexp.caregiverbuddy.chore;


import android.content.Context;
import android.util.Log;

import com.govst.zacharyexp.caregiverbuddy.chore.Chore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/*
* A class containing all tools for converting, processing values ...
*/
public class ChoreTools {
    public static final String PATH = "/data/user/0/com.example.zacharyexp.myapplication/files/";
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
        Log.i("appAction","Writing Chore container ... ");
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("chores", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);
            objectOutputStream.close();
        }catch (Exception e){
            Log.i("appAction","An error occurred !");
            Log.e("appAction", Log.getStackTraceString(e));
        }
    }

    //Reader
    public static ArrayList<Chore> readAnArray(Context context){
        Log.i("appAction","Reading Chore container ...");
        ArrayList tempContainer = new ArrayList();
        try {
            FileInputStream fileInputStream = context.openFileInput("chores");
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
    public static int relativeTimeToInt (Chore chore){
        Log.i("appAction","Determine relative time int describer ...");

        int result = 0;
        String absoluteTime = chore.getAbsoluteTime();
        String relativeTime = chore.getRelativeTime();

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

    //Sort a chore container with the relative time
    public static ArrayList<Chore> choreContainerSorter (ArrayList<Chore> mainArray){
        Log.i("appAction","Sort chore container by relative time ");
        ArrayList<Chore> result = new ArrayList<>();

        StringBuilder before = new StringBuilder();
        StringBuilder after = new StringBuilder();

        List<String> keys = new ArrayList<String>();
        List<Date> keyDates = new ArrayList<Date>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(Chore chore : mainArray) {
            try {
                keyDates.add(chore.getStartDate().getTime());
                //System.out.println(dateFormat.parse(string));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collections.sort(keyDates);

        //--Description statement--
        for (Date date: keyDates){
            for(Chore chore : mainArray) {
                if(chore.getStartDate().getTime().equals(date)) {
                    result.add(chore);
                }
            }
            //before.append("'" + drug.getRelativeTimeDescriber() + "', ");
        }

        //--Description statement--
        /*for (Chore chore : mainArray){
            before.append("'" + chore.getRelativeTimeDescriber() + "', ");
        }

        Log.i("appAction", "List state BEFORE : " + before.toString());


        //Case the array is empty
        if (mainArray.isEmpty()){
            Log.i("appAction","Chore container is Empty !");
        }else{

            Chore minObj = mainArray.get(0);
            while (mainArray.size() != 0) {
                for (Chore chore : mainArray) {
                    if (chore.getRelativeTimeDescriber() <= minObj.getRelativeTimeDescriber()) {
                        minObj = chore;
                    }
                }
                result.add(minObj);
                mainArray.remove(minObj);
                minObj = new Chore();
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
        for (Chore chore : result){
            after.append("'" + chore.getRelativeTimeDescriber() + "', ");
        }

        Log.i("appAction", "List state AFTER : " + after.toString());*/

        return result;
    }

    //Delete every chore object which is under a specific date
    public static void deleteChoreBeforeADate (ArrayList<Chore> chores, Calendar maximumDate){
        Log.i("appAction","Delete older chore objects ... ");
        int initialSize = chores.size();

        ArrayList<Chore> haveToBeDestroyed = new ArrayList<>();

        for (Chore chore : chores){
            if (chore.getEndDate().compareTo(maximumDate) <= 0){
                haveToBeDestroyed.add(chore);
            }
        }
        for (Chore chore : haveToBeDestroyed){
            chores.remove(chore);
        }

        Log.i("appAction","Deleted objects : " + (initialSize - chores.size()));
    }

    //convert a calendar to an hexadecimal value
    public static String dateToStringValue(Calendar calendar){
        //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String temp1, temp2;
        if(calendar.get(Calendar.MONTH) < 9) {
            temp1 = "0" + Integer.toString(calendar.get(Calendar.MONTH) + 1);
        } else {
            temp1 = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        }
        if(calendar.get(Calendar.DAY_OF_MONTH) < 9) {
            temp2 = "0" + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        } else {
            temp2 = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        }
        String day = Integer.toString(calendar.get(Calendar.YEAR)) + "-" + temp1 + "-" + temp2;
        return day;
    }

}
