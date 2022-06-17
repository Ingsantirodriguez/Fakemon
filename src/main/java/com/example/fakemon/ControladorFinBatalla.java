package com.example.fakemon;

import com.example.fakemon.Controlador;
import javafx.event.ActionEvent;
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
    public Button exit;
    public ImageView winner;
    public Button Continuar;
    public Text ganador;
    public Text ataqueRest;
    public Text vidaRest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sonido.stopMusic();
        ganador.setText("Ganador: " + battle.getWinner().getName());
        vidaRest.setText("Vida: " + battle.getWinner().getCurrentLife());
        ataqueRest.setText("Ataque: " + battle.getWinner().getAttackDamage());
        try {
            Path imgFile = Paths.get(battle.getWinner().getImgPath());
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
        Parent root = FXMLLoader.load(getClass().getResource("fxml/fightScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
