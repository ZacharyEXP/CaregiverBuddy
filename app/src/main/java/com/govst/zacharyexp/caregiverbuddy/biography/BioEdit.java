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
import android.widget.Button;
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

    ArrayList<EditText> contacts, foods, activities;
    ArrayList<ImageButton> addC, addF, addA, deleteC, deleteF, deleteA;

    private WeekdaysPicker wd;

    Button bio, details;
    Uri selectedImage;
    String photo;

    Map<String, Object> profileMap;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        c = getApplicationContext();
        id = getIntent().getIntExtra("PATIENT_ID", -1);
        p = new Patient(c, id);
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_bio_edit);

        mTextViewProfileName = (TextView) findViewById(R.id.text_view_profile_name);
        mTextViewProfileDescription = (TextView) findViewById(R.id.text_view_profile_description);

        System.out.println("Test A");

        //super.mState = EuclidState.Opening;
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
        }

        //((TextView) mOverlayListItemView.findViewById(R.id.text_view_name)).setText((String) profileMap.get(EuclidListAdapter.KEY_NAME));
        //((TextView) mOverlayListItemView.findViewById(R.id.text_view_description)).setText((String) profileMap.get(EuclidListAdapter.KEY_DESCRIPTION_SHORT));

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

        System.out.println("Test D");

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

                p.setSex(mTextViewDetailsSex.getText().toString());
                p.setEmergencyNum(mTextViewDetailsEmergencyNum.getText().toString());
                p.setWeeklySchedule(wd.getSelectedDays());
                p.setPatientName(mTextViewProfileName.getText().toString());
                p.setPatientDesc(mTextViewProfileDescription.getText().toString());
                p.setPatientPicPath(photo);


                for(EditText t : contacts) {
                    temp1.add(t.getText().toString());
                    System.out.println("Test AA2");
                    System.out.println(t.getText().toString());
                }
                System.out.println(temp1);
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

                Intent intent = new Intent(this, PatientSelectNew.class);
                startActivity(intent);
                break;
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
            addField(R.layout.bio_field_entry, s, contactList);
        }

        for(String s : p.getPrefFood()) {
            addField(R.layout.bio_field_entry, s, foodList);
        }

        for(String s : p.getPrefAct()) {
            addField(R.layout.bio_field_entry, s, activityList);
        }

        wd.setSelectedDays(p.getWeeklySchedule());
    }

    public void onAddField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.bio_field_display, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);

        TextView edittext_var;
        //edittext_var = (TextView) ((View) v.getParent()).findViewById(R.id.number_edit_text);
        //edittext_var.setText(p.);
    }

    public void addField(int id, String s1, LinearLayout ll) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(id, null);
        // Add the new row before the add field button.
        ll.addView(rowView, ll.getChildCount() - 1);

        EditText text_var1;
        ImageButton add, delete;
        text_var1 = (EditText) (rowView.findViewById(R.id.bio_field_1));
        text_var1.setText(s1);

        add = (ImageButton) (rowView.findViewById(R.id.bio_add));
        delete = (ImageButton) (rowView.findViewById(R.id.bio_delete));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addField(id, "", ll);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onDelete(rowView);
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
                System.out.println("Test AA");
                //addC.add(add);
                //deleteC.add(delete);
                break;
            case R.id.food_list:
                foods.add(text_var1);
                //addF.add(add);
                //deleteF.add(delete);
                break;
            case R.id.activity_list:
                activities.add(text_var1);
                //addA.add(add);
                //deleteA.add(delete);
                break;
        }
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
