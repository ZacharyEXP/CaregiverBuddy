package com.govst.zacharyexp.caregiverbuddy;

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

    String sex, emergencyNum;
    String contactS = "";
    String foodS = "";
    String actS = "";
    ArrayList<String> contacts = new ArrayList<String>();
    ArrayList<String> prefFood = new ArrayList<String>();
    ArrayList<String> prefAct = new ArrayList<String>();

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
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void save() {
        highestTask = 0;
        highestMed = 0;

        StringJoiner contactSJ = new StringJoiner("|");
        StringJoiner foodSJ = new StringJoiner("|");
        StringJoiner actSJ = new StringJoiner("|");

        for (String n : contacts) {
            System.out.println(n + "&&&");
            contactSJ.add(n);
        }
        for (String n : prefFood) {
            foodSJ.add(n);
        }
        for (String n : prefAct) {
            actSJ.add(n);
        }

        contactS = contactSJ.toString();
        foodS = foodSJ.toString();
        actS = actSJ.toString();

        System.out.println(contactS + "##");

        edit.putString("Pref Contacts", contactS);
        edit.putString("Pref Food", foodS);
        edit.putString("Pref Activities", actS);

        System.out.println(actS);

        edit.putString("Patient Sex", sex);
        edit.putString("Patient Emergency Num", emergencyNum);

        try {
            edit.putString("Patient Name", patientName);
            edit.putInt("Patient Age", patientAge);
            edit.putString("Patient Desc", patientDesc);
            edit.putInt("Patient ID", patientID);

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

            patientPicPath = pref.getString("Patient Pic Path", null);
            String savedString = pref.getString("Weekly Schedule", null);
            System.out.println(savedString);
            StringTokenizer st = new StringTokenizer(savedString, ",");
            weeklySchedule = new ArrayList<Integer>();
            while(st.hasMoreTokens()) {
                weeklySchedule.add(Integer.parseInt(st.nextToken()));
                //System.out.println(weeklySchedule);
                //System.out.println();
            }

            sex = pref.getString("Patient Sex", null);
            emergencyNum = pref.getString("Patient Emergency Num", null);

            System.out.println(actS);

            contactS = pref.getString("Pref Contacts", null);
            foodS = pref.getString("Pref Food", null);
            actS = pref.getString("Pref Activities", null);

            System.out.println(actS);

            if(contactS.contains("|")) {
                contacts = new ArrayList<String>();
                st = new StringTokenizer(contactS, "|");
                while (st.hasMoreTokens())
                {
                    contacts.add(st.nextToken());
                }
            } else {
                contacts = new ArrayList<String>(Arrays.asList(contactS));
            }

            if(foodS.contains("|")) {
                prefFood = new ArrayList<String>();
                st = new StringTokenizer(foodS, "|");
                while (st.hasMoreTokens())
                {
                    prefFood.add(st.nextToken());
                }
            } else {
                prefFood = new ArrayList<String>(Arrays.asList(foodS));
            }

            if(actS.contains("|")) {
                prefAct = new ArrayList<String>();
                st = new StringTokenizer(actS, "|");
                while (st.hasMoreTokens())
                {
                    prefAct.add(st.nextToken());
                }
            } else {
                prefAct = new ArrayList<String>(Arrays.asList(actS));
            }

            System.out.println(contacts);
            System.out.println(contactS);
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmergencyNum() {
        return emergencyNum;
    }

    public void setEmergencyNum(String emergencyNum) {
        this.emergencyNum = emergencyNum;
    }

    public ArrayList<String> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<String> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<String> getPrefFood() {
        return prefFood;
    }

    public void setPrefFood(ArrayList<String> prefFood) {
        this.prefFood = prefFood;
    }

    public ArrayList<String> getPrefAct() {
        return prefAct;
    }

    public void setPrefAct(ArrayList<String> prefAct) {
        this.prefAct = prefAct;
    }

    public void updateListInfo(String oldName, String newName, String oldAge, String newAge, String oldPic, String newPic) {
        if(names.contains(oldName)){
            names.set(names.indexOf(oldName), newName);
        } else {
            names.add(newName);
        }

        if(ages.contains(oldAge)){
            ages.set(ages.indexOf(oldAge), newAge);
        } else {
            ages.add(newAge);
        }

        if(pics.contains(oldPic)){
            pics.set(pics.indexOf(oldPic), newPic);
        } else {
            pics.add(newPic);
        }
    }
}