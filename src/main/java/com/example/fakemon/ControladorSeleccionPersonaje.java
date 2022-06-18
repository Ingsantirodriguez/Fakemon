package com.example.fakemon;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorSeleccionPersonaje extends Controlador implements Initializable {
    @FXML
    private ImageView Bulbasaur;
    @FXML
    private ImageView Pikachu;
    @FXML
    private ImageView Charmander;
    @FXML
    private ImageView Pidgey;
    @FXML
    private ImageView Squirtle;
    @FXML
    private ImageView Jigglypuff;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        battle.emptyFakemons();
        battle.fillFakemons();
        sonido.stopMusic();
        sonido.playMusic("selection");
        mostrarFakemons();
    }

    private void mostrarFakemons() {

        ImageView[] fakemons = {Bulbasaur, Pikachu, Charmander, Squirtle, Jigglypuff , Pidgey  };
        for (int i = 0; i < fakemons.length; i++) {
            fakemons[i].setOpacity(0);
        }

        Thread thread = new Thread(() -> {
            for (int i = 0; i < fakemons.length; i++) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                FadeTransition fadeTransition = new FadeTransition(javafx.util.Duration.millis(500), fakemons[i]);
                fadeTransition.setFromValue(0);
                fadeTransition.setToValue(1);
                fadeTransition.play();
            }
        });
        thread.start();
    }

    public void config(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/fightScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void irAtras(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/inicio.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    //Seleccionaste Bulbasaur
    public void Bulbasaur(ActionEvent event) throws IOException {
    System.out.println("Bulbasaur");
    battle.setUsrFakemon("Bulbasaur");
    config(event);
    }
    //Seleccionaste Pikachu
    public void Pikachu(ActionEvent event) throws IOException {
        System.out.println("Pikachu");
        battle.setUsrFakemon("Pikachu");
        config(event);
    }
    //Seleccionaste Charmander
    public void Charmander(ActionEvent event) throws IOException {
        System.out.println("Charmander");
        battle.setUsrFakemon("Charmander");
        config(event);
    }
    //Seleccionaste Pidgey
    public void Pidgey(ActionEvent event) throws IOException {
        System.out.println("Pidgey");
        battle.setUsrFakemon("Pidgey");
        config(event);
    }
    //Seleccionaste Squirtle
    public void Squirtle(ActionEvent event) throws IOException {
        System.out.println("Squirtle");
        battle.setUsrFakemon("Squirtle");
        config(event);
    }
    //Seleccionaste Jigglypuff
    public void Jigglypuff(ActionEvent event) throws IOException {
        System.out.println("Jigglypuff");
        battle.setUsrFakemon("Jigglypuff");
        config(event);
    }

    public void subirVolumen(ActionEvent actionEvent) {
        sonido.volumeUp();
    }

    public void bajarVolumen(ActionEvent actionEvent) {
        sonido.volumeDown();
    }

    public void mutear(ActionEvent actionEvent) {
        sonido.muteSound();
    }
}
