package com.example.zacharyexp.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Patient extends Activity implements Serializable {
    // All variables are initialized here
    int patientAge;
    int patientID = -1;
    String patientName, patientDesc, patientPicPath;
    List<Integer> weeklySchedule;

    ArrayList<Task> tasks= new ArrayList<Task>();
    ArrayList<Med> meds = new ArrayList<Med>();

    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> ages = new ArrayList<String>();
    ArrayList<String> pics = new ArrayList<String>();
    int highestId, highestTask, highestMed;
    String nameS, agesS, picsS;
    String[] nameSS, ageSS, picsSS;
    StringTokenizer st;

    SharedPreferences pref;
    SharedPreferences.Editor edit;

    SharedPreferences prefList;
    SharedPreferences.Editor editList;

    Context temp;

    // Default Constructor, used when program first loads. Loads patientlist
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Patient(Context c) {
        prefList = c.getSharedPreferences("patientList", MODE_PRIVATE);
        editList = prefList.edit();
        temp = c;
        //saveList();
        loadList();
    }

    // Main Constructor, used when loading a patient.
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Patient(Context c, int Id) {
        prefList = c.getSharedPreferences("patientList", MODE_PRIVATE);
        editList = prefList.edit();
        temp = c;
        //saveList();
        loadList();

        patientID = Id;
        pref = c.getSharedPreferences("ID" + Integer.toString(patientID), MODE_PRIVATE);
        edit = pref.edit();
        load();
        save();
    }

    // Saves all patient data, overwrites any previous values
    public void save() {
        highestTask = 0;
        highestMed = 0;
        try {
            edit.putString("Patient Name", patientName);
            edit.putInt("Patient Age", patientAge);
            edit.putString("Patient Desc", patientDesc);
            edit.putInt("Patient ID", patientID);

            for(int i = 0; i < tasks.size(); i++) {
                edit.putString("Task #" + Integer.toString(i) + " Desc", tasks.get(i).getDesc());
                edit.putString("Task #" + Integer.toString(i) + " Start", tasks.get(i).getStart());
                edit.putString("Task #" + Integer.toString(i) + " Recur", tasks.get(i).getRecur());
                edit.putString("Task #" + Integer.toString(i) + " Done", tasks.get(i).getDone());
                highestTask++;
            }

            for(int i = 0; i < meds.size(); i++) {
                edit.putString("Med #" + Integer.toString(i) + " Name", meds.get(i).getName());
                edit.putString("Med #" + Integer.toString(i) + " Done", meds.get(i).getDone());
                edit.putString("Med #" + Integer.toString(i) + " Days", meds.get(i).getDays());
                edit.putString("Med #" + Integer.toString(i) + " PicPath", meds.get(i).getPicPath());
                edit.putString("Med #" + Integer.toString(i) + " Amount", meds.get(i).getAmount());
                highestMed++;
            }

            edit.putInt("Highest Task", highestTask);
            edit.putInt("Highest Med", highestMed);
            edit.putString("Patient Pic Path", patientPicPath);
            //if(weeklySchedule != null) {
                //edit.putStringSet("Weekly Schedule", new HashSet<String>(weeklySchedule));
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < weeklySchedule.size(); i++) {
                    str.append(weeklySchedule.get(i)).append(",");
                }
                edit.putString("Weekly Schedule", str.toString());
            //}
            edit.apply();
        } catch (Exception e) {
            e.printStackTrace();
            edit.apply();
        }
    }

    // Loads all patient data into variables
    public void load() {
        String temp1, temp2, temp3, temp4, temp5;
        try {
            patientName = pref.getString("Patient Name", null);
            patientAge= pref.getInt("Patient Age", -1);
            patientDesc = pref.getString("Patient Desc", null);
            patientID = pref.getInt("Patient ID", -1);

            highestTask = pref.getInt("Highest Task", 0);
            highestMed = pref.getInt("Highest Med", 0);

            for(int i = 0; i < highestTask; i++) {
                temp1 = pref.getString("Task #" + Integer.toString(i) + " Desc", null);
                temp2 = pref.getString("Task #" + Integer.toString(i) + " Start", null);
                temp3 = pref.getString("Task #" + Integer.toString(i) + " Recur", null);
                temp4 = pref.getString("Task #" + Integer.toString(i) + " Done", null);
                tasks.add(new Task(temp1, temp2, temp3, temp4));
            }

            for(int i = 0; i < highestMed; i++) {
                temp1 = pref.getString("Med #" + Integer.toString(i) + " Name", null);
                temp2 = pref.getString("Med #" + Integer.toString(i) + " Done", null);
                temp3 = pref.getString("Med #" + Integer.toString(i) + " Days", null);
                temp4 = pref.getString("Med #" + Integer.toString(i) + " PicPath", null);
                temp5 = pref.getString("Med #" + Integer.toString(i) + " Amount", null);
                meds.add(new Med(temp1, temp2, temp3, temp4, temp5));
            }

            patientPicPath = pref.getString("Patient Pic Path", null);
            String savedString = pref.getString("Weekly Schedule", null);
            System.out.println(savedString);
            StringTokenizer st = new StringTokenizer(savedString, ",");
            weeklySchedule = new ArrayList<Integer>();
            while(st.hasMoreTokens()) {
                weeklySchedule.add(Integer.parseInt(st.nextToken()));
                //System.out.println(weeklySchedule);
                //System.out.println
            }
            //System.out.println(weeklySchedule);
            //weeklySchedule = (List<Integer>)pref.getIntS("Weekly Schedule", null);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Saves all basic patient info to use when selecting a patient
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void saveList() {
        nameS = "";
        agesS = "";
        picsS = "";
        StringJoiner namesSJ = new StringJoiner("|");
        StringJoiner agesSJ = new StringJoiner("|");
        StringJoiner picsSJ = new StringJoiner("|");

        if(names.size() > 0) {
            for (String n : names) {
                namesSJ.add(n);
            }
            for (String n : ages) {
                agesSJ.add(n);
            }
            for (String n : pics) {
                picsSJ.add(n);
            }
            nameS = namesSJ.toString();
            agesS = agesSJ.toString();
            picsS = picsSJ.toString();
            System.out.println(nameS + agesS + picsS);
        }
        try {
            editList.putString("Name List", nameS);
            editList.putString("Age List", agesS);
            editList.putString("Pic List", picsS);

            if(patientName != null) {
                if(patientID == -1) {
                    patientID = highestId + 1;
                    highestId++;
                }
                editList.putInt(patientName, patientID);
            }

            editList.putInt("Highest ID", highestId);
            editList.apply();

            pref = temp.getSharedPreferences("ID" + Integer.toString(patientID), MODE_PRIVATE);
            edit = pref.edit();
            save();
        } catch (Exception e) {
            e.printStackTrace();
            editList.apply();
        }
    }

    // Loads all basic patient info for patient selection
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void loadList() {
        try {
            nameS = prefList.getString("Name List", null);
            agesS = prefList.getString("Age List", null);
            picsS = prefList.getString("Pic List", null);
            if(nameS.contains("|")) {
                names = new ArrayList<String>();
                ages = new ArrayList<String>();
                pics = new ArrayList<String>();
                st = new StringTokenizer(nameS, "|");
                while (st.hasMoreTokens())
                {
                    names.add(st.nextToken());
                }
                st = new StringTokenizer(agesS, "|");
                while (st.hasMoreTokens())
                {
                    ages.add(st.nextToken());
                }
                st = new StringTokenizer(picsS, "|");
                while (st.hasMoreTokens())
                {
                    pics.add(st.nextToken());
                }
            } else {
                names = new ArrayList<String>(Arrays.asList(nameS));
                ages = new ArrayList<String>(Arrays.asList(agesS));
                pics = new ArrayList<String>(Arrays.asList(picsS));
            }
            highestId = prefList.getInt("Highest ID", -1);
            patientID = prefList.getInt(patientName, -1);
        } catch (NullPointerException e) {

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

    public void setWeeklySchedule(List<Integer> s) {
        weeklySchedule = s;
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

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void addMed(Med m) {
        meds.add(m);
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

    public List<Integer> getWeeklySchedule() {
        return weeklySchedule;
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

    public ArrayList<String> getTaskDescs() {
        ArrayList<String> arr = new ArrayList<>();
        for(Task t : tasks) {
            arr.add(t.getDesc());
        }
        return arr;
    }

    public ArrayList<String> getTaskStarts() {
        ArrayList<String> arr = new ArrayList<>();
        for(Task t : tasks) {
            arr.add(t.getStart());
        }
        return arr;
    }

    public ArrayList<String> getTasksDone() {
        ArrayList<String> arr = new ArrayList<>();
        for(Task t : tasks) {
            arr.add(t.getDone());
        }
        return arr;
    }

    public ArrayList<String> getTaskRecurrences() {
        ArrayList<String> arr = new ArrayList<>();
        for(Task t : tasks) {
            arr.add(t.getRecur());
        }
        return arr;
    }

    public ArrayList<String> getMedNames() {
        ArrayList<String> arr = new ArrayList<>();
        for(Med m : meds) {
            arr.add(m.getName());
        }
        return arr;
    }

    public ArrayList<String> getMedAmounts() {
        ArrayList<String> arr = new ArrayList<>();
        for(Med m : meds) {
            arr.add(m.getAmount());
        }
        return arr;
    }

    public ArrayList<String> getMedsDone() {
        ArrayList<String> arr = new ArrayList<>();
        for(Med m : meds) {
            arr.add(m.getDone());
        }
        return arr;
    }

    public ArrayList<String> getMedsDays() {
        ArrayList<String> arr = new ArrayList<>();
        for(Med m : meds) {
            arr.add(m.getDays());
        }
        return arr;
    }

    public ArrayList<String> getMedPicPaths() {
        ArrayList<String> arr = new ArrayList<>();
        for(Med m : meds) {
            arr.add(m.getPicPath());
        }
        return arr;
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

    public int getIdFromName(String name) {
        return prefList.getInt(name, -1);
    }
}

class Task {
    String desc, start, recur, done;
    ArrayList<String> arr = new ArrayList<>();

    Task(String de, String s, String r, String d) {
        arr.add(de);
        arr.add(s);
        arr.add(r);
        arr.add(d);
    }

    Task(ArrayList<String> ar) {
        arr = ar;
    }

    public void setDesc(String d) {
        arr.set(0, d);
    }

    public void setStart(String s) {
        arr.set(1, s);
    }

    public void setRecur(String r) {
        arr.set(2, r);
    }

    public void setDone(String d) {
        arr.set(3, d);
    }

    public String getDesc() {
        return arr.get(0);
    }

    public String getStart() {
        return arr.get(1);
    }

    public String getRecur() {
        return arr.get(2);
    }

    public String getDone() {
        return arr.get(3);
    }

    public ArrayList<String> getArray() {

        return arr;
    }
}

class Med {
    String name, done, days, picPath, amount;
    ArrayList<String> arr = new ArrayList<>();

    Med(String n, String d, String da, String p, String a) {
        arr.add(n);
        arr.add(d);
        arr.add(da);
        arr.add(p);
        arr.add(a);
    }

    Med(ArrayList<String> ar) {
        arr = ar;
    }

    public void setName(String n) {
        arr.set(0, n);
    }

    public void setDone(String d) {
        arr.set(1, d);
    }

    public void setDays(String d) {
        arr.set(2, d);
    }

    public void setPicPath(String p) {
        arr.set(3, p);
    }

    public void setAmount(String a) {
        arr.set(4, a);
    }

    public String getName() {
        return arr.get(0);
    }

    public String getDone() {
        return arr.get(1);
    }

    public String getDays() {
        return arr.get(2);
    }

    public String getPicPath() {
        return arr.get(3);
    }

    public String getAmount() {
        return arr.get(4);
    }

    public ArrayList<String> getArray() {
        return arr;
    }
}