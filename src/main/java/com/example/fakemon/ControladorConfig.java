package com.example.fakemon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Background;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.fakemon.DatosConfig.*;

public class ControladorConfig extends Controlador implements Initializable {


    public CheckBox config1;
    public TextField Nombre;
    public CheckBox config2;
    public CheckBox config3;
    public Slider VolumenSeleccion;
    public ToggleButton GeneroToogle;
    public Button NombreBtn;
    public Button volUpButton;
    public Button muteButton;
    public Button volDownButton;

    public void initialize(URL location, ResourceBundle resources) {
        iniciarBotones();
        if(!nombre.equals("")){
            Nombre.setText(nombre);
        }
        if(genero.equals("Masculino")){
            Masculino();
        }
        else Femenino();
    }
    //accion ir al inicio.fxml
    public void inicio(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/inicio.fxml"));
        Scene scene = new Scene(root, Color.WHITE);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    private void iniciarBotones(){
        URL path1 = getClass().getResource("images/mute.png");
        URL path2 = getClass().getResource("images/volumeDown.png");
        URL path3 = getClass().getResource("images/volumeUp.png");

        Image img1 = new Image(String.valueOf(path1), 20,20,false, true);
        Image img2 = new Image(String.valueOf(path2), 20,20,false, true);
        Image img3 = new Image(String.valueOf(path3), 20,20,false, true);

        muteButton.setGraphic(new ImageView(img1));
        volDownButton.setGraphic(new ImageView(img2));
        volUpButton.setGraphic(new ImageView(img3));
    }
    public void guardarNombre(ActionEvent event) {

        nombre = Nombre.getText();
        System.out.println(nombre);
    }

    //cambiar volumen
    public void cambiarVolumen(ActionEvent event) {
        volumen = (int) VolumenSeleccion.getValue();
        System.out.println(volumen);
    }
    //cambiar genero
    public void cambiarGenero(ActionEvent event) {
        if (GeneroToogle.isSelected()) {
            Masculino();
        } else {
            Femenino();
            //GeneroToogle texto rojo oscuro y fondo amarillo claro
        }
    }
    private void Masculino(){
        GeneroToogle.setText("Masculino");
        //GeneroToogle texto azul oscuro y fondo naranja claro
        GeneroToogle.setTextFill(Color.BLUE);
        GeneroToogle.setBackground(new Background(new BackgroundFill(Color.rgb(144,212,237),null,null)));
        genero = "Masculino";
    }
    private void Femenino(){
        GeneroToogle.setText("Femenino");
        //GeneroToogle texto azul oscuro y fondo naranja claro
        GeneroToogle.setTextFill(Color.PURPLE);
        GeneroToogle.setBackground(new Background(new BackgroundFill(Color.rgb(248,200,220),null,null)));
        genero = "Femenino";
    }

    @FXML
    private void subirVolumen(ActionEvent e){
        System.out.println("Subo");
        sonido.volumeUp();
    }

    @FXML
    private void bajarVolumen(ActionEvent e){
        System.out.println("Bajo");
        sonido.volumeDown();
    }

    @FXML
    private void mute(ActionEvent e){
        System.out.println("Mute");
        sonido.muteSound();
    }
}