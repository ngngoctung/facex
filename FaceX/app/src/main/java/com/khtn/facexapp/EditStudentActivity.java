package com.khtn.facexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class EditStudentActivity extends AppCompatActivity {
    private ImageView backOfEditStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        setTitileToolbar();
        initUI();
        initListener();
    }

    private void setTitileToolbar() {
        getSupportActionBar().hide();
    }
    private void initUI() {
        backOfEditStudent = findViewById(R.id.backOfEditStudent);
    }
    private void initListener() {
        backOfEditStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoManagerClassActivity();
            }
        });
    }

    private void gotoManagerClassActivity() {
        Intent intent = new Intent(this, ManagerClassActivity.class);
        startActivity(intent);
    }
}
