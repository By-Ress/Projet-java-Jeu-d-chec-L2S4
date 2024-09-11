module eche.echec {
    requires javafx.controls;
    requires javafx.fxml;


    opens echec.Main to javafx.fxml;
    exports echec.Main;
}