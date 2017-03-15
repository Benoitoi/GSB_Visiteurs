package test.modele.metier;

import modele.metier.Famille;

/**
 * Classe de test unitaire pour la classe Famille
 *
 * @author btssio
 * @version 1.0
 *
 */
public class TestFamille {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("\nTest classe Famille\n");
        Famille famille = null;
        // Test n°1 : instanciation et accesseurs
        System.out.println("\nTest n°1 : instanciation et accesseurs");
        famille = new Famille("codeFamilleTest", "libelleFamilleTest");

        System.out.println("Etat de la famille : " + famille.toString());

        // Test n°2 : mutateurs + accesseurs
        //mutateurs
        System.out.println("\nTest n°2 : mutateurs + accesseurs");
        famille.setCodeFamille("nouveau code");
        famille.setLibelleFamille("test libelle famille");

        //accesseurs
        System.out.println("Etat de la famille : ");
        System.out.println("code : " + famille.getCodeFamille());
        System.out.println("libelle : " + famille.getLibelleFamille());
    }
}
