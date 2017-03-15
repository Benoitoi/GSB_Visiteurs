package test.modele.dao;

import controleurs.CtrlVisiteur;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.dao.DaoTypePraticien;

/**
 *
 * @author btssio
 */
public class TestDaoTypePraticien {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Test DaoTypePraticien\n");
        try {
            System.out.println("Test 1 : obtenir tous les types de praticien");
            System.out.println(DaoTypePraticien.getAll());
            System.out.println("Test 2 : obtenir un type de praticien par un code innexistant\n");
            if(DaoTypePraticien.getOneByCode("nonexistant") == null) {
                System.out.println("succès");
            } else {
                System.out.println("échec");
            }
            System.out.println("Test 3 : obtenir un type de praticien par un code existant\n");
            if(DaoTypePraticien.getOneByCode("MV") == null) {
                System.out.println("échec");
            } else {
                System.out.println("succès");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrlVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
