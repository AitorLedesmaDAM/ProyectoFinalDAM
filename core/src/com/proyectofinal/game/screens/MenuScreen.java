package com.proyectofinal.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.proyectofinal.game.TowerAttack;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.helpers.InputHandler;
import com.proyectofinal.game.utils.Musica;
import com.proyectofinal.game.utils.Preferences;
import com.proyectofinal.game.utils.Settings;

public class MenuScreen implements Screen {

    // Atributos
    private TowerAttack game;
    private Preferences pref;
    private int lvl;

    private StretchViewport viewport;
    private OrthographicCamera camera;
    private Stage stage;

    private static Label.LabelStyle textStyle, textStyleTitulo;
    private Label textLbl1, textLbl2, textLbl3, textLbl4, textLbl5, textLbl6, textLbl7, textLbl8, titulo;
    private Container container1, container2, container3,container4, container5, container6, container7, container8, contenedorTitulo;

    private Musica m = new Musica();

    /**
    * Constructor
    * Le pasamos el game por parametros
    */
    public MenuScreen(TowerAttack game) {
        this.game = game;
        Settings.pantalla = 1;

        pref = new Preferences();
        lvl = pref.obtenerPreference();
        System.out.println(lvl);

        camera = AssetManager.camera;

        // Creamos el Viewport con las mismas dimensiones que la cCamara
        viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera);

        // Creamos el Stage y le asignamos el Viewport
        stage = new Stage(viewport);

        // Añadimos el Fondo
        stage.addActor(new Image(AssetManager.background));

        // Ponemos los estilos de texto a los LabelStyle
        textStyle = AssetManager.textStyle;
        textStyleTitulo = AssetManager.textStyleTitulo;

        canviarMusica();

        // Añadimos los Labels con el texto que queremos y el estilo de letra
        textLbl1 = new Label("1", textStyle);
        textLbl2 = new Label("2", textStyle);
        textLbl3 = new Label("3", textStyle);
        textLbl4 = new Label("4", textStyle);
        textLbl5 = new Label("5", textStyle);
        textLbl6 = new Label("6", textStyle);
        textLbl7 = new Label("7", textStyle);
        textLbl8 = new Label("8", textStyle);

        titulo = new Label("TowerAttack", textStyleTitulo);

        contenedorTitulo = new Container(titulo);


        contenedorTitulo.setTransform(true);
        contenedorTitulo.center();
        contenedorTitulo.setPosition(Settings.GAME_WIDTH / 2,Settings.GAME_HEIGHT/6);

        // Creamos el Contenedor necesario para aplicarle las acciones
            container1 = new Container(textLbl1);

            container1.setTransform(true);
            container1.center();
            container1.setPosition((Settings.GAME_WIDTH / 5),Settings.GAME_HEIGHT/2);

            stage.addActor(container1);

        if (lvl > 0){
            container2 = new Container(textLbl2);

            container2.setTransform(true);
            container2.center();
            container2.setPosition((Settings.GAME_WIDTH / 5)*2,Settings.GAME_HEIGHT/2);

            stage.addActor(container2);
        }
        if (lvl > 1){
            container3 = new Container(textLbl3);

            container3.setTransform(true);
            container3.center();
            container3.setPosition((Settings.GAME_WIDTH / 5)*3,Settings.GAME_HEIGHT/2);

            stage.addActor(container3);
        }

        // Añadimos los Contenedores como Actores al Stage
        stage.addActor(contenedorTitulo);

        // Hacemos que los Contenedores se puedan pulsar
        Gdx.input.setInputProcessor(new InputHandler(this));
    }

    public void canviarMusica(){
        m.iconoMusica(stage);
    }

    @Override
    public void show() {

    }
    
/**
* Metodo para cambiar de pantalla al hacer click
* pasandole por parametros el nivel que se a pulsado y el maximo de tropas
* que se permiten en ese nivel
*/
    public void siguientePantalla(int lvl, int maxTropas){
        game.setScreen(new SeleccionScreen(game, stage.getViewport(), lvl, maxTropas));
    }

    
    @Override
    public void render(float delta) {
        stage.draw();
        stage.act(delta);
    }
    
    /**
    * Metodo para redimensionar el viewport y la camara
    */
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
