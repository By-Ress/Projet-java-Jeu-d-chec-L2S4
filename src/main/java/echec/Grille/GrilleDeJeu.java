package echec.Grille;

import echec.Joueur.Joueur;
import echec.Piece.CaseVide;
import echec.Piece.Piece;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Objects;


public class GrilleDeJeu {
    private static final String IMAGE_PATH = "file:src/main/resources/echec/image/board.png";
    public static int LIGNE = 8;
    public static int COLONNE = 8;
    public Piece[][] grille;
    public Scene scene;
    public Pane grillePane = new Pane();
    public Group root = new Group();
    public Stage stage;

    public GrilleDeJeu(Stage stageS) {
        this.grille = new Piece[LIGNE][COLONNE];
        this.stage = stageS;

        for (int y = 0; y < LIGNE; y++) {
            for (int x = 0; x < COLONNE; x++) {
                grille[y][x] = new CaseVide();
            }
        }
        scene = new Scene(root, LIGNE * 100, COLONNE * 100);
        stage.setTitle("Jeu d'echec");
        stage.setResizable(false);
        stage.setScene(scene);
    }

    public void cleanGrille() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int[] caseVideCoordonner = {i, j};
                addPiece(caseVideCoordonner, new CaseVide());
            }
        }
        afficherGrille();
    }


    public void addPiece(int[] coordonner, Piece piece) {
        if (grille[coordonner[0]][coordonner[1]].caseVide) {
            grille[coordonner[0]][coordonner[1]] = piece;
            grille[coordonner[0]][coordonner[1]].caseVide = Objects.equals(piece.nomPiece, "CaseVide");
        }
    }

    public void afficherGrille() {

        root.getChildren().clear();
        Image board = new Image(IMAGE_PATH);
        ImageView boardView = new ImageView(board);
        root.getChildren().addAll(boardView);
        for (int x = 0; x < LIGNE; x++) {
            for (int y = 0; y < COLONNE; y++) {
                Piece piece = grille[x][y];
                if (!piece.caseVide) {
                    String imagePath = String.format("file:src/main/resources/echec/image/%s_%s.png",
                            piece.nomPiece, piece.joueur == Joueur.noir ? "n" : "b");
                    ImageView pieceView = new ImageView(new Image(imagePath, 100, 100, true, true));
                    pieceView.setLayoutX(x * 100);
                    pieceView.setLayoutY(y * 100);
                    grillePane.getChildren().add(pieceView);
                }
            }
        }
        root.getChildren().add(grillePane);
        stage.show();
    }


    public int[][] getDeplacementPossiblePourUnePiece(Piece piece, int x, int y) {
        int[][] resultat = new int[LIGNE * COLONNE][2];
        int[][] deplacementPiece = piece.getDeplacement();
        int n = 0;

        for (int[] ints : deplacementPiece) {
            int xDestination = x + ints[0];
            int yDestination = y + ints[1];
            if (yDestination >= 0 && yDestination < COLONNE
                    && xDestination >= 0 && xDestination < LIGNE) {
                if (!grille[yDestination][xDestination].caseVide) {
                    resultat[n] = ints;
                    n++;
                }
                if ( grille[yDestination][xDestination].caseVide && grille[yDestination][xDestination] != null) {
                    resultat[n] = ints;
                    n++;
                }
            }
        }
        return resultat;
    }


    public String roiToujoursVivant() {
        boolean roiBlanc = false, roiNoir = false;
        for (int x = 0; x < LIGNE; x++) {
            for (int y = 0; y < COLONNE; y++) {
                if (Objects.equals(grille[x][y].nomPiece, "Roi")) {
                    if (grille[x][y].joueur == Joueur.noir)
                        roiNoir = true;
                    else
                        roiBlanc = true;
                }
            }
        }
        if (roiNoir && roiBlanc)
            return "";
        if (roiNoir)
            return "Roi noir gagnant";
        else
            return "Roi blanc gagnant";
    }


    public void deplacement(int xMouse, int yMouse, Piece pieceSelectionnee) {

        int[][] deplacementsPossibles = getDeplacementPossiblePourUnePiece(pieceSelectionnee, xMouse, yMouse);

        for (int[] deplacementPiece : deplacementsPossibles) {
            int destinationY = yMouse + deplacementPiece[1];
            int destinationX = xMouse + deplacementPiece[0];


            if (grille[destinationX][destinationY].caseVide) {
                Circle circle = new Circle(50);
                circle.setFill(Color.YELLOW);
                circle.setOpacity(0.5);
                circle.setTranslateX(destinationX * 100 + 50);
                circle.setTranslateY(destinationY * 100 + 50);
                grillePane.getChildren().add(circle);

                circle.setOnMouseClicked(e -> {
                    Piece save = grille[(int) e.getSceneX() / 100][(int) e.getSceneY() / 100];
                    grille[(int) e.getSceneX() / 100][(int) e.getSceneY() / 100] = grille[xMouse][yMouse];
                    grille[xMouse][yMouse] = save;
                    grillePane.getChildren().clear();
                    afficherGrille();

                });
            }
            if (!grille[destinationX][destinationY].caseVide && (grille[destinationX][destinationY].joueur != grille[xMouse][yMouse].joueur)) {
                Circle circle = new Circle(50);
                circle.setFill(Color.RED);
                circle.setOpacity(0.5);
                circle.setTranslateX(destinationX * 100 + 50);
                circle.setTranslateY(destinationY * 100 + 50);
                grillePane.getChildren().add(circle);

                circle.setOnMouseClicked(e -> {
                    CaseVide caseVide = new CaseVide();
                    grille[(int) e.getSceneX() / 100][(int) e.getSceneY() / 100] = grille[xMouse][yMouse];
                    grille[xMouse][yMouse] = caseVide;
                    grillePane.getChildren().clear();
                    afficherGrille();
                });
            }

            if (!grille[destinationX][destinationY].dejaJouer)
                grille[destinationX][destinationY].dejaJouer = true;
        }

    }


}
