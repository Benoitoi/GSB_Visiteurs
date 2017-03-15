package test.modele.dao;

import controleurs.CtrlVisiteur;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.dao.DaoFamille;

/**
 *
 * @author btssio
 */
public class TestDaoFamille {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Test DaoFamille\n");
        try {
            System.out.println("Test 1 : obtenir tous les familles");
            System.out.println(DaoFamille.getAllFamilles());
            System.out.println("Test 2 : un famille par un code innexistant\n");
            if(DaoFamille.getOneByCode("nonexistant") == null) {
                System.out.println("succès");
            } else {
                System.out.println("échec");
            }
            System.out.println("Test 3 : un famille par un code existant\n");
            if(DaoFamille.getOneByCode("AIN") == null) {
                System.out.println("échec");
            } else {
                System.out.println("succès");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrlVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
