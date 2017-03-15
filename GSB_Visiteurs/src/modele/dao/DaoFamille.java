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
        rs.close();
        pstmt.close();
        return lesFamilles;
    }

    /**
     * Extraction d'une famille son code
     *
     * @param codeFamille
     * @return laFamille
     * @throws SQLException
     */
    public static Famille getOneByCode(String codeFamille) throws SQLException {
        Famille laFamille = null;
        Jdbc jdbc = Jdbc.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM FAMILLE WHERE FAM_CODE= ?";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, codeFamille);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String famCode = rs.getString("FAM_CODE");
            String famLibelle = rs.getString("FAM_LIBELLE");
            laFamille = new Famille(famCode, famLibelle);
        }
        rs.close();
        pstmt.close();
        return laFamille;
    }
}
