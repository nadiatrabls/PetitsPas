package com.example.petitspas;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AlphabetActivity extends AppCompatActivity {

    String[] lettres = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    String[] mots = {
            "Avion", "Ballon", "Chocolat", "Dinosaure", "Étoile", "Fleur", "Girafe", "Hérisson",
            "Igloo", "Jouet", "Koala", "Lapin", "Maison", "Nuage", "Orange", "Papillon",
            "Quatre", "Robot", "Soleil", "Tigre", "Uniforme", "Voiture", "Wagon",
            "Xylophone", "Yo-yo", "Zèbre"
    };

    int[] images = {
            R.drawable.avion, R.drawable.ballon, R.drawable.chocolat, R.drawable.dinosaure,
            R.drawable.etoile, R.drawable.fleur, R.drawable.girafe, R.drawable.herisson,
            R.drawable.igloo, R.drawable.jouet, R.drawable.koala, R.drawable.lapin,
            R.drawable.maison, R.drawable.nuage, R.drawable.orange, R.drawable.papillon,
            R.drawable.quatre, R.drawable.robot, R.drawable.soleil, R.drawable.tigre,
            R.drawable.uniforme, R.drawable.voiture, R.drawable.wagon,
            R.drawable.xylophone, R.drawable.yoyo, R.drawable.zebre
    };

    int[] sons = {
            R.raw.la_lettre_a_comme_avion, R.raw.la_lettre_b_comme_ballon, R.raw.la_lettre_c_comme_chocolat,
            R.raw.la_lettre_d_comme_dinosaure, R.raw.la_lettre_e_comme_etoile, R.raw.la_lettre_f_comme_fleur,
            R.raw.la_lettre_g_comme_girafe, R.raw.la_lettre_h_comme_herisson, R.raw.la_lettre_i_comme_igloo,
            R.raw.la_lettre_j_comme_jouet, R.raw.la_lettre_k_comme_koala, R.raw.la_lettre_l_comme_lapin,
            R.raw.la_lettre_m_comme_maison, R.raw.la_lettre_n_comme_nuage, R.raw.la_lettre_o_comme_orange,
            R.raw.la_lettre_p_comme_papillon, R.raw.la_lettre_q_comme_quatre, R.raw.la_lettre_r_comme_robot,
            R.raw.la_lettre_s_comme_soleil, R.raw.la_lettre_t_comme_tigre, R.raw.la_lettre_u_comme_uniforme,
            R.raw.la_lettre_v_comme_voiture, R.raw.la_lettre_w_comme_wagon, R.raw.la_lettre_x_comme_xylophone,
            R.raw.la_lettre_y_comme_yo_yo, R.raw.la_lettre_z_comme_zebre
    };

    int index = 0;
    TextView txtLettre, txtMot;
    ImageView imgMot;
    Button btnSon, btnSuivant, btnPrecedent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);

        txtLettre = findViewById(R.id.txtLettre);
        txtMot = findViewById(R.id.txtMot);
        imgMot = findViewById(R.id.imgMot);
        btnSon = findViewById(R.id.btnSon);
        btnSuivant = findViewById(R.id.btnSuivant);
        btnPrecedent = findViewById(R.id.btnPrecedent);

        afficherLettre();

        btnSuivant.setOnClickListener(v -> {
            if (index == lettres.length - 1) {
                // Afficher la dernière lettre, puis lancer le quiz
                afficherLettre();
                new Handler().postDelayed(() -> {
                    Intent intent = new Intent(AlphabetActivity.this, QuizAlphabetActivity.class);
                    startActivity(intent);
                    finish();
                }, 2000); // délai de 2 secondes
            } else {
                index++;
                afficherLettre();
            }
        });

        btnPrecedent.setOnClickListener(v -> {
            index = (index - 1 < 0) ? lettres.length - 1 : index - 1;
            afficherLettre();
        });

        btnSon.setOnClickListener(v -> {
            MediaPlayer mp = MediaPlayer.create(this, sons[index]);
            mp.start();
        });
    }

    private void afficherLettre() {
        txtLettre.setText(lettres[index]);
        txtMot.setText("comme " + mots[index]);
        imgMot.setImageResource(images[index]);
    }
}
