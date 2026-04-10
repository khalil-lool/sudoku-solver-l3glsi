/**
 * Point d'entrée du programme Solveur de Sudoku.
 *
 * Utilisation :
 *   java Main grille.txt    → charge la grille depuis le fichier
 *   java Main               → demande la saisie manuelle
 */
public class Main {

    public static void main(String[] args) {

        try {
            GrilleLoader loader = new GrilleLoader();
            Grille grille;

            // Chargement : depuis un fichier ou saisie manuelle
            if (args.length > 0) {
                grille = loader.chargerFichier(args[0]);
            } else {
                grille = loader.saisirManuellement();
            }

            // Affichage de la grille initiale
            GrilleAfficheur.afficher(grille, "Grille initiale");

            // Résolution
            SudokuSolveur solveur = new SudokuSolveur();
            boolean resolu = solveur.resoudre(grille);

            // Affichage du résultat
            if (resolu) {
                GrilleAfficheur.afficher(grille, "Grille résolue");
            } else {
                System.out.println("Aucune solution possible pour cette grille.");
            }

        } catch (SudokuException e) {
            // Toutes les erreurs remontent ici avec un message clair
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}