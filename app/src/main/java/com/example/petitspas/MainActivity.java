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

        // Bout
        Button btnAlphabet = findViewById(R.id.btnAlphabet);
        Button btnChiffres = findViewById(R.id.btnChiffres);


        // ✅ Lancer AlphabetActivity
        btnAlphabet.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AlphabetActivity.class);
            startActivity(intent);
        });

        btnChiffres.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ChiffresActivity.class);
            startActivity(intent);
        });

    }
}
