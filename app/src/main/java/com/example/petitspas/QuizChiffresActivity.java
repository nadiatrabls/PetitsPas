package com.example.petitspas;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class QuizChiffresActivity extends AppCompatActivity {

    private TextView questionText, scoreText;
    private ImageView image1, image2, image3;
    private Button btnRetourMenu;
    private ImageView etoile1, etoile2, etoile3;

    int[] chiffresImages = {
            R.drawable.chiffre_1, R.drawable.chiffre_2, R.drawable.chiffre_3, R.drawable.chiffre_4,
            R.drawable.chiffre_5, R.drawable.chiffre_6, R.drawable.chiffre_7, R.drawable.chiffre_8,
            R.drawable.chiffre_9, R.drawable.chiffre_10, R.drawable.chiffre_11, R.drawable.chiffre_12,
            R.drawable.chiffre_13, R.drawable.chiffre_14, R.drawable.chiffre_15, R.drawable.chiffre_16,
            R.drawable.chiffre_17, R.drawable.chiffre_18, R.drawable.chiffre_19, R.drawable.chiffre_20
    };

    int currentIndex = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_chiffres);

        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);
        image1 = findViewById(R.id.imgOption1);
        image2 = findViewById(R.id.imgOption2);
        image3 = findViewById(R.id.imgOption3);
        btnRetourMenu = findViewById(R.id.btnRetourMenu);

        etoile1 = findViewById(R.id.etoile1);
        etoile2 = findViewById(R.id.etoile2);
        etoile3 = findViewById(R.id.etoile3);

        btnRetourMenu.setVisibility(View.GONE);
        etoile1.setVisibility(View.GONE);
        etoile2.setVisibility(View.GONE);
        etoile3.setVisibility(View.GONE);

        btnRetourMenu.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        afficherQuestion();
    }

    private void afficherQuestion() {
        if (currentIndex >= chiffresImages.length) {
            afficherFin();
            return;
        }

        int bonneImage = chiffresImages[currentIndex];

        Random rand = new Random();
        int faux1, faux2;
        do { faux1 = rand.nextInt(20); } while (faux1 == currentIndex);
        do { faux2 = rand.nextInt(20); } while (faux2 == currentIndex || faux2 == faux1);

        int[] options = { chiffresImages[faux1], bonneImage, chiffresImages[faux2] };
        shuffleArray(options);

        questionText.setText("Quel est ce chiffre ?");
        scoreText.setText("Score : " + score + "/20");

        image1.setImageResource(options[0]);
        image2.setImageResource(options[1]);
        image3.setImageResource(options[2]);

        image1.setOnClickListener(v -> verifier(options[0], bonneImage));
        image2.setOnClickListener(v -> verifier(options[1], bonneImage));
        image3.setOnClickListener(v -> verifier(options[2], bonneImage));
    }

    private void verifier(int choix, int bonne) {
        if (choix == bonne) {
            score++;
            MediaPlayer.create(this, R.raw.success).start();
            Toast.makeText(this, "✅ Bravo !", Toast.LENGTH_SHORT).show();
        } else {
            MediaPlayer.create(this, R.raw.error).start();
            Toast.makeText(this, "❌ Mauvais choix", Toast.LENGTH_SHORT).show();
        }

        currentIndex++;
        afficherQuestion();
    }

    private void afficherFin() {
        questionText.setText("🎉 Quiz terminé !");
        scoreText.setText("Ton score : " + score + "/20");

        image1.setVisibility(View.GONE);
        image2.setVisibility(View.GONE);
        image3.setVisibility(View.GONE);
        btnRetourMenu.setVisibility(View.VISIBLE);

        etoile1.setVisibility(View.VISIBLE);
        etoile2.setVisibility(View.VISIBLE);
        etoile3.setVisibility(View.VISIBLE);

        etoile1.setImageResource(score >= 7 ? R.drawable.star_on : R.drawable.star_off);
        etoile2.setImageResource(score >= 14 ? R.drawable.star_on : R.drawable.star_off);
        etoile3.setImageResource(score == 20 ? R.drawable.star_on : R.drawable.star_off);
    }

    private void shuffleArray(int[] arr) {
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }
}
