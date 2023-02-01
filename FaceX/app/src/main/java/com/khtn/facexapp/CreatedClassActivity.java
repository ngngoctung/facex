package com.khtn.facexapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreatedClassActivity extends AppCompatActivity {
    private TextView ManaName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_class);
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
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // handle the error
            }
        });
    }

    private void initListener() {

    }

    private void setTitileToolbar() {
        getSupportActionBar().hide();
    }

    private void initUI() {
        ManaName = findViewById(R.id.tvMngName);
    }
}