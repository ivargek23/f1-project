module hr.algebra.javafxclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens hr.algebra.javafxclient to javafx.fxml;
    exports hr.algebra.javafxclient;
}