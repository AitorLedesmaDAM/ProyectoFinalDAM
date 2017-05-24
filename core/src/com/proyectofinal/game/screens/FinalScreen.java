package com.proyectofinal.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.proyectofinal.game.helpers.AssetManager;
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


    /**
    * Constructor
    * Le pasamos un boolean para definir el mensaje y los botones
    */

    public FinalScreen(Boolean ganado) {


        Settings.pantalla = 4;

        camera = AssetManager.camera;

        // Creamos el Viewport con las mismas dimensiones que la Camara
        viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera);

        // Creamos el Stage y le asignamos el viewport
        stage = new Stage(viewport);

        // Añadimos el Fondo
        stage.addActor(new Image(AssetManager.background));

        /// Ponemos los estilos de texto a los LabelStyle
        textStyleTitulo = AssetManager.textStyleTitulo;

        // Mostramos un mensaje diferente si ha ganado o perdido.
        String msg; 
        if (ganado){
            msg= "Has Ganado ";
        }else {
            msg = "Has Perdido!!! ";
        }

        // Ponemos el mensaje en un Label y lo añadimos a un Contenedor
        titulo = new Label(msg, textStyleTitulo);
        contenedorTitulo = new Container(titulo);

        // Definimos la posicion del contenedor
        contenedorTitulo.setTransform(true);
        contenedorTitulo.center();
        contenedorTitulo.setPosition(Settings.GAME_WIDTH / 2 ,Settings.GAME_HEIGHT / 6 + 100);

        // Añadimos el ContenedorTitulo como Actor al Stage
        stage.addActor(contenedorTitulo);

        // Definimos los botones que segun el boolean que se recibe por parametros
        if (ganado) {
            // Añadimos el boton siguente a un Contenedor
            Image sig = new Image(AssetManager.btnSig); 
            sig.setName("Siguente");
            containerBtSig = new Container(sig);
            containerBtSig.setTransform(false);
            containerBtSig.center();
            containerBtSig.setSize(400, 200);
            
            // Definimos su posicion
            containerBtSig.setPosition(Settings.GAME_WIDTH / 2 + 300, Settings.GAME_HEIGHT / 2 + Settings.GAME_HEIGHT / 4 - 60);
            
            // Añadimos el ContainerBtSig como Actor al Stage
            stage.addActor(containerBtSig);
        }

         // Añadimos el boton Reiniciar a un Contenedor
        Image reniciar = new Image(AssetManager.btnReiniciar); 
        reniciar.setName("Reniciar");
        containerBtReniciar = new Container(reniciar);
        containerBtReniciar.setTransform(false);
        containerBtReniciar.center();
        containerBtReniciar.setSize(400, 200);
        
        // Definimos su posicion
        containerBtReniciar.setPosition(Settings.GAME_WIDTH / 2 - 200 , Settings.GAME_HEIGHT / 2 + Settings.GAME_HEIGHT / 4 - 60);

        // Añadimos el ContainerBtReiniciar como Actor al Stage
        stage.addActor(containerBtReniciar); 

        
        // Añadimos el boton Salir a un Contenedor
        Image salir = new Image(AssetManager.btnSalir);
        salir.setName("Salir");
        containerBtSalir = new Container(salir);
        containerBtSalir.setTransform(false);
        containerBtSalir.center();
        containerBtSalir.setSize(400, 200);
        //containerBtSalir.setPosition(Settings.GAME_WIDTH - 450, Settings.GAME_HEIGHT / 2 + Settings.GAME_HEIGHT / 4);
        
        // Definimos su posicion
        containerBtSalir.setPosition(Settings.GAME_WIDTH / 2 - 700 , Settings.GAME_HEIGHT / 2 + Settings.GAME_HEIGHT / 4 - 60);

        // Añadimos el ContainerBtSalir como Actor al Stage
        stage.addActor(containerBtSalir);


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
