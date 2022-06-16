package com.example.fakemon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApplication extends Application {

    public static Parent root;
    public static Stage stage;
    public static Scene scene;


    public static void main(String[] args) {
        launch(args);
    }

    DatosConfig datosConfig = new DatosConfig();

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("fxml/inicio.fxml"));
        scene = new Scene(root, Color.WHITE);
        stage.setScene(scene);
        stage.setTitle("Fakemon");
        stage.show(); // Muestra la ventana
    }
}