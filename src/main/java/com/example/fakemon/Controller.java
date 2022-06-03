package com.example.fakemon;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Controller {
    private GameSounds sonido = new GameSounds();;
    public ToggleButton Genero;
    public Button OkNombre;
    public TextField Nombre;
    @FXML
    private CheckBox config1 = new CheckBox();
    @FXML
    private CheckBox config2 = new CheckBox();
    @FXML
    private CheckBox config3 = new CheckBox();

    private Slider volumeSlider;
    private double volumen;

    private boolean c1 = true;
    private boolean c2 = false;
    private boolean c3 = false;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Boolean flag = true;

    public Controller() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        if (flag){
            sonido.playMusic("home");
            flag = false;
        }
    }
    @FXML

    public void jugar(ActionEvent e){
        System.out.println("Jugar");
    }

    public void config(ActionEvent e){
        System.out.println("Configurar");
    }

    public void salir(ActionEvent e){
        System.exit(0);
    }

    public void irAInicio(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("inicio.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void irAConfig(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("config.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void irAtras(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("characterSelection.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void irAPersonajes(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("characterSelection.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        sonido.stopMusic();
    }

    public void setConfig1(ActionEvent e) {
        if(config1.isSelected()){
            c1 = true;
        }
        else {
            c1 = false;
        }
        System.out.println("Config 1 = " + c1);
    }

    public void setConfig2(ActionEvent e) {
        if(config2.isSelected()){
            c2 = true;
        }
        else {
            c2 = false;
        }
        System.out.println("Config 2 = " + c2);
    }

    public void setConfig3(ActionEvent e) {
        if(config3.isSelected()){
            c3 = true;
        }
        else {
            c3 = false;
        }
        System.out.println("Config 3 = " + c3);
    }

    public void volumeChange(DragEvent e){
        volumen = volumeSlider.getValue();
        System.out.println("El volumen es " + volumen + "%");
    }

    public void pikachu(ActionEvent e) throws IOException{
        root = FXMLLoader.load(getClass().getResource("fightScene.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Elegiste a Pikachu");
    }

    public void charmander(ActionEvent e)throws IOException{
        root = FXMLLoader.load(getClass().getResource("fightScene.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Elegiste a Charmander");
    }

    public void bulbasaur(ActionEvent e)throws IOException{
        root = FXMLLoader.load(getClass().getResource("fightScene.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Elegiste a Bulbasaur");
    }

    public void jigglypuff(ActionEvent e)throws IOException{
        root = FXMLLoader.load(getClass().getResource("fightScene.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Elegiste a Jigglypuff");
    }

    public void pidgey(ActionEvent e)throws IOException{
        root = FXMLLoader.load(getClass().getResource("fightScene.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Elegiste a Pidgey");
    }

    public void squirtle(ActionEvent e)throws IOException{
        root = FXMLLoader.load(getClass().getResource("fightScene.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Elegiste a Squirtle");
    }

    //OkNombre
    public void okNombre(ActionEvent e){
        //imprimir nombre
        System.out.println("Nombre: " + Nombre.getText());
    }

    //cambiar genero
    public void cambiarGenero(ActionEvent e){
        //si  toogle es masculino cambiar a femenino y de color amarillo en negrita y fondo violeta
        if(Genero.isSelected()){
            Genero.setText("Femenino");
            Genero.setStyle("-fx-text-fill: #FFD700; -fx-font-weight: bold; -fx-background-color: #4B0082;");
        }
        else{
            Genero.setText("Masculino");
            //color naranja oscuro, negrita y texto azul claro
            Genero.setStyle("-fx-text-fill: #FF8C00; -fx-font-weight: bold; -fx-background-color: #00BFFF;");
        }
    }


}