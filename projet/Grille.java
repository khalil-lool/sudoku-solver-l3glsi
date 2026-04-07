
public class Grille {

    private int[][] cases;
    private boolean[][] fixe; // Problème 1 résolu : mémorise les cases initiales

    public Grille() {
        this.cases = new int[9][9];
        this.fixe = new boolean[9][9];
    }

    public void setValeur(int ligne, int col, int valeur, boolean estInitial) {
        this.cases[ligne][col] = valeur;
        this.fixe[ligne][col] = estInitial;
    }

    public int getValeur(int ligne, int col) {
        return this.cases[ligne][col];
    }

    public boolean estFixe(int ligne, int col) {
        return this.fixe[ligne][col];
    }
}
