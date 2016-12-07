/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.metier.Visiteur;

/**
 *
 * @author Benoit
 */
public class DaoConnexion {
    
    /**
    * Verification de l'authentification sur le nom et la date d'embauche pour accéder aux fonctionnalités de l'application
    * @param login identifiant (String chaine de caractere)
    * @param mdp mot de passe (String chaine de caractere)
    * @return boolean true si l'authentification est bonne false si elle ne l'est pas
    * @throws SQLException 
    */
    
    public static int verifierInfosConnexion(String login, String mdp) throws SQLException {
        int code = -1;
        boolean bonMdp = false;
        boolean bonLogin = false;
        boolean succes = false;
        String formatMdp = null;
        java.sql.Date sqlDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date date = new Date(mdp); 
            sqlDate = new java.sql.Date(date.getTime());
            formatMdp = formatter.format(sqlDate).toUpperCase();
            date = new Date(formatMdp); 
            sqlDate = new java.sql.Date(date.getTime());
        }catch(Exception e){
            System.out.println("Mauvais mot de passe !");
        }        
        String requete;
        ResultSet rs;
        PreparedStatement pstmt;
        requete = "SELECT * FROM VISITEUR";
        pstmt = Jdbc.getConnexion().prepareStatement(requete);
//        pstmt.setString(1, login);
//        pstmt.setString(2, mdp);
        rs = pstmt.executeQuery();
        while (rs.next() && !succes) {
            //String formatEmbauche = formatter.format(rs.getDate("vis_dateembauche")).toUpperCase();
            try{
                //bonMdp = sqlDate.equals(formatEmbauche);
                
                bonMdp = sqlDate.equals(rs.getDate("vis_dateembauche"));
            }catch(Exception e){
                
            }
            try{
                bonLogin = login.equals(rs.getString("vis_nom"));
            }catch(Exception e){
                
            }
            if (bonLogin) {
                if (bonMdp) {
                    code = 11;
                    System.out.println("Logged in!");
                    succes = true;
                } else {
                    code = 10;
                    System.out.println("Password did not match username!");
                }
            } else {
                if (!bonMdp) {
                    code = 00;//0
                    System.out.println("Username and password did not match the database");
                } else {
                    code = 01;//1
                    System.out.println("Username did not match the database");
                }
            }
        }
        return code;
    }
    
//    /**
//     * Récupération de toutes les informations de l'utilisateur qui se connecte
//     * @param nom nom du visiteur
//     * @param dateEmbauche date d'embauche du visiteur
//     * @return objet Visiteur
//     * @throws SQLException 
//     */
    public static Visiteur getConnectedVisiteur(String login, String mdp) throws SQLException {
        Visiteur unVisiteur = null;
        ResultSet rs;
        PreparedStatement pstmt;
        String formatMdp = null;
        java.sql.Date sqlDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date date = new Date(mdp); 
            sqlDate = new java.sql.Date(date.getTime());
            formatMdp = formatter.format(sqlDate).toUpperCase();
            date = new Date(formatMdp); 
            sqlDate = new java.sql.Date(date.getTime());
        }catch(Exception e){
            System.out.println("Mauvais mot de passe !");
        }    
//        try{
//            Date date = new Date(mdp); 
//            formatMdp = formatter.format(date);
//        }catch(Exception e){
//            System.out.println("Mauvais mot de passe !");
//        } 
        // préparer la requête
        String requete = "SELECT * FROM VISITEUR WHERE VIS_NOM = ? AND VIS_DATEEMBAUCHE = ?";
        pstmt = Jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, login);
        pstmt.setDate(2, sqlDate);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            String matricule = rs.getString("VIS_MATRICULE");
            String nom = rs.getString("VIS_NOM");
            String prenom = rs.getString("Vis_PRENOM");
            String adresse = rs.getString("VIS_ADRESSE");
            String codePostal = rs.getString("VIS_CP");
            String ville = rs.getString("VIS_VILLE");
            Date dateEmbauche = rs.getDate("VIS_DATEEMBAUCHE");
            String codeSecteur = rs.getString("SEC_CODE");
            String codeLaboratoire = rs.getString("LAB_CODE");
            unVisiteur = new Visiteur(matricule, nom, prenom, adresse, codePostal, ville, dateEmbauche, codeSecteur, codeLaboratoire);
        }
        return unVisiteur;
    }
}
