package com.example.fakemon;

import com.example.fakemon.acciones.Atacar;
import com.example.fakemon.acciones.Debilitar;
import com.example.fakemon.acciones.Potenciar;
import com.example.fakemon.acciones.Regenerar;
import com.example.fakemon.fakemons.Fakemon;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.PointLight;
import javafx.scene.Scene;
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static com.example.fakemon.DatosConfig.nombre;
import static com.example.fakemon.MainApplication.stage;
import static java.lang.Thread.sleep;
import static javafx.scene.paint.Color.rgb;

public class BatallaController extends Controlador implements Observer, Initializable {

    public Cylinder CilindroJugador;
    public Cylinder CilindroBot;
    public PointLight Luz;
    public PointLight LuzCenital;
    public Rectangle BarraVidaJugador;
    public Rectangle BarraVidaBot;
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
    private final int fot = 30;
    public Text Batalla_n;
    ArrayList<Button> botones = new ArrayList<>();
    double MenuAltura;
    double BotonesAncho;
    double AlturaCilindro;
    double AnchoBarra;
    double sizefontBoton;
    double altoCilindro;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //observar fakemones
        battle.getUsrFakemon().addObserver(this);
        battle.getBotFakemon().addObserver(this);
        rellenarImagenesPokemon();
        inicializaVariablesVisuales();
        comenzarAnimacion();
    }

    private void mostrarBotones() {
        // cada vez que haga la animacion de mostrar botones es equivalente a que el turno lo tenga el usr
        turno.setText("Turno: USR");

        for (Button b : botones) {
            for (int i = 0; i <= fot; i++) {
                b.setPrefWidth(BotonesAncho * i / fot);
                b.setOpacity((float) i / (float) fot);
                b.setFont(Font.font("Verdana", FontWeight.BOLD,sizefontBoton * i / fot));

                try {
                    sleep(1000 / fot);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //habilitar botones
        for (Button b : botones) {
            b.setDisable(false);
        }
    }

    private void comenzarAnimacion() {
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
                    mostrarBotones();
                } else {
                    processTurn();
                }
            }
        });

        animacionesEntrada.start();
    }

    private void inicializaVariablesVisuales() {

        MenuAltura = Menu.getHeight();
        BotonesAncho = Ataque.getPrefWidth();
        AlturaCilindro = CilindroJugador.getHeight();
        AnchoBarra = BarraRoja1.getWidth();
        sizefontBoton= Ataque.getFont().getSize();
        altoCilindro = CilindroJugador.getHeight();

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

        Menu.setHeight(0);
        MenuBot.setHeight(0);

        botones.add(Ataque);
        botones.add(Debilitar);
        botones.add(Regenerar);
        botones.add(Potenciar);

        for (Button b : botones) {
            b.setPrefWidth(0);
            b.setOpacity(0);
            b.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
            b.setDisable(true);
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
        Batalla_n.setText(battle.getInstance());
    }

    private void ColoreaCilindros() {
        PhongMaterial ColorJugador = new PhongMaterial();
        ColorJugador.setDiffuseColor(rgb(0, 255, 0, 0.5));
        CilindroJugador.setMaterial(ColorJugador);
        PhongMaterial ColorBot = new PhongMaterial();
        ColorBot.setDiffuseColor(rgb(0, 255, 0, 0.5));
        CilindroBot.setMaterial(ColorBot);
    }

    private void rellenarImagenesPokemon() {
        String usfakemon = battle.getUsrFakemon().getImgPath();
        String botfakemon = battle.getBotFakemon().getImgPath();

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
        deshabilitarBotones();
        System.out.println("\nusr --> attack --> bot..");
        new Atacar().actuar(battle.getUsrFakemon(), battle.getBotFakemon());
        System.out.println("new bot life: " + battle.getBotFakemon().getCurrentLife());
        battle.setUsrTurn(false);
        processTurn();
    }

    public void debilitar(ActionEvent actionEvent) {
        deshabilitarBotones();
        System.out.println("\nusr --> weaken --> bot..");
        new Debilitar().actuar(battle.getUsrFakemon(), battle.getBotFakemon());
        System.out.println("new bot attack: " + battle.getBotFakemon().getAttackDamage());
        battle.setUsrTurn(false);
        processTurn();
    }

    public void regenerar(ActionEvent actionEvent) {
        deshabilitarBotones();
        System.out.println("\nusr --> regenerate life");
        new Regenerar().actuar(battle.getUsrFakemon(), battle.getBotFakemon());
        System.out.println("new usr life: " + battle.getUsrFakemon().getCurrentLife());
        battle.setUsrTurn(false);
        processTurn();
    }

    public void potenciar(ActionEvent actionEvent) {
        deshabilitarBotones();
        System.out.println("\nusr --> maximize attack");
        new Potenciar().actuar(battle.getUsrFakemon(), battle.getBotFakemon());
        System.out.println("new usr attack: " + battle.getUsrFakemon().getAttackDamage());
        battle.setUsrTurn(false);
        processTurn();
    }

    private void deshabilitarBotones() {
        for (Button b : botones) {
            b.setDisable(true);
        }
        Thread animacion= new Thread(new Runnable() {
            @Override
            public void run() {

                for (float i=0;i<fot;i++){
                    for(Button b : botones){
                        b.setPrefWidth(AnchoBarra*(1-i/fot));
                        b.setFont(Font.font(sizefontBoton*(1-i/fot)));
                        b.setOpacity(1-i/fot);
                    }
                    try {
                        Thread.sleep(1000/fot);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        animacion.start();
    }

    private void processTurn(){
        if(!battle.usrTurno()){                                   // si es el turno del bot, elijo su accion
            // turno.setText("BOT turn");                        // la ejecuto y espero a que el usr haga un ActionEvent
            System.out.println("\nEjecutando turno del bot..");   // en otro hilo (????
            turno.setText("Turno: BOT");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            battle.botTurn();
            processTurn();
        }else {
            mostrarBotones();
        }
    }

    public void actualizar() {
        Event event = new ActionEvent();
        if(battle.getBotFakemon().getCurrentLife() == 0 || battle.getUsrFakemon().getCurrentLife() == 0){
            battle.setWinner(battle.getUsrFakemon().getCurrentLife() > 0);
            try {
                // Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("fxml/finalBattleScene.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Fakemon");
                stage.show(); // Muestra la ventana
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            
        }
        
        if(battle.getUsrFakemon().getCurrentLife()!= battle.getUsrFakemon().getLastLife()){
            Fakemon fakemon=battle.getUsrFakemon();
            Animacion(fakemon, CilindroJugador, PokebolaJugador,BarraVidaJugador);
            fakemon.setLastLife(fakemon.getCurrentLife());
        }
        else{
            Fakemon fakemon=battle.getBotFakemon();
            Animacion(fakemon, CilindroBot, PokebolaBot,BarraVidaBot);
            fakemon.setLastLife(fakemon.getCurrentLife());
        }
    }

    private void Animacion(Fakemon fakemon, Cylinder cilindro, ImageView pokebola, Rectangle barraVida){

        int vidaMaxima=fakemon.getBasicLife();
        int vidaAnterior=fakemon.getLastLife();
        int vidaRestante=fakemon.getCurrentLife();
        int cambio=vidaRestante-vidaAnterior;
        // System.out.println("\nVida restante: "+vidaRestante+"\nCambio: "+cambio+"\nVida maxima: "+vidaMaxima+"\nVida anterior: "+vidaAnterior);

        double BarraPorVida=AnchoBarra/vidaMaxima;
        double colorPorVida=255/(float)vidaMaxima;
        double alturaPorVida=altoCilindro/vidaMaxima;

        Thread animacion=new Thread(new Runnable() {
            @Override
            public void run() {

                PhongMaterial Color = new PhongMaterial();

                for (float i = 0; i < fot; i++) {

                    barraVida.setWidth((vidaAnterior+cambio*i/fot)*BarraPorVida);

                    int verde=(int)((vidaAnterior+cambio*i/fot)*colorPorVida);
                    int rojo=(int)(255-(vidaAnterior+cambio*i/fot)*colorPorVida);

                    Color.setDiffuseColor(rgb(rojo, verde, 0, 0.5));
                    cilindro.setMaterial(Color);
                    cilindro.setHeight(alturaPorVida*(vidaAnterior+cambio*i/fot));
      //              pokebola.setLayoutY(pokebola.getLayoutY()+alturaPorVida*cambio*i/fot);

                    try {
                        sleep(1000/fot);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        animacion.start();
    }
}





