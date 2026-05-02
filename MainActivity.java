package com.example.sql;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    EditText id, name, location, designation;
    Button insertBtn, viewBtn, updateBtn, deleteBtn;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = findViewById(R.id.txtId);
        name = findViewById(R.id.txtName);
        location = findViewById(R.id.txtLocation);
        designation = findViewById(R.id.txtDesignation);
        insertBtn = findViewById(R.id.btnInsert);
        viewBtn = findViewById(R.id.btnView);
        updateBtn = findViewById(R.id.btnUpdate);
        deleteBtn = findViewById(R.id.btnDelete);
        db = new DB(this);
        // INSERT
        insertBtn.setOnClickListener(v -> {
            boolean inserted = db.insertData(
                    name.getText().toString(),
                    location.getText().toString(),
                    designation.getText().toString()
            );
            Toast.makeText(this,
                    inserted ? "Inserted Successfully" : "Insert Failed",
                    Toast.LENGTH_SHORT).show();
        });
        // VIEW
        viewBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Details.class));
        });

        // UPDATE (FIXED)
        updateBtn.setOnClickListener(v -> {

            if (id.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter ID to Update", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean updated = db.updateData(
                    id.getText().toString(),
                    name.getText().toString(),
                    location.getText().toString(),
                    designation.getText().toString()
            );

            Toast.makeText(this,
                    updated ? "Updated Successfully" : "No Record Found",
                    Toast.LENGTH_SHORT).show();
        });

        // DELETE (FIXED)
        deleteBtn.setOnClickListener(v -> {

            if (id.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter ID to Delete", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean deleted = db.deleteData(id.getText().toString());

            Toast.makeText(this,
                    deleted ? "Deleted Successfully" : "No Record Found",
                    Toast.LENGTH_SHORT).show();
        });
    }
} 
