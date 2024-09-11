package echec.Piece;

public class CaseVide extends Piece {

    public int[][] deplacement = {{0, 0}};

    public CaseVide() {
        this.nomPiece = "CaseVide";
    }

    @Override
    public int[][] getDeplacement() {
        return this.deplacement;
    }
}
