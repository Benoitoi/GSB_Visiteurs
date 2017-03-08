package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modele.metier.Visiteur;

/**
 *
 * @author tlauterbach
 */
public class DaoConnexion {

    /**
     * Verification de l'authentification sur le nom et la date d'embauche pour
     * accéder aux fonctionnalités de l'application
     *
     * @param login identifiant (String chaine de caractere)
     * @param mdp mot de passe (String chaine de caractere)
     * @return code (int entier) : 11 si le login et le mot de passe sont
     * corrects 10 si seul le login est correct 0 (00) si les deux ne sont pas
     * corrects
     * @throws SQLException
     */
    public static int verifierInfosConnexion(String login, String mdp) throws SQLException {
        int code = -1;
        boolean bonMdp = false;
        boolean bonLogin = false;
        java.sql.Date sqlDate = null;
        try {
            Date date = new Date(mdp);
            sqlDate = new java.sql.Date(date.getTime());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formatMdp = formatter.format(sqlDate).toUpperCase();
            date = new Date(formatMdp);
            sqlDate = new java.sql.Date(date.getTime());
        } catch (Exception e) {
            //Si le mot de passe est sous forme d'une date incorrecte la convertion de date ne marchera pas
            System.out.println("Mauvais mot de passe !");
        }
        Jdbc jdbc = Jdbc.getInstance();
        String requete = "SELECT VIS_NOM FROM VISITEUR WHERE VIS_NOM = ?";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, login);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            bonLogin = true;
            requete = "SELECT VIS_NOM, VIS_DATEEMBAUCHE FROM VISITEUR WHERE VIS_NOM = ? AND VIS_DATEEMBAUCHE = ?";
            pstmt = jdbc.getConnexion().prepareStatement(requete);
            pstmt.setString(1, login);
            pstmt.setDate(2, sqlDate);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                bonMdp = true;
            }
        } else {
            requete = "SELECT VIS_DATEEMBAUCHE FROM VISITEUR WHERE VIS_DATEEMBAUCHE = ?";
            pstmt = jdbc.getConnexion().prepareStatement(requete);
            pstmt.setDate(1, sqlDate);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                bonMdp = true;
            }
        }

        if (bonLogin) {
            if (bonMdp) {
                code = 11;
                System.out.println("Bienvenue!");
            } else {
                code = 10;
                System.out.println("Mauvais mot de passe.");
            }
        } else {
            if (!bonMdp) {
                code = 00;//sera lu comme 0
                System.out.println("Login et mot de passe incorrects.");
            } else {
                code = 01;//sera lu comme 1
                System.out.println("Login incorrect");
            }
        }
        return code;
    }

    /**
     * Récupération de toutes les informations de l'utilisateur qui se connecte
     *
     * @param leNom nom du visiteur
     * @param laDateEmbauche date d'embauche du visiteur
     * @return objet Visiteur
     * @throws SQLException
     */
    public static Visiteur getConnectedVisiteur(String leNom, String laDateEmbauche) throws SQLException {
        Visiteur unVisiteur = null;
        java.sql.Date sqlDate = null;
        try {
            Date date = new Date(laDateEmbauche);
            sqlDate = new java.sql.Date(date.getTime());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formatMdp = formatter.format(sqlDate).toUpperCase();
            date = new Date(formatMdp);
            sqlDate = new java.sql.Date(date.getTime());
        } catch (Exception e) {
            //normalement le mot de passe est correct ici mais on laisse quand meme l'exception au cas ou
            System.out.println("Mauvais mot de passe !");
        }

        Jdbc jdbc = Jdbc.getInstance();
        // préparer la requête
        String requete = "SELECT * FROM VISITEUR WHERE VIS_NOM = ? AND VIS_DATEEMBAUCHE = ?";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, leNom);
        pstmt.setDate(2, sqlDate);
        ResultSet rs = pstmt.executeQuery();
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
