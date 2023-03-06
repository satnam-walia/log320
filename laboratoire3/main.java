package laboratoire3;

public class main {
    public static void main(String[] args) throws Exception {
        String nomFichier1 = "laboratoire3/files/monte_cristo_1-Dumas.txt";
        String nomFichier2 = "laboratoire3/files/adventures_of_Sherlock_Holmes-Doyle.txt";
        System.out.printf("La distance cosinus entre les deux documents est de %.6f radians.\n", DocDist.docDistance(nomFichier1,nomFichier2));
         
    }
}
