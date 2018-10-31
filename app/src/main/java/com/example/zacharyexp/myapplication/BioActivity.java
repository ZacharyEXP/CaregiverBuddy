package com.example.zacharyexp.myapplication;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BioActivity extends AppCompatActivity implements View.OnClickListener {
    Patient p;
    Context c;

    //CardView cv;
    TextView personName;
    TextView personAge;
    TextView personDesc;
    ImageView personPhoto;
    RelativeLayout rl;
    Button edit;

    View rootView;
    //LayoutInflater in;

    private static final int PICK_IMAGE = 100;
    private static final int RESULT_OK = -1;

    //@SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);
        //in = inflater;
        //rootView = in.inflate(R.layout.card_view_patient, container, false);

        c = getApplicationContext();
        p = new Patient(c, getIntent().getIntExtra("PATIENT_ID", -1));

        //cv = (CardView)findViewById(R.id.cvPatient);
        personName = (TextView)findViewById(R.id.person_name);
        personAge = (TextView)findViewById(R.id.person_age);
        personDesc= (TextView)findViewById(R.id.person_desc);
        personPhoto = (ImageView)findViewById(R.id.person_photo);
        rl = (RelativeLayout)findViewById(R.id.patLayout);
        edit = (Button)findViewById(R.id.edit);

        System.out.println(p.getPatientName());
        personName.setText(p.getPatientName());
        personAge.setText(Integer.toString(p.getPatientAge()));
        personDesc.setText(p.getPatientDesc());
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
    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.edit:
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
