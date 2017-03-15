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

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Test DaoVisiteur\n");
        try {
            System.out.println("Test 1 : obtenir tous les visiteurs");
            System.out.println(DaoVisiteur.getAllVisiteurs());
            System.out.println("Test 2 : obtenir un visiteur par un matricule innexistant\n");
            if (DaoVisiteur.getOneByMatricule("nonexistant") == null) {
                System.out.println("succès");
            } else {
                System.out.println("échec");
            }
            System.out.println("Test 3 : obtenir un visiteur par un matricule existant\n");
            if (DaoVisiteur.getOneByMatricule("c54") == null) {
                System.out.println("échec");
            } else {
                System.out.println("succès");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrlVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
