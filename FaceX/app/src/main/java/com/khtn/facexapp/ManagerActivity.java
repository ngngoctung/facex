package com.khtn.facexapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManagerActivity extends AppCompatActivity {
    private Button manaClassBtn,exportListBtn, resetPassBtn;
    private TextView ManaName,LogOutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        setTitileToolbar();
        getInfoManager();
        initUI();
        initListener();
    }

    private void getInfoManager() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        DatabaseReference dataRef2 = database.getReference("users/" + firebaseUser.getUid() + "/name");
        dataRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);
                ManaName.setText(name.trim());
                Toast.makeText(ManagerActivity.this, "Hi : " + name, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // handle the error
            }
        });
    }

    private void setTitileToolbar() {
        getSupportActionBar().hide();
    }
    private void initUI() {
        ManaName = findViewById(R.id.tvManaName);
        LogOutBtn = findViewById(R.id.LogOutMana);
        manaClassBtn = findViewById(R.id.ManagerClassBtn);
        exportListBtn = findViewById(R.id.exportBtn);
        resetPassBtn = findViewById(R.id.resetPassBtn);
    }

    private void initListener() {
        manaClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoManaClassActivity();
            }
        });
        exportListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoExtractClassActivity();
            }
        });
        resetPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoResetPasswordActivity();
            }
        });
        //       LogOut
        LogOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                gotoSignInActivity();
            }
        });
    }

    private void gotoResetPasswordActivity() {
        Intent intent = new Intent(this, TestMainActivity.class);
        startActivity(intent);
    }

    private void gotoExtractClassActivity() {
        Intent intent = new Intent(this, ExtractClassActivity.class);
        startActivity(intent);
    }

    private void gotoManaClassActivity() {
        Intent intent = new Intent(this, ManagerClassActivity.class);
        startActivity(intent);
    }

    private void gotoSignInActivity() {
        Intent intent = new Intent(this,SignInActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}