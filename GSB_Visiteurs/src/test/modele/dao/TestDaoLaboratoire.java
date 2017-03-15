package test.modele.dao;

import controleurs.CtrlVisiteur;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.dao.DaoLaboratoire;

/**
 *
 * @author btssio
 */
public class TestDaoLaboratoire {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Test DaoLaboratoire\n");
        try {
            System.out.println("Test 1 : obtenir tous les laboratoires");
            System.out.println(DaoLaboratoire.getAllLaboratoires());
            System.out.println("Test 2 : obtenir un laboratoire par un code innexistant\n");
            if(DaoLaboratoire.getOneByCode("nonexistant") == null) {
                System.out.println("succès");
            } else {
                System.out.println("échec");
            }
            System.out.println("Test 3 : obtenir un laboratoire par un code existant\n");
            if(DaoLaboratoire.getOneByCode("BC") == null) {
                System.out.println("échec");
            } else {
                System.out.println("succès");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrlVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
