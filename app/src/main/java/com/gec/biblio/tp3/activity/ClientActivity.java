/*
 * TP3 Base de données II
 *
 * @Auteurs
 *
 * Weyller Desir
 * Koffi Joachim
 * Antoine Robitaille
 *
 * */

package com.gec.biblio.tp3.activity;

import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.gec.biblio.tp3.R;
import com.gec.biblio.tp3.database_access.DB_Connect;
import com.gec.biblio.tp3.model.Client;

import java.util.ArrayList;

public class ClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_client);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //=================================================

        ArrayList<Client> client = DB_Connect.getClient();


        String p = String.valueOf(client.get(0).getPrenom());
        String n = String.valueOf(client.get(0).getNom());

        setTitle(p+ " "+ n);

        TextView prenom = (TextView) findViewById(R.id.tvPreNom);
        prenom.setText(String.valueOf(client.get(0).getPrenom()));

        TextView  nom = (TextView) findViewById(R.id.tvNom);
        nom.setText(String.valueOf(client.get(0).getNom()));

        TextView adr = (TextView) findViewById(R.id.tvAdress);
        adr.setText(String.valueOf(client.get(0).getAdresse()));

        EditText cell = (EditText) findViewById(R.id.evCell);
        cell.setText(String.valueOf(client.get(0).getTelephone()));

        TextView email = (TextView) findViewById(R.id.tvEmail);
        email.setText(String.valueOf(client.get(0).getEmail()));



    }
}
