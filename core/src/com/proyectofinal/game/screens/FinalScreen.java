package com.proyectofinal.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.helpers.InputHandler;
import com.proyectofinal.game.utils.Settings;

/**
 * Created by ALUMNEDAM on 12/05/2017.
 */

public class FinalScreen implements Screen {

    //Atributos
    StretchViewport viewport;
    OrthographicCamera camera;
    private Stage stage;

    private static Label.LabelStyle textStyleTitulo;
    private Label  titulo;
    private Container contenedorTitulo;
    private Container containerBtSalir, containerBtSig, containerBtReniciar;



    //Constructor

    public FinalScreen(Boolean ganado) {


        Settings.pantalla = 4;

        camera = AssetManager.camera;

        // Creem el viewport amb les mateixes dimensions que la càmera
        viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera);

        // Creem l'stage i assginem el viewport
        stage = new Stage(viewport);

        // Afegim el fons
        stage.addActor(new Image(AssetManager.background));

        //textStyle = AssetManager.textStyle;
        textStyleTitulo = AssetManager.textStyleTitulo;

        String msg; //Mensaje si ha ganado o perdido.
        if (ganado){
            msg= "Has Ganado ";
        }else {
            msg = "Has Perdido!!! ";
        }

        titulo = new Label(msg, textStyleTitulo);

        contenedorTitulo = new Container(titulo);


        contenedorTitulo.setTransform(true);
        contenedorTitulo.center();
        contenedorTitulo.setPosition(Settings.GAME_WIDTH / 2 ,Settings.GAME_HEIGHT / 6 + 100);

        stage.addActor(contenedorTitulo); //se anade el container de titulo

        //Botones

        Image sig = new Image(AssetManager.btnSig); //Seleccion del botón de siguiente
        sig.setName("Siguente");
        containerBtSig = new Container(sig);
        containerBtSig.setTransform(false);
        containerBtSig.center();
        containerBtSig.setSize(400, 200);
       // containerBtSig.setPosition(Settings.GAME_WIDTH / 3 - 490 , Settings.GAME_HEIGHT / 2 + Settings.GAME_HEIGHT / 4);

        containerBtSig.setPosition(Settings.GAME_WIDTH / 2 + 300 , Settings.GAME_HEIGHT / 2 + Settings.GAME_HEIGHT / 4 - 60);


        stage.addActor(containerBtSig);// se anade

        Image reniciar = new Image(AssetManager.btnReiniciar); //Seleccion del botón de Reiniciar
        reniciar.setName("Reniciar");
        containerBtReniciar = new Container(reniciar);
        containerBtReniciar.setTransform(false);
        containerBtReniciar.center();
        containerBtReniciar.setSize(400, 200);
        containerBtReniciar.setPosition(Settings.GAME_WIDTH / 2 - 200 , Settings.GAME_HEIGHT / 2 + Settings.GAME_HEIGHT / 4 - 60);


        stage.addActor(containerBtReniciar); //se anade


        Image salir = new Image(AssetManager.btnSalir); //Seleccion del botón de salir
        salir.setName("Salir");
        containerBtSalir = new Container(salir);
        containerBtSalir.setTransform(false);
        containerBtSalir.center();
        containerBtSalir.setSize(400, 200);
        //containerBtSalir.setPosition(Settings.GAME_WIDTH - 450, Settings.GAME_HEIGHT / 2 + Settings.GAME_HEIGHT / 4);
        containerBtSalir.setPosition(Settings.GAME_WIDTH / 2 - 700 , Settings.GAME_HEIGHT / 2 + Settings.GAME_HEIGHT / 4 - 60);


        stage.addActor(containerBtSalir); // se anade


        //Gdx.input.setInputProcessor(new InputHandler(this));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stage.draw();
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width, height);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
    public Stage getStage() {
        return stage;
    }
}
