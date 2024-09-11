package echec.Piece;
import echec.Joueur.Joueur;

public class Roi extends Piece{

    public int[][] deplacement = {
            {1,0},{-1,0},
            {0,1},{0,-1},
            {1,1},{-1,-1},
            {1,-1},{-1,1}
    };
    public Roi(Joueur joueur ){
        this.joueur=joueur;

        this.caseVide=false;
        this.nomPiece="Roi";
    }

    @Override
    public int[][] getDeplacement() {
        return this.deplacement;
    }
}
