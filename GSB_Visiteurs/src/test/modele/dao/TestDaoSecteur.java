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
        System.out.println("Test DaoSecteur\n");
        try {
            System.out.println("Test 1 : obtenir tous les secteurs\n");
            System.out.println(DaoSecteur.getAllSecteurs());
            System.out.println("Test 2 : obtenir un secteur par un code innexistant\n");
            if(DaoSecteur.getOneByCode("nonexistant") == null) {
                System.out.println("succès");
            } else {
                System.out.println("échec");
            }
            System.out.println("Test 3 : obtenir un secteur par un code existant\n");
            if(DaoSecteur.getOneByCode("E") == null) {
                System.out.println("échec");
            } else {
                System.out.println("succès");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrlVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
