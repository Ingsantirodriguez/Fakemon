package com.example.fakemon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

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
        Parent root = FXMLLoader.load(getClass().getResource("inicio.fxml"));
        Scene scene = new Scene(root, Color.WHITE);
        stage.setScene(scene);
        stage.setTitle("Fakemon");
        stage.show(); // Muestra la ventana
    }
}