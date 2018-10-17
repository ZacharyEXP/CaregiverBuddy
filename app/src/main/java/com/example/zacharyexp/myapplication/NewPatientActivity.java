package com.example.zacharyexp.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.dpro.widgets.WeekdaysPicker;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class NewPatientActivity extends Activity {

    public static final String FILENAME = "patientlist.txt";
    public String nameS, ageS, infoS;
    public EditText info, name, age;
    public WeekdaysPicker weekdaysPicker;
    public List<String> daysS;
    Patient p;

    Uri selectedImage;

    String photo;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);

        Context c = getApplicationContext();

        p = new Patient(c);

        Button button2 = (Button)findViewById(R.id.button2);
        info = (EditText)findViewById(R.id.patient_desc);
        name = (EditText)findViewById(R.id.patient_name);
        age = (EditText)findViewById(R.id.patient_age);
        weekdaysPicker = (WeekdaysPicker)findViewById(R.id.weekdays);
        Button choosePhoto = (Button)findViewById(R.id.button_choose);

        List<Integer> days = Arrays.asList();
        weekdaysPicker.setSelectedDays(days);

        choosePhoto.setOnClickListener(
                v -> {
                    Intent pickPhoto = new Intent(Intent.ACTION_OPEN_DOCUMENT,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);
                }
        );

        button2.setOnClickListener(
                new Button.OnClickListener(){
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    public void onClick(View v){
                        nameS = name.getText().toString();
                        ageS = age.getText().toString();
                        infoS = info.getText().toString();
                        daysS = weekdaysPicker.getSelectedDaysText();

                        p.addListName(nameS);
                        p.setPatientName(nameS);
                        p.addListAge(ageS);
                        p.setPatientAge(Integer.parseInt(ageS));
                        p.addListPic(photo);
                        p.setPatientPicPath(photo);
                        p.setPatientDesc(infoS);
                        p.setWeeklySchedule(daysS);
                        p.saveList();

                        startPatientManagement(v);
                    }
                }
        );
    }

    public void startPatientManagement(View view) {
        Intent intent = new Intent(this, PatientSelect.class);
        startActivity(intent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if(resultCode == RESULT_OK){
            selectedImage = imageReturnedIntent.getData();
            System.out.println(selectedImage.toString());
            photo = selectedImage.toString();
        }
    }
}
