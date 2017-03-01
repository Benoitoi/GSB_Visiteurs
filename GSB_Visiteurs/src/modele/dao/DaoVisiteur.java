package modele.dao;

import gsb_visiteurs.Connexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.metier.Laboratoire;
import modele.metier.Visiteur;
import modele.metier.Secteur;

/**
 *
 * @author pnoel
 */
public class DaoVisiteur {

    /**
     * Extraction de tout les visiteurs
     * 
     * @return ArrayList<Visiteur> : collection de visiteurs
     * @throws SQLException
     */
    public static ArrayList<Visiteur> getAllVisiteurs() throws SQLException {
        ArrayList<Visiteur> lesVisiteurs = new ArrayList<>();
        Jdbc jdbc = Connexion.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM VISITEUR";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String matricule = rs.getString("VIS_MATRICULE");
            String nom = rs.getString("VIS_NOM");
            String prenom = rs.getString("Vis_PRENOM");
            String adresse = rs.getString("VIS_ADRESSE");
            String codePostal = rs.getString("VIS_CP");
            String ville = rs.getString("VIS_VILLE");
            Date dateEmbauche = rs.getDate("VIS_DATEEMBAUCHE");
            String codeSecteur = rs.getString("SEC_CODE");
            String codeLaboratoire = rs.getString("LAB_CODE");
            lesVisiteurs.add(new Visiteur(matricule, nom, prenom, adresse, codePostal, ville, dateEmbauche, codeSecteur, codeLaboratoire));
        }
        return lesVisiteurs;
    }

    /**
     * Extraction de tous les secteurs
     * 
     * @return ArrayList<Secteur> : collection de secteurs
     * @throws SQLException 
     */
    public static ArrayList<Secteur> getAllSecteurs() throws SQLException {
        ArrayList<Secteur> lesSecteurs = new ArrayList<>();
        Jdbc jdbc = Connexion.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM SECTEUR";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String code = rs.getString("SEC_CODE");
            String libelle = rs.getString("SEC_LIBELLE");
            lesSecteurs.add(new Secteur(code, libelle));
        }
        return lesSecteurs;
    }

    /**
     * Extraction de tous les laboratoires
     * 
     * @return ArrayList<Laboratoire> : collection de laboratoires
     * @throws SQLException 
     */
    public static ArrayList<Laboratoire> getAllLaboratoires() throws SQLException {
        ArrayList<Laboratoire> lesLaboratoires = new ArrayList<>();
        Jdbc jdbc = Connexion.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM LABO";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String code = rs.getString("LAB_CODE");
            String nom = rs.getString("LAB_NOM");
            String chefDeVente = rs.getString("LAB_CHEFVENTE");
            lesLaboratoires.add(new Laboratoire(code, nom, chefDeVente));
        }
        return lesLaboratoires;
    }
}
