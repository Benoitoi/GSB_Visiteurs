package test.modele.metier;

import java.sql.Date;
import java.text.ParseException;
import modele.metier.Laboratoire;
import modele.metier.Secteur;
import modele.metier.Visiteur;
import util.DateConvertion;

/**
 * Classe de test unitaire pour la classe Visiteur
 *
 * @author btssio
 * @version 1.0
 *
 */
public class TestVisiteur {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("\nTest classe Visiteur\n");
        Visiteur visiteur = null;
        // Test n°1 : instanciation et accesseurs
        System.out.println("\nTest n°1 : instanciation et accesseurs");
        Secteur secteur = new Secteur("N", "Nord");
        Laboratoire laboratoire = new Laboratoire("TL", "TESTLABO", "chef test");
        visiteur = new Visiteur("a0", "testnom", "testprenom", "1 rue du test", "00000", "testville", doDate("01-jan-2000"), secteur, laboratoire);

        System.out.println("Etat du visiteur : " + visiteur.toString());

        // Test n°2 : mutateurs + accesseurs
        //mutateurs
        System.out.println("\nTest n°2 : mutateurs + accesseurs");
        visiteur.setMatricule("b1");
        visiteur.setNom("nouveaunom");
        visiteur.setPrenom("nouveauprenom");
        visiteur.setAdresse("2 nouvelle rue");
        visiteur.setCodePostal("11111");
        visiteur.setVille("nouvelleville");
        visiteur.setDateEmbauche(doDate("31-dec-1999"));
        Secteur secteur2 = new Secteur("E", "Est");
        visiteur.setSecteur(secteur2);
        Laboratoire laboratoire2 = new Laboratoire("LT", "LABTEST", "test chef");
        visiteur.setLaboratoire(laboratoire2);

        //accesseurs
        System.out.println("Etat du visiteur : ");
        System.out.println("matricule : " + visiteur.getMatricule());
        System.out.println("nom : " + visiteur.getNom());
        System.out.println("prenom : " + visiteur.getPrenom());
        System.out.println("adresse : " + visiteur.getAdresse());
        System.out.println("code postal : " + visiteur.getCodePostal());
        System.out.println("ville : " + visiteur.getVille());
        System.out.println("date embauche : " + visiteur.getDateEmbauche());
        System.out.println("code secteur : " + visiteur.getSecteur().getCodeSecteur());
        System.out.println("code laboratoire : " + visiteur.getLaboratoire().getCodeLaboratoire());
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
