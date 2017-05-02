package com.proyectofinal.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.proyectofinal.game.TowerAttack;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.helpers.InputHandler;
import com.proyectofinal.game.utils.Settings;

public class SeleccionScreen implements Screen{

    private Stage stage;
    private TowerAttack game;
    OrthographicCamera camera;
    Viewport viewport;

    private static Label.LabelStyle textStyle, textStylePequenio;

    private Label contador, textoTropas;
    private int maxTropasContador;

    private Container miniMapa, containerTropasMax, containerTextocontainerTropasMax, containerCaballero, containerNinja, containerRobot;

    public SeleccionScreen(Batch batch, Viewport _viewport, String lvl, int _maxTropasContador) {
        Settings.pantalla = 2;

        camera = AssetManager.camera;

        miniMapa = new Container(new Image(AssetManager.mapa1));
        miniMapa.setTransform(true);
        miniMapa.center();
        miniMapa.setSize(Settings.MINIMAPA_WIDTH, Settings.MINIMAPA_HEIGHT); //HACER CONSTANTES DE LOS TAMAÑOS EN SETTINGS
        miniMapa.setPosition(Settings.GAME_WIDTH - Settings.MINIMAPA_WIDTH, 0);

        textStyle = AssetManager.textStyle;
        textStylePequenio = AssetManager.textStylePequenio;

        maxTropasContador = _maxTropasContador;
        contador = new Label("" + maxTropasContador, textStyle);
        containerTropasMax = new Container(contador);
        containerTropasMax.setTransform(true);
        containerTropasMax.center();
        containerTropasMax.setPosition(350, 175);

        textoTropas = new Label("Tropas máximas restantes: ", textStylePequenio);
        containerTextocontainerTropasMax = new Container(textoTropas);
        containerTextocontainerTropasMax.center();
        containerTextocontainerTropasMax.setPosition(400,50);

        containerCaballero = new Container(new Image(AssetManager.caballeroSelec));
        containerCaballero.setTransform(true);
        containerCaballero.center();
        containerCaballero.setSize(Settings.TROPA_SELEC_WIDTH, Settings.TROPA_SELEC_HEIGHT);
        containerCaballero.setPosition(Settings.GAME_WIDTH / 3 - Settings.TROPA_SELEC_WIDTH*2, Settings.GAME_HEIGHT / 2);

        containerNinja = new Container(new Image(AssetManager.ninjaSelec));
        containerNinja.setTransform(true);
        containerNinja.center();
        containerNinja.setSize(Settings.TROPA_SELEC_WIDTH, Settings.TROPA_SELEC_HEIGHT);
        containerNinja.setPosition(Settings.GAME_WIDTH / 3 - (Settings.TROPA_SELEC_WIDTH/2 + Settings.TROPA_SELEC_WIDTH/3), Settings.GAME_HEIGHT / 2);

        containerRobot = new Container(new Image(AssetManager.robotSelec));
        containerRobot.setTransform(true);
        containerRobot.center();
        containerRobot.setSize(Settings.TROPA_SELEC_WIDTH, Settings.TROPA_SELEC_HEIGHT);
        containerRobot.setPosition(Settings.GAME_WIDTH / 3 + Settings.TROPA_SELEC_WIDTH/3, Settings.GAME_HEIGHT / 2);

        // Creem el viewport amb les mateixes dimensions que la càmera
        //StretchViewport viewport;
        viewport = _viewport;


        // Creem l'stage i assginem el viewport
        stage = new Stage(viewport, batch);

        // Afegim el fons
        stage.addActor(new Image(AssetManager.background));
        stage.addActor(miniMapa);
        stage.addActor(containerTropasMax);
        stage.addActor(containerTextocontainerTropasMax);
        stage.addActor(containerCaballero);
        stage.addActor(containerNinja);
        stage.addActor(containerRobot);

        Gdx.input.setInputProcessor(new InputHandler(this));
    }

    public void modMaxTropas(){
        maxTropasContador--;
        if (maxTropasContador > -1) {
            contador.setText("" + maxTropasContador);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
