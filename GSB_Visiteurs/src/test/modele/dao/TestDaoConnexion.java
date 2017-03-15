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
            checkConnection(DaoConnexion.verifierInfosConnexion("swiss", "18-jun-2003"));
            System.out.println("Test 1 : cas bon login et mauvais mdp");
            checkConnection(DaoConnexion.verifierInfosConnexion("swiss", "18-jn-2003"));
            System.out.println("Test 1 : cas mauvais login et bon mdp");
            checkConnection(DaoConnexion.verifierInfosConnexion("swis", "18-jun-2003"));
            System.out.println("Test 1 : cas mauvais login et mauvais mdp");
            checkConnection(DaoConnexion.verifierInfosConnexion("swis", "18-jn-2003"));

            System.out.println();
            System.out.println("Test 2 : Récupération de toutes les informations de l'utilisateur qui se connecte");
            System.out.println("Test 2 : cas bon login et bon mdp");
            if (DaoConnexion.getConnectedVisiteur("swiss", "18-jun-2003") == null) {
                System.out.println("échec");
            } else {
                System.out.println("succès");
            }
            System.out.println("Test 2 : cas bon login et mauvais mdp");
            if (DaoConnexion.getConnectedVisiteur("swiss", "18-jn-2003") == null) {
                System.out.println("succès");
            } else {
                System.out.println("échec");
            }
            System.out.println("Test 2 : cas mauvais login et bon mdp");
            if (DaoConnexion.getConnectedVisiteur("swis", "18-jun-2003") == null) {
                System.out.println("succès");
            } else {
                System.out.println("échec");
            }
            System.out.println("Test 2 : cas mauvais login et mauvais mdp");
            if (DaoConnexion.getConnectedVisiteur("swis", "18-jn-2003") == null) {
                System.out.println("succès");
            } else {
                System.out.println("échec");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void checkConnection(int code) {
        if (code == 11) {
            System.out.println("Vous êtes bien connecté!");
        } else if (code == 10) {
            System.out.println("Mauvais mot de passe.");
        } else if (code == 00) {
            System.out.println("Login et mot de passe incorrects.");
        } else if (code == 01) {
            System.out.println("Login incorrect");
        }
    }
}
