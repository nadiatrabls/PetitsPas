package com.example.petitspas;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class QuizChiffresActivity extends AppCompatActivity {

    private TextView questionText, scoreText;
    private ImageView imgChiffre;
    private Button btnOption1, btnOption2, btnOption3, btnRetourMenu;

    int[] chiffreImages = {
            R.drawable.chiffre_1, R.drawable.chiffre_2, R.drawable.chiffre_3, R.drawable.chiffre_4,
            R.drawable.chiffre_5, R.drawable.chiffre_6, R.drawable.chiffre_7, R.drawable.chiffre_8,
            R.drawable.chiffre_9, R.drawable.chiffre_10, R.drawable.chiffre_11, R.drawable.chiffre_12,
            R.drawable.chiffre_13, R.drawable.chiffre_14, R.drawable.chiffre_15, R.drawable.chiffre_16,
            R.drawable.chiffre_17, R.drawable.chiffre_18, R.drawable.chiffre_19, R.drawable.chiffre_20
    };

    int currentIndex = 0;
    int score = 0;
    int bonneValeur = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_chiffres);

        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);
        imgChiffre = findViewById(R.id.imgChiffre);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnRetourMenu = findViewById(R.id.btnRetourMenu);

        btnRetourMenu.setVisibility(View.GONE);
        btnRetourMenu.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        // 🔁 Restaurer l’état si rotation
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("currentIndex", 0);
            score = savedInstanceState.getInt("score", 0);
        }

        afficherQuestion();
    }

    private void afficherQuestion() {
        if (currentIndex >= chiffreImages.length) {
            afficherFin();
            return;
        }

        imgChiffre.setImageResource(chiffreImages[currentIndex]);
        bonneValeur = currentIndex + 1; // Exemple : image chiffre_1 → valeur 1

        scoreText.setText("Score : " + score + "/20");
        questionText.setText("Combien vois-tu ?");

        int faux1, faux2;
        Random rand = new Random();
        do { faux1 = rand.nextInt(20) + 1; } while (faux1 == bonneValeur);
        do { faux2 = rand.nextInt(20) + 1; } while (faux2 == bonneValeur || faux2 == faux1);

        int[] propositions = {bonneValeur, faux1, faux2};
        shuffle(propositions);

        btnOption1.setText(String.valueOf(propositions[0]));
        btnOption2.setText(String.valueOf(propositions[1]));
        btnOption3.setText(String.valueOf(propositions[2]));

        btnOption1.setOnClickListener(v -> verifier(propositions[0]));
        btnOption2.setOnClickListener(v -> verifier(propositions[1]));
        btnOption3.setOnClickListener(v -> verifier(propositions[2]));
    }

    private void verifier(int choix) {
        if (choix == bonneValeur) {
            Toast.makeText(this, "✅ Bravo !", Toast.LENGTH_SHORT).show();
            MediaPlayer.create(this, R.raw.success).start();
            score++;
        } else {
            Toast.makeText(this, "❌ Mauvais choix", Toast.LENGTH_SHORT).show();
            MediaPlayer.create(this, R.raw.error).start();
        }
        currentIndex++;
        afficherQuestion();
    }

    private void afficherFin() {
        questionText.setText("🎉 Quiz terminé !");
        scoreText.setText("Ton score : " + score + "/20");

        imgChiffre.setVisibility(View.GONE);
        btnOption1.setVisibility(View.GONE);
        btnOption2.setVisibility(View.GONE);
        btnOption3.setVisibility(View.GONE);
        btnRetourMenu.setVisibility(View.VISIBLE);
    }

    private void shuffle(int[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    // ✅ Sauvegarde de l’état (rotation écran)
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentIndex", currentIndex);
        outState.putInt("score", score);
    }
}
