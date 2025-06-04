package com.example.petitspas;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChiffresActivity extends AppCompatActivity {

    private TextView txtChiffre;
    private ImageView imgChiffre;
    private Button btnSon, btnSuivant, btnPrecedent;

    int[] chiffres = {
            R.string.un, R.string.deux, R.string.trois, R.string.quatre, R.string.cinq,
            R.string.six, R.string.sept, R.string.huit, R.string.neuf, R.string.dix,
            R.string.onze, R.string.douze, R.string.treize, R.string.quatorze, R.string.quinze,
            R.string.seize, R.string.dix_sept, R.string.dix_huit, R.string.dix_neuf, R.string.vingt
    };

    int[] images = {
            R.drawable.chiffre_1, R.drawable.chiffre_2, R.drawable.chiffre_3, R.drawable.chiffre_4, R.drawable.chiffre_5,
            R.drawable.chiffre_6, R.drawable.chiffre_7, R.drawable.chiffre_8, R.drawable.chiffre_9, R.drawable.chiffre_10,
            R.drawable.chiffre_11, R.drawable.chiffre_12, R.drawable.chiffre_13, R.drawable.chiffre_14, R.drawable.chiffre_15,
            R.drawable.chiffre_16, R.drawable.chiffre_17, R.drawable.chiffre_18, R.drawable.chiffre_19, R.drawable.chiffre_20
    };

    int[] sons = {
            R.raw.chiffre_1, R.raw.chiffre_2, R.raw.chiffre_3, R.raw.chiffre_4, R.raw.chiffre_5,
            R.raw.chiffre_6, R.raw.chiffre_7, R.raw.chiffre_8, R.raw.chiffre_9, R.raw.chiffre_10,
            R.raw.chiffre_11, R.raw.chiffre_12, R.raw.chiffre_13, R.raw.chiffre_14, R.raw.chiffre_15,
            R.raw.chiffre_16, R.raw.chiffre_17, R.raw.chiffre_18, R.raw.chiffre_19, R.raw.chiffre_20
    };

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chiffres);

        txtChiffre = findViewById(R.id.txtChiffre);
        imgChiffre = findViewById(R.id.imgChiffre);
        btnSon = findViewById(R.id.btnSon);
        btnSuivant = findViewById(R.id.btnSuivant);
        btnPrecedent = findViewById(R.id.btnPrecedent);

        afficherChiffre();

        btnSon.setOnClickListener(v -> MediaPlayer.create(this, sons[index]).start());

        btnSuivant.setOnClickListener(v -> {
            index++;
            if (index < chiffres.length) {
                afficherChiffre();
            } else {
                Toast.makeText(this, "🎉 C’est parti pour le quiz !", Toast.LENGTH_SHORT).show();

                // Attendre 1,5 secondes puis lancer le quiz
                new Handler().postDelayed(() -> lancerQuizChiffres(), 1500);
            }
        });

        btnPrecedent.setOnClickListener(v -> {
            index = (index - 1 + chiffres.length) % chiffres.length;
            afficherChiffre();
        });
    }

    private void afficherChiffre() {
        txtChiffre.setText(getString(chiffres[index]));
        imgChiffre.setImageResource(images[index]);
    }

    private void lancerQuizChiffres() {
        Intent intent = new Intent(ChiffresActivity.this, QuizChiffresActivity.class);
        startActivity(intent);
        finish();
    }
}
