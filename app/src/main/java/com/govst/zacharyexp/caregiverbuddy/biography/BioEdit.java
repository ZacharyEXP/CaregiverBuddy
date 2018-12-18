package com.govst.zacharyexp.caregiverbuddy.biography;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dpro.widgets.WeekdaysPicker;
import com.govst.zacharyexp.caregiverbuddy.Patient;
import com.govst.zacharyexp.caregiverbuddy.PatientSelectNew;
import com.govst.zacharyexp.caregiverbuddy.R;
import com.govst.zacharyexp.caregiverbuddy.library.EuclidActivity2;
import com.govst.zacharyexp.caregiverbuddy.library.EuclidListAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BioEdit extends EuclidActivity2 {
    Patient p;
    Context c;
    int id;

    private LinearLayout parentLinearLayout;
    private LinearLayout contactList;
    private LinearLayout foodList;
    private LinearLayout activityList;

    ArrayList<EditText> contacts, foods, activities;
    ArrayList<ImageButton> addC, addF, addA, deleteC, deleteF, deleteA;

    private WeekdaysPicker wd;

    Uri selectedImage;
    String photo;

    Map<String, Object> profileMap;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        c = getApplicationContext();
        id = getIntent().getIntExtra("PATIENT_ID", -1);
        if(id != -1) {
            p = new Patient(c, id);
        } else {
            p = new Patient(c);
        }
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_bio_edit);

        mTextViewProfileName = findViewById(R.id.text_view_profile_name);
        mTextViewProfileAge = findViewById(R.id.text_view_profile_age);
        mTextViewProfileDescription = findViewById(R.id.text_view_profile_description);

        profileMap = new HashMap<>();
        profileMap.put(EuclidListAdapter.KEY_AVATAR, p.getPatientPicPath());
        profileMap.put(EuclidListAdapter.KEY_NAME, p.getPatientName());
        profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, "Age " + p.getPatientAge());
        profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, p.getPatientDesc());

        if (mOverlayListItemView == null) {
            mOverlayListItemView = getLayoutInflater().inflate(R.layout.overlay_list_item, mWrapper, false);
        } else {
            mWrapper.removeView(mOverlayListItemView);
            mOverlayListItemView = getLayoutInflater().inflate(R.layout.overlay_list_item, mWrapper, false);
        }

        mOverlayListItemView.findViewById(R.id.view_avatar_overlay).setBackground(sOverlayShape);

        try {
            Picasso.with(BioEdit.this).load(Uri.parse((String) profileMap.get(EuclidListAdapter.KEY_AVATAR)))
                    .resize(sScreenWidth, sProfileImageHeight).centerCrop()
                    .placeholder(R.color.blue)
                    .into((ImageView) findViewById(R.id.image_view_avatar));
        } catch (Exception e) {
            e.printStackTrace();
            Picasso.with(BioEdit.this).load(R.drawable.ic_add_a_photo)
                    .resize(sScreenWidth, sProfileImageHeight).centerCrop()
                    .placeholder(R.color.blue)
                    .into((ImageView) findViewById(R.id.image_view_avatar));
        }

        mTextViewProfileName.setText((String) profileMap.get(EuclidListAdapter.KEY_NAME));
        mTextViewProfileDescription.setText((String) profileMap.get(EuclidListAdapter.KEY_DESCRIPTION_SHORT));
        setProfileDetailsInfo(profileMap);

        contacts = new ArrayList<>();
        foods = new ArrayList<>();
        activities = new ArrayList<>();
        addC = new ArrayList<>();
        addF= new ArrayList<>();
        addA = new ArrayList<>();
        deleteC = new ArrayList<>();
        deleteF = new ArrayList<>();
        deleteA = new ArrayList<>();

        refreshDetails();
    }

    @Override
    protected BaseAdapter getAdapter() {
        Map<String, Object> profileMap;
        List<Map<String, Object>> profilesList = new ArrayList<>();

        profileMap = new HashMap<>();
        profileMap.put(EuclidListAdapter.KEY_AVATAR, p.getPatientPicPath());
        profileMap.put(EuclidListAdapter.KEY_NAME, p.getPatientName());
        profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, "Age " + p.getPatientAge());
        profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, p.getPatientDesc());
        profilesList.add(profileMap);

        return new EuclidListAdapter(this, R.layout.list_item, profilesList);
    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onClick(View v) {
        // Perform action on click
        switch(v.getId()) {
            case R.id.image_view_avatar:
                Intent pickPhoto = new Intent(Intent.ACTION_OPEN_DOCUMENT,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
                break;
            case R.id.button_confirm:
                ArrayList<String> temp1 = new ArrayList<>();
                ArrayList<String> temp2 = new ArrayList<>();
                ArrayList<String> temp3 = new ArrayList<>();

                p.updateListInfo(p.getPatientName(), mTextViewProfileName.getText().toString(), Integer.toString(p.getPatientAge()), mTextViewProfileAge.getText().toString(), p.getPatientPicPath(), photo);

                p.setSex(mTextViewDetailsSex.getText().toString());
                try {
                    p.setPatientAge(Integer.parseInt(mTextViewProfileAge.getText().toString()));
                } catch (Exception e) {
                    p.setPatientAge(0);
                }
                p.setEmergencyNum(mTextViewDetailsEmergencyNum.getText().toString());
                p.setWeeklySchedule(wd.getSelectedDays());
                p.setPatientName(mTextViewProfileName.getText().toString());
                p.setPatientDesc(mTextViewProfileDescription.getText().toString());
                p.setPatientPicPath(photo);


                for(EditText t : contacts) {
                    temp1.add(t.getText().toString());
                }
                p.setContacts(temp1);

                for(EditText t : foods) {
                    temp2.add(t.getText().toString());
                }
                p.setPrefFood(temp2);

                for(EditText t : activities) {
                    temp3.add(t.getText().toString());
                }
                p.setPrefAct(temp3);

                p.save();
                p.saveList();

                Intent intent = new Intent(this, BioNewActivity.class);
                intent.putExtra("PATIENT_ID", id);
                startActivity(intent);
                break;
        }
    }


    public void refreshDetails() {
        mWrapper = findViewById(R.id.wrapper);
        parentLinearLayout = findViewById(R.id.parent_linear_layout);
        mToolbar = findViewById(R.id.toolbar_list);
        mToolbarProfile = findViewById(R.id.toolbar_profile);
        mProfileDetails = findViewById(R.id.wrapper_profile_details);
        mTextViewDetailsSex = findViewById(R.id.details_sex);
        mTextViewDetailsEmergencyNum = findViewById(R.id.details_emergency_number);
        mButtonProfile = findViewById(R.id.button_profile);

        mTextViewDetailsSex.setText(p.getSex());
        mTextViewDetailsEmergencyNum.setText(p.getEmergencyNum());

        contactList = findViewById(R.id.contact_list);
        foodList = findViewById(R.id.food_list);
        activityList = findViewById(R.id.activity_list);

        wd = findViewById(R.id.pat_weekdays);

        for(String s : p.getContacts()) {
            addField(R.layout.bio_field_entry, s, contactList);
        }
        if(p.getContacts().size() == 0) {
            addField(R.layout.bio_field_entry, "", contactList);
        }

        for(String s : p.getPrefFood()) {
            addField(R.layout.bio_field_entry, s, foodList);
        }
        if(p.getPrefFood().size() == 0) {
            addField(R.layout.bio_field_entry, "", foodList);
        }

        for(String s : p.getPrefAct()) {
            addField(R.layout.bio_field_entry, s, activityList);
        }
        if(p.getPrefAct().size() == 0) {
            addField(R.layout.bio_field_entry, "", activityList);
        }

        try {
            wd.setSelectedDays(p.getWeeklySchedule());
        } catch (Exception e) {

        }
    }

    public void addField(int id, String s1, LinearLayout ll) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(id, null);
        // Add the new row before the add field button.
        ll.addView(rowView, ll.getChildCount() - 1);

        EditText text_var1;
        ImageButton add, delete;
        text_var1 = rowView.findViewById(R.id.bio_field_1);
        text_var1.setText(s1);

        add = rowView.findViewById(R.id.bio_add);
        delete = rowView.findViewById(R.id.bio_delete);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addField(id, "", ll);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ll.getChildCount() > 3) {
                    ll.removeView(rowView);
                    switch (ll.getId()) {
                        case R.id.contact_list:
                            contacts.remove(text_var1);
                            break;
                        case R.id.food_list:
                            foods.remove(text_var1);
                            break;
                        case R.id.activity_list:
                            activities.remove(text_var1);
                            break;
                    }
                }
            }
        });

        switch(ll.getId()) {
            case R.id.contact_list:
                contacts.add(text_var1);
                break;
            case R.id.food_list:
                foods.add(text_var1);
                break;
            case R.id.activity_list:
                activities.add(text_var1);
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if(resultCode == RESULT_OK){
            selectedImage = imageReturnedIntent.getData();
            photo = selectedImage.toString();
        }
    }
}
