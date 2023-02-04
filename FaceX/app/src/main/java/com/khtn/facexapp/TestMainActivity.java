package com.khtn.facexapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class TestMainActivity extends AppCompatActivity {
    //UI Views
    private ImageView originalImage;
    private Button detectFaceBtn;
    private ImageView croppedImageIv;

    private static final String TAG = "FACE_DETECT_TAG";
    private static final int SCALING_FACTOR = 10;

    private FaceDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);

        //Initialize Face Detector
        FaceDetectorOptions realTimeFdo =
                new FaceDetectorOptions.Builder()
                        .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
                        .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                        .build();
        detector = FaceDetection.getClient(realTimeFdo);


        // Init UI views
        originalImage = findViewById(R.id.originalImage);
        croppedImageIv = findViewById(R.id.croppedImage);
        detectFaceBtn = findViewById(R.id.detectFaceBtn);

        Picasso.with(this).load("https://firebasestorage.googleapis.com/v0/b/javaapp-81dfa.appspot.com/o/uploads%2F1675504182448.jpg?alt=media&token=e4537641-626b-4d78-bca1-e0052b13a7e3").into(originalImage);

        detectFaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.images);
//                Uri imageUri = null;
//                try{
//                    Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
//                }
//                catch (IOException e){
//                    e.printStackTrace();
//                }

                BitmapDrawable bitmapDrawable = (BitmapDrawable) originalImage.getDrawable();
                Bitmap bitmap1 = bitmapDrawable.getBitmap();

                analyzephoto(bitmap1);
            }
        });
    }
    private void analyzephoto(Bitmap bitmap)
    {
        Bitmap smallerBitmap = Bitmap.createScaledBitmap(
                bitmap,
                bitmap.getWidth()/SCALING_FACTOR,
                bitmap.getHeight()/SCALING_FACTOR,
                false
        );

        InputImage inputImage = InputImage.fromBitmap(smallerBitmap,0);

        detector.process(inputImage)
                .addOnSuccessListener(new OnSuccessListener<List<Face>>() {
                    @Override
                    public void onSuccess(List<Face> faces) {
                        for(Face face:faces)
                        {
                            Rect rect = face.getBoundingBox();
                            rect.set(rect.left*SCALING_FACTOR,
                                    rect.top*(SCALING_FACTOR-1),
                                    rect.right*SCALING_FACTOR,
                                    (rect.bottom*SCALING_FACTOR)+90);
                        }
                        croppedDetectFace(bitmap, faces);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
    private void croppedDetectFace(Bitmap bitmap, List<Face> faces){
        Rect rect = faces.get(0).getBoundingBox();

        int x = Math.max(rect.left,0);
        int y = Math.max(rect.top,0);
        int width = rect.width();
        int height = rect.height();

        Bitmap croppedBitmap = Bitmap.createBitmap(
                bitmap,
                x,
                y,
                (x + width > bitmap.getWidth()) ? bitmap.getWidth() - x: width,
                (y + height > bitmap.getHeight()) ? bitmap.getHeight() - y:height
        );

        croppedImageIv.setImageBitmap(croppedBitmap);

    }
}