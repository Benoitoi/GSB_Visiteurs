package test.modele.dao;

import controleurs.CtrlVisiteur;
import java.sql.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.dao.DaoRapportVisite;
import modele.metier.Laboratoire;
import modele.metier.Praticien;
import modele.metier.RapportVisite;
import modele.metier.Secteur;
import modele.metier.TypePraticien;
import modele.metier.Visiteur;
import util.DateConvertion;

/**
 *
 * @author btssio
 */
public class TestDaoRapportVisite {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Test DaoRapportVisite\n");
        try {
            System.out.println("Test 1 : obtenir tous les rapports de visite");
            System.out.println(DaoRapportVisite.getAllRapportsVisite());
            System.out.println("Test 2 : un rapport par un code innexistant\n");
            if(DaoRapportVisite.getOneByNum(-1) == null) {
                System.out.println("succès");
            } else {
                System.out.println("échec");
            }
            System.out.println("Test 3 : un rapport par un code existant\n");
            if(DaoRapportVisite.getOneByNum(11) == null) {
                System.out.println("échec");
            } else {
                System.out.println("succès");
            }
            System.out.println("Test 4 : Créer un nouveau rapport\n");
            Date sqlDate = null;
            RapportVisite rapportVisite = null;

            Secteur secteur = new Secteur("N", "Nord");
            Laboratoire laboratoire = new Laboratoire("TL", "TESTLABO", "chef test");
            sqlDate = doDate("01-jan-2000");
            Visiteur visiteur = new Visiteur("zzz", "testnom", "testprenom", "1 rue du test", "00000", "testville", sqlDate, secteur, laboratoire);

            TypePraticien typePraticien = new TypePraticien("codeTypeTest", "libelleLieuTypeTest", "typeLieuTest");
            Praticien praticien = new Praticien(31, "nomPraticienTest", "prenomPraticienTest", "3 rue du test", 44000, "villeTest", 0.5f, typePraticien);
            sqlDate = doDate("31-dec-1999");
            int numRapport = 99;
            rapportVisite = new RapportVisite(visiteur, numRapport, praticien, sqlDate, "motifRapportTest", "BilanRapportTest");
            System.out.println(DaoRapportVisite.insert(rapportVisite));
            System.out.println("Test 5 : affichage de ce rapport");
            System.out.println(DaoRapportVisite.getOneByNum(numRapport));
            System.out.println("Test 6 : mise à jour de ce rapport");
            rapportVisite.setMotifRapport("NOUVEAU MOTIF");
            System.out.println(DaoRapportVisite.update(rapportVisite));
            System.out.println(DaoRapportVisite.getOneByNum(numRapport));
            System.out.println("Test 7 : suppression de ce rapport");
            System.out.println(DaoRapportVisite.delete(numRapport));

        } catch (SQLException ex) {
            Logger.getLogger(CtrlVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Date doDate(String aDate) {
        Date sqlDate = null;
        try {
            sqlDate = DateConvertion.convert(aDate);
        } catch (ParseException ex) {
            System.out.println("Mauvaise date.");
        }
        return sqlDate;
    }
}
