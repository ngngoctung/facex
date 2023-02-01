package com.khtn.facexapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.khtn.facexapp.adapter.StudentAdapter;
import com.khtn.facexapp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentCreatedClassActivity extends AppCompatActivity {
    private ImageView ivBack;
    private StudentAdapter adapter;
    private List<Student> list;

    Request request = new Request();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_created_class);

        setToolBarTitle();
        initListView();
        initUI();
        initListener();
    }

    public StudentCreatedClassActivity() {
        list = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Class").child(request.getChooseClass());

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Student student = dataSnapshot.getValue(Student.class);
                    list.add(student);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new StudentAdapter(this, list);
    }

    private void initListView() {
        ListView listView = findViewById(R.id.listCreatedStudents);
        listView.setAdapter(adapter);
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