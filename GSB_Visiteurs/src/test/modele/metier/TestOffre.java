package test.modele.metier;

import java.sql.Date;
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
 * Classe de test unitaire pour la classe Offre
 *
 * @author btssio
 * @version 1.0
 *
 */
public class TestOffre {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("\nTest classe Offre\n");
        Date sqlDate = null;
        Offre offre = null;
        
        Famille famille = new Famille("codeFamilleTest", "libelleFamilleTest");
        Medicament medicament = new Medicament("depotLegalTest", "nomCommercialTest", famille, "compositionTest", "testEffets", "testContreIndication", 1.2f);
        
        Secteur secteur = new Secteur("N", "Nord");
        Laboratoire laboratoire = new Laboratoire("TL", "TESTLABO", "chef test");
        sqlDate = doDate("01-jan-2000");
        Visiteur visiteur = new Visiteur("a0", "testnom", "testprenom", "1 rue du test", "00000", "testville", sqlDate, secteur, laboratoire);
        
        TypePraticien typePraticien = new TypePraticien("codeTypeTest", "libelleLieuTypeTest", "typeLieuTest");
        Praticien praticien = new Praticien(999, "nomPraticienTest", "prenomPraticienTest", "3 rue du test", 44000, "villeTest", 0.5f, typePraticien);
        
        sqlDate = doDate("31-dec-1999");
        RapportVisite rapport = new RapportVisite(visiteur, 99, praticien, sqlDate, "motifRapportTest", "BilanRapportTest");
        // Test n°1 : instanciation et accesseurs
        System.out.println("\nTest n°1 : instanciation et accesseurs");
        offre = new Offre(visiteur, rapport, medicament, 5);
        
        System.out.println("Etat de l'offre : " + offre.toString());

        // Test n°2 : mutateurs + accesseurs
        //mutateurs
        System.out.println("\nTest n°2 : mutateurs + accesseurs");
        
        visiteur.setMatricule("b1");
        offre.setVisiteur(visiteur);
        rapport.setNumeroRapport(100);
        offre.setRapport(rapport);
        medicament.setDepotLegal("test depot legal");
        offre.setMedicament(medicament);
        offre.setQuantiteOffre(3);

        //accesseurs
        System.out.println("Etat de l'offre : ");
        System.out.println("Matricule visiteur : " + offre.getVisiteur().getMatricule());
        System.out.println("Numéro rapport : " + offre.getRapport().getNumeroRapport());
        System.out.println("Medicament : " + offre.getMedicament().getDepotLegal());
        System.out.println("Quantite Offre : " + offre.getQuantiteOffre());
    }
    
    private static Date doDate(String date) {
        Date sqlDate = null;
        try {
            sqlDate = DateConvertion.convert(date);
        } catch (Exception ex) {
            System.out.println("Mauvaise date");
        }
        return sqlDate;
    }
}
