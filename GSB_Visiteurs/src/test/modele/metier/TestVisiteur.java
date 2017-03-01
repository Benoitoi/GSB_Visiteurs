package test.modele.metier;

import java.util.Date;
import modele.metier.Visiteur;

/**
 * Classe de test unitaire pour la classe Visiteur
 *
 * @author btssio
 * @version 1.0
 *
 */
public class TestVisiteur {

    public static void main(String[] args) {
        System.out.println("\nTest classe Visiteur\n");
        Visiteur visiteur = null;
        // Test n°1 : instanciation et accesseurs
        System.out.println("\nTest n°1 : instanciation et accesseurs");
        visiteur = new Visiteur("a0", "testnom", "testprenom", "1 rue du test", "00000", "testville", new Date("01-jan-00"), "S", "SW");

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
        visiteur.setDateEmbauche(new Date("31-dec-99"));
        visiteur.setCodeSecteur("P");
        visiteur.setCodeLaboratoire("GY");

        //accesseurs
        System.out.println("Etat du visiteur : ");
        System.out.println("matricule : " + visiteur.getMatricule());
        System.out.println("nom : " + visiteur.getNom());
        System.out.println("prenom : " + visiteur.getPrenom());
        System.out.println("adresse : " + visiteur.getAdresse());
        System.out.println("code postal : " + visiteur.getCodePostal());
        System.out.println("ville : " + visiteur.getVille());
        System.out.println("date embauche : " + visiteur.getDateEmbauche());
        System.out.println("code secteur : " + visiteur.getCodeSecteur());
        System.out.println("code laboratoire : " + visiteur.getCodeLaboratoire());
    }
}
