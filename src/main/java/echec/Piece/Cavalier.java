package echec.Piece;

import echec.Joueur.Joueur;

public class Cavalier extends Piece{

    public int[][] deplacement = {
            {2,1},{2,-1},{-2,1},{-2,-1},
            {1,2},{-1,2},{1,-2},{-1,-2}
    };
    public Cavalier(Joueur joueur){
        this.joueur=joueur;
        this.caseVide=false;
        this.nomPiece="Cavalier";

    }


    @Override
    public int[][] getDeplacement() {
        return this.deplacement;
    }
}
