package com.example.zacharyexp.myapplication;

import android.app.Activity;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

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
    int patientAge;
    ArrayList<Integer> medAmount = new ArrayList<Integer>();;

    public static final String FILENAME = "patientlist.txt";
    final static String path = Environment.getDataDirectory().getAbsolutePath() + "/data/com.example.zacharyexp.myapplication/files/";
    File file;

    public Patient() {
        /*patientName = "Test";
        patientDesc = "Test Desc";
        weeklySchedule = "M/T/F";
        patientPicPath = "No path";
        patientAge = 77;*/
        load();
        file = new File(path + "Test.txt");
        save();
    }

    public void save() {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(("#Patient Name" + "\n").getBytes());
            outputStream.write((patientName + "\n").getBytes());
            outputStream.write(("#Patient Age" + "\n").getBytes());
            outputStream.write((Integer.toString(patientAge) + "\n").getBytes());
            outputStream.write(("#Patient Description" + "\n").getBytes());
            outputStream.write((patientDesc + "\n").getBytes());
            outputStream.write(("#Weekly Schedule" + "\n").getBytes());
            outputStream.write((weeklySchedule + "\n").getBytes());
            outputStream.write(("#Task Descriptions" + "\n").getBytes());
            for(int i = 0; i < taskDesc.size(); i++) {
                outputStream.write((taskDesc.get(i) + "\n").getBytes());
            }
            outputStream.write(("#Task Starts" + "\n").getBytes());
            for(int i = 0; i < taskStart.size(); i++) {
                outputStream.write((taskStart.get(i) + "\n").getBytes());
            }
            outputStream.write(("#Tasks Done" + "\n").getBytes());
            for(int i = 0; i < taskDone.size(); i++) {
                outputStream.write((taskDone.get(i) + "\n").getBytes());
            }
            outputStream.write(("#Tasks Recurrence" + "\n").getBytes());
            for(int i = 0; i < taskRecurrence.size(); i++) {
                outputStream.write((taskRecurrence.get(i) + "\n").getBytes());
            }
            outputStream.write(("#Med Names" + "\n").getBytes());
            for(int i = 0; i < medName.size(); i++) {
                outputStream.write((medName.get(i) + "\n").getBytes());
            }
            outputStream.write(("#Med Amounts" + "\n").getBytes());
            for(int i = 0; i < medAmount.size(); i++) {
                outputStream.write((medAmount.get(i) + "\n").getBytes());
            }
            outputStream.write(("#Meds Done" + "\n").getBytes());
            for(int i = 0; i < medDone.size(); i++) {
                outputStream.write((medDone.get(i) + "\n").getBytes());
            }
            outputStream.write(("#Med Days" + "\n").getBytes());
            for(int i = 0; i < medDays.size(); i++) {
                outputStream.write((medDays.get(i) + "\n").getBytes());
            }
            outputStream.write(("#Med Pics Paths" + "\n").getBytes());
            for(int i = 0; i < medPicPath.size(); i++) {
                outputStream.write((medPicPath.get(i) + "\n").getBytes());
            }
            outputStream.write(("#Patient Pic Path" + "\n").getBytes());
            outputStream.write((patientPicPath + "\n").getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(path + "Test.txt"));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines, temp;

            while ((lines = bufferedReader.readLine())!= null) {
                //data = lines.split(" \\| ");
                stringBuffer.delete(0, stringBuffer.length());

                System.out.println(lines);
                if(lines.contains("#Patient Name")) {
                    patientName = bufferedReader.readLine();
                } else if(lines.contains("#Patient Age")) {
                    patientAge = Integer.parseInt(bufferedReader.readLine());
                } else if(lines.contains("#Patient Description")) {
                    patientDesc = bufferedReader.readLine();
                } else if(lines.contains("#Weekly Schedule")) {
                    weeklySchedule = bufferedReader.readLine();
                    System.out.println(weeklySchedule);
                } else if(lines.contains("#Task Descriptions")) {
                    bufferedReader.mark(500);
                    temp = bufferedReader.readLine();
                    while(!temp.contains("#Task Starts")) {
                        taskDesc.add(temp);
                        temp = bufferedReader.readLine();
                    }
                    bufferedReader.reset();
                    System.out.println(temp);
                }
            }

            fileInputStream.close();
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

    public void addMedAmount(int amount) {
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

    public int getMedAmount(int index) {
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
