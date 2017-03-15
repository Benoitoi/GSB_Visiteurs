package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.metier.Medicament;
import modele.metier.Offre;
import modele.metier.RapportVisite;
import modele.metier.Visiteur;

/**
 *
 * @author bjaouen
 */
public class DaoOffre {

    /**
     * Extraction de toutes les offres échantillon
     *
     * @return tous les offres récupérées
     * @throws SQLException
     */
    public static ArrayList<Offre> getAllOffres() throws SQLException {
        ArrayList<Offre> lesOffresEchantillons = new ArrayList<>();
        Jdbc jdbc = Jdbc.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM OFFRIR";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String matriculeVisiteur = rs.getString("VIS_MATRICULE");
            int numeroRapport = rs.getInt("RAP_NUM");
            String depotLegal = rs.getString("MED_DEPOTLEGAL");
            int quantiteOffre = rs.getInt("OFF_QTE");
            Visiteur visiteur = DaoVisiteur.getOneByMatricule(matriculeVisiteur);
            Medicament medicament = DaoMedicament.getOneByDepotLegal(depotLegal);
            RapportVisite rapport = DaoRapportVisite.getOneByNum(numeroRapport);
            lesOffresEchantillons.add(new Offre(visiteur, rapport, medicament, quantiteOffre));
        }
        rs.close();
        pstmt.close();
        return lesOffresEchantillons;
    }

    /**
     * Extraction de toutes les offres d'un rapport
     *
     * @param numeroRapportVisite
     * @return lesOffresEchantillons toute les offre du rapport de visite donné
     * @throws SQLException
     */
    public static ArrayList<Offre> getAllByRapport(int numeroRapportVisite) throws SQLException {
        ArrayList<Offre> lesOffresEchantillons = null;
        Jdbc jdbc = Jdbc.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM OFFRE WHERE RAP_NUM = ?";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setInt(1, numeroRapportVisite);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String matriculeVisiteur = rs.getString("VIS_MATRICULE");
            int numeroRapport = rs.getInt("RAP_NUM");
            String depotLegal = rs.getString("MED_DEPOTLEGAL");
            int quantiteOffre = rs.getInt("OFF_QTE");
            Visiteur visiteur = DaoVisiteur.getOneByMatricule(matriculeVisiteur);
            RapportVisite rapport = DaoRapportVisite.getOneByNum(numeroRapport);
            Medicament medicament = DaoMedicament.getOneByDepotLegal(depotLegal);
            lesOffresEchantillons.add(new Offre(visiteur, rapport, medicament, quantiteOffre));
        }
        return lesOffresEchantillons;
    }

    /**
     * Extraction de toutes les offres d'un rapport
     *
     * @param numeroRapportVisite
     * @return lesOffresEchantillons toute les offre du rapport de visite donné
     * @throws SQLException
     */
    public static ArrayList<Offre> getOne(int numeroRapportVisite) throws SQLException {
        ArrayList<Offre> lesOffresEchantillons = null;
        Jdbc jdbc = Jdbc.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM OFFRE WHERE RAP_NUM = ?";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setInt(1, numeroRapportVisite);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String matriculeVisiteur = rs.getString("VIS_MATRICULE");
            int numeroRapport = rs.getInt("RAP_NUM");
            String depotLegal = rs.getString("MED_DEPOTLEGAL");
            int quantiteOffre = rs.getInt("OFF_QTE");
            Visiteur visiteur = DaoVisiteur.getOneByMatricule(matriculeVisiteur);
            RapportVisite rapport = DaoRapportVisite.getOneByNum(numeroRapport);
            Medicament medicament = DaoMedicament.getOneByDepotLegal(depotLegal);
            lesOffresEchantillons.add(new Offre(visiteur, rapport, medicament, quantiteOffre));
        }
        return lesOffresEchantillons;
    }

    /**
     * Cette méthode met à jour une offre
     *
     * @param uneOffre l'offre à mettre à jour
     * @return nb nombre de lignes affectées
     * @throws SQLException
     */
    public static int update(Offre uneOffre) throws SQLException {
        int nb;
        Jdbc jdbc = Jdbc.getInstance();
        String requete = "UPDATE OFFRIR SET VIS_MATRICULE= ?, RAP_NUM= ?, MED_DEPOTLEGAL= ?, OFF_QTE= ? WHERE VIS_MATRICULE= ? AND RAP_NUM= ?  AND MED_DEPOTLEGAL= ?";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, uneOffre.getVisiteur().getMatricule());
        pstmt.setInt(2, uneOffre.getRapport().getNumeroRapport());
        pstmt.setString(3, uneOffre.getMedicament().getDepotLegal());
        pstmt.setInt(4, uneOffre.getQuantiteOffre());
        pstmt.setString(5, uneOffre.getVisiteur().getMatricule());
        pstmt.setInt(6, uneOffre.getRapport().getNumeroRapport());
        pstmt.setString(7, uneOffre.getMedicament().getDepotLegal());
        nb = pstmt.executeUpdate();
        return nb;
    }

    /**
     * Cette méthode supprime une offre
     *
     * @param uneOffre l'offre à supprimer
     * @return nb nombre de lignes affectées
     * @throws SQLException
     */
    public static int delete(Offre uneOffre) throws SQLException {
        int nb;
        Jdbc jdbc = Jdbc.getInstance();
        String requete = "DELETE FROM OFFRIR WHERE VIS_MATRICULE= ? AND RAP_NUM= ? AND MED_DEPOTLEGAL= ? AND OFF_QTE= ?";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, uneOffre.getVisiteur().getMatricule());
        pstmt.setInt(2, uneOffre.getRapport().getNumeroRapport());
        pstmt.setString(3, uneOffre.getMedicament().getDepotLegal());
        pstmt.setInt(4, uneOffre.getQuantiteOffre());
        nb = pstmt.executeUpdate();
        return nb;
    }

    /**
     * Creation d'une nouvelle offre
     *
     * @param uneOffre la nouvelle offre a ajouter
     * @return nb nombres de lignes affectées
     * @throws SQLException
     */
    public static int insert(Offre uneOffre) throws SQLException {
        int nb;
        Jdbc jdbc = Jdbc.getInstance();
        String requete = "INSERT INTO OFFRIR (VIS_MATRICULE, RAP_NUM, MED_DEPOTLEGAL , OFF_QTE) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, uneOffre.getVisiteur().getMatricule());
        pstmt.setInt(2, uneOffre.getRapport().getNumeroRapport());
        pstmt.setString(3, uneOffre.getMedicament().getDepotLegal());
        pstmt.setInt(4, uneOffre.getQuantiteOffre());
        nb = pstmt.executeUpdate();
        pstmt.close();
        return nb;
    }
}
