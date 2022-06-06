package com.example.fakemon;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static com.example.fakemon.DatosConfig.*;

public class ControladorSeleccionPersonaje extends Controlador implements Initializable {
    public ImageView Bulbasaur;
    public ImageView Pikachu;
    public ImageView Charmander;
    public ImageView Pidgey;
    public ImageView Squirtle;
    public ImageView Jigglypuff;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        emptyFakemons();
//        fillFakemons();
        battle.emptyFakemons();
        battle.fillFakemons();
        sonido.stopMusic();
        sonido.playMusic("selection");
    }

    public void config(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fightScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void irAtras(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("inicio.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    //Seleccionaste Bulbasaur
    public void Bulbasaur(ActionEvent event) throws IOException {
    System.out.println("Bulbasaur");
    // fakemonElegido = "Bulbasaur";
    battle.setUsrFakemon("Bulbasaur");
    config(event);
    }
    //Seleccionaste Pikachu
    public void Pikachu(ActionEvent event) throws IOException {
        System.out.println("Pikachu");
        //fakemonElegido = "Pikachu";
        battle.setUsrFakemon("Pikachu");
        config(event);
    }
    //Seleccionaste Charmander
    public void Charmander(ActionEvent event) throws IOException {
        System.out.println("Charmander");
        //fakemonElegido = "Charmander";
        battle.setUsrFakemon("Charmander");
        config(event);
    }
    //Seleccionaste Pidgey
    public void Pidgey(ActionEvent event) throws IOException {
        System.out.println("Pidgey");
        //fakemonElegido = "Pidgey";
        battle.setUsrFakemon("Pidgey");
        config(event);
    }
    //Seleccionaste Squirtle
    public void Squirtle(ActionEvent event) throws IOException {
        System.out.println("Squirtle");
        //fakemonElegido = "Squirtle";
        battle.setUsrFakemon("Squirtle");
        config(event);
    }
    //Seleccionaste Jigglypuff
    public void Jigglypuff(ActionEvent event) throws IOException {
        System.out.println("Jigglypuff");
        //fakemonElegido = "Jigglypuff";
        battle.setUsrFakemon("Jigglypuff");
        config(event);
    }





}
