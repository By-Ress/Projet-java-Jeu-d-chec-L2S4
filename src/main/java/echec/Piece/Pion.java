package echec.Piece;

import echec.Grille.GrilleDeJeu;
import echec.Joueur.Joueur;

public class Pion extends Piece {

    int[][] deplacement;
    int[][] deplacementJoueurNoir = {{0, 1}};
    int[][] deplacementJoueurNoirSiPasBouger = {{0, 1},{1,1},{-1,1},{0,2}};
    int[][] deplacementJoueurBlanc = {{0,-1}};
    int[][] deplacementJoueurBlancSiPasBouger = {{0, -1},{1,-1},{-1,-1},{0,-2}};

    public Pion(Joueur joueur) {
        this.joueur = joueur;

        this.caseVide = false;
        this.nomPiece = "Pion";
        this.deplacement = getDeplacement();
    }




    @Override
    public int[][] getDeplacement() {
        return (joueur==Joueur.blanc) ?
                (!dejaJouer  ? deplacementJoueurBlancSiPasBouger : deplacementJoueurBlanc)
                : (!dejaJouer ? deplacementJoueurNoirSiPasBouger : deplacementJoueurNoir);
    }







}
