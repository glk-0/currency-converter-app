package com.example.currencyconverterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView historyRecView;
    private historyRecViewAdapter adapter;
    private DataBaseHelper dataBaseHelper;
    private List<Conversion> conversions ;
    private Button btnClearAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        adapter = new historyRecViewAdapter(this);
        historyRecView = findViewById(R.id.historyRecView);

        historyRecView.setAdapter(adapter);
        historyRecView.setLayoutManager(new GridLayoutManager(this,1));

        dataBaseHelper= MainActivity.getDataBaseHelper();

        conversions =dataBaseHelper.getAllConversions() ;
        adapter.setConversions(conversions);

        btnClearAll= findViewById(R.id.btnClearAll);
        
        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cleared = dataBaseHelper.clearAll();
                if (cleared) {
                    try {
                        conversions = dataBaseHelper.getAllConversions();
                        adapter.setConversions(conversions);
                        Toast.makeText(HistoryActivity.this, "Successfully cleared history!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(HistoryActivity.this, "Something went wrong while retrieving data!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(HistoryActivity.this, "Failed to clear history!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}