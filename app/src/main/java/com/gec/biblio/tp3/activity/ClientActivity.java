package com.gec.biblio.tp3.activity;

import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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


                System.out.println(client);



    }
}
