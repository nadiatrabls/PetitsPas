package com.example.petitspas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bouttons
        Button btnAlphabet = findViewById(R.id.btnAlphabet);
        Button btnChiffres = findViewById(R.id.btnChiffres);
        Button btnQuizAlphabet = findViewById(R.id.btnQuizAlphabet);
        Button btnQuizChiffres = findViewById(R.id.btnQuizChiffres);


        btnAlphabet.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AlphabetActivity.class);
            startActivity(intent);
        });

        btnChiffres.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ChiffresActivity.class);
            startActivity(intent);
        });
        btnQuizAlphabet.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizAlphabetActivity.class);
            startActivity(intent);
        });

        btnQuizChiffres.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizChiffresActivity.class);
            startActivity(intent);
        });
    }
}
