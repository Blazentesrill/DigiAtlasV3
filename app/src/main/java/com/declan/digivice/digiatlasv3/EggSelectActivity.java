package com.declan.digivice.digiatlasv3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class EggSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String deviceId = getIntent().getStringExtra("device_id");
        if (deviceId == null) deviceId = "dm20";

        /* ------------------------------------
           Load correct layout per device
        ------------------------------------ */
        if (deviceId.equals("dmx")) {
            setContentView(R.layout.activity_egg_select_dmx);
        } else {
            setContentView(R.layout.activity_egg_select_dm20);
        }

        /* ------------------------------------
           Back button (shared ID in both layouts)
        ------------------------------------ */
        MaterialButton backBtn = findViewById(R.id.btn_back_to_devices);
        backBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, DeviceSelectActivity.class));
            finish();
        });

        /* ------------------------------------
           DM20 Egg Select
        ------------------------------------ */
        if (deviceId.equals("dm20")) {

            MaterialButton dmVer1Btn = findViewById(R.id.btn_dm_ver1);
            MaterialButton dmVer2Btn = findViewById(R.id.btn_dm_ver2);
            MaterialButton dmVer3Btn = findViewById(R.id.btn_dm_ver3);
            MaterialButton dmVer4Btn = findViewById(R.id.btn_dm_ver4);
            MaterialButton dmVer5Btn = findViewById(R.id.btn_dm_ver5);
            MaterialButton dmZubaBtn = findViewById(R.id.btn_dm_zuba);
            MaterialButton dmHackBtn = findViewById(R.id.btn_dm_hack);
            MaterialButton dmSlayerdraBtn = findViewById(R.id.btn_dm_slayerdra);
            MaterialButton dmBreakdraBtn = findViewById(R.id.btn_dm_breakdra);

            dmVer1Btn.setOnClickListener(v ->
                    openDex("dm_ver1.json", "Digital Monster Ver.1")
            );

            dmVer2Btn.setOnClickListener(v ->
                    openDex("dm_ver2.json", "Digital Monster Ver.2")
            );

            dmVer3Btn.setOnClickListener(v ->
                    openDex("dm_ver3.json", "Digital Monster Ver.3")
            );

            dmVer4Btn.setOnClickListener(v ->
                    openDex("dm_ver4.json", "Digital Monster Ver.4")
            );

            dmVer5Btn.setOnClickListener(v ->
                    openDex("dm_ver5.json", "Digital Monster Ver.5")
            );

            dmZubaBtn.setOnClickListener(v ->
                    openDex("dm_zuba.json", "Zuba")
            );

            dmHackBtn.setOnClickListener(v ->
                    openDex("dm_hack.json", "Hack")
            );

            dmSlayerdraBtn.setOnClickListener(v ->
                    openDex("dm_slayerdra.json", "Slayerdra")
            );

            dmBreakdraBtn.setOnClickListener(v ->
                    openDex("dm_breakdra.json", "Breakdra")
            );
        }

        /* ------------------------------------
           DMX Egg Select
        ------------------------------------ */
        if (deviceId.equals("dmx")) {

            // These IDs must exist ONLY in activity_egg_select_dmx.xml
            MaterialButton dmxAlphaBtn = findViewById(R.id.btn_dmx_alpha);
            MaterialButton dmxBetaBtn  = findViewById(R.id.btn_dmx_beta);

            dmxAlphaBtn.setOnClickListener(v ->
                    openDex("dmx_verA.json", "Digital Monster X Alpha")
            );

            dmxBetaBtn.setOnClickListener(v ->
                    openDex("dmx_verB.json", "Digital Monster X Beta")
            );

        }
    }

    /* ------------------------------------
       Shared dex launcher
    ------------------------------------ */
    private void openDex(String jsonFile, String deviceName) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("dex_file", jsonFile);
        intent.putExtra("device_name", deviceName);
        startActivity(intent);
        finish();
    }
}

