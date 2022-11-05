module frkv.javafx.laborationthree {
    requires javafx.controls;
    requires javafx.fxml;


    opens frkv.javafx.laborationthree to javafx.fxml;
    exports frkv.javafx.laborationthree;
    exports frkv.javafx.laborationthree.controller;
    opens frkv.javafx.laborationthree.controller to javafx.fxml;
}