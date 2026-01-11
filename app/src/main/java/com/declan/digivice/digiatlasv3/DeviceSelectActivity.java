package com.declan.digivice.digiatlasv3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class DeviceSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_select);

        MaterialButton dm20Btn = findViewById(R.id.btn_dm20);
        MaterialButton dmxBtn = findViewById(R.id.btn_dmx);
        dmxBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, EggSelectActivity.class);
            intent.putExtra("device_id", "dmx");
            startActivity(intent);
        });
        dm20Btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, EggSelectActivity.class);
            intent.putExtra("device_id", "dm20");
            startActivity(intent);
        });
    }
}
