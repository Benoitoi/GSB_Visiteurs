package test.modele.dao;

import controleurs.CtrlVisiteur;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.dao.DaoOffre;
import modele.metier.Famille;
import modele.metier.Laboratoire;
import modele.metier.Medicament;
import modele.metier.Offre;
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
public class TestDaoOffre {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Test DaoSecteur\n");
        try {
            System.out.println("Test 1 : obtenir toutes les offres\n");
            System.out.println(DaoOffre.getAllOffres());
            System.out.println("Test 2 : obtenir les offres d'un rapport innexistant\n");
            ArrayList<Offre> lesOffre = DaoOffre.getAllByRapport(-1);
            if (lesOffre.isEmpty()) {
                System.out.println("succès Auncun rapport trouvé");
            } else {
                System.out.println("échec");
                System.out.println(lesOffre);
            }
            System.out.println("Test 3 : obtenir les offres d'un rapport existant\n");
            lesOffre = DaoOffre.getAllByRapport(13);
            if (lesOffre == null) {
                System.out.println("échec Auncun rapport trouvé");
            } else {
                System.out.println("succès");
                System.out.println(lesOffre);
            }
            System.out.println("Test 4 : créer une offre par un numéro de rapport existant\n");

            Date sqlDate = null;
            Offre offre = null;

            Famille famille = new Famille("codeFamilleTest", "libelleFamilleTest");
            Medicament medicament = new Medicament("3MYC7", "nomCommercialTest", famille, "compositionTest", "testEffets", "testContreIndication", 1.2f);

            Secteur secteur = new Secteur("N", "Nord");
            Laboratoire laboratoire = new Laboratoire("TL", "TESTLABO", "chef test");
            sqlDate = doDate("01-jan-2000");
            Visiteur visiteur = new Visiteur("zzz", "testnom", "testprenom", "1 rue du test", "00000", "testville", sqlDate, secteur, laboratoire);

            TypePraticien typePraticien = new TypePraticien("codeTypeTest", "libelleLieuTypeTest", "typeLieuTest");
            Praticien praticien = new Praticien(999, "nomPraticienTest", "prenomPraticienTest", "3 rue du test", 44000, "villeTest", 0.5f, typePraticien);

            sqlDate = doDate("31-dec-1999");
            RapportVisite rapport = new RapportVisite(visiteur, 11, praticien, sqlDate, "motifRapportTest", "BilanRapportTest");
            offre = new Offre(visiteur, rapport, medicament, 5);
            System.out.println("Test 4 : créer une offre\n");
            System.out.println(DaoOffre.insert(offre));
            System.out.println("Test 5 : afficher cette nouvelle offre");
            System.out.println(DaoOffre.getOne(offre));

            System.out.println("Test 6 : mettre à jour une offre existante\n");
            offre.setQuantiteOffre(75);
            System.out.println(DaoOffre.update(offre));
            System.out.println(DaoOffre.getOne(offre));
            System.out.println("Test 7 : supprimer l'offre existante\n");
            System.out.println(DaoOffre.delete(offre));
            if (DaoOffre.getOne(offre) == null) {
                System.out.println("succès bien supprimé");
            } else {
                System.out.println("échec pas supprimé");
                System.out.println(offre);
            }
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
