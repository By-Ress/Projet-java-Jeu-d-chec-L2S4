package echec.JeuEchec;

import echec.Grille.GrilleDeJeu;
import echec.Joueur.Joueur;
import echec.Piece.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class JeuEchec {
    public boolean enCours = false;
    public GrilleDeJeu jeu;
    Stage stage;
    Stage optionStage = new Stage();
    boolean joueur1 = true, joueur2 = false;

    public JeuEchec(Stage stage) {
        this.jeu = new GrilleDeJeu(stage);
        this.stage = stage;
        initialisationJeu();
        lancerJeu();
        jeu.afficherGrille();
        fenettreOptions();
    }

    public void lancerJeu() {
        jeu.afficherGrille();
        enCours = true;
        Joueur1DeJouer();

        enCours = false;
        return;
    }


    void Jouer() {
        jeu.afficherGrille();
        if (!(Objects.equals(jeu.roiToujoursVivant(), ""))) {

            joueur2 = false;
            joueur1 = false;
        }

        if (joueur1)
            Joueur1DeJouer();
        if (joueur2)
            Joueur2DeJouer();
    }

    void popupVainqueur() {

        if (!Objects.equals(jeu.roiToujoursVivant(), "")) {
            Stage vainqueur = new Stage();
            Text message = new Text();
            message.setText(jeu.roiToujoursVivant());
            message.setLayoutX(100);
            message.setLayoutY(400);
            Image backGround = new Image("file:src/main/resources/echec/image/gagner.jpeg",
                    500, 500, true, true);
            ImageView backGroundView = new ImageView(backGround);
            Group group = new Group();
            group.getChildren().add(backGroundView);
            group.getChildren().add(message);
            Button recommencerPartie = new Button("Recommancer !!");
            recommencerPartie.setLayoutX(250);
            recommencerPartie.setLayoutY(250);
            recommencerPartie.setOnMouseClicked(e -> {
                joueur1 = true;
                joueur2 = true;
                jeu = new GrilleDeJeu(stage);
                initialisationJeu();
                lancerJeu();
                vainqueur.close();
            });
            group.getChildren().add(recommencerPartie);

            Scene vainqueurScene = new Scene(group, 500, 500, Color.LIGHTGRAY);
            vainqueur.setScene(vainqueurScene);
            vainqueur.setTitle("RESULTAT !!!!");
            vainqueur.show();
        }
    }

    void Joueur2DeJouer() {
        joueur2 = true;
        popupVainqueur();
        jeu.scene.setOnMouseClicked(mouseEvent -> {
            int mouseX = (int) mouseEvent.getSceneX() / 100;
            int mouseY = (int) mouseEvent.getSceneY() / 100;

            if (jeu.grille[mouseX][mouseY].joueur == Joueur.noir) {
                Piece selectioner = jeu.grille[mouseX][mouseY];
                System.out.println("le joueur 2 a clicker en x = " + mouseX + ", y = " + mouseX);
                jeu.deplacement(mouseX, mouseY, selectioner);
                joueur1 = true;
                joueur2 = false;
                jeu.afficherGrille();
                Jouer();
            } else
                Joueur2DeJouer();
        });

    }

    void Joueur1DeJouer() {
        joueur1 = true;
        popupVainqueur();
        jeu.scene.setOnMouseClicked(mouseEvent -> {
            int mouseX = (int) mouseEvent.getSceneX() / 100;
            int mouseY = (int) mouseEvent.getSceneY() / 100;
            if (jeu.grille[mouseX][mouseY].joueur == Joueur.blanc) {
                Piece selectioner = jeu.grille[mouseX][mouseY];
                System.out.println("le joueur 1 a clicker en x = " + mouseX + ", y = " + mouseY);
                jeu.deplacement(mouseX, mouseY, selectioner);
                joueur2 = true;
                joueur1 = false;
                jeu.afficherGrille();
                Jouer();
            } else
                Joueur1DeJouer();
        });

    }

    public void initialisationJeu() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                int[] caseVideCoordonner = {y, x};
                this.jeu.addPiece(caseVideCoordonner, new CaseVide());
            }
        }

        for (int i = 0; i < 8; i++) {
            int[] pionNoir = {i, 1};
            int[] pionBlanc = {i, 6};
            this.jeu.addPiece(pionNoir, new Pion(Joueur.noir));
            this.jeu.addPiece(pionBlanc, new Pion(Joueur.blanc));
        }
        this.jeu.addPiece(new int[]{0, 0}, new Tour(Joueur.noir));
        this.jeu.addPiece(new int[]{7, 0}, new Tour(Joueur.noir));
        this.jeu.addPiece(new int[]{0, 7}, new Tour(Joueur.blanc));
        this.jeu.addPiece(new int[]{7, 7}, new Tour(Joueur.blanc));

        this.jeu.addPiece(new int[]{1, 0}, new Cavalier(Joueur.noir));
        this.jeu.addPiece(new int[]{6, 0}, new Cavalier(Joueur.noir));
        this.jeu.addPiece(new int[]{1, 7}, new Cavalier(Joueur.blanc));
        this.jeu.addPiece(new int[]{6, 7}, new Cavalier(Joueur.blanc));

        this.jeu.addPiece(new int[]{2, 0}, new Fou(Joueur.noir));
        this.jeu.addPiece(new int[]{5, 0}, new Fou(Joueur.noir));
        this.jeu.addPiece(new int[]{2, 7}, new Fou(Joueur.blanc));
        this.jeu.addPiece(new int[]{5, 7}, new Fou(Joueur.blanc));

        this.jeu.addPiece(new int[]{4, 0}, new Roi(Joueur.noir));
        this.jeu.addPiece(new int[]{4, 7}, new Roi(Joueur.blanc));

        this.jeu.addPiece(new int[]{3, 0}, new Reine(Joueur.noir));
        this.jeu.addPiece(new int[]{3, 7}, new Reine(Joueur.blanc));
    }

    void fenettreOptions() {
        optionStage.setTitle("Option");

        VBox layout = new VBox();

        Button initialiseAllcase = new Button("Initialiser grille");

        initialiseAllcase.setOnMouseClicked(e -> {
            initialisationJeu();
            jeu.afficherGrille();
        });

        Button supprimerEllementGrille = new Button("Supprimer grille");

        supprimerEllementGrille.setOnMouseClicked(e -> {
            jeu = new GrilleDeJeu(stage);
            jeu.afficherGrille();
        });

        Button lanceJeu = new Button("Lancer partie");

        lanceJeu.setOnMouseClicked(e -> {
            lancerJeu();
        });

        TextField x = new TextField();
        x.setPromptText("Donner x : ");
        TextField y = new TextField();
        y.setPromptText("Donner y : ");

        ChoiceBox<String> choiceBoxPiece = new ChoiceBox<>();
        String[] piece = {"Roi", "Reine", "Tour", "Cavalier", "Pion", "Fou"};
        choiceBoxPiece.getItems().addAll(piece);

        ChoiceBox<String> choiceBoxCouleur = new ChoiceBox<>();
        String[] couleur = {"Blanc", "Noir"};
        choiceBoxCouleur.getItems().addAll(couleur);

        Button submit = new Button("Créer Pièce");

        submit.setOnMouseClicked(e -> {
            try {
                int xText = Integer.parseInt(x.getText());
                int yText = Integer.parseInt(y.getText());

                if (xText > 7 || xText < 0 || yText < 0 || yText > 7) {
                    System.out.println("Hors de la table !!!");
                } else {
                    Piece pieceAjouter;
                    switch (choiceBoxPiece.getValue()) {
                        case "Roi" ->
                                pieceAjouter = new Roi((Objects.equals(choiceBoxCouleur.getValue(), "Blanc") ? Joueur.blanc : Joueur.noir));
                        case "Reine" ->
                                pieceAjouter = new Reine((Objects.equals(choiceBoxCouleur.getValue(), "Blanc") ? Joueur.blanc : Joueur.noir));
                        case "Tour" ->
                                pieceAjouter = new Tour((Objects.equals(choiceBoxCouleur.getValue(), "Blanc") ? Joueur.blanc : Joueur.noir));
                        case "Pion" ->
                                pieceAjouter = new Pion((Objects.equals(choiceBoxCouleur.getValue(), "Blanc") ? Joueur.blanc : Joueur.noir));
                        case "Fou" ->
                                pieceAjouter = new Fou((Objects.equals(choiceBoxCouleur.getValue(), "Blanc") ? Joueur.blanc : Joueur.noir));
                        case "Cavalier" ->
                                pieceAjouter = new Cavalier((Objects.equals(choiceBoxCouleur.getValue(), "Blanc") ? Joueur.blanc : Joueur.noir));
                        default -> {
                            System.out.println("Type de pièce invalide !");
                            return;
                        }
                    }

                    jeu.addPiece(new int[]{xText, yText}, pieceAjouter);
                }
            } catch (NumberFormatException ex) {
                System.out.println("Veuillez entrer des coordonnées valides !");
            }

            jeu.afficherGrille();
        });

        Button commencerAJouer = new Button("Commencer a jouer (Pion blanc et noir obliger roi compris) ");

        commencerAJouer.setOnMouseClicked(e -> {
            lancerJeu();

        });

        layout.getChildren().addAll(initialiseAllcase, supprimerEllementGrille, lanceJeu, x, y,
                choiceBoxPiece, choiceBoxCouleur, submit, commencerAJouer);
        Scene scene = new Scene(layout, 500, 500);
        optionStage.setScene(scene);
        optionStage.show();
    }


}
