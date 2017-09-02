package com.gec.biblio.tp3.database_access;

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

import android.util.Log;

import com.gec.biblio.tp3.model.Client;
import com.gec.biblio.tp3.model.Livre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


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

    private static final String PRENOM = "PrClient";
    private static final String NOM = "NomClient";
    private static final String ADDRESS = "AdrClient";
    private static final String PHONE = "CelClient";
    private static final String EMAIL = "EmailClient";


    private static final String prenom = "Weyller";


   public static Connection DBConnection() {


        try {
            //
            Class.forName("com.mysql.jdbc.Driver");
            //

            String url = "jdbc:mysql://10.0.2.2:3306/DB_Bibliotheque2";
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
           // stmt.executeQuery("use DB_Bibliotheque");



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

    //=================================================================================================================

    public static ArrayList<Client> getClient() {

        boolean status = false;


        ArrayList<Client> unClient = new ArrayList<>();



        try {

            con = DBConnection();

            //==================================================

            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
           // stmt.executeQuery("use DB_Bibliotheque");



            String query = "select NomClient, PrClient, AdrClient, CelClient, EmailClient from Client where PrClient "  + " = '"+ prenom + "'" + " limit 1 ";
            ResultSet rs = stmt.executeQuery(query);

            Client client = new Client();

           //status = rs.next();
            Log.e("LOG_STATUS CLIENT", "Status Client = " + status);

            while (rs.next()) {

                client.setNom(rs.getString(NOM));
                client.setPrenom(rs.getString(PRENOM));
                client.setAdresse(rs.getString(ADDRESS));
                client.setTelephone(rs.getString(PHONE));
                client.setEmail(rs.getString(EMAIL));

                //=====================================


                unClient.add(client);


            }

            System.out.println(unClient);

            for (Client l: unClient
                 ) { System.out.println(l);

            }


            stmt.close();
            con.commit();
            con.close();

        }catch (Exception e)
        {
            e.printStackTrace();
            Log.e("LOG_ERROR", "ERROR requests");
        }



        return unClient;
    }


    //=================================================================================================================

    public static int saveClient(String cell, String prenom) {

        int status = 0;

        try {

            con = DBConnection();

            //==================================================

            con.setAutoCommit(false);
            Statement stmt = con.createStatement();


            String query = "UPDATE CLIENT SET CelClient =  '" + cell +"'   WHERE PrClient =  '" + prenom + "'  ";
            status = stmt.executeUpdate(query);

//            PreparedStatement ps=con.prepareStatement("UPDATE Client SET CelClient = ? WHERE PrClient = ?;");
//            ps.setString(1,cell);
//            ps.setString(2,prenom);
//            status=ps.executeUpdate();


            Log.e("LOG_STATUS CLIENT", "Status Client = " + status);


            stmt.close();
            con.commit();
            con.close();

        }catch (Exception e)
        {
            e.printStackTrace();
            Log.e("LOG_ERROR", "ERROR INSERT");
        }



        return status;
    }


}


