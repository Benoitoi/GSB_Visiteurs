package test.modele.dao;

import controleurs.CtrlVisiteur;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.dao.DaoSecteur;

/**
 *
 * @author btssio
 */
public class TestDaoSecteur {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Test DaoVisiteur\n");
        try {
            System.out.println("Test 1 : obtenir tous les secteurs\n");
            System.out.println(DaoSecteur.getAllSecteurs());
        } catch (SQLException ex) {
            Logger.getLogger(CtrlVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
