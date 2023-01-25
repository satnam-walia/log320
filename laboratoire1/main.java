package laboratoire1;

import java.util.Arrays;
import java.util.Random;


public class main {
    public static void main(String[] args) {
        RechercheTableau rt = new RechercheTableau();
        
        int tableau[]= CreatingArray(1000);
        int nbExecution = 1000000;
        
        System.out.println("**********         Debut recherche         *************");
        //tempsExecution(nbExecution, rt, tableau);
        nbComparison(rt, tableau, 5555);
        rechercheIndex(rt, tableau, 5555);
    }
    public static int[] CreatingArray(int n) {
        int[] arrayOfNumbers = new int[n];
        int counter = 1;
        for (int i = 0; i < arrayOfNumbers.length ; i++) {
            arrayOfNumbers[i] = counter;
            counter ++;
        }
        return arrayOfNumbers;
    }

    public static void tempsExecution(int nbExecution, RechercheTableau rt, int[] tableau) {
        
        int chercheVal;
        long debutTemps;
        long finTemps;

        long[] tempsExecutionLinaire = new long[nbExecution];
        long[] tempsExecutionBinaire = new long[nbExecution];
        long[] tempsExecutionBinaireModifie = new long[nbExecution];

        double avg;

        Random rand = new Random();

        for (int i = 0; i < nbExecution; i++) {
            chercheVal = tableau[rand.nextInt(tableau.length)];
            debutTemps = System.nanoTime();
            rt.RechercheLineaire(tableau, tableau.length, chercheVal);
            finTemps = System.nanoTime();
            tempsExecutionLinaire[i] = finTemps - debutTemps;

            debutTemps = System.nanoTime();
            rt.RechercheBinaire(tableau, tableau.length, chercheVal);
            finTemps = System.nanoTime();
            tempsExecutionBinaire[i] = finTemps - debutTemps;
    

            debutTemps = System.nanoTime();
            rt.RechercheBinaireModifie(tableau, tableau.length, chercheVal);
            finTemps = System.nanoTime();
            tempsExecutionBinaireModifie[i] = finTemps - debutTemps;
           
        }
        avg = Arrays.stream(tempsExecutionLinaire).average().orElse(0);
        System.out.println("\ntemps d'exécution moyen pour RechercheLinaire: " + avg + " ns");

        avg = Arrays.stream(tempsExecutionBinaire).average().orElse(0);
        System.out.println("temps d'exécution moyen pour RechercheLinaire: " + avg + " ns");

        avg = Arrays.stream(tempsExecutionBinaireModifie).average().orElse(0);
        System.out.println("temps d'exécution moyen pour RechercheBinaireModifie: " + avg + " ns\n");
    }

    public static void nbComparison(RechercheTableau rt, int[] tableau, int x){
        // System.out.println("\nTableau donne: \n");
        // for(int i = 0; i < tableau.length; i++){
        //     System.out.println("Tableau[" + i + "]: " + tableau[i]);
        // }
        rt.RechercheLineaire(tableau, tableau.length, x);
        System.out.println("RechercheLinaire counter: " + rt.getCounter());

        rt.RechercheBinaire(tableau, tableau.length, x);
        System.out.println("RechercheBinaire counter: " + rt.getCounter());

        rt.RechercheBinaireModifie(tableau, tableau.length, x);
        System.out.println("RechercheBinaireModifie counter: " + rt.getCounter());
    }

    public static void rechercheIndex(RechercheTableau rt, int[] tableau, int x){
        int index = 0;
        index = rt.RechercheLineaire(tableau, tableau.length, x);
        System.out.println("Recherche Lineaire index: " + index);
        index = rt.RechercheBinaire(tableau, tableau.length, x);
        System.out.println("Recherche Binaire index: " + index);
        index = rt.RechercheBinaireModifie(tableau, tableau.length, x);
        System.out.println("Recherche Binaire modifie index: " + index);

    }
}

