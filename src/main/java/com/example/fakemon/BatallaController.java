package com.example.fakemon;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.PointLight;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Rectangle;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;
import static java.lang.Thread.sleep;
import static javafx.scene.paint.Color.rgb;

public class BatallaController extends Controlador implements Initializable {


    public Cylinder CilindroJugador;
    public Cylinder CilindroBot;
    public PointLight Luz;

    public PointLight LuzCenital;
    public Rectangle BarraVidaJugador;
    public Rectangle BarraVidaBot;
    public Button boton;
    public ImageView ImagenBot;
    public ImageView ImagenJugador;

    float vidaJugador=100;
    float vidaBot=100;

    float vidaJugadorMax=100;
    float vidaBotMax=100;


    float vidaActualJugador;
    float vidaActualBot;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        PhongMaterial ColorJugador = new PhongMaterial();
        ColorJugador.setDiffuseColor(rgb(0, 255, 0, 0.5));
        CilindroJugador.setMaterial(ColorJugador);
        PhongMaterial ColorBot = new PhongMaterial();
        ColorBot.setDiffuseColor(rgb(0, 255, 0, 0.5));
        CilindroBot.setMaterial(ColorBot);

        String usfakemon= "src/main/resources/com/example/fakemon/images/"+battle.getUsrFakemon().getName()+".png";
        String botfakemon= "src/main/resources/com/example/fakemon/images/"+battle.getBotFakemon().getName()+".png";
        Path imageFile = Paths.get(usfakemon);
        try {
            ImagenJugador.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Path imgFile = Paths.get(botfakemon);

        try {
            ImagenBot.setImage(new Image(imgFile.toUri().toURL().toExternalForm()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                int x= 1;

                Luz.setTranslateX(1);
                while (true) {

                    if(Luz.getTranslateX()==0 || Luz.getTranslateX()==700) {
                        x *= -1;
                        Luz.setTranslateX(abs(Luz.getTranslateX() - 1));
                    }
                    Luz.setTranslateX((Luz.getTranslateX() + x));

                    try {
                        sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


        });
        hilo.start();


        vidaActualBot=100;
        vidaActualJugador=100;
    }













    public void ataque(ActionEvent actionEvent) {

        float cambioVidaBot=-1;
        float cambioVidaJugador=-49;

        Thread barraVida = new Thread(new Runnable() {
            @Override
            public void run() {
                int fotogramas=30;
                for(int i=0;i<fotogramas;i++) {
                BarraVidaBot.setWidth(BarraVidaBot.getWidth() + (cambioVidaBot/fotogramas)*2.2);
                BarraVidaJugador.setWidth(BarraVidaJugador.getWidth() + (cambioVidaJugador/fotogramas)*2.2);

                    float porcentajeVidaBot=(vidaBot/vidaBotMax);
                    float porcentajeVidaJugador=(vidaJugador/vidaJugadorMax);

                    //porcentajeVidaJugador=(-porcentajeVidaJugador+porcentajeVidaJugador)/2;
                    //porcentajeVidaBot=(-porcentajeVidaBot+porcentajeVidaBot)/2;

                    PhongMaterial ColorJugador = new PhongMaterial();
                    ColorJugador.setDiffuseColor(rgb((int)(255-255*porcentajeVidaJugador), (int)(255*porcentajeVidaJugador), 0, 0.5));
                    CilindroJugador.setMaterial(ColorJugador);
                    PhongMaterial ColorBot = new PhongMaterial();
                    ColorBot.setDiffuseColor(rgb((int)(255-255*porcentajeVidaBot), (int)(255*porcentajeVidaBot), 0, 0.5));
                    CilindroBot.setMaterial(ColorBot);

                    vidaBot=vidaBot+cambioVidaBot/fotogramas;
                    vidaJugador=vidaJugador+cambioVidaJugador/fotogramas;

                try {
                    sleep(1000/fotogramas);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ;}

            }
        });

            barraVida.start();


    }
}
