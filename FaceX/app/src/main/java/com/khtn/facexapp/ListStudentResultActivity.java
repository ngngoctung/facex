package com.khtn.facexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

    public ListStudentResultActivity() {
        list = new ArrayList<>();

        list.add(new Student(1, R.drawable.person_icon, "Phan Tân", "MSSV: 19200473"));
        list.add(new Student(2, R.drawable.person_icon, "Nguyễn Văn A ", "MSSV: 19200473"));
        list.add(new Student(3, R.drawable.person_icon, "Nguyễn Văn B", "MSSV: 19200234"));
        list.add(new Student(4, R.drawable.person_icon, "Nguyễn Văn C", "MSSV: 19200342"));
        list.add(new Student(5, R.drawable.person_icon, "Nguyễn Văn D", "MSSV: 19200234"));
        list.add(new Student(6, R.drawable.person_icon, "Nguyễn Văn E", "MSSV: 19200345"));
        list.add(new Student(7, R.drawable.person_icon, "Nguyễn Văn F", "MSSV: 19200542"));
        list.add(new Student(8, R.drawable.person_icon, "Nguyễn Văn G", "MSSV: 19200656"));
        list.add(new Student(9, R.drawable.person_icon, "Nguyễn Văn H", "MSSV: 19200676"));
        list.add(new Student(10, R.drawable.person_icon, "Nguyễn Văn P", "MSSV: 19200342"));
        list.add(new Student(11, R.drawable.person_icon, "Nguyễn Văn K", "MSSV: 19200456"));
        list.add(new Student(12, R.drawable.person_icon, "Nguyễn Văn T", "MSSV: 19200786"));
//
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