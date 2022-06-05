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
import static com.example.fakemon.DatosConfig.*;
import com.example.fakemon.DatosConfig.*;
public class ControladorFightScene implements Initializable {

    public ImageView userFakemon;
    public ImageView botFakemon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selecRandomFakemon();
        String usfakemon= "src/main/resources/com/example/fakemon/images/"+fakemonElegido+".png";
        String botfakemon= "src/main/resources/com/example/fakemon/images/"+fakemonOponente+".png";
        Path imageFile = Paths.get(usfakemon);
        try {
            userFakemon.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Path imgFile = Paths.get(botfakemon);
        try {
            botFakemon.setImage(new Image(imgFile.toUri().toURL().toExternalForm()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
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
