package com.example.sql;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Details extends AppCompatActivity {

    ListView listView;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        listView = findViewById(R.id.listView);
        db = new DB(this);

        Cursor cursor = db.getAllData();
        ArrayList<String> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            list.add("ID: " + cursor.getInt(0) +
                    "\nName: " + cursor.getString(1) +
                    "\nLocation: " + cursor.getString(2) +
                    "\nDesignation: " + cursor.getString(3));
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
    }
} 
