package com.khtn.facexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class RollCallStudentActivity extends AppCompatActivity {
    private ImageView ivBack;
    private Button btnCompleteRollCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_call_student);

        setToolBarTitle();
        initUI();
        initListener();
    }

    private void initUI() {
        btnCompleteRollCall = findViewById(R.id.btnCompleteRollCall);
        ivBack = findViewById(R.id.ivBack);
    }

    private void initListener() {
        btnCompleteRollCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoListStudentResultActivity();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RollCallStudentActivity.this,RollCallActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void gotoListStudentResultActivity() {
        Intent intent = new Intent(this,ListStudentResultActivity.class);
        startActivity(intent);
    }

    private void setToolBarTitle() {
        getSupportActionBar().hide();
    }
}