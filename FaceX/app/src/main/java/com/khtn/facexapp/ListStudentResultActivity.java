package com.khtn.facexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.khtn.facexapp.adapter.StudentAdapter;
import com.khtn.facexapp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class ListStudentResultActivity extends AppCompatActivity {
    private ImageView ivBackRollCall;
    private Button btnExport;
    private Button btnFinish;
    private StudentAdapter adapter;
    private List<Student> list;

    Request request = new Request();


    public ListStudentResultActivity() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student_result);

        setToolBarTitle();
        initListView();
        initUI();
        initListener();
    }

    private void initListView() {
        ListView listView = findViewById(R.id.lvStudents);
        listView.setAdapter(adapter);
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

    }

    private void setToolBarTitle() {
        getSupportActionBar().hide();
    }
}