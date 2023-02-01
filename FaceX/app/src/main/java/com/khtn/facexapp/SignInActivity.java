package com.khtn.facexapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignInActivity extends AppCompatActivity {
    private EditText edtUser, edtPassWord;
    private TextView tvSignUp, tvForgotPass;
    private Button btnSignIn;
    public static final String TAG = SignInActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setTitileToolbar();
        initUI();
        initListener();
    }

    private void setTitileToolbar() {
        getSupportActionBar().hide();
    }

    private void initListener() {
        //Sign In
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickSingIn();
            }
        });
        //Sign Up
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSignUpActivity();
            }
        });

        //Forgot Pass
        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoForgotPassActivity();
            }
        });

    }

    private void gotoForgotPassActivity() {
        Intent intent = new Intent(this, ForgotPassActivity.class);
        startActivity(intent);
    }

    private void onclickSingIn() {
        String strUser = edtUser.getText().toString().trim();
        String strPass = edtPassWord.getText().toString().trim();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(strUser, strPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                    //check account is manager of teacher
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                            DatabaseReference dataRef1 = database.getReference("users/" + firebaseUser.getUid() + "/role");
                            dataRef1.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String role = dataSnapshot.getValue(String.class);
                                    Toast.makeText(SignInActivity.this, "role: " + role, Toast.LENGTH_SHORT).show();
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

//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Username or PassWord error",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void gotoManagerActivity() {
        Intent intent = new Intent(this,ManagerActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    private void gotoRollCallActivity() {
        Intent intent = new Intent(this,RollCallActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    private void gotoSignUpActivity() {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    private void initUI() {
        edtUser = findViewById(R.id.username_id);
        edtPassWord = findViewById(R.id.password_id);
        tvForgotPass = findViewById(R.id.tvForgotPass);
        tvSignUp = findViewById(R.id.tvSignUp);
        btnSignIn = findViewById(R.id.login_btn);
    }


}