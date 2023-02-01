package com.khtn.facexapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ManagerClassActivity extends AppCompatActivity {
    private Button classCreatedBtn,createNewClassBtn,editInfoBtn;
    private ImageView backOfClassMana;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_class);
        setTitileToolbar();
        initUI();
        initListener();
    }

    private void setTitileToolbar() {
        getSupportActionBar().hide();
    }
    private void initUI() {
        classCreatedBtn = findViewById(R.id.classCreatedBtn);
        createNewClassBtn = findViewById(R.id.createNewClassBtn);
        editInfoBtn = findViewById(R.id.editInfoBtn);
        backOfClassMana = findViewById(R.id.backOfClassMana);
    }
    private void initListener() {
        //        click classCreatedBtn
        classCreatedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCtreatedClassActivity();
            }
        });
        //         click createnewClassBtn
        createNewClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCreateClassActivity();
            }
        });

        //        click editInfoBtn
        editInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoEditStudentActivity();
            }
        });
        //backtoManagerActivity
        backOfClassMana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoManagerActivity();
            }
        });
    }

    private void gotoEditStudentActivity() {
        Intent intent = new Intent(this, EditStudentActivity.class);
        startActivity(intent);
    }

    private void gotoCreateClassActivity() {
        Intent intent = new Intent(this, CreateClassAcitivity.class);
        startActivity(intent);
    }

    private void gotoManagerActivity() {
        Intent intent = new Intent(this, ManagerActivity.class);
        startActivity(intent);
    }

    private void gotoCtreatedClassActivity() {
        Intent intent = new Intent(this, CreatedClassActivity.class);
        startActivity(intent);
    }

}