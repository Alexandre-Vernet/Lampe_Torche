package com.ynov.vernet.lampetorche;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView imageViewLampeTorche;
    private int compteurLampeTorche = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewLampeTorche = findViewById(R.id.btnLampeTorche);

        imageViewLampeTorche.setOnClickListener(v -> {
            compteurLampeTorche++;
            if (compteurLampeTorche % 2 == 0) {
                // Changer l'image
                imageViewLampeTorche.setImageResource(R.drawable.lampe_allumee);

                // Allumer la lampe torche
                etatLampeTorche(true);
            } else {
                // Changer l'image
                imageViewLampeTorche.setImageResource(R.drawable.lampe_eteinte);

                // Allumer la lampe torche
                etatLampeTorche(false);
            }
        });
    }

    // Changer l'état de la lampe
    public void etatLampeTorche(Boolean etat) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            String cameraId;
            try {
                assert camManager != null;
                cameraId = camManager.getCameraIdList()[0];
                camManager.setTorchMode(cameraId, etat);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }
}