package com.declan.digivice.digiatlasv3;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DexRepository {
    private final Context context;
    private final Gson gson = new Gson();

    public DexRepository(Context context) {
        this.context = context;
    }

    public DeviceDex loadDexFromAssets(String fileName) {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(fileName))
            );
            return gson.fromJson(br, DeviceDex.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load dex JSON: " + fileName, e);
        }
    }
}
