/**
 * Vérifie si un chiffre respecte les règles du Sudoku
 * avant d'être placé dans la grille.
 */
public class SudokuValidateur {

    /**
     * Retourne true si 'num' peut être placé à la case (ligne, col)
     * sans violer aucune règle du Sudoku.
     *
     * @param grille  la grille actuelle
     * @param ligne   la ligne de la case (0 à 8)
     * @param col     la colonne de la case (0 à 8)
     * @param num     le chiffre à tester (1 à 9)
     */
    public boolean estValide(Grille grille, int ligne, int col, int num) {

        // Règle 1 : le chiffre ne doit pas déjà être dans la même ligne
        for (int j = 0; j < 9; j++) {
            if (grille.getValeur(ligne, j) == num) {
                return false;
            }
        }

        // Règle 2 : le chiffre ne doit pas déjà être dans la même colonne
        for (int i = 0; i < 9; i++) {
            if (grille.getValeur(i, col) == num) {
                return false;
            }
        }

        // Règle 3 : le chiffre ne doit pas déjà être dans la sous-grille 3x3
        // On calcule le coin supérieur gauche de la sous-grille concernée
        int debutLigne = (ligne / 3) * 3;
        int debutCol  = (col  / 3) * 3;

        for (int i = debutLigne; i < debutLigne + 3; i++) {
            for (int j = debutCol; j < debutCol + 3; j++) {
                if (grille.getValeur(i, j) == num) {
                    return false;
                }
            }
        }

        // Aucune règle violée : le chiffre peut être placé ici
        return true;
    }
}