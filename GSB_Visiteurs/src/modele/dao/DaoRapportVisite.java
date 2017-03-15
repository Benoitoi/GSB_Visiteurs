package modele.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.metier.Praticien;
import modele.metier.RapportVisite;
import modele.metier.Visiteur;

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
            Visiteur visiteur = DaoVisiteur.getOneByMatricule(matriculeVisiteur);
            Praticien praticien = DaoPraticien.getOneByNum(numeroPraticien);
            lesRapportVisites.add(new RapportVisite(visiteur, numeroRapport, praticien, dateRapport, motifRapport, bilanRapport));
        }
        rs.close();
        pstmt.close();
        return lesRapportVisites;
    }

    /**
     * Extraction d'un type de rapport selon son numero
     *
     * @param numRapport
     * @return leRapportVisite
     * @throws SQLException
     */
    public static RapportVisite getOneByNum(int numRapport) throws SQLException {
        RapportVisite leRapportVisite = null;
        Jdbc jdbc = Jdbc.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM RAPPORT_VISITE WHERE RAP_NUM= ?";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setInt(1, numRapport);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String matriculeVisiteur = rs.getString("VIS_MATRICULE");
            int numeroRapport = rs.getInt("RAP_NUM");
            int numeroPraticien = rs.getInt("PRA_NUM");
            Date dateRapport = rs.getDate("RAP_DATE");
            String bilanRapport = rs.getString("RAP_BILAN");
            String motifRapport = rs.getString("RAP_MOTIF");
            Visiteur visiteur = DaoVisiteur.getOneByMatricule(matriculeVisiteur);
            Praticien praticien = DaoPraticien.getOneByNum(numeroPraticien);
            leRapportVisite = new RapportVisite(visiteur, numRapport, praticien, dateRapport, bilanRapport, motifRapport);
        }
        rs.close();
        pstmt.close();
        return leRapportVisite;
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
        pstmt.setString(1, unRapportVisite.getVisiteur().getMatricule());
        pstmt.setInt(2, unRapportVisite.getNumeroRapport());
        pstmt.setInt(3, unRapportVisite.getPraticien().getNumero());
        pstmt.setDate(4, unRapportVisite.getDateRapport());
        pstmt.setString(5, unRapportVisite.getMotifRapport());
        pstmt.setString(6, unRapportVisite.getBilanRapport());
        nb = pstmt.executeUpdate();
        pstmt.close();
        return nb;
    }

    /**
     * Cette méthode met à jour un rapport de visite,
     *
     * @param unRapportVisite le rapport de visite à mettre è jour
     * @return nb nombre de lignes affectées
     * @throws SQLException
     */
    public static int update(RapportVisite unRapportVisite) throws SQLException {
        int nb;
        Jdbc jdbc = Jdbc.getInstance();
        String requete = "UPDATE RAPPORT_VISITE SET VIS_MATRICULE= ?, RAP_NUM= ?, PRA_NUM= ?, RAP_DATE= ?, RAP_BILAN= ?, RAP_MOTIF= ? WHERE RAP_NUM= ?";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, unRapportVisite.getVisiteur().getMatricule());
        pstmt.setInt(2, unRapportVisite.getNumeroRapport());
        pstmt.setInt(3, unRapportVisite.getPraticien().getNumero());
        pstmt.setDate(4, unRapportVisite.getDateRapport());
        pstmt.setString(5, unRapportVisite.getMotifRapport());
        pstmt.setString(6, unRapportVisite.getBilanRapport());
        pstmt.setInt(7, unRapportVisite.getNumeroRapport());
        nb = pstmt.executeUpdate();
        return nb;
    }

    /**
     * Cette méthode supprime un rapport
     *
     * @param numeroRapport le rapport à supprimer
     * @return nb nombre de lignes affectées
     * @throws SQLException
     */
    public static int delete(int numeroRapport) throws SQLException {
        int nb;
        Jdbc jdbc = Jdbc.getInstance();
        String requete = "DELETE FROM RAPPORT_VISITE WHERE RAP_NUM= ?";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setInt(1, numeroRapport);
        nb = pstmt.executeUpdate();
        return nb;
    }
}
