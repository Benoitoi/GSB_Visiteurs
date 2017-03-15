package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.metier.Praticien;
import modele.metier.TypePraticien;

/**
 *
 * @author bjaouen
 */
public class DaoPraticien {

    /**
     * Extraction de tous les praticiens
     *
     * @return ArrayList<Praticien> : liste d'objets Praticien
     * @throws SQLException
     */
    public static ArrayList<Praticien> getAllPraticiens() throws SQLException {
        ArrayList<Praticien> lesPraticiens = new ArrayList<>();
        Jdbc jdbc = Jdbc.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM PRATICIEN";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            int numero = rs.getInt("PRA_NUM");
            String nom = rs.getString("PRA_NOM");
            String prenom = rs.getString("PRA_PRENOM");
            String adresse = rs.getString("PRA_ADRESSE");
            int codePostal = rs.getInt("PRA_CP");
            String ville = rs.getString("PRA_VILLE");
            float coefficientNotoriete = rs.getFloat("PRA_COEFNOTORIETE");
            String typeCode = rs.getString("TYP_CODE");
            TypePraticien typePraticien = DaoTypePraticien.getOneByCode(typeCode);
            lesPraticiens.add(new Praticien(numero, nom, prenom, adresse, codePostal, ville, coefficientNotoriete, typePraticien));
        }
        rs.close();
        pstmt.close();
        return lesPraticiens;
    }

    /**
     * Extraction d'un praticien, sur son numero
     *
     * @param numeroPraticien identifiant
     * @return objet Praticien
     * @throws SQLException
     */
    public static Praticien getOneByNum(int numeroPraticien) throws SQLException {
        Praticien unPraticien = null;
        Jdbc jdbc = Jdbc.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM PRATICIEN WHERE PRA_NUM= ?";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setInt(1, numeroPraticien);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int numero = rs.getInt("PRA_NUM");
            String nom = rs.getString("PRA_NOM");
            String prenom = rs.getString("PRA_PRENOM");
            String adresse = rs.getString("PRA_ADRESSE");
            int codePostal = rs.getInt("PRA_CP");
            String ville = rs.getString("PRA_VILLE");
            float coefficientNotoriete = rs.getFloat("PRA_COEFNOTORIETE");
            String typeCode = rs.getString("TYP_CODE");
            TypePraticien type = DaoTypePraticien.getOneByCode(typeCode);
            unPraticien = new Praticien(numero, nom, prenom, adresse, codePostal, ville, coefficientNotoriete, type);
        }
        rs.close();
        pstmt.close();
        return unPraticien;
    }
}
