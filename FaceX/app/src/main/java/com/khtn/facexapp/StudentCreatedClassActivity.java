package com.khtn.facexapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.khtn.facexapp.adapter.StudentsAdapter;
import com.khtn.facexapp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentCreatedClassActivity extends AppCompatActivity {
    private ImageView ivBack;
    RecyclerView rcvStudent;
    private StudentsAdapter mStudentsAdapter;
    private List<Student> mListStudent;

    Request request = new Request();

    private  DatabaseReference  databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_created_class);

        setToolBarTitle();
        initUI();
        initListener();

        getListStudentFromFirebase();
    }

    private  void getListStudentFromFirebase(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Class").child(request.getChooseClass());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Student student = dataSnapshot.getValue(Student.class);
                    mListStudent.add(student);
                }

                mStudentsAdapter = new StudentsAdapter(StudentCreatedClassActivity.this, mListStudent);
                rcvStudent.setAdapter(mStudentsAdapter);
                mStudentsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentCreatedClassActivity.this, "Get list failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUI() {
        ivBack = findViewById(R.id.ivBackListStudent);
        rcvStudent = findViewById(R.id.listCreatedStudents);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvStudent.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvStudent.addItemDecoration(dividerItemDecoration);
        mListStudent = new ArrayList<>();
    }

    private void initListener(){
       ivBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               gotoCreatedClassActivity();
           }
       });
    }
    private void gotoCreatedClassActivity() {
        Intent intent = new Intent(this, CreatedClassActivity.class);
        startActivity(intent);
    }
    private void setToolBarTitle() {
        getSupportActionBar().hide();
    }
}