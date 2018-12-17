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
import com.govst.zacharyexp.caregiverbuddy.Patient;
import com.govst.zacharyexp.caregiverbuddy.R;
import com.govst.zacharyexp.caregiverbuddy.library.EuclidActivity2;
import com.govst.zacharyexp.caregiverbuddy.library.EuclidListAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oleksii Shliama on 1/27/15.
 */
public class BioEdit extends EuclidActivity2 {
    Patient p; //= new Patient();
    Context c;
    int id;

    private LinearLayout parentLinearLayout;
    private LinearLayout contactList;
    private LinearLayout foodList;
    private LinearLayout activityList;

    private WeekdaysPicker wd;

    Button bio, details;
    Uri selectedImage;
    String photo;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        c = getApplicationContext();
        id = getIntent().getIntExtra("PATIENT_ID", -1);
        p = new Patient(c, id);
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_bio_edit);

        System.out.println("Test A");

        //super.mState = EuclidState.Opening;
        Map<String, Object> profileMap;
        profileMap = new HashMap<>();
        profileMap.put(EuclidListAdapter.KEY_AVATAR, p.getPatientPicPath());
        profileMap.put(EuclidListAdapter.KEY_NAME, p.getPatientName());
        profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, "Age " + p.getPatientAge());
        profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, p.getPatientDesc());

        bio = (Button)findViewById(R.id.toolbar_profile_bio);
        details = (Button)findViewById(R.id.toolbar_profile_details);

        /*bio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Bio clicked.");
                setContentView(R.layout.activity_euclid2);

                bio = (Button)findViewById(R.id.toolbar_profile_bio);
                details = (Button)findViewById(R.id.toolbar_profile_details);
            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Details clicked.");
                setContentView(R.layout.activity_euclid3);

                bio = (Button)findViewById(R.id.toolbar_profile_bio2);
                details = (Button)findViewById(R.id.toolbar_profile_details2);
            }
        });*/

        if (mOverlayListItemView == null) {
            mOverlayListItemView = getLayoutInflater().inflate(R.layout.overlay_list_item, mWrapper, false);
        } else {
            mWrapper.removeView(mOverlayListItemView);
            mOverlayListItemView = getLayoutInflater().inflate(R.layout.overlay_list_item, mWrapper, false);
        }

        //mOverlayListItemView = getLayoutInflater().inflate(R.layout.overlay_list_item, mWrapper, false);

        mOverlayListItemView.findViewById(R.id.view_avatar_overlay).setBackground(sOverlayShape);

        //Picasso.with(BioNewActivity.this).load(Uri.parse((String) profileMap.get(EuclidListAdapter.KEY_AVATAR)))
        //.resize(sScreenWidth, sProfileImageHeight).centerCrop()
        //.placeholder(R.color.blue)
        //.into((ImageView) findViewById(R.id.image_view_reveal_avatar));
        Picasso.with(BioEdit.this).load(Uri.parse((String) profileMap.get(EuclidListAdapter.KEY_AVATAR)))
                .resize(sScreenWidth, sProfileImageHeight).centerCrop()
                .placeholder(R.color.blue)
                .into((ImageView) findViewById(R.id.image_view_avatar));

        ((TextView) mOverlayListItemView.findViewById(R.id.text_view_name)).setText((String) profileMap.get(EuclidListAdapter.KEY_NAME));
        ((TextView) mOverlayListItemView.findViewById(R.id.text_view_description)).setText((String) profileMap.get(EuclidListAdapter.KEY_DESCRIPTION_SHORT));
        setProfileDetailsInfo(profileMap);

    }

    @Override
    protected BaseAdapter getAdapter() {
        Map<String, Object> profileMap;
        List<Map<String, Object>> profilesList = new ArrayList<>();

        System.out.println("Test D");

        /*ArrayList<String> avatars = new ArrayList<>();
        for (String s : p.getListPics()) {
            System.out.println("Test B");
            avatars.add(s);
        }
        ArrayList<String> names = new ArrayList<>();
        for (String s : p.getListNames()) {
            System.out.println("Test C");
            names.add(s);
        }
        ArrayList<String> ages = new ArrayList<>();
        for (String s : p.getListAges()) {
            System.out.println("Test C");
            ages.add(s);
        }*/

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
            case R.id.image_view_avatar:
                Intent pickPhoto = new Intent(Intent.ACTION_OPEN_DOCUMENT,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void refreshBio() {
        mWrapper = (RelativeLayout) findViewById(R.id.wrapper);
        //mListView = (ListView) findViewById(R.id.list_view);
        mToolbar = (FrameLayout) findViewById(R.id.toolbar_list);
        mToolbarProfile = (RelativeLayout) findViewById(R.id.toolbar_profile);
        mProfileDetails = (LinearLayout) findViewById(R.id.wrapper_profile_details);
        mTextViewProfileName = (TextView) findViewById(R.id.text_view_profile_name);
        mTextViewProfileDescription = (TextView) findViewById(R.id.text_view_profile_description);
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

        //mOverlayListItemView = getLayoutInflater().inflate(R.layout.overlay_list_item, mWrapper, false);

        mOverlayListItemView.findViewById(R.id.view_avatar_overlay).setBackground(sOverlayShape);

        try{
            Picasso.with(BioEdit.this).load(Uri.parse((String) profileMap.get(EuclidListAdapter.KEY_AVATAR)))
                    .resize(sScreenWidth, sProfileImageHeight).centerCrop()
                    .placeholder(R.color.blue)
                    .into((ImageView) findViewById(R.id.image_view_avatar));

            ((TextView) mOverlayListItemView.findViewById(R.id.text_view_name)).setText((String) profileMap.get(EuclidListAdapter.KEY_NAME));
            ((TextView) mOverlayListItemView.findViewById(R.id.text_view_description)).setText((String) profileMap.get(EuclidListAdapter.KEY_DESCRIPTION_SHORT));
            setProfileDetailsInfo(profileMap);
        } catch (Exception e) {

        }
    }

    public void refreshDetails() {
        mWrapper = (RelativeLayout) findViewById(R.id.wrapper);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);
        //mListView = (ListView) findViewById(R.id.list_view);
        mToolbar = (FrameLayout) findViewById(R.id.toolbar_list);
        mToolbarProfile = (RelativeLayout) findViewById(R.id.toolbar_profile);
        mProfileDetails = (LinearLayout) findViewById(R.id.wrapper_profile_details);
        mTextViewDetailsSex = (TextView) findViewById(R.id.details_sex);
        mTextViewDetailsEmergencyNum = (TextView) findViewById(R.id.details_emergency_number);
        mButtonProfile = findViewById(R.id.button_profile);

        mTextViewDetailsSex.setText(p.getSex());
        mTextViewDetailsEmergencyNum.setText(p.getEmergencyNum());

        contactList = (LinearLayout) findViewById(R.id.contact_list);
        foodList = (LinearLayout) findViewById(R.id.food_list);
        activityList = (LinearLayout) findViewById(R.id.activity_list);

        wd = (WeekdaysPicker)findViewById(R.id.pat_weekdays);

        for(String s : p.getContacts()) {
            addField(R.layout.bio_field_display, s, s, contactList);
        }

        for(String s : p.getPrefFood()) {
            addField(R.layout.bio_field_display, s, "AAA", foodList);
            addField(R.layout.bio_field_display, s, "AAA", foodList);
            addField(R.layout.bio_field_display, s, "AAA", foodList);
            addField(R.layout.bio_field_display, s, "AAA", foodList);
            addField(R.layout.bio_field_display, s, "AAA", foodList);
            addField(R.layout.bio_field_display, s, "AAA", foodList);
            addField(R.layout.bio_field_display, s, "AAA", foodList);

        }

        for(String s : p.getPrefAct()) {
            addField(R.layout.bio_field_display, s, "BBB", activityList);
            addField(R.layout.bio_field_display, s, "BBB", activityList);
            addField(R.layout.bio_field_display, s, "BBB", activityList);
            addField(R.layout.bio_field_display, s, "BBB", activityList);
            addField(R.layout.bio_field_display, s, "BBB", activityList);
            addField(R.layout.bio_field_display, s, "BBB", activityList);
            addField(R.layout.bio_field_display, s, "BBB", activityList);
            addField(R.layout.bio_field_display, s, "BBB", activityList);

        }

        wd.setSelectedDays(p.getWeeklySchedule());
    }

    public void onAddField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.bio_field_display, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);

        TextView edittext_var;
        edittext_var = (TextView) ((View) v.getParent()).findViewById(R.id.number_edit_text);
        //edittext_var.setText(p.);
    }

    public void addField(int id, String s1, String s2, LinearLayout ll) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(id, null);
        // Add the new row before the add field button.
        ll.addView(rowView, ll.getChildCount() - 1);

        TextView text_var1, text_var2;
        text_var1 = (TextView) (rowView.findViewById(R.id.bio_field_1));
        text_var2 = (TextView) (rowView.findViewById(R.id.bio_field_2));

        text_var1.setText(s1);
        text_var2.setText(s2);
    }

    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
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
