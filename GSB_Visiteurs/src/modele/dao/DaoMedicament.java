package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.metier.Famille;
import modele.metier.Medicament;

/**
 *
 * @author bjaouen
 */
public class DaoMedicament {

    /**
     * Extraction de tous les médicaments
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<Medicament> getAllMedicaments() throws SQLException {
        ArrayList<Medicament> lesMedicaments = new ArrayList<>();
        Jdbc jdbc = Jdbc.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM MEDICAMENT";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String depotLegal = rs.getString("MED_DEPOTLEGAL");
            String nomCommercial = rs.getString("MED_NOMCOMMERCIAL");
            String codeFamille = rs.getString("FAM_CODE");
            String composition = rs.getString("MED_COMPOSITION");
            String effets = rs.getString("MED_EFFETS");
            String contreIndication = rs.getString("MED_CONTREINDIC");
            float prixEchantillon = rs.getFloat("MED_PRIXECHANTILLON");
            Famille famille = DaoFamille.getOneByCode(codeFamille);
            lesMedicaments.add(new Medicament(depotLegal, nomCommercial, famille, composition, effets, contreIndication, prixEchantillon));
        }
        rs.close();
        pstmt.close();
        return lesMedicaments;
    }

    /**
     * Extraction d'un médicament selon son dépôt légal
     *
     * @param leDepotLegal
     * @return leMedicament
     * @throws SQLException
     */
    public static Medicament getOneByDepotLegal(String leDepotLegal) throws SQLException {
        Medicament leMedicament = null;
        Jdbc jdbc = Jdbc.getInstance();
        //préparer la requête
        String requete = "SELECT * FROM MEDICAMENT WHERE MED_DEPOTLEGAL= ?";
        PreparedStatement pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, leDepotLegal);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String depotLegal = rs.getString("MED_DEPOTLEGAL");
            String nomCommercial = rs.getString("MED_NOMCOMMERCIAL");
            String codeFamille = rs.getString("FAM_CODE");
            String composition = rs.getString("MED_COMPOSITION");
            String effets = rs.getString("MED_EFFETS");
            String contreIndication = rs.getString("MED_CONTREINDIC");
            float prixEchantillon = rs.getFloat("MED_PRIXECHANTILLON");
            Famille famille = DaoFamille.getOneByCode(codeFamille);
            leMedicament = new Medicament(depotLegal, nomCommercial, famille, composition, effets, contreIndication, prixEchantillon);
        }
        rs.close();
        pstmt.close();
        return leMedicament;
    }
}
