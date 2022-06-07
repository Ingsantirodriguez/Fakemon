package com.example.fakemon;

import com.example.fakemon.batalla.Batalla;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.PointLight;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    public Rectangle Menu;
    public Button Ataque;
    public Button Debilitar;
    public Button Regenerar;
    public Button Potenciar;

    public Text TextMenu;
    public ImageView PokebolaBot;
    public ImageView PokebolaJugador;
    public Rectangle BarraRoja1;
    public Rectangle BarraRoja2;
    public Rectangle MenuBot;
    public Text TextMenu1;
    public Text MovimientoRival;

    private final int fot = 30;
    public Button botonOk;

    float vidaJugador = 100;
    float vidaBot = 100;

    float vidaJugadorMax = 100;
    float vidaBotMax = 100;


    float vidaActualJugador;
    float vidaActualBot;

    ArrayList<Button> botones = new ArrayList<>();
    double MenuAltura;
    double BotonesAncho;

    double AlturaCilindro;
    double AnchoBarra;
    double sizefontBoton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        RellenarImagenesPokemon();

        InicializaVariablesVisuales();

        ComenzarAnimacion();

        AnimacionLuz();

        //quitar
        vidaActualBot = 100;
        vidaActualJugador = 100;
    }

    private void MostrarBotones() {
        Thread DesplegarBotones = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Button b : botones) {
                    for (int i = 0; i <= fot; i++) {
                        b.setPrefWidth(BotonesAncho * i / fot);
                        b.setOpacity((float) i / (float) fot);
                        b.setFont(Font.font("Verdana", FontWeight.BOLD,sizefontBoton * i / fot));

                        try {
                            //noinspection BusyWait
                            sleep(1000 / fot);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
        DesplegarBotones.start();
    }

    private void AnimacionLuz() {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                int x = 1;

                Luz.setTranslateX(1);
                while (true) {

                    if (Luz.getTranslateX() == 0 || Luz.getTranslateX() == 700) {
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
    }

    private void ComenzarAnimacion() {
        Thread animacionesEntrada = new Thread(new Runnable() {
            @Override
            public void run() {


                for (int i = 0; i < fot; i++) {
                    CilindroBot.setHeight(AlturaCilindro * (float) i / (float) fot);
                    CilindroJugador.setHeight(AlturaCilindro * (float) i / (float) fot);

                    try {
                        sleep(1000 / fot);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                for (int i = 0; i <= fot; i++) {
                    ImagenBot.setOpacity((float) i / (float) fot);
                    ImagenJugador.setOpacity((float) i / (float) fot);
                    PokebolaJugador.setOpacity((float) i / (float) fot);
                    PokebolaBot.setOpacity((float) i / (float) fot);
                    BarraVidaBot.setWidth(AnchoBarra * (float) i / (float) fot);
                    BarraVidaJugador.setWidth(AnchoBarra * (float) i / (float) fot);


                    try {
                        sleep(1000 / fot);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                BarraRoja1.setWidth(AnchoBarra);
                BarraRoja2.setWidth(AnchoBarra);


                for (int i = 0; i < fot; i++) {
                    Menu.setHeight(MenuAltura * i / fot);
                    MenuBot.setHeight(MenuAltura * i / fot);
                    TextMenu.setOpacity((float) i / (float) fot);
                    TextMenu1.setOpacity((float) i / (float) fot);

                    try {
                        sleep(1000 / fot);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                if (Batalla.getTurno().equals("user")) {
                    MostrarBotones();
                } else {
                    for (int i = 0; i < fot; i++) {
                        MovimientoRival.setOpacity((float) i / (float) fot);
                        botonOk.setOpacity((float) i / (float) fot);
                        try {
                            sleep(1000 / fot);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        animacionesEntrada.start();
    }

    private void InicializaVariablesVisuales() {

        MenuAltura = Menu.getHeight();
        BotonesAncho = Ataque.getPrefWidth();
        AlturaCilindro = CilindroJugador.getHeight();
        AnchoBarra = BarraRoja1.getWidth();
        sizefontBoton= botonOk.getFont().getSize();

        ColoreaCilindros();

        CilindroJugador.setHeight(0);
        CilindroBot.setHeight(0);

        ImagenBot.setOpacity(0);
        ImagenJugador.setOpacity(0);

        PokebolaJugador.setOpacity(0);
        PokebolaBot.setOpacity(0);

        BarraVidaBot.setWidth(0);
        BarraVidaJugador.setWidth(0);
        BarraRoja1.setWidth(0);
        BarraRoja2.setWidth(0);


        TextMenu.setOpacity(0);
        TextMenu1.setOpacity(0);
        MovimientoRival.setOpacity(0);

        botonOk.setOpacity(0);

        Menu.setHeight(0);
        MenuBot.setHeight(0);

        //Nuevo arraylist
        ;
        botones.add(Ataque);
        botones.add(Debilitar);
        botones.add(Regenerar);
        botones.add(Potenciar);

        for (Button b : botones) {
            b.setPrefWidth(0);
            b.setOpacity(0);
            //Texto en negrita
            b.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        }

        //Boton Ataque texto marron fondo rosa
        Ataque.setTextFill(Color.rgb(100, 150, 130, 1));
        Ataque.setStyle("-fx-background-color: #ffc0cb;");
        //Boton Debilitar texto verde fondo violeta
        Debilitar.setTextFill(Color.rgb(0, 150, 120, 0.8));
        Debilitar.setStyle("-fx-background-color: #e6e6fa;");
        //Boton Regenerar texto azul fondo blanco
        Regenerar.setTextFill(Color.rgb(0, 0, 150, 0.8));
        Regenerar.setStyle("-fx-background-color: #ffffff;");
        //Boton Potenciar texto naranja fondo doraado
        Potenciar.setTextFill(Color.rgb(150, 100, 0, 0.8));
        Potenciar.setStyle("-fx-background-color: #ffa500;");

        TextMenu.setText("Player-" + battle.getUsrFakemon().getName());
    }

    private void ColoreaCilindros() {
        PhongMaterial ColorJugador = new PhongMaterial();
        ColorJugador.setDiffuseColor(rgb(0, 255, 0, 0.5));
        CilindroJugador.setMaterial(ColorJugador);
        PhongMaterial ColorBot = new PhongMaterial();
        ColorBot.setDiffuseColor(rgb(0, 255, 0, 0.5));
        CilindroBot.setMaterial(ColorBot);
    }

    private void RellenarImagenesPokemon() {
        String usfakemon = "src/main/resources/com/example/fakemon/images/" + battle.getUsrFakemon().getName() + ".png";
        String botfakemon = "src/main/resources/com/example/fakemon/images/" + battle.getBotFakemon().getName() + ".png";
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
    }


    public void ataque(ActionEvent actionEvent) {

        float cambioVidaBot = -1;
        float cambioVidaJugador = -49;

        Thread barraVida = new Thread(new Runnable() {
            @Override
            public void run() {
                int fotogramas = 30;
                for (int i = 0; i < fotogramas; i++) {
                    BarraVidaBot.setWidth(BarraVidaBot.getWidth() + (cambioVidaBot / fotogramas) * 2.2);
                    BarraVidaJugador.setWidth(BarraVidaJugador.getWidth() + (cambioVidaJugador / fotogramas) * 2.2);

                    float porcentajeVidaBot = (vidaBot / vidaBotMax);
                    float porcentajeVidaJugador = (vidaJugador / vidaJugadorMax);

                    //porcentajeVidaJugador=(-porcentajeVidaJugador+porcentajeVidaJugador)/2;
                    //porcentajeVidaBot=(-porcentajeVidaBot+porcentajeVidaBot)/2;

                    PhongMaterial ColorJugador = new PhongMaterial();
                    ColorJugador.setDiffuseColor(rgb((int) (255 - 255 * porcentajeVidaJugador), (int) (255 * porcentajeVidaJugador), 0, 0.5));
                    CilindroJugador.setMaterial(ColorJugador);
                    PhongMaterial ColorBot = new PhongMaterial();
                    ColorBot.setDiffuseColor(rgb((int) (255 - 255 * porcentajeVidaBot), (int) (255 * porcentajeVidaBot), 0, 0.5));
                    CilindroBot.setMaterial(ColorBot);

                    vidaBot = vidaBot + cambioVidaBot / fotogramas;
                    vidaJugador = vidaJugador + cambioVidaJugador / fotogramas;

                    try {
                        sleep(1000 / fotogramas);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ;
                }

            }
        });

        barraVida.start();
    }


    public void PasarTurno(ActionEvent actionEvent) {

        Thread ocultarMovRival = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < fot; i++) {
                    MovimientoRival.setOpacity(1 - i / (float)fot);
                    botonOk.setOpacity(i / (float)fot);
                    botonOk.setCancelButton(true);
                }
            }
        });
        ocultarMovRival.start();

        MostrarBotones();

    }
}
