package com.ynov.vernet.lampetorche;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnLampeTorche;
    private int compteurLampeTorche = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnLampeTorche = findViewById(R.id.btnLampeTorche);

        btnLampeTorche.setOnClickListener(v -> {
            compteurLampeTorche++;
            if (compteurLampeTorche % 2 == 0)
                etatLampeTorche(true);
            else
                etatLampeTorche(false);
        });
    }

    public void etatLampeTorche(Boolean etat) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            String cameraId = null;
            try {
                cameraId = camManager.getCameraIdList()[0];
                camManager.setTorchMode(cameraId, etat);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }
}