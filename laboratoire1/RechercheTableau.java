package laboratoire1;

import java.util.HashMap;

public class RechercheTableau{

    // Ne pas changer ces fonctions, elles seront utilisées pour tester votre programme
    // Elles peuvent cependant servir seulement d'interface et utiliser une méthode "helper" 
    // avec des paramètres supplémentaires, au besoin. 
    public int RechercheLineaire(int[] tab, int n, int x) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(tab[i], i);
        }
        if (map.containsKey(x)) {
            return map.get(x);
        } else {
            return -1;
        }
    }

    public int RechercheBinaire(int[] tab, int n, int val) {
        return RechercheBinaireRecursive(tab, 0, n-1, val);
    }
    
    private int RechercheBinaireRecursive(int[] tab, int debut, int fin, int val) {
        if (debut > fin) {
            return -1;
        }
        int milieu = (debut + fin) / 2;
        if (tab[milieu] == val) {
            return milieu;
        }
        if (tab[milieu] < val) {
            return RechercheBinaireRecursive(tab, milieu + 1, fin, val);
        } else {
            return RechercheBinaireRecursive(tab, debut, milieu - 1, val);
        }
    }
    
    public int RechercheBinaireModifie(int[] tab, int n, int val){
        return RechercheBinaireModifieRescursive(tab, 0, n-1, val);
    }

    public int RechercheBinaireModifieRescursive(int[] tab, int debut, int fin, int val) {
        if (debut > fin) {
            return -1;
        }
        int milieu1 = debut + (fin - debut) / 3;
        int milieu2 = debut + 2 * (fin - debut) / 3;
        if (tab[milieu1] == val) {
            return milieu1;
        }
        if (tab[milieu2] == val) {
            return milieu2;
        }
        if (val < tab[milieu1]) {
            return RechercheBinaireModifieRescursive(tab, debut, milieu1 - 1, val);
        } else if (val > tab[milieu2]) {
            return RechercheBinaireModifieRescursive(tab, milieu2 + 1, fin, val);
        } else {
            return RechercheBinaireModifieRescursive(tab, milieu1 + 1, milieu2 - 1, val);
        }
    }

}