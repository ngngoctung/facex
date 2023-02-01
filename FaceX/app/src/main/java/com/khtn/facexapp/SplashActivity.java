package com.khtn.facexapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setTitleToolBar();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoLoginActivity();
            }
        },2000);
    }

    private void setTitleToolBar() {
        getSupportActionBar().hide();
    }

    private void gotoLoginActivity() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser == null) {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        } else {
            //get role from database and compare to return teacher of manager
            DatabaseReference databaseReference = database.getReference("users/" + firebaseUser.getUid() + "/role");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String role = dataSnapshot.getValue(String.class);
                    if (role.equals("teacher")) {
                        // go to the teacher activity interface
                        gotoRollCallActivity();
                    } else if (role.equals("manager")) {
                        // go to the manager activity interface
                        gotoManagerActivity();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // handle the error
                }
            });
        }
        finish();
    }

    private void gotoRollCallActivity() {
        Intent intent = new Intent(this,RollCallActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    private void gotoManagerActivity() {
        Intent intent = new Intent(this,ManagerActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}