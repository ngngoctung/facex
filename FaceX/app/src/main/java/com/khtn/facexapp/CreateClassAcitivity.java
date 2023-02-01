package com.khtn.facexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateClassAcitivity extends AppCompatActivity {
    private ImageView backOfCreateNewClass;
    private Button addStudentBtn;
    private EditText className;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
        setTitileToolbar();
        initUI();
        initListener();
    }

    private void setTitileToolbar() {
        getSupportActionBar().hide();
    }
    private void initUI() {
        backOfCreateNewClass = findViewById(R.id.backOfCreateNewClass);
        addStudentBtn = findViewById(R.id.add_btn);
        className = findViewById(R.id.class_name_id);
    }
    private void initListener() {
        backOfCreateNewClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoManagerClassActivity();
            }
        });

        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameClass = className.getText().toString().trim();
                Request request = new Request();
                request.setChooseClass(nameClass);
                Toast.makeText(CreateClassAcitivity.this,nameClass, Toast.LENGTH_SHORT).show();
                gotoAddStudent();
            }
        });

    }

    private void gotoManagerClassActivity() {
        Intent intent = new Intent(this, ManagerClassActivity.class);
        startActivity(intent);
    }

    private void gotoAddStudent(){
        Intent intent = new Intent(this, AddStudent.class);
        startActivity(intent);
    }
}
