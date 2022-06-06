package com.example.fakemon;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ControladorFightScene extends Controlador implements Initializable {

    public ImageView userFakemon;
    public ImageView botFakemon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //selecRandomFakemon();
        sonido.stopMusic();
        battle.selecRandomFakemon();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                this.configUsrFakemon();
                this.configBotFakemon();
            }
            private void configUsrFakemon() {
                String usfakemon= "src/main/resources/com/example/fakemon/images/"+battle.getUsrFakemon().getName()+".png";
                Path imageFile = Paths.get(usfakemon);
                try {
                    userFakemon.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                sonido.playMusic(battle.getUsrFakemon());
                try{
                    TimeUnit.SECONDS.sleep(3);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                sonido.stopMusic();
            }

            private void configBotFakemon(){
                String botfakemon= "src/main/resources/com/example/fakemon/images/"+battle.getBotFakemon().getName()+".png";
                Path imgFile = Paths.get(botfakemon);

                try {
                    botFakemon.setImage(new Image(imgFile.toUri().toURL().toExternalForm()));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                sonido.playMusic(battle.getBotFakemon());
                try{
                    TimeUnit.SECONDS.sleep(3);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                sonido.stopMusic();
            }


        });
        t.start();
    }

    public void config(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fightScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void irAtras(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("characterSelection.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void salir(ActionEvent event) {
        System.exit(0);
    }


}
