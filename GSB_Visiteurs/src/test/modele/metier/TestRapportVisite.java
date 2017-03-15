/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.modele.metier;

import java.sql.Date;
import java.text.ParseException;
import modele.metier.Laboratoire;
import modele.metier.Praticien;
import modele.metier.RapportVisite;
import modele.metier.Secteur;
import modele.metier.TypePraticien;
import modele.metier.Visiteur;
import util.DateConvertion;

/**
 *
 * @author Benoit
 */
public class TestRapportVisite {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("\nTest classe RapportVisite\n");
        Date sqlDate = null;
        RapportVisite rapportVisite = null;
        
        Secteur secteur = new Secteur("N", "Nord");
        Laboratoire laboratoire = new Laboratoire("TL", "TESTLABO", "chef test");
        sqlDate = doDate("01-jan-2000");
        Visiteur visiteur = new Visiteur("a0", "testnom", "testprenom", "1 rue du test", "00000", "testville", sqlDate, secteur, laboratoire);
        
        TypePraticien typePraticien = new TypePraticien("codeTypeTest", "libelleLieuTypeTest", "typeLieuTest");
        Praticien praticien = new Praticien(999, "nomPraticienTest", "prenomPraticienTest", "3 rue du test", 44000, "villeTest", 0.5f, typePraticien);

        // Test n°1 : instanciation et accesseurs
        System.out.println("\nTest n°1 : instanciation et accesseurs");
        sqlDate = doDate("31-dec-1999");
        rapportVisite = new RapportVisite(visiteur, 99, praticien, sqlDate, "motifRapportTest", "BilanRapportTest");
        
        System.out.println("Etat du rapport de visite : " + rapportVisite.toString());

        // Test n°2 : mutateurs + accesseurs
        //mutateurs
        System.out.println("\nTest n°2 : mutateurs + accesseurs");
        
        visiteur.setVille("Nantes");
        rapportVisite.setVisiteur(visiteur);
        rapportVisite.setNumeroRapport(100);
        praticien.setVille("Bordeau");
        rapportVisite.setPraticien(praticien);
        sqlDate = doDate("01-jan-2000");
        rapportVisite.setDateRapport(sqlDate);
        rapportVisite.setMotifRapport("test rapport motif");
        rapportVisite.setBilanRapport("test rapport bilan");

        //accesseurs
        System.out.println("Etat du rapport de visite : ");
        System.out.println("Matricule visiteur : " + rapportVisite.getVisiteur().getMatricule());
        System.out.println("Numéro rapport : " + rapportVisite.getNumeroRapport());
        System.out.println("Numéro praticien : " + rapportVisite.getPraticien().getNumero());
        System.out.println("Date rapport : " + rapportVisite.getDateRapport());
        System.out.println("Motif rapport : " + rapportVisite.getMotifRapport());
        System.out.println("Bilan rapport : " + rapportVisite.getBilanRapport());
    }
    
    private static Date doDate(String date) {
        Date sqlDate = null;
        try {
            sqlDate = DateConvertion.convert(date);
        } catch (ParseException ex) {
            System.out.println("Mauvaise date.");
        }
        return sqlDate;
    }
}
