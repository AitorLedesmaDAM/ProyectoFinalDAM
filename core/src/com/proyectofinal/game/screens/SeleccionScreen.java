package com.proyectofinal.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
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
    private Container miniMapa, tropasMax;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;

    private static Label.LabelStyle textStyle;

    private Label contador;
    int cont = 50;

    public SeleccionScreen(Batch batch, Viewport viewport) {
        Settings.pantalla = 2;

        OrthographicCamera camera = AssetManager.camera;

        miniMapa = new Container(new Image(AssetManager.mapa1));
        miniMapa.setTransform(true);
        miniMapa.center();
        miniMapa.setSize(Settings.MINIMAPA_WIDTH, Settings.MINIMAPA_HEIGHT); //HACER CONSTANTES DE LOS TAMAÑOS EN SETTINGS
        miniMapa.setPosition(Settings.GAME_WIDTH - Settings.MINIMAPA_WIDTH, 0);

        textStyle = AssetManager.textStyle;

        contador = new Label("" + cont, textStyle);
        tropasMax = new Container(contador);

        tropasMax.setTransform(true);
        tropasMax.center();
        tropasMax.setPosition((Settings.GAME_WIDTH / 5),Settings.GAME_HEIGHT/4);

        tiledMap = AssetManager.tiledMap;
        renderer = AssetManager.renderer;

        // Creem el viewport amb les mateixes dimensions que la càmera
        //StretchViewport viewport;
        viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera);

        // Creem l'stage i assginem el viewport
        stage = new Stage(viewport, batch);

        // Afegim el fons
        stage.addActor(new Image(AssetManager.background));

        stage.addActor(miniMapa);
        stage.addActor(tropasMax);

        Gdx.input.setInputProcessor(new InputHandler(this));
    }

    public void modMaxTropas(){
        if (cont >= 0) {
            contador.setText("" + cont--);
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
}
