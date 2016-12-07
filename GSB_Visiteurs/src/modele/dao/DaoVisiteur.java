/*
 * DaoVisiteur
 * @author btssio
 * @version 15/04/2014
 */
package modele.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.metier.Visiteur;
import java.util.List;

/**
 *
 * @author bjaouen
 */
public class DaoVisiteur {

    /**
     * Extraction d'un adresse, sur son identifiant
     * @param idVisiteur identifiant
     * @return objet Visiteur
     * @throws SQLException 
     */
    public static Visiteur selectOne(String matriculeVisiteur) throws SQLException {
        Visiteur unVisiteur = null;
        ResultSet rs;
        PreparedStatement pstmt;
         //préparer la requête
        String requete = "SELECT * FROM VISITEUR WHERE VIS_MATRICULE= ?";
        pstmt = Jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, matriculeVisiteur);
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
//
//        /**
//     * Extraction d'un adresse, sur son identifiant
//     * @param idVisiteur identifiant
//     * @return objet Visiteur
//     * @throws SQLException 
//     */
//    public static List<Visiteur> selectAllByVille(String villeRecherchee) throws SQLException {
//        List<Visiteur> lesVisiteurs = new ArrayList<Visiteur>();
//        ResultSet rs;
//        PreparedStatement pstmt;
//        Jdbc jdbc = Jdbc.getInstance();
//         préparer la requête
//        String requete = "SELECT * FROM VISITEUR WHERE VILLE LIKE ?";
//        pstmt = jdbc.getConnexion().prepareStatement(requete);
//        pstmt.setString(1, "%" + villeRecherchee + "%");
//        rs = pstmt.executeQuery();
//        while (rs.next()) {
//            int id = rs.getInt("ID");
//            String rue = rs.getString("RUE");
//            String cdp = rs.getString("CDP");
//            String ville = rs.getString("VILLE");
//            lesVisiteurs.add(new Visiteur(id, rue, cdp, ville));
//        }
//        return lesVisiteurs;
//    }
//    
//    /**
//     * Extraction de toutes les adresses
//     * @return collection d'adresses
//     * @throws SQLException 
//     */
//    public static List<Visiteur> selectAll() throws SQLException {
//        List<Visiteur> lesVisiteurs = new ArrayList<Visiteur>();
//        Visiteur unVisiteur;
//        ResultSet rs;
//        PreparedStatement pstmt;
//        Jdbc jdbc = Jdbc.getInstance();
//         préparer la requête
//        String requete = "SELECT * FROM VISITEUR";
//        pstmt = jdbc.getConnexion().prepareStatement(requete);
//        rs = pstmt.executeQuery();
//        while (rs.next()) {
//            int id = rs.getInt("ID");
//            String rue = rs.getString("RUE");
//            String cdp = rs.getString("CDP");
//            String ville = rs.getString("VILLE");
//            unVisiteur = new Visiteur(id, rue, cdp, ville);
//            lesVisiteurs.add(unVisiteur);
//        }
//        return lesVisiteurs;
//    }
//
//    /**
//     * Extraction de toutes les adresses, ordonnées
//     * @param cleTri 1=>ID ; 2=>VILLE
//     * @param ordreTri 1=>ASC ; 2=>DESC
//     * @return collection d'adresses
//     * @throws SQLException 
//     */
//    public static List<Visiteur> selectAll(int cleTri, int ordreTri) throws SQLException {
//        List<Visiteur> lesVisiteurs = new ArrayList<Visiteur>();
//        Visiteur unVisiteur;
//        ResultSet rs;
//        PreparedStatement pstmt;
//        Jdbc jdbc = Jdbc.getInstance();
//         préparer la requête
//        String requete = "SELECT * FROM VISITEUR";
//        switch (cleTri) {
//            case 1: // Tri par Id
//                requete += " ORDER BY ID";
//                break;
//            case 2: // Tri par ville
//                requete += " ORDER BY VILLE";
//                break;
//        }
//        if (cleTri == 1 || cleTri == 2) {
//            switch (ordreTri) {
//                case 1: // Tri croissant
//                    requete += " ASC";
//                    break;
//                case 2: // Tri décroissant
//                    requete += " DESC";
//                    break;
//            }
//        }
//        pstmt = jdbc.getConnexion().prepareStatement(requete);
//        rs = pstmt.executeQuery();
//        while (rs.next()) {
//            int id = rs.getInt("ID");
//            String rue = rs.getString("RUE");
//            String cdp = rs.getString("CDP");
//            String ville = rs.getString("VILLE");
//            unVisiteur = new Visiteur(id, rue, cdp, ville);
//            lesVisiteurs.add(unVisiteur);
//        }
//        return lesVisiteurs;
//    }
//
//    public static int insert(int idVisiteur, Visiteur unVisiteur) throws SQLException {
//        int nb;
//        Jdbc jdbc = Jdbc.getInstance();
//        String requete;
//        ResultSet rs;
//        PreparedStatement pstmt;
//        requete = "INSERT INTO VISITEUR (ID, RUE, CDP , VILLE) VALUES (?, ?, ?, ?)";
//        pstmt = jdbc.getConnexion().prepareStatement(requete);
//        pstmt.setInt(1, idVisiteur);
//        pstmt.setString(2, unVisiteur.getRue());
//        pstmt.setString(3, unVisiteur.getCp());
//        pstmt.setString(4, unVisiteur.getVille());
//        nb = pstmt.executeUpdate();
//        return nb;
//    }
//
//    public static int update(int idVisiteur, Visiteur unVisiteur) throws SQLException {
//        int nb;
//        Jdbc jdbc = Jdbc.getInstance();
//        String requete;
//        ResultSet rs;
//        PreparedStatement pstmt;
//        requete = "UPDATE VISITEUR SET RUE = ? , CDP = ? , VILLE = ? WHERE ID = ?";
//        pstmt = jdbc.getConnexion().prepareStatement(requete);
//        pstmt.setString(1, unVisiteur.getRue());
//        pstmt.setString(2, unVisiteur.getCp());
//        pstmt.setString(3, unVisiteur.getVille());
//        pstmt.setInt(4, idVisiteur);
//        nb = pstmt.executeUpdate();
//        return nb;
//    }
//
//    public static int delete(int idVisiteur) throws SQLException {
//        int nb;
//        Jdbc jdbc = Jdbc.getInstance();
//        String requete;
//        ResultSet rs;
//        PreparedStatement pstmt;
//        requete = "DELETE  FROM VISITEUR WHERE ID = ?";
//        pstmt = jdbc.getConnexion().prepareStatement(requete);
//        pstmt.setInt(1, idVisiteur);
//        nb = pstmt.executeUpdate();
//        return nb;
//    }
}
