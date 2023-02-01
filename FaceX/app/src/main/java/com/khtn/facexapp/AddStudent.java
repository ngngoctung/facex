package com.khtn.facexapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.khtn.facexapp.model.Student;

public class AddStudent extends AppCompatActivity {
    private ImageView iBack;
    private EditText name, id;
    private Button btnAddStudent, btnNext;
    Request request = new Request();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        setTitileToolbar();
        initUI();
        initListener();
    }
    private void setTitileToolbar() {
        getSupportActionBar().hide();
    }

    private void initUI() {
        name = findViewById(R.id.student_name);
        id = findViewById(R.id.student_id);
        btnAddStudent = findViewById(R.id.add_btn);
        btnNext = findViewById(R.id.next_btn);
        iBack = findViewById(R.id.ivBack);
    }

    private void initListener() {
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameOfStudent = name.getText().toString().trim();
                int idOfStudent = Integer.parseInt(id.getText().toString().trim());
                if(nameOfStudent.isEmpty())
                {
                    Toast.makeText(AddStudent.this,"Name/ID empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Student student = new Student(nameOfStudent, idOfStudent);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Class");

                    myRef.child(request.getChooseClass()).child(id.getText().toString().trim()).setValue(student);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAddStudent();
            }
        });

        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoManagerClassActivity();
            }
        });

    }



    private void gotoAddStudent(){
        Intent intent = new Intent(this, AddStudent.class);
        startActivity(intent);
    }
    private void gotoManagerClassActivity() {
        Intent intent = new Intent(this, ManagerClassActivity.class);
        startActivity(intent);
    }
}