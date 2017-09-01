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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gec.biblio.tp3.R;
import com.gec.biblio.tp3.database_access.DB_Connect;
import com.gec.biblio.tp3.model.Client;

import java.util.ArrayList;

public class ClientActivity extends AppCompatActivity {

    Button save;
    String cellText;
    EditText editTxtTel;

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

        editTxtTel = (EditText)findViewById(R.id.evCell);
        cellText = String.valueOf(editTxtTel.getText());


        final String p = String.valueOf(client.get(0).getPrenom());
        String n = String.valueOf(client.get(0).getNom());

        setTitle(p+ " "+ n);

        TextView prenom = (TextView) findViewById(R.id.tvPreNom);
        prenom.setText(String.valueOf(client.get(0).getPrenom()));

        TextView  nom = (TextView) findViewById(R.id.tvNom);
        nom.setText(String.valueOf(client.get(0).getNom()));

        TextView adr = (TextView) findViewById(R.id.tvAdress);
        adr.setText(String.valueOf(client.get(0).getAdresse()));

        final EditText cell = (EditText) findViewById(R.id.evCell);
        cell.setText(String.valueOf(client.get(0).getTelephone()));

        TextView email = (TextView) findViewById(R.id.tvEmail);
        email.setText(String.valueOf(client.get(0).getEmail()));

        //-----------------------

        save = (Button)findViewById(R.id.btnUpdate);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int resultat = DB_Connect.saveClient(cellText,p);

                Log.e("LOG STATUS CELL", "MY STATUS: " + resultat);
                if (resultat == 1) {

                    Toast.makeText(ClientActivity.this,"Telephone modifie  avec success", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}










