package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.metier.Famille;

/**
 *
 * @author bjaouen
 */
public class DaoFamille {

    /**
     * Extraction de toutes les familles
     * 
     * @return
     * @throws SQLException 
     */
    public static ArrayList<Famille> getAllFamilles() throws SQLException {
        ArrayList<Famille> lesFamilles = new ArrayList<>();
        Jdbc jdbc = Jdbc.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM FAMILLE";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String famCode = rs.getString("FAM_CODE");
            String famLibelle = rs.getString("FAM_LIBELLE");
            lesFamilles.add(new Famille(famCode, famLibelle));
        }
        return lesFamilles;
    }
}
