package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.metier.Secteur;

/**
 *
 * @author pnoel
 */
public class DaoSecteur {

    /**
     * Extraction de tout les secteurs
     *
     * @return ArrayList<Secteur> : collection de secteurs
     * @throws SQLException
     */
    public static ArrayList<Secteur> getAllSecteurs() throws SQLException {
        ArrayList<Secteur> lesSecteurs = new ArrayList<>();
        Jdbc jdbc = Jdbc.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM SECTEUR";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String code = rs.getString("SEC_CODE");
            String libelle = rs.getString("SEC_LIBELLE");
            lesSecteurs.add(new Secteur(code, libelle));
        }
        rs.close();
        pstmt.close();
        return lesSecteurs;
    }

    /**
     * Extraction d'un secteur selon son code
     *
     * @param codeSecteur
     * @return leSecteurs
     * @throws SQLException
     */
    public static Secteur getOneByCode(String codeSecteur) throws SQLException {
        Secteur leSecteurs = null;
        Jdbc jdbc = Jdbc.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM SECTEUR WHERE SEC_CODE= ?";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, codeSecteur);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String code = rs.getString("SEC_CODE");
            String libelle = rs.getString("SEC_LIBELLE");
            leSecteurs = new Secteur(code, libelle);
        }
        rs.close();
        pstmt.close();
        return leSecteurs;
    }
}
