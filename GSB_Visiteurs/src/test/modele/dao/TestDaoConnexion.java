package test.modele.dao;

import controleurs.CtrlConnexion;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.dao.DaoConnexion;

/**
 *
 * @author btssio
 */
public class TestDaoConnexion {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Test DaoConnexion\n");
        try {
            System.out.println("Test 1 : verifierInfosConnexion");
            System.out.println("Test 1 : cas bon login et bon mdp");
            DaoConnexion.verifierInfosConnexion("swiss", "18-jun-2003");
            System.out.println("Test 1 : cas bon login et mauvais mdp");
            DaoConnexion.verifierInfosConnexion("swiss", "18-jn-2003");
            System.out.println("Test 1 : cas mauvais login et bon mdp");
            DaoConnexion.verifierInfosConnexion("swis", "18-jun-2003");
            System.out.println("Test 1 : cas mauvais login et mauvais mdp");
            DaoConnexion.verifierInfosConnexion("swis", "18-jn-2003");
            
            System.out.println();
            System.out.println("Test 2 : Récupération de toutes les informations de l'utilisateur qui se connecte");
            System.out.println("Test 2 : cas bon login et bon mdp");
            System.out.println(DaoConnexion.getConnectedVisiteur("swiss", "18-jun-2003"));
            System.out.println("Test 2 : cas bon login et mauvais mdp");
            System.out.println(DaoConnexion.getConnectedVisiteur("swiss", "18-jn-2003"));
            System.out.println("Test 2 : cas mauvais login et bon mdp");
            System.out.println(DaoConnexion.getConnectedVisiteur("swis", "18-jun-2003"));
            System.out.println("Test 2 : cas mauvais login et mauvais mdp");
            System.out.println(DaoConnexion.getConnectedVisiteur("swis", "18-jn-2003"));
        } catch (SQLException ex) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
