package com.gec.biblio.tp3.database_access;

/**
 * Created by Administrator on 8/27/2017.
 */


import android.util.Log;

import com.gec.biblio.tp3.model.Livre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class DB_Connect {

    private final String USER = "root";
    private final String PASSWORD = "root";

    static Connection con;

    private static final String NUM_EXEMPLAIRE = "NumExemplaire";
    private static final String ISBN = "ISBN";
    private static final String DATE = "DatePub";
    private static final String PAGES = "NbPages";
    private static final String QUANTITE = "Nombre";
    private static final String DISPONIBLE = "Disponibilite";
    private static final String TITRE = "Titre";
    private static final String NOM_EDITEUR = "NomEditeur";
    private static final String NOM_AUTEUR = "NomAuteur";
    private static final String PR_AUTEUR = "PrAuteur";
    private static final String ID_TYPE = "IdTypeLivre";


    //------------------------------

   public static Connection DBConnection() {


        try {
            //
            Class.forName("com.mysql.jdbc.Driver");
            //

            String url = "jdbc:mysql://10.0.2.2:3306/DB_Bibliotheque";
            con = DriverManager.getConnection(
                    url, "root", "root");
            //
            System.out.println("URL: " + url);
            System.out.println("Connection: " + con);
            //

            //con.close();

            Log.e("LOG_CAT", "connection created");


        } catch (Exception e) {
            e.printStackTrace();

            Log.e("LOG_ERROR", "ERROR connection");

        }
        return con;
    }
    //========================================================================



    public static ArrayList<Livre> getLivre() {

       boolean status = false;
      //  Client client = new Client();


         ArrayList<Livre> listeLivre = new ArrayList<>();



        try {

            con = DBConnection();

            //==================================================

            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            stmt.executeQuery("use DB_Bibliotheque");



            String query = "select LivreExemplaire.NumExemplaire, LivreEdition.ISBN, LivreEdition.DatePub," +
                    "LivreEdition.NbPages, LivreEdition.Nombre, LivreExemplaire.Disponibilite, Livre.Titre, Editeur.NomEditeur, " +
                    "Auteur.NomAuteur, Auteur.PrAuteur, LivreEdition.IdTypeLivre" +
                    " from Livre, LivreEdition, LivreExemplaire, Auteur, Editeur" +
                    " WHERE livreexemplaire.ISBN = livreedition.ISBN" +
                    " AND" +
                    " livre.Titre = livreedition.Titre" +
                    " AND" +
                    " auteur.IdAuteur = livreedition.IdAuteur" +
                    " AND" +
                    " livreedition.IdEditeur = editeur.IdEditeur ";
            ResultSet rs = stmt.executeQuery(query);

            status = rs.next();

            while (rs.next()) {

                Livre livre = new Livre();

                livre.setNumExemplaire(rs.getInt(NUM_EXEMPLAIRE));
                livre.setISBN(rs.getLong(ISBN));
                livre.setDatePublication(rs.getString(DATE));

                livre.setNbPages(rs.getInt(PAGES));
                livre.setQuantite(rs.getInt(QUANTITE));
                livre.setDisponibilite(rs.getInt(DISPONIBLE));
                livre.setTitre(rs.getString(TITRE));
                livre.setNomEditeur(rs.getString(NOM_EDITEUR));
                livre.setNomAuteur(rs.getString(NOM_AUTEUR));
                livre.setPrenAuteur(rs.getString(PR_AUTEUR));
                livre.setTypeLivre(rs.getInt(ID_TYPE));

                //=====================================

                listeLivre.add(livre);

            }






            Log.e("LOG_STATUS", "Status = " + status);

            stmt.close();
            con.commit();
            con.close();




        }catch (Exception e)
        {
            e.printStackTrace();
            Log.e("LOG_ERROR", "ERROR requests");
        }



        return listeLivre;
    }


}


