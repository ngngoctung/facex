package com.khtn.facexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity {
    private EditText edtEmail;
    private Button btnSubmit, btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        setTitileToolbar();
        initUI();
        initListener();
    }

    private void initListener() {
        // SEND MAIL TO RESET PASS
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                String emailAddress = edtEmail.getText().toString().trim();
                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotPassActivity.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(ForgotPassActivity.this, "Reset Password Fail", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        //SIGN IN AGAIN
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPassActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initUI() {
        edtEmail = findViewById(R.id.edtEmail);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSignIn = findViewById(R.id.btnSignInAgain);
    }

    private void setTitileToolbar() {
        getSupportActionBar().hide();
    }
}