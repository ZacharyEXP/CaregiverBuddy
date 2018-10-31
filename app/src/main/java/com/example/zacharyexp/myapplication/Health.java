package com.example.zacharyexp.myapplication;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/*
This class represent the "Drug" Object and all its parameters
 */

public class Health implements Serializable{


    //Main titles and period
    private String healthName;
    private String typeName;
    private Calendar startDate;
    private Calendar endDate;

    //Frequency
    private Integer timesPerFrequency;
    private String frequency;

    //Time in the frequency
    private String absoluteTime;
    private String relativeTime;




    //Relative time integer reference
    // If i == 1 => 'Breakfast'
    // If i == 4 => 'Lunch'
    // If i == 7 => 'Dinner'
    // +1 if it happens after
    // -1 if it happens before
    private int relativeTimeDescriber;


    //Drug static container
    //private static ArrayList<Drug> drugsList;


    //***Constructors***
    public Health(){
        healthName = "";
        typeName = "";
        startDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        timesPerFrequency = 1;
        frequency = "Day";
        absoluteTime = "Breakfast";
        relativeTime = "Before";
    }

    public Health(String cHealthName, String cTypeName, Calendar cStartDate, Calendar cEndDate, Integer cTimesPerFrequency, String cFrequency, String cAbsoluteTime, String cRelativeTime){
        healthName = cHealthName;
        typeName = cTypeName;
        startDate = cStartDate;
        endDate = cEndDate;
        timesPerFrequency = cTimesPerFrequency;
        frequency = cFrequency;
        absoluteTime = cAbsoluteTime;
        relativeTime = cRelativeTime;
    }


    //Determine if the drug has to be taken on the current day
    public Boolean isHappeningToday (){
        Log.i("appAction", "Launching isHappeningToday method ...");
        Log.i("appAction", "Converting calendars to integers ...");

        //Boolean result
        Boolean result = false;

        //object calendar copies
        Calendar thisStartDate = (Calendar) this.getStartDate().clone();
        Calendar thisEndDate = (Calendar) this.getEndDate().clone();

        //Converting calendars date to integers
        int startDateInt = HealthTools.dateToInteger(thisStartDate);
        int currentDateInt = HealthTools.dateToInteger(Calendar.getInstance());

        //Date used in the process
        Calendar targetDate = thisStartDate;

        //Container for all the dates (integers) which match with the frequency
        ArrayList matchingDates = new ArrayList<>();
        matchingDates.add(startDateInt);


        //Case when frequency is set to 'Day'
        if (this.frequency.equals("Day")){
            Log.i("appAction","The Health object frequency is set to 'Day'.");

            while(targetDate.compareTo(thisEndDate) < 0){
                targetDate.add(Calendar.DAY_OF_MONTH, this.timesPerFrequency);
                matchingDates.add(HealthTools.dateToInteger(targetDate));
            }
        }else if(this.frequency.equals("Week")){
            Log.i("appAction","The Health object frequency is set to 'Week'.");

            while (targetDate.compareTo(thisEndDate) < 0){
                targetDate.add(Calendar.DAY_OF_MONTH, this.timesPerFrequency * 7);
                matchingDates.add(HealthTools.dateToInteger(targetDate));
            }
        }else if(this.frequency.equals("Month")){
            Log.i("appAction","The Health object frequency is set to 'Month'.");

            while (targetDate.compareTo(thisEndDate) < 0){
                targetDate.add(Calendar.MONTH, this.timesPerFrequency);
                matchingDates.add(HealthTools.dateToInteger(targetDate));
            }
        }else if(this.frequency.equals("Year")){
            Log.i("appAction","The Health object frequency is set to 'Year'.");

            while (targetDate.compareTo(thisEndDate) < 0){
                targetDate.add(Calendar.YEAR, this.timesPerFrequency);
                matchingDates.add(HealthTools.dateToInteger(targetDate));
            }
        }


        //Result description
        Log.i("appAction","Matching dates list length : " + matchingDates.size());

        for(int i = 0; i < matchingDates.size(); i ++) {
            if (matchingDates.get(i).equals(currentDateInt)){
                result = true;
                Log.i("appAction","|||| Result ||||\n" + result);
            }
        }
        return result;
    }



    //***Getters***
    public String getHealthName() {
        return healthName;
    }

    public String getTypeName() {
        return typeName;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public Integer getTimesPerFrequency() {
        return timesPerFrequency;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getAbsoluteTime() {
        return absoluteTime;
    }

    public String getRelativeTime() {
        return relativeTime;
    }

    public int getRelativeTimeDescriber() {
        return relativeTimeDescriber;
    }



    //***Setters***
    public void setHealthName(String drugName) {
        this.healthName = healthName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public void setTimesPerFrequency(Integer timesPerFrequency) {
        this.timesPerFrequency = timesPerFrequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setAbsoluteTime(String absoluteTime) {
        this.absoluteTime = absoluteTime;
    }

    public void setRelativeTime(String relativeTime) {
        this.relativeTime = relativeTime;
    }

    public void setRelativeTimeDescriber(int relativeTimeDescriber) {
        this.relativeTimeDescriber = relativeTimeDescriber;
    }



    //***ToString***
    public String toString(){
        return "\n>>>>>Health Object<<<<<<\n"
                +"-Health Name = " + this.healthName + "\n"
                +"-Type Name = " + this.typeName + "\n"
                +"-Start Date = " + this.startDate + "\n"
                +"-End Date = " + this.endDate + "\n"
                +"-Times per Frequency = " + this.timesPerFrequency + "\n"
                +"-Frequency = " + this.frequency + "\n"
                +"-Absolute Time = " + this.absoluteTime + "\n"
                +"-Relative Time = " + this.relativeTime + "\n"
                +"-Relative Time Describer = " + this.getRelativeTimeDescriber();
    }


}