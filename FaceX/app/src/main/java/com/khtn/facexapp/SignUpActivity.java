package com.khtn.facexapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private EditText edtName, edtEmail, edtPassWord, edtConfPassWord, edtPhoneNum;
    private Button btnSignUpLecturer, getBtnSignUpManager;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";
    public static final String TAG = SignInActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitleToolBar();

        initUI();
        initListener();
    }
    private void setTitleToolBar() {
        getSupportActionBar().hide();
    }
    private void initListener() {
        btnSignUpLecturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCreateTeacher();
            }
        });
    }

    private void onClickCreateTeacher() {
        String strName = edtName.getText().toString().trim();
        String strEmail = edtEmail.getText().toString().trim();
        String strPassWord = edtPassWord.getText().toString().trim();
        String strConfirPass = edtConfPassWord.getText().toString().trim();
        String strPhoneNum = edtPhoneNum.getText().toString().trim();

        if(strName.isEmpty())
        {
            edtName.setError("Empty");
        }
        else if(!strEmail.matches(emailPattern)) {
            edtEmail.setError("Invalid Email");
        }
        else if(strPhoneNum.isEmpty())
        {
            edtPhoneNum.setError("Empty");
        }
        else if (!strPassWord.equals(strConfirPass)) {
            edtConfPassWord.setError("Password Not Match");
        } else {

            FirebaseAuth mAuth = FirebaseAuth.getInstance();

            mAuth.createUserWithEmailAndPassword(strEmail, strPassWord)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "Sign Up Success");
                                gotoRollCallActivity();
                                finishAffinity();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "Sign Up Failure", task.getException());
                                Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void gotoRollCallActivity() {
        Intent intent = new Intent(this, RollCallActivity.class);
        startActivity(intent);
    }

    private void initUI() {
        edtName = findViewById(R.id.name_id);
        edtEmail = findViewById(R.id.email_id);
        edtPassWord = findViewById(R.id.password_id);
        edtConfPassWord = findViewById(R.id.confirmpassword_id);
        edtPhoneNum = findViewById(R.id.phone_id);
        getBtnSignUpManager = findViewById(R.id.manager_btn);
        btnSignUpLecturer = findViewById(R.id.lecturer_btn);
    }
}