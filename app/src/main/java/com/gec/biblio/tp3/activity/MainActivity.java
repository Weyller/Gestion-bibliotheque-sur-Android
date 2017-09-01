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


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gec.biblio.tp3.R;
import com.gec.biblio.tp3.database_access.DB_Connect;
import com.gec.biblio.tp3.model.Livre;

import java.sql.Connection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button bouttonListe,bouttonClient;
    ArrayList<Livre> listLivre = new ArrayList<>();
    ListView listView;
    ArrayAdapter<Livre> adapter;
    private View mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setTitle("Access Client");

        setContentView(R.layout.activity_main);



        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    //=================================================
        listView = (ListView)findViewById(R.id.list);
        mProgressView = findViewById(R.id.login_progress2);

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

               // listView.setBackgroundColor(Color.argb(1,125,154,255));



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

                // Show a progress spinner, and kick off a background task to
                // perform the list display

                showProgress(true);

                Intent intent = new Intent(MainActivity.this, ClientActivity.class);

                startActivity(intent);

            }
        });

    }
    //----------------------------------------------

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            listView.setVisibility(show ? View.GONE : View.VISIBLE);
            listView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    listView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            listView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        mProgressView.setVisibility(View.INVISIBLE);




    }

    @Override
    public void onBackPressed() {
        Toast.makeText(MainActivity.this,"Vous etes deja authetifié",Toast.LENGTH_SHORT).show();
    }




}