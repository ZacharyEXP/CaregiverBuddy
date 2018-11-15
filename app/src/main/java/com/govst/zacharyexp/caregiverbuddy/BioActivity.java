package com.govst.zacharyexp.caregiverbuddy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dpro.widgets.WeekdaysPicker;

public class BioActivity extends AppCompatActivity implements View.OnClickListener {
    Patient p;
    Context c;

    //CardView cv;
    TextView personName;
    TextView personAge;
    TextView personDesc;
    EditText personNameEdit;
    EditText personAgeEdit;
    EditText personDescEdit;
    ImageView personPhoto;
    RelativeLayout rl;
    Button editPhoto;
    Button editBio;
    Button editBioSubmit;
    WeekdaysPicker wd;

    View rootView;
    //LayoutInflater in;

    private static final int PICK_IMAGE = 100;
    private static final int RESULT_OK = -1;

    //@SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_new2);
        //in = inflater;
        //rootView = in.inflate(R.layout.card_view_patient, container, false);

        /*c = getApplicationContext();
        p = new Patient(c, getIntent().getIntExtra("PATIENT_ID", -1));

        //cv = (CardView)findViewById(R.id.cvPatient);
        personName = (TextView)findViewById(R.id.person_name);
        personAge = (TextView)findViewById(R.id.person_age);
        personDesc = (TextView)findViewById(R.id.person_desc);
        personNameEdit = (EditText) findViewById(R.id.person_name_edit);
        personAgeEdit = (EditText) findViewById(R.id.person_age_edit);
        personDescEdit = (EditText) findViewById(R.id.person_desc_edit);
        personNameEdit.setVisibility(View.GONE);
        personAgeEdit.setVisibility(View.GONE);
        personDescEdit.setVisibility(View.GONE);
        personPhoto = (ImageView)findViewById(R.id.person_photo);
        rl = (RelativeLayout)findViewById(R.id.patLayout);
        editPhoto = (Button)findViewById(R.id.edit_photo);
        wd = (WeekdaysPicker)findViewById(R.id.pat_weekdays);
        editBio = (Button)findViewById(R.id.edit_bio);
        editBioSubmit = (Button)findViewById(R.id.edit_bio_confirm);
        editBioSubmit.setVisibility(View.GONE);


        System.out.println(p.getPatientName());
        personName.setText(p.getPatientName());
        personAge.setText(Integer.toString(p.getPatientAge()));
        personDesc.setText(p.getPatientDesc());

        editBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personName.setVisibility(View.GONE);
                personNameEdit.setVisibility(View.VISIBLE);
                personNameEdit.setText(personName.getText());
                personAge.setVisibility(View.GONE);
                personAgeEdit.setVisibility(View.VISIBLE);
                personAgeEdit.setText(personAge.getText());
                personDesc.setVisibility(View.GONE);
                personDescEdit.setVisibility(View.VISIBLE);
                personDescEdit.setText(personDesc.getText());
                wd.setEditable(true);
                editBio.setVisibility(View.GONE);
                editBioSubmit.setVisibility(View.VISIBLE);
            }
        });

        editBioSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personNameEdit.setVisibility(View.GONE);
                personName.setVisibility(View.VISIBLE);
                personName.setText(personNameEdit.getText());
                personAgeEdit.setVisibility(View.GONE);
                personAge.setVisibility(View.VISIBLE);
                personAge.setText(personAgeEdit.getText());
                personDescEdit.setVisibility(View.GONE);
                personDesc.setVisibility(View.VISIBLE);
                personDesc.setText(personDescEdit.getText());
                wd.setEditable(false);
                editBioSubmit.setVisibility(View.GONE);
                editBio.setVisibility(View.VISIBLE);

                p.setPatientName(personNameEdit.getText().toString());
                p.setPatientAge(Integer.parseInt(personAge.getText().toString()));
                p.setPatientDesc(personDescEdit.getText().toString());
                p.setWeeklySchedule(wd.getSelectedDays());
                p.save();
            }
        });

        /*personName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                personName.setVisibility(View.GONE);
                personNameEdit.setVisibility(View.VISIBLE);
                personNameEdit.setText(personName.getText());
                personAge.setVisibility(View.GONE);
                personAgeEdit.setVisibility(View.VISIBLE);
                personAgeEdit.setText(personAge.getText());
                personDesc.setVisibility(View.GONE);
                personDescEdit.setVisibility(View.VISIBLE);
                personDescEdit.setText(personDesc.getText());
                wd.setEditable(true);
                return false;
            }
        });
        personAge.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                personName.setVisibility(View.GONE);
                personNameEdit.setVisibility(View.VISIBLE);
                personNameEdit.setText(personName.getText());
                personAge.setVisibility(View.GONE);
                personAgeEdit.setVisibility(View.VISIBLE);
                personAgeEdit.setText(personAge.getText());
                personDesc.setVisibility(View.GONE);
                personDescEdit.setVisibility(View.VISIBLE);
                personDescEdit.setText(personDesc.getText());
                wd.setEditable(true);
                return false;
            }
        });
        personDesc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                personName.setVisibility(View.GONE);
                personNameEdit.setVisibility(View.VISIBLE);
                personNameEdit.setText(personName.getText());
                personAge.setVisibility(View.GONE);
                personAgeEdit.setVisibility(View.VISIBLE);
                personAgeEdit.setText(personAge.getText());
                personDesc.setVisibility(View.GONE);
                personDescEdit.setVisibility(View.VISIBLE);
                personDescEdit.setText(personDesc.getText());
                wd.setEditable(true);
                return false;
            }
        });
        wd.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                wd.setEditable(true);
                return false;
            }
        });
        //personPhoto.setImageResource(R.drawable.ic_launcher_background);
        //personPhoto.setImageResource(p.getPatientPicPath());

        try {
            personPhoto.setImageURI(Uri.parse(p.getPatientPicPath()));
            android.view.ViewGroup.LayoutParams layoutParams = personPhoto.getLayoutParams();
            layoutParams.width = 300;
            layoutParams.height = 300;
            personPhoto.setLayoutParams(layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
            personPhoto.setImageResource(R.drawable.ic_launcher_background);
        }
        wd.setSelectedDays(p.getWeeklySchedule());
        */
    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.edit_photo:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, PICK_IMAGE);
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE && data!=null) {
            Uri selectedImage = data.getData();
            personPhoto.setImageURI(selectedImage);
        }
    }
}
