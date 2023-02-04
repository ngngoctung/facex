package com.khtn.facexapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.khtn.facexapp.model.Student;

import com.squareup.picasso.Picasso;

public class AddStudent extends AppCompatActivity {
    private ImageView iBack;
    ImageView imageView;
    private EditText name, id;
    private Button btnAddStudent, btnNext, btnUpload;
    Request request = new Request();

    private static final int PICK_IMAGE_REQUEST = 1;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        setTitileToolbar();
        initUI();
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Class").child(request.getChooseClass());
        initListener();


    }

    private void setTitileToolbar() {
        getSupportActionBar().hide();
    }

    private void initUI() {
        name = findViewById(R.id.student_name);
        id = findViewById(R.id.student_id);
        btnAddStudent = findViewById(R.id.add_btn);
        btnNext = findViewById(R.id.next_btn);
        iBack = findViewById(R.id.ivBack);
        btnUpload = findViewById(R.id.upload_btn);
        imageView = findViewById(R.id.image_view_avatar);

    }

    private void initListener() {
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                String nameOfStudent = name.getText().toString().trim();
                int idOfStudent = Integer.parseInt(id.getText().toString().trim());
                if(nameOfStudent.isEmpty())
                {
                    Toast.makeText(AddStudent.this,"Name/ID empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Student student = new Student(nameOfStudent, idOfStudent);
                    Student student = new Student(nameOfStudent, idOfStudent);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Class");

                    myRef.child(request.getChooseClass()).child(id.getText().toString().trim()).setValue(student);
                }
                */
                uploadFile();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAddStudent();
            }
        });

        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoManagerClassActivity();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenFileChooser();
            }
        });

    }

    private void OpenFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(imageView);
        }
    }

    private void gotoAddStudent() {
        Intent intent = new Intent(this, AddStudent.class);
        startActivity(intent);
    }

    private void gotoManagerClassActivity() {
        Intent intent = new Intent(this, ManagerClassActivity.class);
        startActivity(intent);
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        String nameOfStudent = name.getText().toString().trim();
        int idOfStudent = Integer.parseInt(id.getText().toString().trim());

        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(AddStudent.this, "Upload successful", Toast.LENGTH_LONG).show();
                    Student student = new Student(nameOfStudent, idOfStudent, taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
                    String uploadID = mDatabaseRef.push().getKey();


                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Class").child(request.getChooseClass());

                    myRef.child(id.getText().toString().trim()).setValue(student);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddStudent.this, "Upload Failed", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}