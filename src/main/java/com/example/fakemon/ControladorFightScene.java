package com.example.fakemon;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static com.example.fakemon.DatosConfig.nombre;

public class ControladorFightScene extends Controlador implements Initializable {
    @FXML
    private ImageView userFakemon;
    @FXML
    private ImageView botFakemon;
    @FXML
    private Button Batalla;
    @FXML
    private Text usrName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarVisuales();
    }

    private void iniciarVisuales(){
        URL pathImg = getClass().getResource("images/batalla.png");
        Image battleImg = new Image(String.valueOf(pathImg),152, 50, false, true);
        Batalla.setGraphic(new ImageView(battleImg));

        Thread visuales = new Thread(new Runnable() {
            @Override
            public void run() {
                iniciarUsr();

            }
        });

        visuales.start();
    }

    private void iniciarUsr(){
        usrName.setText(nombre);
        sonido.stopMusic();
        userFakemon.setOpacity(0);
        String usfakemon= battle.getUsrFakemon().getImgPath();
        Path imageFile = Paths.get(usfakemon);
        try {
            userFakemon.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        FadeTransition fadeTransition = new FadeTransition(javafx.util.Duration.millis(500), userFakemon);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setAutoReverse(true);
        fadeTransition.setDelay(javafx.util.Duration.millis(500));
        fadeTransition.play();

        delay(3);
        sonido.stopMusic();
        iniciarBot();
    }

    private void iniciarBot(){
        battle.selecRandomFakemon();
        String botfakemon= battle.getBotFakemon().getImgPath();
        Path imgFile = Paths.get(botfakemon);
        botFakemon.setOpacity(0);
        try {
            botFakemon.setImage(new Image(imgFile.toUri().toURL().toExternalForm()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        sonido.playMusic(battle.getBotFakemon());
        FadeTransition fadeTransition = new FadeTransition(javafx.util.Duration.millis(500), botFakemon);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
        delay(3);
        sonido.stopMusic();
    }

    private void delay(int delay){
        try{
            TimeUnit.SECONDS.sleep(delay);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void irAtras(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/characterSelection.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void irABatalla(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Batalla.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    public void salir(ActionEvent event) {
        System.exit(0);
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
