package com.khtn.facexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class RollCallActivity extends AppCompatActivity {
    private TextView LogOut;
    Intent intent = getIntent();
    private Spinner spnClasses;
    private Button btnAttendance;
    String ClassName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_call);

        setToolbarTitle();
        initUI();
        setSpiner();
        initListener();
    }

    private void setToolbarTitle() {
        getSupportActionBar().hide();
    }

    private void initListener() {
//       LogOut
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                gotoSignInActivity();
            }
        });
//        Roll Call
        btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RollCallActivity.this, RollCallStudentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void gotoSignInActivity() {
            Intent intent = new Intent(this,SignInActivity.class);
            startActivity(intent);
            finishAffinity();
    }

    private void setSpiner() {
        ArrayList<String> array = new ArrayList<>();
        array.add("19DTV3A");
        array.add("19DTV3B");
        array.add("19DTV3C");
        array.add("CNTT1");
        array.add("CNTT2");

        ArrayAdapter adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, array);
        spnClasses.setAdapter(adapter);
//Xử lí sự kiện khi click vào item:
        spnClasses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                ClassName = array.get(position);
//                Intent intent1 = new Intent(inputrollcallActivity.this, rollcallActivity.class);
//                Bundle bundle1 = new Bundle();
//                bundle1.putString("classes", ClassName);
//                intent1.putExtra("data",bundle1);
//                startActivity(intent1);
                Toast.makeText(RollCallActivity.this,"you chose class: " + ClassName, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initUI() {
        spnClasses = findViewById(R.id.spnChooseClass);
        btnAttendance = findViewById(R.id.btnAttendance);
        LogOut = findViewById(R.id.tvLogOut);
    }

}