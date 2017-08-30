package com.gec.biblio.tp3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.gec.biblio.tp3.R;
import com.gec.biblio.tp3.model.Livre;

import java.util.ArrayList;

public class LivreActivity extends AppCompatActivity {

    ArrayList<Livre> listLivre = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livre);


        setTitle("Affichage Livre");

        // On recueille les donnees envoyees par la page precedente
        Intent intent = getIntent();

          Livre livre = (Livre) intent.getSerializableExtra("livres");



            System.out.println(livre.getTitre());



        String s = String.valueOf(livre.getNumExemplaire());


        TextView num = (TextView) findViewById(R.id.textViewExempl);
        num.setText(String.valueOf(livre.getNumExemplaire()));

        TextView isbn = (TextView) findViewById(R.id.textViewisbn);
        isbn.setText(String.valueOf(livre.getISBN()));

        TextView date = (TextView) findViewById(R.id.textViewdate);
        date.setText(String.valueOf(livre.getDatePublication()));

        TextView editeur = (TextView) findViewById(R.id.textViewedit);
        editeur.setText(String.valueOf(livre.getNomEditeur()));

        TextView auteur = (TextView) findViewById(R.id.textViewauteur);
        String nom = String.valueOf(livre.getNomAuteur());
        String prenm = String.valueOf(livre.getPrenAuteur());
        auteur.setText(prenm +" "+ nom);

        TextView disp = (TextView) findViewById(R.id.textViewquant);
        int dispo = livre.getDisponibilite();
        if (dispo == 1) {
            disp.setText("Oui");
        } else {
            disp.setText("Non");
        }

        //-----------------

        TextView titre = (TextView) findViewById(R.id.textViewTitre);
        titre.setText(String.valueOf(livre.getTitre()));












    }
}
