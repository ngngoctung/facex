package com.khtn.facexapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CreatedClassActivity extends AppCompatActivity {
    private ImageView ivBack;
    private TextView ManaName;
    private Spinner spnClasses;
    private Button displayBtn;
    String ClassName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_class);
        setTitileToolbar();
        getInfoManager();
        initUI();
        setSpiner();
        initListener();
    }

    private void setSpiner() {


        ArrayList<String> array = new ArrayList<>();
        array.add(" ");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Class");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    // TODO: handle the post
//                    Toast.makeText(RollCallActivity.this," Name of class = " + ,Toast.LENGTH_LONG).show();
                    array.add(dataSnapshot.getKey());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayAdapter adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, array);
        spnClasses.setAdapter(adapter);
        //Xử lí sự kiện khi click vào item:
        spnClasses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                ClassName = array.get(position);
                Request request = new Request();
                request.setChooseClass(ClassName);
//                Toast.makeText(RollCallActivity.this,"you chose class: " + request.getChooseClass(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
        displayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoStudentCreatedClassActivity();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoManagerClassActivity();
            }
        });
    }

    private void setTitileToolbar() {
        getSupportActionBar().hide();
    }

    private void initUI() {
        ManaName = findViewById(R.id.tvMngName);
        spnClasses = findViewById(R.id.spnCreatedClass);
        displayBtn = findViewById(R.id.displayButton);
        ivBack = findViewById(R.id.ivBackCreatedClass);
    }

    private void gotoStudentCreatedClassActivity() {
        Intent intent = new Intent(this,StudentCreatedClassActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    private void gotoManagerClassActivity() {
        Intent intent = new Intent(this,ManagerClassActivity.class);
        startActivity(intent);
        finishAffinity();
    }



}