package com.example.fakemon;

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
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static com.example.fakemon.DatosConfig.nombre;
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
    public Text turno;
    public ImageView PokebolaBot;
    public ImageView PokebolaJugador;
    public Rectangle BarraRoja1;
    public Rectangle BarraRoja2;
    public Rectangle MenuBot;
    public Text TextMenu1;
    //public Text MovimientoRival;
    private final int fot = 30;
    // public Button botonOk;
//    float vidaJugador = 100;
//    float vidaBot = 100;
//    float vidaJugadorMax = 100;
//    float vidaBotMax = 100;
//    float vidaActualJugador;
//    float vidaActualBot;
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
        //AnimacionLuz();

    }

    private void MostrarBotones() {
        // cada vez que haga la animacion de mostrar botones es equivalente a que el turno lo tenga el usr
        turno.setText("\nTurno: USR");
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
//        Thread DesplegarBotones = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // cada vez que haga la animacion de mostrar botones es equivalente a que el turno lo tenga el usr
//                turno.setText("Turno: USR");
//                for (Button b : botones) {
//                    for (int i = 0; i <= fot; i++) {
//                        b.setPrefWidth(BotonesAncho * i / fot);
//                        b.setOpacity((float) i / (float) fot);
//                        b.setFont(Font.font("Verdana", FontWeight.BOLD,sizefontBoton * i / fot));
//
//                        try {
//                            //noinspection BusyWait
//                            sleep(1000 / fot);
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                }
//            }
//        });
//        DesplegarBotones.start();
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

                if (battle.usrTurno()) {
                    MostrarBotones();
                }
                else {
                    processTurn();
//                    for (int i = 0; i < fot; i++) {
//                        //MovimientoRival.setOpacity((float) i / (float) fot);
//                        //botonOk.setOpacity((float) i / (float) fot);
//                        try {
//                            sleep(1000 / fot);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
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
        //sizefontBoton= botonOk.getFont().getSize();

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
        //MovimientoRival.setOpacity(0);
        //botonOk.setOpacity(0);
        Menu.setHeight(0);
        MenuBot.setHeight(0);

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

        TextMenu.setText(nombre+"-" + battle.getUsrFakemon().getName());
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
        this.ataque();
    }

    private void ataque(){
        Thread barraVida = new Thread(new Runnable() {
            @Override
            public void run() {
                int fotogramas = 30;
                float cambioVidaBot = 0;
                float cambioVidaJugador = 0;

                if(battle.usrTurno()){
                    // turno del usuario ==> bot recibe ataque de usr
                    System.out.println("\nusr --> attack --> bot..");
                    cambioVidaBot = -(float) battle.getUsrFakemon().getAttackDamage();
                    new Atacar().actuar(battle.getUsrFakemon(), battle.getBotFakemon());
//                    battle.getBotFakemon().receiveAttack(battle.getUsrFakemon().getAttackDamage());
                    battle.setUsrTurn(false);
                }else {
                    // turno del bot ==> usr recibe ataque de bot
                    System.out.println("\nbot --> attack --> usr..");
                    cambioVidaJugador = -(float) battle.getBotFakemon().getAttackDamage();
                    new Atacar().actuar(battle.getBotFakemon(), battle.getUsrFakemon());
//                    battle.getUsrFakemon().receiveAttack(battle.getBotFakemon().getAttackDamage());
                    battle.setUsrTurn(true);
                }

                for (int i = 0; i < fotogramas; i++) {
                    BarraVidaBot.setWidth(BarraVidaBot.getWidth() + (cambioVidaBot / fotogramas) * 2.2);
                    BarraVidaJugador.setWidth(BarraVidaJugador.getWidth() + (cambioVidaJugador / fotogramas) * 2.2);

                    try {
                        sleep(1000 / fotogramas);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("after bot life: " + battle.getBotFakemon().getCurrentLife());
                System.out.println("after usr life: " + battle.getUsrFakemon().getCurrentLife());

                float porcentajeVidaBot = (float) (battle.getBotFakemon().getCurrentLife()*100/battle.getBotFakemon().getBasicLife())/100;
                float porcentajeVidaJugador = (float) (battle.getUsrFakemon().getCurrentLife()*100/battle.getUsrFakemon().getBasicLife())/100;

                PhongMaterial ColorJugador = new PhongMaterial();
                ColorJugador.setDiffuseColor(rgb((int) (255 - 255 * porcentajeVidaJugador), (int) (255 * porcentajeVidaJugador), 0, 0.5));
                CilindroJugador.setMaterial(ColorJugador);
                PhongMaterial ColorBot = new PhongMaterial();
                ColorBot.setDiffuseColor(rgb((int) (255 - 255 * porcentajeVidaBot), (int) (255 * porcentajeVidaBot), 0, 0.5));
                CilindroBot.setMaterial(ColorBot);

                processTurn();
            }
        });

        barraVida.start();

    }

    private void debilitar(){
        Thread barraVida = new Thread(new Runnable() {
            @Override
            public void run() {

                if(battle.usrTurno()){
                    // turno del usuario ---> debilito al bot
                    System.out.println("\nusr --> weaken --> bot..");
                    new Debilitar().actuar(battle.getUsrFakemon(), battle.getBotFakemon());
//                    battle.getBotFakemon().weakening(battle.getUsrFakemon().weaken());
                    battle.setUsrTurn(false);
                }else {
                    // turno del bot ---> debilito al usuario
                    System.out.println("\nbot --> weaken --> usr..");
                    new Debilitar().actuar(battle.getBotFakemon(), battle.getUsrFakemon());
//                    battle.getUsrFakemon().weakening(battle.getBotFakemon().weaken());
                    battle.setUsrTurn(true);
                }

                // Agregar efectos visuales ???
                processTurn();
            }
        });

        barraVida.start();
    }
    public void debilitar(ActionEvent actionEvent) {
        debilitar();
    }

    public void regenerar(ActionEvent actionEvent) {
        regenerar();
    }

    private void regenerar(){
        Thread barraVida = new Thread(new Runnable() {
            @Override
            public void run() {
                int fotogramas = 30;
                float cambioVidaBot = battle.getBotFakemon().getCurrentLife();
                float cambioVidaJugador = battle.getUsrFakemon().getCurrentLife();

                if(battle.usrTurno()){
                    System.out.println("\nusr --> regenerate life");
//                    battle.getUsrFakemon().regenerate();
                    new Regenerar().actuar(battle.getUsrFakemon(), battle.getBotFakemon());
                    cambioVidaJugador -= battle.getUsrFakemon().getCurrentLife();
                    battle.setUsrTurn(false);
                }else {
                    System.out.println("\nbot --> regenerate life");
//                    battle.getBotFakemon().regenerate();
                    new Regenerar().actuar(battle.getBotFakemon(), battle.getUsrFakemon());
                    cambioVidaBot -= battle.getBotFakemon().getCurrentLife();
                    battle.setUsrTurn(true);
                }

                // Agregar efectos visuales ???

                for (int i = 0; i < fotogramas; i++) {
                    BarraVidaBot.setWidth(BarraVidaBot.getWidth() + (cambioVidaBot / fotogramas) * 2.2);
                    BarraVidaJugador.setWidth(BarraVidaJugador.getWidth() + (cambioVidaJugador / fotogramas) * 2.2);

                    try {
                        sleep(1000 / fotogramas);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("after bot life: " + battle.getBotFakemon().getCurrentLife());
                System.out.println("after usr life: " + battle.getUsrFakemon().getCurrentLife());

                float porcentajeVidaBot = (float) (battle.getBotFakemon().getCurrentLife()*100/battle.getBotFakemon().getBasicLife())/100;
                float porcentajeVidaJugador = (float) (battle.getUsrFakemon().getCurrentLife()*100/battle.getUsrFakemon().getBasicLife())/100;

                PhongMaterial ColorJugador = new PhongMaterial();
                ColorJugador.setDiffuseColor(rgb((int) (255 - 255 * porcentajeVidaJugador), (int) (255 * porcentajeVidaJugador), 0, 0.5));
                CilindroJugador.setMaterial(ColorJugador);
                PhongMaterial ColorBot = new PhongMaterial();
                ColorBot.setDiffuseColor(rgb((int) (255 - 255 * porcentajeVidaBot), (int) (255 * porcentajeVidaBot), 0, 0.5));
                CilindroBot.setMaterial(ColorBot);

                processTurn();
            }
        });

        barraVida.start();
    }

    public void potenciar(ActionEvent actionEvent) {
        potenciar();
    }

    private void potenciar(){
        Thread barraVida = new Thread(new Runnable() {
            @Override
            public void run() {

                if(battle.usrTurno()){
                    System.out.println("\nusr --> maximize attack");
//                    battle.getUsrFakemon().maximizeAttack();
                    new Potenciar().actuar(battle.getUsrFakemon(), battle.getBotFakemon());
                    battle.setUsrTurn(false);
                }else {
                    System.out.println("\nbot --> maximize attack");
//                    battle.getBotFakemon().maximizeAttack();
                    new Potenciar().actuar(battle.getBotFakemon(), battle.getUsrFakemon());
                    battle.setUsrTurn(true);
                }

                // agregar animaciones ???

                System.out.println("after bot attack: " + battle.getBotFakemon().getAttackDamage());
                System.out.println("after usr attac: " + battle.getUsrFakemon().getAttackDamage());

                processTurn();
            }
        });

        barraVida.start();
    }

    private void processTurn(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(battle.getUsrFakemon().getCurrentLife() >0 && battle.getBotFakemon().getCurrentLife() >0){
            if(!battle.usrTurno()){                                 // si es el turno del bot, elijo su accion
                turno.setText("\nTurno: BOT");                        // la ejecuto y espero a que el usr haga un ActionEvent
                System.out.println("\nEjecutando turno del bot..");   // en otro hilo (????
                botTurn();
            }else {
                MostrarBotones();
            }
        }else{
            if(battle.getUsrFakemon().getCurrentLife() >0){
                // ganador: usr
                battle.setWinner(battle.getUsrFakemon());
            }else{
                // ganador bot
                battle.setWinner(battle.getBotFakemon());
            }
            // de aca me tengo que ir a la ESCENA FINAL
            while (true){

            }
        }

//        Thread hiloTurno = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//
//                if(!battle.usrTurno()){                                 // si es el turno del bot, elijo su accion
//                    turno.setText("Turno: BOT");                        // la ejecuto y espero a que el usr haga un ActionEvent
//                    System.out.println("Ejecutando turno del bot..");   // en otro hilo (????
//                    botTurn();
//                }else {
//                    MostrarBotones();
//                }
//            }
//        });
//        hiloTurno.start();
    }

    private void botTurn(){                     // tampoco que vamos a hacer una AI....
        Random rd = new Random();
        int val = rd.nextInt(4) + 1;    // random number beetwen 1-4

        switch (val){
            case 1:
                ataque();
                break;
            case 2:
                debilitar();
                break;
            case 3:
                potenciar();
                break;
            case 4:
                // no tiene sentido regenerar vida cuando la suya es maxima..
                if(battle.getBotFakemon().getCurrentLife() == battle.getBotFakemon().getBasicLife()){
                    botTurn();
                }else{
                    regenerar();
                }
                break;
            default:
                System.out.println("error generando numero random");
                break;
        }
    }
    public void PasarTurno(ActionEvent actionEvent) {
        Thread ocultarMovRival = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < fot; i++) {
                    //MovimientoRival.setOpacity(1 - i / (float)fot);
                    //botonOk.setOpacity(i / (float)fot);
                    //botonOk.setCancelButton(true);
                }
                ataque();
            }
        });

        ocultarMovRival.start();
        MostrarBotones();

    }
}
