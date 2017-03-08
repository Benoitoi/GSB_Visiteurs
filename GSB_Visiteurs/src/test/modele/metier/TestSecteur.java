package test.modele.metier;

import modele.metier.Secteur;

/**
 * Classe de test unitaire pour la classe Secteur
 *
 * @author btssio
 * @version 1.0
 *
 */
public class TestSecteur {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("\nTest classe Secteur\n");
        Secteur secteur = null;
        // Test n°1 : instanciation et accesseurs
        System.out.println("\nTest n°1 : instanciation et accesseurs");
        secteur = new Secteur("E", "Est");

        System.out.println("Etat du secteur : " + secteur.toString());

        // Test n°2 : mutateurs + accesseurs
        //mutateurs
        System.out.println("\nTest n°2 : mutateurs + accesseurs");
        secteur.setCodeSecteur("N");
        secteur.setLibelleSecteur("Nord");

        //accesseurs
        System.out.println("Etat du secteur : ");
        System.out.println("code : " + secteur.getCodeSecteur());
        System.out.println("libelle : " + secteur.getLibelleSecteur());
    }
}
