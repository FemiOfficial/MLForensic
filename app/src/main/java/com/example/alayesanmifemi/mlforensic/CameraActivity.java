package com.example.alayesanmifemi.mlforensic;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.content.pm.PackageInfo;
import android.widget.Button;
import android.widget.ImageView;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener{

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView  img_captured;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Button btn_capture = (Button)findViewById(R.id.btn_capture);
        img_captured = (ImageView) findViewById(R.id.img_captured);
        //disable btn where no camera

        btn_capture.setOnClickListener(this);

        if(!hasCamera()){
            btn_capture.setEnabled(false);
        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_capture:
                launchCamera(view);

        }
    }

    //Check if user has a camera
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }
    //launch camera

    public void launchCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //tAKE PICTURE AND PASS RESULTS ALONG TO onActivityResult
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            img_captured.setImageBitmap(photo);
        }
    }
}
