package com.gec.biblio.tp3.activity;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gec.biblio.tp3.R;
import com.gec.biblio.tp3.database_access.DB_Connect;
import com.gec.biblio.tp3.model.Livre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button bouttonListe,bouttonClient;
    ArrayList<Livre> listLivre = new ArrayList<>();
    ListView listView;
    static Livre livre;
    ImageView imageView;
    ArrayAdapter<Livre> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    //=================================================
        listView = (ListView)findViewById(R.id.list);

        setTitle("Access Client");
        Connection con = DB_Connect.DBConnection();

         listLivre = DB_Connect.getLivre();
//
//        for (Livre l: listeLivre){
//
//            System.out.println(l);
//        }


        bouttonListe = (Button)findViewById(R.id.btList);
        bouttonListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter = new ArrayAdapter<Livre>(MainActivity.this,R.layout.list_view_livre, R.id.textViewauteur, listLivre)
                {
                    public Livre getItem(int position){
                        return listLivre.get(position);
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        TextView text1 = (TextView) view.findViewById(R.id.textViewauteur);
                        TextView text2 = (TextView) view.findViewById(R.id.textViewTitre);
                        TextView text3 = (TextView) view.findViewById(R.id.textViewExemp);
                        /**********************/


                        // text1.setTextColor(Color.BLUE);
                        // text2.setTextColor(Color.BLUE);
                        // text1.setTypeface(Typeface.DEFAULT_BOLD);
                        text2.setTypeface(Typeface.DEFAULT_BOLD);
                        text2.setTextSize(20);
                        text1.setText(listLivre.get(position).toString());
                        text1.setText((listLivre.get(position).getPrenAuteur() + " " +listLivre.get(position).getNomAuteur()));
                        text2.setText(listLivre.get(position).getTitre());
                        text3.setText("Exemplaire: "+ listLivre.get(position).getNumExemplaire()
                        //        + "  Etat: " + chiens.get(position).getEtatSteril()
                        //        + "  Sexe: " + chiens.get(position).getSexe()
                                      );

                        return view;
                    }
                };
                //-------------------------------------------------------------------------------------------------------

                listView.setBackgroundColor(Color.argb(0,125,154,255));

                // OBJET ADAPTER adapter passe a la listView
                listView.setAdapter(adapter);

                //--- ANONYMOUS INTERCLASS OBJET LISTVIEW lvChien --------------------------------------------------------
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View v, int position, long l)
                    {


                         Livre livres = (Livre) adapter.getItemAtPosition(position);
                        Log.e("LOG_CAT", "POSITION " +livres.getTitre().toString());
                        Intent intent = new Intent(MainActivity.this, LivreActivity.class);
                        intent.putExtra("livres", livres);
                        startActivity(intent);
                    }
                });










            }
        });
        //================================================================================================================

        bouttonClient = (Button)findViewById(R.id.btnClient);
        bouttonClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, LivreActivity.class);
                intent.putExtra("livres", listLivre);
                startActivity(intent);

            }
        });







    }
    //----------------------------------------------






}