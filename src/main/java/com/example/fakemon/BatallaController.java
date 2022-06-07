package com.example.fakemon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.PointLight;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;
import static java.lang.Thread.sleep;
import static javafx.scene.paint.Color.rgb;
import static com.example.fakemon.DatosConfig.*;
public class BatallaController extends Controlador implements Initializable {

    @FXML
    private Cylinder CilindroJugador;
    @FXML
    private Cylinder CilindroBot;
    @FXML
    private PointLight Luz;
    @FXML
    private PointLight LuzCenital;
    @FXML
    private Rectangle BarraVidaJugador;
    @FXML
    private Rectangle BarraVidaBot;
    @FXML
    private Button boton;
    @FXML
    private ImageView ImagenBot;
    @FXML
    private ImageView ImagenJugador;
    @FXML
    private Rectangle Menu;
    @FXML
    private Button Ataque;
    @FXML
    private Button Debilitar;
    @FXML
    private Button Regenerar;
    @FXML
    private Button Potenciar;
    @FXML
    private Text TextMenu;
    private float vidaJugador=100;
    private float vidaBot=100;
    private float vidaJugadorMax=100;
    private float vidaBotMax=100;
    private float vidaActualJugador;
    private float vidaActualBot;

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


        ImagenBot.setOpacity(0);
        ImagenJugador.setOpacity(0);
        TextMenu.setOpacity(0);
        double MenuAltura=Menu.getHeight();
        double BotonesAncho=Ataque.getPrefWidth();
        Menu.setHeight(0);

        //Nuevo arraylist
        ArrayList<Button> botones = new ArrayList<>();
        botones.add(Ataque);
        botones.add(Debilitar);
        botones.add(Regenerar);
        botones.add(Potenciar);

        for (Button b : botones) {
            b.setPrefWidth(0);
            b.setOpacity(0);
        }


        TextMenu.setText(nombre+"-"+battle.getUsrFakemon().getName());
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


        Thread animacionesEntrada= new Thread(new Runnable() {
            @Override
            public void run() {

                int fot=30;
                for(int i=0;i<=fot;i++){
                    ImagenBot.setOpacity((float)i/(float)fot);
                    ImagenJugador.setOpacity((float)i/(float)fot);



                    try {
                        sleep(1000/fot);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                for (int i = 0; i <fot; i++) {
                    Menu.setHeight(MenuAltura*i/fot);
                    try {
                        sleep(1000/fot);
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                TextMenu.setOpacity(1);
                for(Button b:botones){
                    for (int i=1;i<=fot;i++){
                        b.setPrefWidth(BotonesAncho*i/fot);
                        b.setOpacity((float)i/(float)fot);
                        try {
                            sleep(1000/fot);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        animacionesEntrada.start();
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
