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

public class QuizAlphabetActivity extends AppCompatActivity {

    private TextView questionText, scoreText;
    private ImageView image1, image2, image3;
    private ImageView etoile1, etoile2, etoile3;
    private Button btnRetourMenu;

    int currentIndex = 0;
    int score = 0;

    String[] lettres = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    String[] bonnesReponses = {
            "avion", "ballon", "chocolat", "dinosaure", "etoile", "fleur", "girafe", "herisson",
            "igloo", "jouet", "koala", "lapin", "maison", "nuage", "orange", "papillon",
            "quatre", "robot", "soleil", "tigre", "uniforme", "voiture", "wagon", "xylophone",
            "yoyo", "zebre"
    };

    int[][] propositions = {
            {R.drawable.ballon, R.drawable.avion, R.drawable.soleil},
            {R.drawable.ballon, R.drawable.tigre, R.drawable.koala},
            {R.drawable.chocolat, R.drawable.lapin, R.drawable.etoile},
            {R.drawable.dinosaure, R.drawable.robot, R.drawable.papillon},
            {R.drawable.fleur, R.drawable.etoile, R.drawable.maison},
            {R.drawable.fleur, R.drawable.orange, R.drawable.wagon},
            {R.drawable.soleil, R.drawable.lapin, R.drawable.girafe},
            {R.drawable.herisson, R.drawable.voiture, R.drawable.ballon},
            {R.drawable.igloo, R.drawable.quatre, R.drawable.chocolat},
            {R.drawable.jouet, R.drawable.zebre, R.drawable.igloo},
            {R.drawable.koala, R.drawable.voiture, R.drawable.robot},
            {R.drawable.lapin, R.drawable.orange, R.drawable.herisson},
            {R.drawable.maison, R.drawable.fleur, R.drawable.tigre},
            {R.drawable.nuage, R.drawable.ballon, R.drawable.etoile},
            {R.drawable.orange, R.drawable.xylophone, R.drawable.igloo},
            {R.drawable.papillon, R.drawable.yoyo, R.drawable.girafe},
            {R.drawable.quatre, R.drawable.zebre, R.drawable.ballon},
            {R.drawable.robot, R.drawable.lapin, R.drawable.fleur},
            {R.drawable.soleil, R.drawable.avion, R.drawable.chocolat},
            {R.drawable.tigre, R.drawable.jouet, R.drawable.igloo},
            {R.drawable.uniforme, R.drawable.soleil, R.drawable.ballon},
            {R.drawable.voiture, R.drawable.etoile, R.drawable.igloo},
            {R.drawable.wagon, R.drawable.lapin, R.drawable.fleur},
            {R.drawable.xylophone, R.drawable.voiture, R.drawable.koala},
            {R.drawable.yoyo, R.drawable.quatre, R.drawable.nuage},
            {R.drawable.zebre, R.drawable.robot, R.drawable.ballon}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_alphabet);

        // Liaison des vues
        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);
        image1 = findViewById(R.id.imgOption1);
        image2 = findViewById(R.id.imgOption2);
        image3 = findViewById(R.id.imgOption3);
        etoile1 = findViewById(R.id.etoile1);
        etoile2 = findViewById(R.id.etoile2);
        etoile3 = findViewById(R.id.etoile3);
        btnRetourMenu = findViewById(R.id.btnRetourMenu);

        btnRetourMenu.setVisibility(View.GONE); // Cacher le bouton au début

        btnRetourMenu.setOnClickListener(v -> {
            Intent intent = new Intent(QuizAlphabetActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        afficherQuestion();
    }

    private void afficherQuestion() {
        if (currentIndex >= lettres.length) {
            afficherFinQuiz();
            return;
        }

        String lettre = lettres[currentIndex];
        questionText.setText("La lettre " + lettre + " comme ?");
        scoreText.setText("Score : " + score + " / " + lettres.length);

        int[] images = propositions[currentIndex];
        image1.setImageResource(images[0]);
        image2.setImageResource(images[1]);
        image3.setImageResource(images[2]);

        image1.setVisibility(View.VISIBLE);
        image2.setVisibility(View.VISIBLE);
        image3.setVisibility(View.VISIBLE);

        image1.setOnClickListener(v -> verifierReponse(images[0]));
        image2.setOnClickListener(v -> verifierReponse(images[1]));
        image3.setOnClickListener(v -> verifierReponse(images[2]));
    }

    private void verifierReponse(int imageCliquee) {
        int bonneImage = getResources().getIdentifier(
                bonnesReponses[currentIndex],
                "drawable",
                getPackageName()
        );

        if (imageCliquee == bonneImage) {
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

    private void afficherFinQuiz() {
        questionText.setText("🎉 Quiz terminé !");
        scoreText.setText("Ton score final : " + score + " / " + lettres.length);

        image1.setVisibility(View.GONE);
        image2.setVisibility(View.GONE);
        image3.setVisibility(View.GONE);
        btnRetourMenu.setVisibility(View.VISIBLE);

        etoile1.setImageResource(score >= 8 ? R.drawable.star_on : R.drawable.star_off);
        etoile2.setImageResource(score >= 16 ? R.drawable.star_on : R.drawable.star_off);
        etoile3.setImageResource(score == lettres.length ? R.drawable.star_on : R.drawable.star_off);
    }
}
