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

        // Boutons
        Button btnAlphabet = findViewById(R.id.btnAlphabet);
        Button btnChiffres = findViewById(R.id.btnChiffres);
        Button btnSyllabes = findViewById(R.id.btnSyllabes);
        Button btnVocabulaire = findViewById(R.id.btnVocabulaire);
        Button btnOrdreImages = findViewById(R.id.btnOrdreImages);
        Button btnRecompenses = findViewById(R.id.btnRecompenses);

        // ✅ Lancer AlphabetActivity
        btnAlphabet.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AlphabetActivity.class);
            startActivity(intent);
        });

        btnChiffres.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ChiffresActivity.class);
            startActivity(intent);
        });


        btnSyllabes.setOnClickListener(view ->
                Toast.makeText(this, "Module Syllabes à venir", Toast.LENGTH_SHORT).show());

        btnVocabulaire.setOnClickListener(view ->
                Toast.makeText(this, "Module Vocabulaire à venir", Toast.LENGTH_SHORT).show());

        btnOrdreImages.setOnClickListener(view ->
                Toast.makeText(this, "Module Ordre des Images à venir", Toast.LENGTH_SHORT).show());

        btnRecompenses.setOnClickListener(view ->
                Toast.makeText(this, "Module Récompenses à venir", Toast.LENGTH_SHORT).show());
    }
}
