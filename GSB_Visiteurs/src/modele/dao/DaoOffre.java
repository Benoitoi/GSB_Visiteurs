package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.metier.Offre;

/**
 *
 * @author bjaouen
 */
public class DaoOffre {

    /**
     * Extraction de toutes les offres échantillon
     *
     * @return
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
            String medicament = rs.getString("MED_DEPOTLEGAL");
            int quantiteOffre = rs.getInt("OFF_QTE");
            lesOffresEchantillons.add(new Offre(matriculeVisiteur, numeroRapport, medicament, quantiteOffre));
        }
        return lesOffresEchantillons;
    }
    
    /**
     * Creation d'une nouvelle offre
     *
     * @param uneOffre la nouvelle offre a ajouter
     * @return nb : nombres de lignes affectées
     * @throws SQLException
     */
    public static int insert(Offre uneOffre) throws SQLException {
        int nb;
        Jdbc jdbc = Jdbc.getInstance();
        String requete = "INSERT INTO OFFRIR (VIS_MATRICULE, RAP_NUM, MED_DEPOTLEGAL , OFF_QTE) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, uneOffre.getMatriculeVisiteur());
        pstmt.setInt(2, uneOffre.getNumeroRapport());
        pstmt.setString(3, uneOffre.getMedicament());
        pstmt.setInt(4, uneOffre.getQuantiteOffre());
        nb = pstmt.executeUpdate();
        return nb;
    }
}
