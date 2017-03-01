package test.modele.metier;

import java.util.Date;
import jdk.nashorn.internal.codegen.CompilerConstants;
import modele.metier.Laboratoire;

/**
 * Classe de test unitaire pour la classe Laboratoire
 *
 * @author btssio
 * @version 1.0
 *
 */
public class TestLaboratoire {

    public static void main(String[] args) {
        System.out.println("\nTest classe Laboratoire\n");
        Laboratoire laboratoire = null;
        // Test n°1 : instanciation et accesseurs
        System.out.println("\nTest n°1 : instanciation et accesseurs");
        laboratoire = new Laboratoire("LT", "LABTEST", "test chef");

        System.out.println("Etat du laboratoire : " + laboratoire.toString());

        // Test n°2 : mutateurs + accesseurs
        //mutateurs
        System.out.println("\nTest n°2 : mutateurs + accesseurs");
        laboratoire.setCodeLaboratoire("NL");
        laboratoire.setNomLaboratoire("NOUVEAULAB");
        laboratoire.setChefDeVente("nouveau chef");

        //accesseurs
        System.out.println("Etat du laboratoire : ");
        System.out.println("code : " + laboratoire.getCodeLaboratoire());
        System.out.println("nom : " + laboratoire.getNomLaboratoire());
        System.out.println("chef : " + laboratoire.getChefDeVente());
    }
}
