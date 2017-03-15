package test.modele.metier;

import modele.metier.Famille;
import modele.metier.Medicament;

/**
 * Classe de test unitaire pour la classe Medicament
 *
 * @author btssio
 * @version 1.0
 *
 */
public class TestMedicament {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("\nTest classe Medicament\n");
        Medicament medicament = null;
        // Test n°1 : instanciation et accesseurs
        System.out.println("\nTest n°1 : instanciation et accesseurs");
        Famille famille = new Famille("codeFamilleTest", "libelleFamilleTest");
        medicament = new Medicament("depotLegalTest", "nomCommercialTest", famille, "compositionTest", "testEffets", "testContreIndication", 1.2f);

        System.out.println("Etat du médicament : " + medicament.toString());

        // Test n°2 : mutateurs + accesseurs
        //mutateurs
        System.out.println("\nTest n°2 : mutateurs + accesseurs");
        medicament.setDepotLegal("nouveauDP");
        medicament.setNomCommercial("nouveau nom commerical");
        famille.setCodeFamille("nouveau code famille");
        medicament.setFamille(famille);
        medicament.setComposition("nouvelle compostion");
        medicament.setEffets("nouveaux effets");
        medicament.setContreIndication("nouvelle contre indication");
        medicament.setPrixEchantillon(2.4f);

        //accesseurs
        System.out.println("Etat du médicament : ");
        System.out.println("depot legal : " + medicament.getDepotLegal());
        System.out.println("nom commercial : " + medicament.getNomCommercial());
        System.out.println("code famille : " + medicament.getFamille().getCodeFamille());
        System.out.println("composition : " + medicament.getComposition());
        System.out.println("effets : " + medicament.getEffets());
        System.out.println("contre indications : " + medicament.getContreIndication());
        System.out.println("prix echantillon : " + medicament.getPrixEchantillon());
    }
}
