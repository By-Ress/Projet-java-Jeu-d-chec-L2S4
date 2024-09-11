package echec.Piece;

import echec.Joueur.Joueur;

public class Fou extends Piece {
    public int[][] deplacement = {{1, -1}, {2, -2}, {3, -3}, {4, -4}, {5, -5}, {6, -6}, {7, -7}
            ,{-1, -1}, {-2, -2}, {-3, -3}, {-4, -4}, {-5, -5}, {-6, -6}, {-7, -7},
            {1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6}, {7, 7},
            {-1, 1}, {-2, 2}, {-3, 3}, {-4, 4}, {-5, 5}, {-6, 6}, {-7, 7}};


    public Fou(Joueur joueur) {
        this.joueur = joueur;

        this.caseVide = false;
        this.nomPiece = "Fou";
    }


    @Override
    public int[][] getDeplacement() {
        return this.deplacement;


    }


}
