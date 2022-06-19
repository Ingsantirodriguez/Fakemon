package com.example.fakemon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.fakemon.DatosConfig.nombre;

public class ControladorInicio extends Controlador implements Initializable {
    @FXML
    private Button ConfigBtn;
    @FXML
    private Button Jugarbtn;
    @FXML
    private Button SalirBt;
    @FXML
    private Label nameWarning;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!sonido.getMusicOn().equals("home")){
            if(sonido.musicOn()){
                sonido.stopMusic();
            }
            sonido.playMusic("home");
        }
    }

    //Al hacer click en el boton Jugar cargar pantalla characterSelection.fxml
    public void jugar(ActionEvent event) throws IOException {
        if (nombre.equals("")){
            nameWarning.setText("Ingresa tu nombre para poder jugar");
        }else{
            Parent root = FXMLLoader.load(getClass().getResource("fxml/characterSelection.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    //Al hacer click en el boton Salir cerrar la aplicacion.
    public void salir(ActionEvent event) {
        System.exit(0);
    }

    //Al hacer click en el boton Jugar cargar pantalla config.fxml
    public void config(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/config.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}


