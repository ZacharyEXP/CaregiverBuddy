package com.govst.zacharyexp.caregiverbuddy.biography;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dpro.widgets.WeekdaysPicker;
import com.govst.zacharyexp.caregiverbuddy.MainSelect;
import com.govst.zacharyexp.caregiverbuddy.Patient;
import com.govst.zacharyexp.caregiverbuddy.R;
import com.govst.zacharyexp.caregiverbuddy.library.EuclidActivity2;
import com.govst.zacharyexp.caregiverbuddy.library.EuclidListAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BioNewActivity extends EuclidActivity2 {
    Patient p;
    Context c;
    int id;

    private LinearLayout parentLinearLayout;
    private LinearLayout contactList;
    private LinearLayout foodList;
    private LinearLayout activityList;

    private WeekdaysPicker wd;

    Button bio, details;
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

        Map<String, Object> profileMap;
        profileMap = new HashMap<>();
        profileMap.put(EuclidListAdapter.KEY_AVATAR, p.getPatientPicPath());
        profileMap.put(EuclidListAdapter.KEY_NAME, p.getPatientName());
        profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, "Age " + p.getPatientAge());
        profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, p.getPatientDesc());

        bio = findViewById(R.id.toolbar_profile_bio);
        details = findViewById(R.id.toolbar_profile_details);

        if (mOverlayListItemView == null) {
            mOverlayListItemView = getLayoutInflater().inflate(R.layout.overlay_list_item, mWrapper, false);
        } else {
            mWrapper.removeView(mOverlayListItemView);
            mOverlayListItemView = getLayoutInflater().inflate(R.layout.overlay_list_item, mWrapper, false);
        }

        mOverlayListItemView.findViewById(R.id.view_avatar_overlay).setBackground(sOverlayShape);

        try {
            Picasso.with(BioNewActivity.this).load(Uri.parse((String) profileMap.get(EuclidListAdapter.KEY_AVATAR)))
                    .resize(sScreenWidth, sProfileImageHeight).centerCrop()
                    .placeholder(R.color.blue)
                    .into((ImageView) findViewById(R.id.image_view_avatar));
        } catch (Exception e) {
            e.printStackTrace();
        }

        ((TextView) mOverlayListItemView.findViewById(R.id.text_view_name)).setText((String) profileMap.get(EuclidListAdapter.KEY_NAME));
        ((TextView) mOverlayListItemView.findViewById(R.id.text_view_description)).setText((String) profileMap.get(EuclidListAdapter.KEY_DESCRIPTION_SHORT));
        setProfileDetailsInfo(profileMap);

        refreshBio();
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onClick(View v) {
        // Perform action on click
        switch(v.getId()) {
            case R.id.toolbar_profile_bio:
                setContentView(R.layout.activity_euclid2);

                bio = findViewById(R.id.toolbar_profile_bio);
                details = findViewById(R.id.toolbar_profile_details);

                refreshBio();
                break;
            case R.id.toolbar_profile_details:
                setContentView(R.layout.activity_euclid3);

                bio = findViewById(R.id.toolbar_profile_bio2);
                details = findViewById(R.id.toolbar_profile_details2);
                refreshDetails();
                break;
            case R.id.toolbar_profile_bio2:
                System.out.println("Bio clicked.");
                setContentView(R.layout.activity_euclid2);

                bio = findViewById(R.id.toolbar_profile_bio);
                details = findViewById(R.id.toolbar_profile_details);
                refreshBio();
                break;
            case R.id.toolbar_profile_details2:
                System.out.println("Details clicked.");
                setContentView(R.layout.activity_euclid3);

                bio = findViewById(R.id.toolbar_profile_bio2);
                details = findViewById(R.id.toolbar_profile_details2);
                refreshDetails();
                break;
            case R.id.toolbar_profile_back2:
                Intent intent = new Intent(getApplicationContext(), MainSelect.class);
                intent.putExtra("PATIENT_ID", id);
                startActivity(intent);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void refreshBio() {
        mWrapper = findViewById(R.id.wrapper);
        mToolbar = findViewById(R.id.toolbar_list);
        mToolbarProfile = findViewById(R.id.toolbar_profile);
        mProfileDetails = findViewById(R.id.wrapper_profile_details);
        mTextViewProfileName = findViewById(R.id.text_view_profile_name);
        mTextViewProfileDescription = findViewById(R.id.text_view_profile_description);
        mButtonProfile = findViewById(R.id.button_profile);

        Map<String, Object> profileMap;
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

        try{
            Picasso.with(BioNewActivity.this).load(Uri.parse((String) profileMap.get(EuclidListAdapter.KEY_AVATAR)))
                    .resize(sScreenWidth, sProfileImageHeight).centerCrop()
                    .placeholder(R.color.blue)
                    .into((ImageView) findViewById(R.id.image_view_avatar));
        } catch (Exception e) {

        }

        ((TextView) mOverlayListItemView.findViewById(R.id.text_view_name)).setText((String) profileMap.get(EuclidListAdapter.KEY_NAME));
        ((TextView) mOverlayListItemView.findViewById(R.id.text_view_description)).setText((String) profileMap.get(EuclidListAdapter.KEY_DESCRIPTION_SHORT));
        setProfileDetailsInfo(profileMap);

        mButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BioEdit.class);
                intent.putExtra("PATIENT_ID", id);
                startActivity(intent);
            }
        });
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
            addField(R.layout.bio_field_display, s, contactList);
        }

        for(String s : p.getPrefFood()) {
            addField(R.layout.bio_field_display, s, foodList);
        }

        for(String s : p.getPrefAct()) {
            addField(R.layout.bio_field_display, s, activityList);
        }

        wd.setSelectedDays(p.getWeeklySchedule());
    }

    public void addField(int id, String s1, LinearLayout ll) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(id, null);
        // Add the new row before the add field button.
        ll.addView(rowView, ll.getChildCount() - 1);

        TextView text_var1;
        text_var1 = rowView.findViewById(R.id.bio_field_1);

        text_var1.setText(s1);
    }
}
