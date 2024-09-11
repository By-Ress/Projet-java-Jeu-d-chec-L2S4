package echec.Main;

import echec.Grille.GrilleDeJeu;
import echec.JeuEchec.JeuEchec;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {


    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        JeuEchec jeuEchec = new JeuEchec(stage);




        stage.show();
    }

}