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
        return lesSecteurs;
    }
}
