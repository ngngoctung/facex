package com.example.facexapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class TeacherActivity extends AppCompatActivity {
    private TextView tvSignout;
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
    }

    private void setTitleToolBar() {
        getSupportActionBar().hide();
    }
}