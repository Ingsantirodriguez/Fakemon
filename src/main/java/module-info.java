module com.example.fakemon {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.fakemon to javafx.fxml;
    exports com.example.fakemon;
}