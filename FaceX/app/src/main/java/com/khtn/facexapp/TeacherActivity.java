package com.khtn.facexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class TeacherActivity extends AppCompatActivity {
    private TextView tvSignout;
    private Button btnRollCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        setTitleToolBar();
        initUI();
        initListener();
    }

    private void initListener() {
        tvSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignOut();
            }
        });

        btnRollCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoRollCallActivity();
            }
        });
    }

    private void gotoRollCallActivity() {
        Intent intent = new Intent(this, RollCallActivity.class);
        startActivity(intent);
    }

    private void onClickSignOut() {
        FirebaseAuth.getInstance().signOut();
        gotoSignInActivity();

    }

    private void gotoSignInActivity() {
        Intent intent = new Intent(this,SignInActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    private void initUI() {
        tvSignout = findViewById(R.id.tvSignOut);
        btnRollCall = findViewById(R.id.btnRollCall);
    }

    private void setTitleToolBar() {
        getSupportActionBar().hide();
    }
}