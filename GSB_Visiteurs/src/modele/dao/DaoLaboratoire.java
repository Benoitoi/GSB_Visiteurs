package modele.dao;

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
        ArrayList<Laboratoire> lesLaboratoires = new ArrayList<>();
        Jdbc jdbc = Jdbc.getInstance();
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
        rs.close();
        pstmt.close();
        return lesLaboratoires;
    }

    /**
     * Extraction d'un laboratoire selon son code
     *
     * @param codeLabo
     * @return leLaboratoire
     * @throws SQLException
     */
    public static Laboratoire getOneByCode(String codeLabo) throws SQLException {
        Laboratoire leLaboratoire = null;
        Jdbc jdbc = Jdbc.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM LABO WHERE LAB_CODE= ?";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, codeLabo);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String code = rs.getString("LAB_CODE");
            String nom = rs.getString("LAB_NOM");
            String chefDeVente = rs.getString("LAB_CHEFVENTE");
            leLaboratoire = new Laboratoire(code, nom, chefDeVente);
        }
        rs.close();
        pstmt.close();
        return leLaboratoire;
    }
}
