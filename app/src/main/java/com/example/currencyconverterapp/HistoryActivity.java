package com.example.currencyconverterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView historyRecView;
    private historyRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        adapter = new historyRecViewAdapter(this);
        historyRecView = findViewById(R.id.historyRecView);

        historyRecView.setAdapter(adapter);
        historyRecView.setLayoutManager(new GridLayoutManager(this,2));

        ArrayList<Conversion> conversions = new ArrayList<>();
        conversions.add(new Conversion("USD","CAD",1.0,1.0,1.0));
        adapter.setConversions(conversions);
    }
}