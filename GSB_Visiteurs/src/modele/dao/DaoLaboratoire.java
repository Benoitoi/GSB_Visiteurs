package modele.dao;

import gsb_visiteurs.Connexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.metier.Laboratoire;

/**
 *
 * @author pnoel
 */
public class DaoLaboratoire {

    /**
     * Extraction de tout les laboratoires
     *
     * @return ArrayList<Laboratoire> : collection de laboratoires
     * @throws SQLException
     */
    public static ArrayList<Laboratoire> getAllLaboratoires() throws SQLException {
        ArrayList<Laboratoire> lesLaboratoires = new ArrayList<Laboratoire>();
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
