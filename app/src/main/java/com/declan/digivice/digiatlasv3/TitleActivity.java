package com.declan.digivice.digiatlasv3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        MaterialButton startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> {
            startActivity(new Intent(this, DeviceSelectActivity.class));
            finish();
        });
    }
}
