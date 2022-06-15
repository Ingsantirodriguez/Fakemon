module com.example.fakemon {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens com.example.fakemon to javafx.fxml;
    exports com.example.fakemon;
    exports com.example.fakemon.acciones;
    opens com.example.fakemon.acciones to javafx.fxml;
    exports com.example.fakemon.controllers;
    opens com.example.fakemon.controllers to javafx.fxml;
    exports com.example.fakemon.fxml;
    opens com.example.fakemon.fxml to javafx.fxml;
}