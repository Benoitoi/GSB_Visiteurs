package test.modele.dao;

import controleurs.CtrlVisiteur;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.dao.DaoVisiteur;

/**
 *
 * @author btssio
 */
public class TestDaoVisiteur {

    public static void main(String[] args) {
        System.out.println("Test DaoVisiteur\n");
        try {
            System.out.println("Test 1 : obtenir tous les visiteurs");
            System.out.println(DaoVisiteur.getAllVisiteurs());
            System.out.println("Test 2 : obtenir tous les secteurs\n");
            System.out.println(DaoVisiteur.getAllSecteurs());
            System.out.println("Test 3 : obtenir tous les laboratoires\n");
            System.out.println(DaoVisiteur.getAllLaboratoires());
        } catch (SQLException ex) {
            Logger.getLogger(CtrlVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
