package com.example.fakemon;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

//Clase ControladorInicio hereda de Controller.
public class ControladorInicio extends  Controlador implements Initializable {


    public Button ConfigBtn;
    public Button Jugarbtn;
    public Button SalirBt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!sonido.getMusicOn().equals("home")){
            if(sonido.musicOn()){
                sonido.stopMusic();
            }
            //Boton Jugar Texto Amarillo y fondo degrade azul
            try {
                sonido.playMusic("home");
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println(sonido.getMusicOn());
        }


    }




    //Al hacer click en el boton Jugar cargar pantalla characterSelection.fxml
    public void jugar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("characterSelection.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //Al hacer click en el boton Salir cerrar la aplicacion.
    public void salir(ActionEvent event) {
        System.exit(0);
    }

    //Al hacer click en el boton Jugar cargar pantalla config.fxml
    public void config(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("config.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}


