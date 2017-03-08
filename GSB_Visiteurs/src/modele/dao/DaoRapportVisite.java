package modele.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.metier.RapportVisite;

/**
 *
 * @author bjaouen
 */
public class DaoRapportVisite {

    /**
     * Extraction de tous les rapports de visite
     *
     * @return ArrayList<RapportVisite> : liste d'objets RapportVisite
     * @throws SQLException
     */
    public static ArrayList<RapportVisite> getAllRapportsVisite() throws SQLException {
        ArrayList<RapportVisite> lesRapportVisites = new ArrayList<>();
        Jdbc jdbc = Jdbc.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM RAPPORT_VISITE";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String matriculeVisiteur = rs.getString("VIS_MATRICULE");
            int numeroRapport = rs.getInt("RAP_NUM");
            int numeroPraticien = rs.getInt("PRA_NUM");
            Date dateRapport = rs.getDate("RAP_DATE");
            String bilanRapport = rs.getString("RAP_BILAN");
            String motifRapport = rs.getString("RAP_MOTIF");
            lesRapportVisites.add(new RapportVisite(matriculeVisiteur, numeroRapport, numeroPraticien, dateRapport, motifRapport, bilanRapport));
        }
        return lesRapportVisites;
    }

    /**
     * Creation d'un nouveau rapport de visite
     *
     * @param unRapportVisite le nouveau rapport de visite a ajouter
     * @return nb : nombres de lignes affectées
     * @throws SQLException
     */
    public static int insert(RapportVisite unRapportVisite) throws SQLException {
        int nb;
        Jdbc jdbc = Jdbc.getInstance();
        String requete = "INSERT INTO RAPPORT_VISITE (VIS_MATRICULE, RAP_NUM, PRA_NUM , RAP_DATE, RAP_BILAN, RAP_MOTIF) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, unRapportVisite.getMatriculeVisiteur());
        pstmt.setInt(2, unRapportVisite.getNumeroRapport());
        pstmt.setInt(3, unRapportVisite.getNumeroPraticien());
        pstmt.setDate(4, unRapportVisite.getDateRapport());
        pstmt.setString(5, unRapportVisite.getMotifRapport());
        pstmt.setString(6, unRapportVisite.getBilanRapport());
        nb = pstmt.executeUpdate();
        return nb;
    }
}
