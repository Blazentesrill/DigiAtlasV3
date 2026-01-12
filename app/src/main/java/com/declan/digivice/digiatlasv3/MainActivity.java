package com.declan.digivice.digiatlasv3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    // Shared dex so DetailActivity can read it
    public static DeviceDex DEX;

    // Keep track of current device
    private String currentDeviceId = "dm20";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load json
        String dexFile = getIntent().getStringExtra("dex_file");
        if (dexFile == null || dexFile.trim().isEmpty()) {
            dexFile = "dm_ver1.json"; // fallback
        }

        try {
            DEX = new DexRepository(this).loadDexFromAssets(dexFile);
        } catch (RuntimeException ex) {
            Toast.makeText(this,
                    "Failed to load JSON: " + dexFile + "\nCheck /assets file name + JSON format.",
                    Toast.LENGTH_LONG).show();

            // Go back to Device Select so user isn't stuck
            startActivity(new Intent(this, DeviceSelectActivity.class));
            finish();
            return;
        }

        // Capture device id from JSON (DM20, DMX, etc.)
        if (DEX != null && DEX.device != null && !DEX.device.trim().isEmpty()) {
            currentDeviceId = DEX.device.toLowerCase();
        }

        TextView titleText = findViewById(R.id.titleText);
        String deviceName = getIntent().getStringExtra("device_name");

        if (titleText != null) {
            if (deviceName != null) {
                titleText.setText("DigiAtlas — " + deviceName);
            } else {
                titleText.setText("DigiAtlas");
            }
        }

        if (findViewById(R.id.openEggSelect) != null) {
            findViewById(R.id.openEggSelect).setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, EggSelectActivity.class);
                intent.putExtra("device_id", currentDeviceId);
                startActivity(intent);
            });
        }

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DigimonAdapter adapter = new DigimonAdapter(
                DEX.digimon,
                digimon -> {
                    Intent intent = new Intent(this, DetailActivity.class);
                    intent.putExtra("id", digimon.id);
                    startActivity(intent);
                }
        );

        recyclerView.setAdapter(adapter);
    }
}
