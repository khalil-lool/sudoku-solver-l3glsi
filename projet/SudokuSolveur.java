/**
 * Résout une grille de Sudoku par l'algorithme de backtracking.
 *
 * Principe du backtracking :
 *  1. Trouver la première case vide (valeur 0)
 *  2. Essayer les chiffres de 1 à 9
 *  3. Si un chiffre est valide, le placer et recommencer depuis l'étape 1
 *  4. Si aucun chiffre ne fonctionne, effacer et remonter (backtrack)
 *  5. Si plus aucune case vide, la grille est résolue
 */
public class SudokuSolveur {

    private SudokuValidateur validateur = new SudokuValidateur();

    /**
     * Lance la résolution de la grille.
     * Retourne true si une solution a été trouvée, false sinon.
     *
     * @param grille  la grille à résoudre (modifiée directement)
     */
    public boolean resoudre(Grille grille) {

        // Parcourir toutes les cases de la grille
        for (int ligne = 0; ligne < 9; ligne++) {
            for (int col = 0; col < 9; col++) {

                // On cherche une case vide
                if (grille.getValeur(ligne, col) == 0) {

                    // On essaie chaque chiffre de 1 à 9
                    for (int num = 1; num <= 9; num++) {

                        if (validateur.estValide(grille, ligne, col, num)) {

                            // Le chiffre semble valide : on le place
                            grille.setValeur(ligne, col, num, false);

                            // On continue à résoudre le reste de la grille
                            if (resoudre(grille)) {
                                return true; // solution trouvée !
                            }

                            // Ça n'a pas marché plus loin : on efface et on essaie le suivant
                            grille.setValeur(ligne, col, 0, false);
                        }
                    }

                    // Aucun chiffre ne fonctionne ici : on remonte
                    return false;
                }
            }
        }

        // On a parcouru toutes les cases sans en trouver de vide : c'est résolu
        return true;
    }
}