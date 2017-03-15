package test.modele.dao;

import controleurs.CtrlVisiteur;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.dao.DaoMedicament;

/**
 *
 * @author btssio
 */
public class TestDaoMedicament {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Test DaoMedicament\n");
        try {
            System.out.println("Test 1 : obtenir tous les medicaments");
            System.out.println(DaoMedicament.getAllMedicaments());
            System.out.println("Test 2 : obtenir un medicament par un depot legal innexistant\n");
           if(DaoMedicament.getOneByDepotLegal("nonexistant") == null) {
                System.out.println("succès");
            } else {
                System.out.println("échec");
            }
            System.out.println("Test 3 : obtenir un medicament par un depot legal existant\n");
            if(DaoMedicament.getOneByDepotLegal("DEPRIL9") == null) {
                System.out.println("échec");
            } else {
                System.out.println("succès");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrlVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
