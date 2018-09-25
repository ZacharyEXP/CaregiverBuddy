package com.example.zacharyexp.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Patient extends Activity implements Serializable {
    String patientName, patientDesc, weeklySchedule, patientPicPath;
    ArrayList<String> taskDesc = new ArrayList<String>();
    ArrayList<String> taskStart = new ArrayList<String>();
    ArrayList<String> taskRecurrence = new ArrayList<String>();
    ArrayList<String> taskDone = new ArrayList<String>();
    ArrayList<String> medName = new ArrayList<String>();
    ArrayList<String> medDone = new ArrayList<String>();
    ArrayList<String> medDays = new ArrayList<String>();
    ArrayList<String> medPicPath = new ArrayList<String>();
    int patientAge, patientID;
    ArrayList<String> medAmount = new ArrayList<String>();;

    //SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
    SharedPreferences pref; //= getSharedPreferences("myPref", MODE_PRIVATE);
    SharedPreferences.Editor edit; //= pref.edit();

    public Patient(Context c) {
        //patientName = "Test";
       // patientDesc = "Test Desc";
       // weeklySchedule = "M/T/F";
        //patientPicPath = "No path";
        //patientAge = 77;
        //medName.add("Penicillin");
        //medName.add("Aspirin");
        pref = c.getSharedPreferences("patientList", MODE_PRIVATE);
        edit = pref.edit();
    }

    public Patient(Context c, int Id) {
        patientName = "Test";
        patientDesc = "Test Desc";
        weeklySchedule = "M/T/F";
        patientPicPath = "No path";
        patientAge = 77;
        medName.add("Penicillin");
        medName.add("Aspirin");
        patientID = Id;
        pref = c.getSharedPreferences("ID" + Integer.toString(patientID), MODE_PRIVATE);
        edit = pref.edit();
        load();
        save();
    }

    public Patient(Context c, String name) {
        patientName = name;
        pref = c.getSharedPreferences("patientList", MODE_PRIVATE);
        try {
            patientID = pref.getInt(patientName, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pref = c.getSharedPreferences("ID" + Integer.toString(patientID), MODE_PRIVATE);
        edit = pref.edit();
        load();
        save();
    }

    public void save() {
        try {
            edit.putString("Patient Name", patientName);
            edit.putInt("Patient Age", patientAge);
            edit.putString("Patient Desc", patientDesc);
            edit.putInt("Patient ID", patientID);
            edit.putString("Weekly Schedule", weeklySchedule);
            edit.putStringSet("Task Desc", new HashSet<String>(taskDesc));
            edit.putStringSet("Task Start", new HashSet<String>(taskStart));
            edit.putStringSet("Task Done", new HashSet<String>(taskDone));
            edit.putStringSet("Task Recurrence", new HashSet<String>(taskRecurrence));
            edit.putStringSet("Med Name", new HashSet<String>(medName));
            edit.putStringSet("Med Amount", new HashSet<String>(medAmount));
            edit.putStringSet("Med Done", new HashSet<String>(medDone));
            edit.putStringSet("Med Days", new HashSet<String>(medDays));
            edit.putStringSet("Med Pic Path", new HashSet<String>(medPicPath));
            edit.putString("Patient Pic Path", patientPicPath);
            edit.apply();
        } catch (Exception e) {
            e.printStackTrace();
            edit.apply();
        }
    }

    public void load() {
        try {
            patientName = pref.getString("Patient Name", null);
            patientAge= pref.getInt("Patient Age", -1);
            patientDesc = pref.getString("Patient Desc", null);
            patientID = pref.getInt("Patient ID", -1);
            weeklySchedule = pref.getString("Weekly Schedule", null);
            taskDesc = new ArrayList<String>(pref.getStringSet("Task Desc", null));
            taskStart= new ArrayList<String>(pref.getStringSet("Task Start", null));
            taskDone = new ArrayList<String>(pref.getStringSet("Task Done", null));
            taskRecurrence = new ArrayList<String>(pref.getStringSet("Task Recurrence", null));
            medName = new ArrayList<String>(pref.getStringSet("Med Name", null));
            medAmount = new ArrayList<String>(pref.getStringSet("Med Amount", null));
            medDone = new ArrayList<String>(pref.getStringSet("Med Done", null));
            medDays = new ArrayList<String>(pref.getStringSet("Med Days", null));
            medPicPath = new ArrayList<String>(pref.getStringSet("Med Pic Path", null));
            patientPicPath = pref.getString("Patient Pic Path", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Setters
    public void setPatientName(String name) {
        patientName = name;
    }

    public void setPatientDesc(String desc) {
        patientDesc = desc;
    }

    public void setPatientAge(int age) {
        patientAge = age;
    }

    public void setWeeklySchedule(String schedule) {
        weeklySchedule = schedule;
    }

    public void addTaskDesc(String desc) {
        taskDesc.add(desc);
    }

    public void addTaskStart(String start) {
        taskStart.add(start);
    }

    public void addTaskDone(String done) {
        taskDone.add(done);
    }

    public void addTaskRecurrance(String recurrence) {
        taskRecurrence.add(recurrence);
    }

    public void addMedName(String name) {
        medName.add(name);
    }

    public void addMedAmount(String amount) {
        medAmount.add(amount);
    }

    public void addMedDone(String done) {
        medDone.add(done);
    }

    public void addMedDays(String days) {
        medDays.add(days);
    }

    public void addMedPicPath(String path) {
        medPicPath.add(path);
    }

    public void setPatientPicPath(String path) {
        patientPicPath = path;
    }

    // Getters
    public String getPatientName() {
        return patientName;
    }

    public String getPatientDesc() {
        return patientDesc;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public String getWeeklySchedule() {
        return weeklySchedule;
    }

    public String getTaskDesc(int index) {
        return taskDesc.get(index);
    }

    public String getTaskStart(int index) {
        return taskStart.get(index);
    }

    public String getTaskDone(int index) {
        return taskDone.get(index);
    }

    public String getTaskRecurrance(int index) {
        return taskRecurrence.get(index);
    }

    public String getMedName(int index) {
        return medName.get(index);
    }

    public String getMedAmount(int index) {
        return medAmount.get(index);
    }

    public String getMedDone(int index) {
        return medDone.get(index);
    }

    public String getMedDays(int index) {
        return medDays.get(index);
    }

    public String getMedPicPath(int index) {
        return medPicPath.get(index);
    }

    public String getPatientPicPath() {
        return patientPicPath;
    }
}
