package com.example.zacharyexp.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        String info = getIntent().getStringExtra("EXTRA_INFO");

        TextView name = (TextView)findViewById(R.id.patName);
        TextView age = (TextView)findViewById(R.id.patName);
        TextView desc = (TextView)findViewById(R.id.patDesc);
        System.out.println(info);
    }
}
