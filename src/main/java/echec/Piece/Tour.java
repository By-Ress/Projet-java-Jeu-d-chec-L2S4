package echec.Piece;

import echec.Joueur.Joueur;

public class Tour extends Piece {

    int[][] deplacement = {
            {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0},    // toutes les deplacements en ligne
            {-1, 0}, {-2, 0}, {-3, 0}, {-4, 0}, {-5, 0}, {-6, 0}, {-7, 0},

            {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7},     // toutes les deplacements en colonne
            {0, -1}, {0, -2}, {0, -3}, {0, -4}, {0, -5}, {0, -6}, {0, -7}
    };

    public Tour(Joueur joueur) {
        this.joueur=joueur;
        this.caseVide = false;
        this.nomPiece = "Tour";
    }

    @Override
    public int[][] getDeplacement() {
        return this.deplacement;
    }
}
