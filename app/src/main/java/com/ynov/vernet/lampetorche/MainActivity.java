package com.ynov.vernet.lampetorche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            String cameraId = null;
            try {
                cameraId = camManager.getCameraIdList()[0];
                camManager.setTorchMode(cameraId, true);   //Turn ON
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }
}