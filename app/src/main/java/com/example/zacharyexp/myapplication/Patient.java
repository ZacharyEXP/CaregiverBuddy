package com.example.zacharyexp.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Patient extends Activity implements Serializable {
    int patientAge, patientID;
    String patientName, patientDesc, weeklySchedule, patientPicPath;
    ArrayList<String> taskDesc = new ArrayList<String>();
    ArrayList<String> taskStart = new ArrayList<String>();
    ArrayList<String> taskRecurrence = new ArrayList<String>();
    ArrayList<String> taskDone = new ArrayList<String>();
    ArrayList<String> medName = new ArrayList<String>();
    ArrayList<String> medDone = new ArrayList<String>();
    ArrayList<String> medDays = new ArrayList<String>();
    ArrayList<String> medPicPath = new ArrayList<String>();
    ArrayList<String> medAmount = new ArrayList<String>();;

    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> ages = new ArrayList<String>();
    ArrayList<String> pics = new ArrayList<String>();

    SharedPreferences pref;
    SharedPreferences.Editor edit;

    SharedPreferences prefList;
    SharedPreferences.Editor editList;

    public Patient(Context c) {
        prefList = c.getSharedPreferences("patientList", MODE_PRIVATE);
        editList = prefList.edit();
        loadList();
    }

    public Patient(Context c, int Id) {
        prefList = c.getSharedPreferences("patientList", MODE_PRIVATE);
        editList = prefList.edit();
        loadList();

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

    /*public Patient(Context c, String name) {
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
    }*/

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

    public void saveList() {
        try {
            editList.putStringSet("Name List", new HashSet<String>(names));
            editList.putStringSet("Age List", new HashSet<String>(ages));
            editList.putStringSet("Pic List", new HashSet<String>(pics));
            editList.apply();
        } catch (Exception e) {
            e.printStackTrace();
            editList.apply();
        }
    }

    public void loadList() {
        try {
            names = new ArrayList<String>(prefList.getStringSet("Name List", null));
            ages = new ArrayList<String>(prefList.getStringSet("Age List", null));
            pics = new ArrayList<String>(prefList.getStringSet("Pic List", null));
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

    public void addListName(String name) {
        names.add(name);
    }

    public void addListAge(String age) {
        ages.add(age);
    }

    public void addListPic(String pic) {
        pics.add(pic);
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

    public String getListName(int index) {
        return names.get(index);
    }

    public String getListAge(int index) {
        return ages.get(index);
    }

    public String getListPic(int index) {
        return pics.get(index);
    }

    public ArrayList<String> getListNames() {
        return names;
    }

    public ArrayList<String> getListAges() {
        return ages;
    }

    public ArrayList<String> getListPics() {
        return pics;
    }
}
