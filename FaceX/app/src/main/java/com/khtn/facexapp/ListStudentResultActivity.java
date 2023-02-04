package com.khtn.facexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.khtn.facexapp.adapter.StudentsAdapter;
import com.khtn.facexapp.model.Student;
import com.khtn.facexapp.adapter.StudentsAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListStudentResultActivity extends AppCompatActivity {
    private ImageView ivBackRollCall;
    private Button btnExport;
    private Button btnFinish;
    RecyclerView rcvStudent;
    private StudentsAdapter mStudentsAdapter;
    private List<Student> mListStudent;

    Request request = new Request();

    private  DatabaseReference  databaseReference;


    public ListStudentResultActivity() {
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

                mStudentsAdapter = new StudentsAdapter(ListStudentResultActivity.this, mListStudent);
                rcvStudent.setAdapter(mStudentsAdapter);
                mStudentsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ListStudentResultActivity.this, "Get list failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student_result);

        setToolBarTitle();
        initUI();
        initListener();

        getListStudentFromFirebase();
    }


    private void initListener() {
        ivBackRollCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListStudentResultActivity.this, RollCallStudentActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoRollCallActivity();
            }
        });

        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ListStudentResultActivity.this, "Export File Success", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void gotoRollCallActivity() {
        Intent intent = new Intent(this, RollCallActivity.class);
        startActivity(intent);
    }

    private void initUI() {
        btnExport = findViewById(R.id.btnExport);
        btnFinish = findViewById(R.id.btnFinish);
        ivBackRollCall = findViewById(R.id.ivBackRollCall);
        rcvStudent = findViewById(R.id.lvStudents);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvStudent.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvStudent.addItemDecoration(dividerItemDecoration);
        mListStudent = new ArrayList<>();
    }

    private void setToolBarTitle() {
        getSupportActionBar().hide();
    }
}