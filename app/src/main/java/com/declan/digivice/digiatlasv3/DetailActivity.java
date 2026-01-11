package com.declan.digivice.digiatlasv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class DetailActivity extends AppCompatActivity {

    // Find Digimon by id from loaded dex
    private DigimonEntry findById(String id) {
        if (MainActivity.DEX == null || MainActivity.DEX.digimon == null) return null;
        for (DigimonEntry d : MainActivity.DEX.digimon) {
            if (d.id.equals(id)) return d;
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ensure dex is loaded
        if (MainActivity.DEX == null) {
            MainActivity.DEX = new DexRepository(this)
                    .loadDexFromAssets("dm_ver1.json");
        }

        setContentView(R.layout.activity_detail);

        // Views
        TextView title = findViewById(R.id.title);
        TextView stage = findViewById(R.id.stage);
        ImageView digimonImage = findViewById(R.id.digimonImage);

        TextView evolvesFromLabel = findViewById(R.id.evolvesFromLabel);
        LinearLayout evolvesFromContainer = findViewById(R.id.evolvesFromContainer);
        View evolutionDivider = findViewById(R.id.evolutionDivider);

        LinearLayout evolvesContainer = findViewById(R.id.evolvesContainer);

        MaterialButton backBtn = findViewById(R.id.backToListButton);

        // Back button
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        // Get Digimon ID
        String id = getIntent().getStringExtra("id");
        DigimonEntry entry = findById(id);

        if (entry == null) {
            title.setText("Digimon not found");
            stage.setText("");
            digimonImage.setVisibility(View.GONE);
            evolvesFromLabel.setVisibility(View.GONE);
            evolvesFromContainer.setVisibility(View.GONE);
            evolutionDivider.setVisibility(View.GONE);
            return;
        }

        // Basic info
        title.setText(entry.name);
        stage.setText(entry.stage);

        // Image
        int imageResId = getResources().getIdentifier(
                entry.id.toLowerCase(),
                "drawable",
                getPackageName()
        );

        if (imageResId != 0) {
            digimonImage.setImageResource(imageResId);
            digimonImage.setVisibility(View.VISIBLE);
        } else {
            digimonImage.setVisibility(View.GONE);
        }

        /* -------- Evolves From -------- */

        evolvesFromContainer.removeAllViews();

        if (entry.evolvesFrom != null && !entry.evolvesFrom.isEmpty()) {
            evolvesFromLabel.setVisibility(View.VISIBLE);
            evolvesFromContainer.setVisibility(View.VISIBLE);
            evolutionDivider.setVisibility(View.VISIBLE);

            for (String parentId : entry.evolvesFrom) {
                DigimonEntry parent = findById(parentId);
                if (parent == null) continue;

                TextView parentView = new TextView(this);
                parentView.setText("• " + parent.name);
                parentView.setTextSize(16f);
                parentView.setPadding(0, 4, 0, 4);
                parentView.setClickable(true);

                parentView.setOnClickListener(v -> {
                    Intent intent = new Intent(this, DetailActivity.class);
                    intent.putExtra("id", parent.id);
                    startActivity(intent);
                });

                evolvesFromContainer.addView(parentView);
            }
        } else {
            evolvesFromLabel.setVisibility(View.GONE);
            evolvesFromContainer.setVisibility(View.GONE);
            evolutionDivider.setVisibility(View.GONE);
        }

        /* -------- Evolves To -------- */

        evolvesContainer.removeAllViews();

        if (entry.evolvesTo == null || entry.evolvesTo.isEmpty()) {
            TextView tv = new TextView(this);
            tv.setText("No evolutions listed.");
            evolvesContainer.addView(tv);
            return;
        }

        for (int i = 0; i < entry.evolvesTo.size(); i++) {
            EvolutionEdge evo = entry.evolvesTo.get(i);

            if (i > 0) {
                TextView orText = new TextView(this);
                orText.setText("OR");
                orText.setTextSize(14f);
                orText.setPadding(0, 12, 0, 12);
                evolvesContainer.addView(orText);
            }

            DigimonEntry target = findById(evo.to);
            String targetName = (target != null) ? target.name : evo.to;

            TextView targetView = new TextView(this);
            targetView.setText("• " + targetName);
            targetView.setTextSize(18f);
            targetView.setPadding(0, 6, 0, 4);
            targetView.setClickable(true);

            targetView.setOnClickListener(v -> {
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra("id", evo.to);
                startActivity(intent);
            });

            evolvesContainer.addView(targetView);

            if (evo.requirements != null) {
                for (String req : evo.requirements) {
                    TextView reqView = new TextView(this);
                    reqView.setText("   - " + req);
                    reqView.setPadding(0, 2, 0, 2);
                    evolvesContainer.addView(reqView);
                }
            }
        }
    }
}
