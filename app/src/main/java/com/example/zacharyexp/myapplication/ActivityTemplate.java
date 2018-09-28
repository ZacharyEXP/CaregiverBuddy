package com.example.zacharyexp.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ActivityTemplate extends Activity {
    private RecyclerView rv;

    String[] myDataset = new String[]{"1", "2", "3", "4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_template);
        setContentView(R.layout.my_recycler_view);

        rv = (RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        // use a linear layout manager

        // specify an adapter (see also next example)
        ArrayList<String> persons = new ArrayList<>(Arrays.asList("London", "Tokyo", "New York", "London2", "Tokyo2", "New York2", "London3", "Tokyo3"));
        MyAdapter mAdapter = new MyAdapter(persons);
        rv.setAdapter(mAdapter);
    }
    // ...
}