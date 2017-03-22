package test.modele.dao;

import controleurs.CtrlVisiteur;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
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
            System.out.println("\nTest 2 : un rapport par un code inexistant");
            RapportVisite rapportVisite = DaoRapportVisite.getOneByNum(-1);
            if (rapportVisite == null) {
                System.out.println("succès Auncun rapport trouvé");
            } else {
                System.out.println("échec");
                System.out.println(rapportVisite);
            }
            System.out.println("\nTest 3 : un rapport par un code existant");
            rapportVisite = DaoRapportVisite.getOneByNum(11);
            if (rapportVisite == null) {
                System.out.println("échec le rapport n'a pas été trouvé");
            } else {
                System.out.println("succès");
                System.out.println(rapportVisite);
            }
            System.out.println("\nTest 4 : Créer un nouveau rapport");
            Date sqlDate = null;
            rapportVisite = null;

            Secteur secteur = new Secteur("N", "Nord");
            Laboratoire laboratoire = new Laboratoire("TL", "TESTLABO", "chef test");
            sqlDate = doDate("01-jan-2000");
            Visiteur visiteur = new Visiteur("zzz", "testnom", "testprenom", "1 rue du test", "00000", "testville", sqlDate, secteur, laboratoire);

            TypePraticien typePraticien = new TypePraticien("codeTypeTest", "libelleLieuTypeTest", "typeLieuTest");
            Praticien praticien = new Praticien(31, "nomPraticienTest", "prenomPraticienTest", "3 rue du test", 44000, "villeTest", 0.5f, typePraticien);
            sqlDate = doDate("31-dec-1999");
            rapportVisite = new RapportVisite(visiteur, -1, praticien, sqlDate, "motifRapportTest", "BilanRapportTest");
            System.out.println(DaoRapportVisite.insert(rapportVisite));
            int numRap = actualisation();
            rapportVisite.setNumeroRapport(numRap);
            System.out.println("\nTest 5 : affichage de ce rapport");
            System.out.println(DaoRapportVisite.getOneByNum(numRap));
            System.out.println("\nTest 6 : mise à jour de ce rapport");
            rapportVisite.setMotifRapport("NOUVEAU MOTIF");
            System.out.println(DaoRapportVisite.update(rapportVisite));
            System.out.println(DaoRapportVisite.getOneByNum(numRap));
            System.out.println("\nTest 7 : suppression de ce rapport");
            System.out.println(DaoRapportVisite.delete(numRap));
            if (DaoRapportVisite.getOneByNum(numRap) == null) {
                System.out.println("succès bien supprimé");
            } else {
                System.out.println("échec pas supprimé");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CtrlVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static int actualisation() {
        ArrayList<RapportVisite> lesRapportsVisite = null;
        try {
            lesRapportsVisite = DaoRapportVisite.getAllRapportsVisite();
        } catch (SQLException ex) {
            Logger.getLogger(TestDaoRapportVisite.class.getName()).log(Level.SEVERE, null, ex);
        }
        //le numéro de rapport est autoincrémenté, pour le trouvé ca sera le numéro le plus élevé présent dans la base
        int numeroRapport = -1;
        for (RapportVisite unRapportVisite : lesRapportsVisite) {
            if (unRapportVisite.getNumeroRapport() > numeroRapport) {
                numeroRapport = unRapportVisite.getNumeroRapport();
            }
        }
        return numeroRapport;
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
