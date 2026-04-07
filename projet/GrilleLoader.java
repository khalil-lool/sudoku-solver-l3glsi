
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GrilleLoader {

    public Grille chargerFichier(String chemin) throws SudokuException {
        try {
            Grille grille = new Grille();
            File fichier = new File(chemin);
            Scanner scanner = new Scanner(fichier);

            int lignesLues = 0;
            while (scanner.hasNextLine() && lignesLues < 9) {
                String ligneTexte = scanner.nextLine().trim();
                if (ligneTexte.isEmpty()) {
                    continue;
                }

                String[] valeurs = ligneTexte.split("\\s+");
                if (valeurs.length != 9) {
                    scanner.close();
                    throw new SudokuException("Format incorrect : la ligne " + (lignesLues + 1) + " n'a pas 9 chiffres.");
                }

                for (int col = 0; col < 9; col++) {
                    try {
                        int val = Integer.parseInt(valeurs[col]);
                        if (val < 0 || val > 9) {
                            throw new SudokuException("Chiffre hors 0-9.");
                        }
                        // Si val > 0, la case est considérée comme fixe (true)
                        grille.setValeur(lignesLues, col, val, val != 0);
                    } catch (NumberFormatException e) {
                        scanner.close();
                        throw new SudokuException("Le fichier contient des lettres ou symboles invalides.");
                    }
                }
                lignesLues++;
            }
            scanner.close();
            if (lignesLues != 9) {
                throw new SudokuException("Le fichier doit contenir 9 lignes.");
            }
            return grille;

        } catch (FileNotFoundException e) {
            // Problème 2 résolu : On attrape l'erreur système pour envoyer TA SudokuException
            throw new SudokuException("Fichier introuvable : " + chemin);
        }
    }

    public Grille saisirManuellement() throws SudokuException {
        Grille grille = new Grille();
        Scanner clavier = new Scanner(System.in);
        System.out.println("Saisissez la grille (9 chiffres séparés par espaces, 0 pour vide) :");

        for (int i = 0; i < 9; i++) {
            System.out.print("Ligne " + (i + 1) + " : ");
            String entree = clavier.nextLine().trim();
            String[] valeurs = entree.split("\\s+");

            if (valeurs.length != 9) {
                throw new SudokuException("Erreur : 9 chiffres requis par ligne.");
            }

            for (int j = 0; j < 9; j++) {
                try {
                    // Problème 3 résolu : Protection contre les saisies de lettres au clavier
                    int val = Integer.parseInt(valeurs[j]);
                    if (val < 0 || val > 9) {
                        throw new SudokuException("Chiffre hors 0-9.");
                    }
                    grille.setValeur(i, j, val, val != 0);
                } catch (NumberFormatException e) {
                    throw new SudokuException("Erreur : La saisie contient des caractères non numériques.");
                }
            }
        }
        return grille;
    }
}
