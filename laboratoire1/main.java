package laboratoire1;

import java.util.Arrays;
import java.util.Random;


public class CalculeTempsExecution {
    static Random rand = new Random();
    public static void main(String[] args) {
        RechercheTableau rt = new RechercheTableau();
        
        int tableau[]= RandomArray(1000);
        int nbExecution = 100000;
       
        tempsExecution(nbExecution, rt, tableau);
        nbComparison(rt, tableau);
      
    }
    public static int[] RandomArray(int n) {
        int[] randomArray = new int[n+1];
        for (int i = 0; i < randomArray.length ; i++) {
            randomArray[i] = i;
        }
        return randomArray;
    }

    public static void tempsExecution(int nbExecution, RechercheTableau rt, int[] tableau) {
        
        int chercheVal;
        long debutTemps;
        long finTemps;

        long[] tempsExecutionLinaire = new long[nbExecution];
        long[] tempsExecutionBinaire = new long[nbExecution];
        long[] tempsExecutionBinaireModifie = new long[nbExecution];

        double avg;

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

    public static void nbComparison(RechercheTableau rt, int[] tableau){
        rt.RechercheLineaire(tableau, tableau.length, 7);
        System.out.println("RechercheLinaire counter: " + rt.getCounter());

        rt.RechercheBinaire(tableau, tableau.length, 7);
        System.out.println("RechercheBinaire counter: " + rt.getCounter());

        rt.RechercheBinaireModifie(tableau, tableau.length, 7);
        System.out.println("RechercheBinaireModifie counter: " + rt.getCounter());
    }
}

