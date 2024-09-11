package echec.Piece;
import echec.Joueur.Joueur;

public abstract class Piece {

    public Joueur joueur;
    public String nomPiece;
    public boolean caseVide=true;
    public boolean dejaJouer = false;
    public abstract int[][] getDeplacement();





}
