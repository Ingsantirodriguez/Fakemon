package com.example.fakemon;

import com.example.fakemon.acciones.Atacar;
import com.example.fakemon.acciones.Debilitar;
import com.example.fakemon.acciones.Potenciar;
import com.example.fakemon.acciones.Regenerar;
import com.example.fakemon.fakemons.Fakemon;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static com.example.fakemon.DatosConfig.nombre;
import static com.example.fakemon.MainApplication.*;
import static java.lang.Thread.*;
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

    public Text Batalla_n;
    ArrayList<Button> botones = new ArrayList<>();
    ArrayList<Node> nodos = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sonido.playMusic("battle");
        InicializarVariables();
        AnimacionEntrada();
        if(battle.usrTurno()) {
            MostrarBotones();
        }
        ;



    }

    private void TurnoBot() {

        turno.setText("Turno del Bot");

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();}
        Thread thread = new Thread(() -> {
            battle.botTurn();
        });
        thread.start();
        //al terminar el turno del bot, se muestran los botones
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        turno.setText("Turno del Jugador");
        MostrarBotones();

    }

    private void MostrarBotones() {
        ParallelTransition parallelTransition = new ParallelTransition();
        for (int i = 0; i < botones.size(); i++) {

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(750), botones.get(i));
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.setDelay(Duration.millis(i * 500+2000));
            parallelTransition.getChildren().add(fadeTransition);
            botones.get(i).setDisable(false);
        }

        Thread thread = new Thread(parallelTransition::play);
        thread.start();
    }



    private void InicializarVariables() {

        battle.getBotFakemon().addObserver(this);
        battle.getUsrFakemon().addObserver(this);

        botones.add(Ataque);
        botones.add(Debilitar);
        botones.add(Regenerar);
        botones.add(Potenciar);

        for (Button b : botones)  {
            b.setOpacity(0);
            b.setDisable(true);
        }

        nodos.add(BarraVidaJugador);
        nodos.add(BarraVidaBot);
        nodos.add(BarraRoja1);
        nodos.add(BarraRoja2);
        nodos.add(Menu);
        nodos.add(MenuBot);
        nodos.add(TextMenu);
        nodos.add(TextMenu1);
        nodos.add(PokebolaBot);
        nodos.add(PokebolaJugador);
        //nodos.add(CilindroJugador);
        //nodos.add(CilindroBot);
        nodos.add(ImagenBot);
        nodos.add(ImagenJugador);
        nodos.add(turno);

        for (Node n: nodos) {
            n.setOpacity(0);
        }

        String nombreDelFakemon = battle.getUsrFakemon().getName();

        TextMenu.setText(nombre+"_"+nombreDelFakemon);
        Path imgFile = Paths.get(nombreDelFakemon);

        try {
            ImagenJugador.setImage(new Image(imgFile.toUri().toURL().toExternalForm()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }




        nombreDelFakemon = battle.getBotFakemon().getName();


        TextMenu1.setText("Bot_"+nombreDelFakemon);
        imgFile = Paths.get(nombreDelFakemon);

        try {
            ImagenBot.setImage(new Image(imgFile.toUri().toURL().toExternalForm()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        Batalla_n.setText("Batalla numero: "+battle.getNroBatalla());

        if(battle.usrTurno()) {
            turno.setText("Turno de: "+ nombre);
        } else {
            turno.setText("Turno de: Bot");
        }




    }



    private void AnimacionEntrada() {

        //animaciones paralelas
        ParallelTransition pt = new ParallelTransition();

        for (Node n:nodos) {
            FadeTransition ft = new FadeTransition(Duration.seconds(1), n);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.setAutoReverse(true);
            pt.getChildren().add(ft);

        }

        Thread thread = new Thread(() -> {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pt.play();

            if(!battle.usrTurno()) {
                TurnoBot();
            }

        });
        thread.start();

    }





    @Override
    public void actualizar() {

        ParallelTransition parallelTransition = new ParallelTransition();

        ScaleTransition st = new ScaleTransition(Duration.seconds(1), BarraVidaJugador);
        double vidaAnterior = (double)(battle.getUsrFakemon().getLastLife())/battle.getUsrFakemon().getBasicLife();
        st.setFromX(vidaAnterior);
        double vidaActual = (double)(battle.getUsrFakemon().getCurrentLife())/battle.getUsrFakemon().getBasicLife();
        st.setToX(vidaActual);

        st.setDelay(Duration.millis(500));
        st.setAutoReverse(true);
        //final suavizado
        st.setInterpolator(Interpolator.EASE_BOTH);
        parallelTransition.getChildren().add(st);

        ScaleTransition st2 = new ScaleTransition(Duration.seconds(1), BarraVidaBot);
        vidaAnterior = (double)(battle.getBotFakemon().getLastLife())/battle.getBotFakemon().getBasicLife();
        st2.setFromX(vidaAnterior);
        vidaActual = (double)(battle.getBotFakemon().getCurrentLife())/battle.getBotFakemon().getBasicLife();
        st2.setToX(vidaActual);

        st2.setDelay(Duration.millis(500));
        st2.setAutoReverse(true);
        //final suavizado
        st2.setInterpolator(Interpolator.EASE_BOTH);
        parallelTransition.getChildren().add(st2);

        parallelTransition.play();

        battle.getUsrFakemon().setLastLife(battle.getUsrFakemon().getCurrentLife());
        battle.getBotFakemon().setLastLife(battle.getBotFakemon().getCurrentLife());

        //si algun fakemon murio ir a la siguiente escena
        int vidaJugador = battle.getUsrFakemon().getCurrentLife();
        int vidaBot = battle.getBotFakemon().getCurrentLife();
        if(vidaBot <= 0 || vidaJugador <= 0) {
            try {
                SiguientePantalla();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }





    }

    private void SiguientePantalla() throws IOException {

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean ganoUsr= battle.getUsrFakemon().getCurrentLife() > 0;
        battle.setWinner(ganoUsr);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("fxml/finalBattleScene.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Fakemon");
                stage.show();
                FadeTransition ft = new FadeTransition(Duration.seconds(1), scene.getRoot());
                ft.setFromValue(0);
                ft.setAutoReverse(true);
                ft.setToValue(1);
                ft.play();
            }
        });




    }

    public void ataque(ActionEvent actionEvent) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                battle.setUsrTurn(false);
                System.out.println("\nusr --> attack --> bot..");
                new Atacar().actuar(battle.getUsrFakemon(), battle.getBotFakemon());
                System.out.println("new bot life: " + battle.getBotFakemon().getCurrentLife());
                OcultarBotones();
                TurnoBot();
            }
        });
        thread.start();
    }

    public void debilitar(ActionEvent actionEvent) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                battle.setUsrTurn(false);
                System.out.println("\nusr --> debilitar --> bot..");
                new Debilitar().actuar(battle.getUsrFakemon(), battle.getBotFakemon());
                System.out.println("new bot attack: " + battle.getBotFakemon().getAttackDamage());
                OcultarBotones();
                TurnoBot();
            }
        });
        thread.start();
    }

    public void regenerar(ActionEvent actionEvent) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                battle.setUsrTurn(false);
                System.out.println("\nusr --> regenerar --> usr..");
                new Regenerar().actuar(battle.getUsrFakemon(), battle.getUsrFakemon());
                System.out.println("new usr life: " + battle.getUsrFakemon().getCurrentLife());
                OcultarBotones();
                TurnoBot();
            }
        });
        thread.start();
    }

    public void potenciar(ActionEvent actionEvent) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                battle.setUsrTurn(false);
                System.out.println("\nusr --> potenciar --> usr..");
                new Potenciar().actuar(battle.getUsrFakemon(), battle.getUsrFakemon());
                System.out.println("new usr attack: " + battle.getUsrFakemon().getAttackDamage());
                OcultarBotones();
                TurnoBot();
            }
        });

        thread.start();
    }

    private void OcultarBotones() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<botones.size();i++) {
                    botones.get(i).setDisable(true);
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ParallelTransition parallelTransition = new ParallelTransition();
                for (Button b: botones) {
                    FadeTransition ft = new FadeTransition(Duration.seconds(1), b);
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setAutoReverse(true);
                    parallelTransition.getChildren().add(ft);
                }
                parallelTransition.play();
            }


        });
        thread.start();
    }


    public void subirVolumen(ActionEvent actionEvent) {
        sonido.volumeUp();
    }

    public void bajarVolumen(ActionEvent actionEvent) {
        sonido.volumeDown();
    }

    public void mutear(ActionEvent actionEvent) {
        sonido.muteSound();
    }

}





