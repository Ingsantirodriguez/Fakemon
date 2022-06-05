package com.example.fakemon;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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



    //accion ir al inicio.fxml
    public void inicio(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("inicio.fxml"));
        Scene scene = new Scene(root, Color.WHITE);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    //guardar nombre
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
    GeneroToogle.setBackground(new Background(new BackgroundFill(Color.rgb(220,179,31),null,null)));
    genero = "Masculino";
}
private void Femenino(){
    GeneroToogle.setText("Femenino");
    //GeneroToogle texto azul oscuro y fondo naranja claro
    GeneroToogle.setTextFill(Color.PURPLE);
    GeneroToogle.setBackground(new Background(new BackgroundFill(Color.rgb(255,255,128),null,null)));
    genero = "Femenino";
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        VolumenSeleccion.setValue(volumen);
        if(!nombre.equals("")){
            Nombre.setText(nombre);
        }
        //texto en genero en negrita
        GeneroToogle.setStyle("-fx-font-weight: bold");

        //imprimir genero
        System.out.println(genero);
        if(genero.equals("Masculino")){
            Masculino();
        }
        else{
            Femenino();
        }
        //setear volumen
        VolumenSeleccion.setValue(volumen);
    }
}
