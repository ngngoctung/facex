package com.khtn.facexapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StudentCreatedClassActivity extends AppCompatActivity {
    private ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_created_class);

        setToolBarTitle();
//        initListView();
        initUI();
        initListener();
    }

    private void initListener(){
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCreatedClassActivity();
            }
        });
    }

    private void setToolBarTitle() {
        getSupportActionBar().hide();
    }

    private void initUI() {
        ivBack = findViewById(R.id.ivBackListStudent);

    }

    private void gotoCreatedClassActivity() {
        Intent intent = new Intent(this, CreatedClassActivity.class);
        startActivity(intent);
    }

}