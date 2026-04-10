/**
 * GrilleAfficheur.java
 *
 * Auteur  : Membre 3
 * Rôle    : Afficher une grille de Sudoku dans la console
 *           avec des caractères spéciaux de dessin de boîte (box-drawing).
 *
 * Dépend de : Grille.java (Membre 1)
 *
 * Utilisation :
 *   GrilleAfficheur.afficher(grille, "Grille initiale");
 *   GrilleAfficheur.afficher(grille, "Grille résolue");
 */
public class GrilleAfficheur {

    // =========================================================
    // CARACTÈRES SPÉCIAUX POUR DESSINER LE TABLEAU
    // =========================================================

    // -- Ligne du HAUT --
    private static final String HAUT_GAUCHE        = "╔";
    private static final String HAUT_JONC_FIN      = "╤";  // jonction fine (entre 2 cases du même bloc)
    private static final String HAUT_JONC_EPAIS    = "╦";  // jonction épaisse (entre 2 blocs 3x3)
    private static final String HAUT_DROITE        = "╗";

    // -- Lignes INTÉRIEURES fines (entre 2 lignes d'un même bloc 3x3) --
    private static final String FIN_GAUCHE         = "╟";
    private static final String FIN_JONC_FIN       = "┼";  // croisement fin-fin
    private static final String FIN_JONC_EPAIS     = "╫";  // croisement fin-épais
    private static final String FIN_DROITE         = "╢";

    // -- Lignes INTÉRIEURES épaisses (séparation entre blocs 3x3) --
    private static final String EPAIS_GAUCHE       = "╠";
    private static final String EPAIS_JONC_FIN     = "╪";  // croisement épais-fin
    private static final String EPAIS_JONC_EPAIS   = "╬";  // croisement épais-épais
    private static final String EPAIS_DROITE       = "╣";

    // -- Ligne du BAS --
    private static final String BAS_GAUCHE         = "╚";
    private static final String BAS_JONC_FIN       = "╧";  // jonction fine bas
    private static final String BAS_JONC_EPAIS     = "╩";  // jonction épaisse bas
    private static final String BAS_DROITE         = "╝";

    // -- Traits horizontaux --
    private static final String TRAIT_EPAIS        = "═";  // trait épais (séparation de blocs)
    private static final String TRAIT_FIN          = "─";  // trait fin  (séparation simple)

    // -- Séparateurs verticaux --
    private static final String BORD_EPAIS         = "║";  // bord gauche/droit et séparation de blocs
    private static final String BORD_FIN           = "│";  // séparation entre cases simples


    // =========================================================
    // MÉTHODE PRINCIPALE : afficher une grille
    // =========================================================

    /**
     * Affiche une grille de Sudoku 9x9 dans la console.
     *
     * @param grille  objet Grille (Membre 1) contenant les valeurs
     * @param titre   texte affiché au-dessus de la grille
     */
    public static void afficher(Grille grille, String titre) {

        System.out.println();
        System.out.println("  *** " + titre + " ***");
        System.out.println();

        for (int ligne = 0; ligne < 9; ligne++) {

            // --- Ligne horizontale de séparation AVANT chaque rangée ---
            if (ligne == 0) {
                // Tout en haut : ligne épaisse d'ouverture
                dessinerLigneHorizontale(HAUT_GAUCHE, TRAIT_EPAIS, HAUT_JONC_FIN, HAUT_JONC_EPAIS, HAUT_DROITE);

            } else if (ligne % 3 == 0) {
                // Tous les 3 rangs : ligne épaisse (séparation entre blocs 3x3)
                dessinerLigneHorizontale(EPAIS_GAUCHE, TRAIT_EPAIS, EPAIS_JONC_FIN, EPAIS_JONC_EPAIS, EPAIS_DROITE);

            } else {
                // Sinon : ligne fine (séparation entre cases simples)
                dessinerLigneHorizontale(FIN_GAUCHE, TRAIT_FIN, FIN_JONC_FIN, FIN_JONC_EPAIS, FIN_DROITE);
            }

            // --- Rangée de chiffres ---
            StringBuilder sb = new StringBuilder();
            sb.append(BORD_EPAIS); // bord gauche

            for (int col = 0; col < 9; col++) {

                // Récupération de la valeur via la méthode du Membre 1
                int valeur = grille.getValeur(ligne, col);

                // 0 = case vide → on affiche un espace (comme demandé dans le sujet)
                if (valeur == 0) {
                    sb.append("   ");
                } else {
                    sb.append(" ").append(valeur).append(" ");
                }

                // Séparateur vertical après la case
                if (col == 8) {
                    sb.append(BORD_EPAIS); // fin de ligne : bord droit
                } else if ((col + 1) % 3 == 0) {
                    sb.append(BORD_EPAIS); // fin d'un bloc 3x3 : séparateur épais
                } else {
                    sb.append(BORD_FIN);   // séparateur simple entre 2 cases du même bloc
                }
            }

            System.out.println(sb.toString());
        }

        // --- Ligne tout en bas ---
        dessinerLigneHorizontale(BAS_GAUCHE, TRAIT_EPAIS, BAS_JONC_FIN, BAS_JONC_EPAIS, BAS_DROITE);

        System.out.println();
    }


    // =========================================================
    // MÉTHODE UTILITAIRE : dessiner une ligne horizontale
    // =========================================================

    /**
     * Construit et affiche une ligne horizontale complète.
     *
     * @param gauche     caractère tout à gauche
     * @param trait      caractère répété pour remplir (épais ou fin)
     * @param joncFin    jonction entre 2 cases du même bloc
     * @param joncEpais  jonction entre 2 blocs 3x3
     * @param droite     caractère tout à droite
     */
    private static void dessinerLigneHorizontale(
            String gauche, String trait,
            String joncFin, String joncEpais, String droite) {

        StringBuilder sb = new StringBuilder();
        sb.append(gauche);

        for (int col = 0; col < 9; col++) {
            // 3 traits pour correspondre à la largeur d'une case " X "
            sb.append(trait).append(trait).append(trait);

            if (col < 8) {
                if ((col + 1) % 3 == 0) {
                    sb.append(joncEpais); // jonction entre blocs 3x3
                } else {
                    sb.append(joncFin);   // jonction simple
                }
            }
        }

        sb.append(droite);
        System.out.println(sb.toString());
    }


    // =========================================================
    // MÉTHODE DE TEST AUTONOME
    // Permet de tester GrilleAfficheur SEUL, sans les autres membres.
    // Le Membre 4 ne l'utilisera pas, il a son propre Main.java.
    // =========================================================

    public static void main(String[] args) {

        // --- Construction de la grille initiale de test (exemple du sujet) ---
        Grille grilleInitiale = new Grille();
        int[][] valeursInitiales = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // Utilisation de setValeur exactement comme GrilleLoader (Membre 2)
                grilleInitiale.setValeur(i, j, valeursInitiales[i][j], valeursInitiales[i][j] != 0);
            }
        }

        // --- Construction de la grille résolue de test ---
        Grille grilleSoluce = new Grille();
        int[][] valeursSoluce = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grilleSoluce.setValeur(i, j, valeursSoluce[i][j], true);
            }
        }

        // --- Affichage des deux grilles ---
        afficher(grilleInitiale, "Grille initiale");
        afficher(grilleSoluce,   "Grille résolue");
    }
}