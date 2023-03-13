package laboratoire4;
import java.util.ArrayList;

public class algorithme {

    public static int joueur;

    // Pour référence sur la façon de faire le minmax / alphabeta :
    // https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-4-alpha-beta-pruning/
    public static double minMax(int[][] grid, double alpha, double beta, boolean max, int niveau) {

        if (max) { // Si le noeud est un max
            double maxVal = Double.NEGATIVE_INFINITY;
            // @TODO :
            // Implémenter la génération des mouvements possibles avec genereMouvement()
            ArrayList<position> mouvements = genereMouvement(grid);

            for (position pos : mouvements) {
                // Nouveau grid
                grid[pos.getX()][pos.getY()] = joueur;
                Double result = minMax(grid, alpha, beta, false, niveau - 1);
                grid[pos.getX()][pos.getY()] = 0;

                maxVal = Double.max(maxVal, result);
                alpha = Double.max(alpha, result);

                if (beta <= alpha && niveau - 1 > 0) { // Coupure alpha
                    break;
                }
            }
            return new Double(maxVal);

        } else { // Si le noeud est un min
            Double minVal = Double.POSITIVE_INFINITY;
            ArrayList<position> mouvements = genereMouvement(grid);

            for (position pos : mouvements) {
                // Nouveau grid
                if (joueur == 2) {
                    grid[pos.getX()][pos.getY()] = 4;
                } else {
                    grid[pos.getX()][pos.getY()] = 2;
                }
                Double result = minMax(grid, alpha, beta, true, niveau - 1);
                grid[pos.getX()][pos.getY()] = 0;
                minVal = Double.min(minVal, result);
                beta = Double.min(beta, result);

                if (beta <= alpha && niveau - 1 > 0) { // Coupure alpha
                    break;
                }
            }
            return new Double(minVal);
        }
    }

}
