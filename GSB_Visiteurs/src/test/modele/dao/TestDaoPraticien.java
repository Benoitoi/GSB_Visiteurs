package test.modele.dao;

import controleurs.CtrlVisiteur;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.dao.DaoPraticien;

/**
 *
 * @author btssio
 */
public class TestDaoPraticien {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Test DaoPraticien\n");
        try {
            System.out.println("Test 1 : obtenir tous les praticiens");
            System.out.println(DaoPraticien.getAllPraticiens());
            System.out.println("Test 2 : obtenir un praticien par un code innexistant\n");
            if(DaoPraticien.getOneByNum(-1) == null) {
                System.out.println("succès");
            } else {
                System.out.println("échec");
            }
            System.out.println("Test 3 : obtenir un praticien par un code existant\n");
            if(DaoPraticien.getOneByNum(10) == null) {
                System.out.println("échec");
            } else {
                System.out.println("succès");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrlVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
