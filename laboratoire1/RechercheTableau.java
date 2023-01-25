package laboratoire1;

public class RechercheTableau{

    int counter = 0;

    public int getCounter() {
        return counter;
    }
    // Ne pas changer ces fonctions, elles seront utilisées pour tester votre programme
    // Elles peuvent cependant servir seulement d'interface et utiliser une méthode "helper" 
    // avec des paramètres supplémentaires, au besoin. 
    public int RechercheLineaire(int[] tab, int n, int x) {
        counter = 0;
        for (int i = 0; i < n; i++) {
            counter +=2;
            if (tab[i] == x) { 
                return i;
            }   
        }
        return -1;
    }

    public int RechercheBinaire(int[] tab, int n, int val) {
        counter = 0;
        return RechercheBinaireRecursive(tab, 0, n-1, val);
    }
    
    private int RechercheBinaireRecursive(int[] tab, int debut, int fin, int val) {
        counter ++;
        if (debut > fin) {
            return -1;
        }
        int milieu = (debut + fin) / 2;
        counter ++;
        if (tab[milieu] == val) {
            return milieu;
        }
        counter ++;
        if (tab[milieu] < val) {
            return RechercheBinaireRecursive(tab, milieu + 1, fin, val);
        } else {
            return RechercheBinaireRecursive(tab, debut, milieu - 1, val);
        }
    }
    
    public int RechercheBinaireModifie(int[] tab, int n, int val){
        counter = 0;
        return RechercheBinaireModifieRescursive(tab, 0, n-1, val);
    }

    public int RechercheBinaireModifieRescursive(int[] tab, int debut, int fin, int val) {
        counter ++;
        if (debut > fin) {
            return -1;
        }
        int milieu1 = debut + (fin - debut) / 3;
        int milieu2 = debut + 2 * (fin - debut) / 3;
        counter ++;
        if (tab[milieu1] == val) {
            return milieu1;
        }
        counter ++;
        if (tab[milieu2] == val) {
            return milieu2;
        }
        counter ++;
        if (val < tab[milieu1]) {
            return RechercheBinaireModifieRescursive(tab, debut, milieu1 - 1, val);
        } else if (val > tab[milieu2]) {
            counter ++;
            return RechercheBinaireModifieRescursive(tab, milieu2 + 1, fin, val);
        } else {
            counter ++;
            return RechercheBinaireModifieRescursive(tab, milieu1 + 1, milieu2 - 1, val);
        }   
    }
 
}
