package com.khtn.facexapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.khtn.facexapp.model.Student;

public class AddStudent extends AppCompatActivity {
    private EditText name, id;
    private Button btnAddStudent;
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
        name = findViewById(R.id.nameStudent_id);
        id = findViewById(R.id.idStudent_id);
        btnAddStudent = findViewById(R.id.buttonAddStudent_id);
    }

    private void initListener() {
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameOfStudent = name.getText().toString().trim();
                int idOfStudent = Integer.parseInt(id.getText().toString().trim());
                Student student = new Student(nameOfStudent, idOfStudent);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Test");

                myRef.child(request.getChooseClass()).child(id.getText().toString().trim()).setValue(student);
            }
        });

    }

}