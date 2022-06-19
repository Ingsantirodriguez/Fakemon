package com.example.fakemon;

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

public class ControladorFinBatalla extends Controlador implements Initializable {
    @FXML
    private Button exit;
    @FXML
    private ImageView winner;
    @FXML
    private Button Continuar;
    @FXML
    private Text ganador;
    @FXML
    private Text ataqueRest;
    @FXML
    private Text vidaRest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sonido.stopMusic();
        ganador.setText("Ganador: " + battle.getWinner().getName());
        vidaRest.setText("Vida: " + battle.getWinner().getCurrentLife());
        ataqueRest.setText("Ataque: " + battle.getWinner().getAttackDamage());
        try {
            Path imgFile = Paths.get(battle.getWinner().getImgCampeon());
            winner.setImage(new Image(imgFile.toUri().toURL().toExternalForm()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        if(battle.getWinner().equals(battle.getUsrFakemon())){
            sonido.playMusic("win");
            System.out.println("reseteando valores..");
            battle.getUsrFakemon().resetFakemon();
        }else{
            sonido.playMusic("lose");
        }
    }

    public void salir(ActionEvent event) {
        System.exit(0);
    }

    public void continuar(ActionEvent event) throws IOException {
        Parent root = null;
        if(battle.getNroBatalla() <= 5){
            root = FXMLLoader.load(getClass().getResource("fxml/fightScene.fxml"));
        }else{
            root = FXMLLoader.load(getClass().getResource("fxml/finalTorneoScene.fxml"));
        }

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
