package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.metier.TypePraticien;

/**
 *
 * @author bjaouen
 */
public class DaoTypePraticien {

    /**
     * Extraction des types de praticien
     *
     * @return ArrayList<TypePraticien>
     * @throws SQLException
     */
    public static ArrayList<TypePraticien> getAll() throws SQLException {
        ArrayList<TypePraticien> lesTypesPraticiens = new ArrayList<>();
        Jdbc jdbc = Jdbc.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM TYPE_PRATICIEN";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String code = rs.getString("TYP_CODE");
            String libelle = rs.getString("TYP_LIBELLE");
            String lieu = rs.getString("TYP_LIEU");
            lesTypesPraticiens.add(new TypePraticien(code, libelle, lieu));
        }
        rs.close();
        pstmt.close();
        return lesTypesPraticiens;
    }

    /**
     * Extraction d'un type de praticien selon son code
     *
     * @param codeType
     * @return leType
     * @throws SQLException
     */
    public static TypePraticien getOneByCode(String codeType) throws SQLException {
        TypePraticien leType = null;
        Jdbc jdbc = Jdbc.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM TYPE_PRATICIEN WHERE TYP_CODE= ?";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, codeType);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String code = rs.getString("TYP_CODE");
            String libelle = rs.getString("TYP_LIBELLE");
            String lieu = rs.getString("TYP_LIEU");
            leType = new TypePraticien(code, libelle, lieu);
        }
        rs.close();
        pstmt.close();
        return leType;
    }
}
