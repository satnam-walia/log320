package laboratoire1;

import java.util.Arrays;
import java.util.Random;


public class CalculeTempsExecution {
    static Random rand = new Random();
    public static void main(String[] args) {
        RechercheTableau rt = new RechercheTableau();
        
        int tableau[]= RandomArray(10);
        moyenneTempsExecution(10, () -> {
            int chercheVal = tableau[rand.nextInt(tableau.length)];
            rt.RechercheLineaire(tableau, tableau.length, chercheVal);
        });

        moyenneTempsExecution(10, () -> {
            int chercheVal = tableau[rand.nextInt(tableau.length)];
            rt.RechercheBinaire(tableau, chercheVal, chercheVal);
        });

        moyenneTempsExecution(10, () -> {
            int chercheVal = tableau[rand.nextInt(tableau.length)];
            rt.RechercheBinaireModifie(tableau, chercheVal, chercheVal);
        });

    }

    public static int[] RandomArray(int n) {
        int[] randomArray = new int[n];
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = rand.nextInt(n);
        }
        return randomArray;
    }

    public static void moyenneTempsExecution(int n, Runnable fonction) {
        long[] tempsExecution = new long[n];
        for (int i = 0; i < n; i++) {
            long debutTemps = System.nanoTime();
            fonction.run();
            long finTemps = System.nanoTime();
            tempsExecution[i] = finTemps - debutTemps;
        }
        double avg = Arrays.stream(tempsExecution).average().orElse(0);
        System.out.println("average execution time: " + avg + " ns");
    }
}

