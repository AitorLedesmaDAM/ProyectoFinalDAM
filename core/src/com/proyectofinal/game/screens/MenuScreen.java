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
import com.proyectofinal.game.utils.Settings;

public class MenuScreen implements Screen {

    private TowerAttack game;
    private Stage stage;

    private static Label.LabelStyle textStyle, textStyleTitulo;
    private Label textLbl, textLbl2, textLbl3, textLbl4, textLbl5, textLbl6, textLbl7, textLbl8, titulo;
    private Container container1, container2, container3,container4, container5, container6, container7, container8, contenedorTitulo;

    public MenuScreen(TowerAttack game) {
        this.game = game;
        Settings.pantalla = 1;

        OrthographicCamera camera = AssetManager.camera;

        // Creem el viewport amb les mateixes dimensions que la càmera
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera);

        // Creem l'stage i assginem el viewport
        stage = new Stage(viewport);

        // Afegim el fons
        stage.addActor(new Image(AssetManager.background));

        textStyle = AssetManager.textStyle;
        textStyleTitulo = AssetManager.textStyleTitulo;

        textLbl = new Label("1", textStyle);
        textLbl2 = new Label("2", textStyle);
        textLbl3 = new Label("3", textStyle);
        textLbl4 = new Label("4", textStyle);
        textLbl5 = new Label("5", textStyle);
        textLbl6 = new Label("6", textStyle);
        textLbl7 = new Label("7", textStyle);
        textLbl8 = new Label("8", textStyle);
        titulo = new Label("TowerAttack", textStyleTitulo);

        // Creem el contenidor necessari per aplicar-li les accions
        container1 = new Container(textLbl);
        container2 = new Container(textLbl2);
        container3 = new Container(textLbl3);
        container4 = new Container(textLbl4);
        container5 = new Container(textLbl5);
        container6 = new Container(textLbl6);
        container7 = new Container(textLbl7);
        container8 = new Container(textLbl8);
        contenedorTitulo = new Container(titulo);

        container1.setTransform(true);
        container1.center();
        container1.setPosition((Settings.GAME_WIDTH / 5),Settings.GAME_HEIGHT/2);

        container2.setTransform(true);
        container2.center();
        container2.setPosition((Settings.GAME_WIDTH / 5)*2,Settings.GAME_HEIGHT/2);

        container3.setTransform(true);
        container3.center();
        container3.setPosition((Settings.GAME_WIDTH / 5)*3,Settings.GAME_HEIGHT/2);

        container4.setTransform(true);
        container4.center();
        container4.setPosition((Settings.GAME_WIDTH / 5)*4,Settings.GAME_HEIGHT/2);

        container5.setTransform(true);
        container5.center();
        container5.setPosition((Settings.GAME_WIDTH / 5),Settings.GAME_HEIGHT/2 + Settings.GAME_HEIGHT/4);

        container6.setTransform(true);
        container6.center();
        container6.setPosition((Settings.GAME_WIDTH / 5)*2,Settings.GAME_HEIGHT/2 +Settings.GAME_HEIGHT/4);

        container7.setTransform(true);
        container7.center();
        container7.setPosition((Settings.GAME_WIDTH / 5)*3,Settings.GAME_HEIGHT/2 + Settings.GAME_HEIGHT/4);

        container8.setTransform(true);
        container8.center();
        container8.setPosition((Settings.GAME_WIDTH / 5)*4,Settings.GAME_HEIGHT/2 + Settings.GAME_HEIGHT/4);

        contenedorTitulo.setTransform(true);
        contenedorTitulo.center();
        contenedorTitulo.setPosition(Settings.GAME_WIDTH / 2,Settings.GAME_HEIGHT/6);

        stage.addActor(contenedorTitulo);
        stage.addActor(container1);
        stage.addActor(container2);
        stage.addActor(container3);
        stage.addActor(container4);
        stage.addActor(container5);
        stage.addActor(container6);
        stage.addActor(container7);
        stage.addActor(container8);

        Gdx.input.setInputProcessor(new InputHandler(this));
    }

    @Override
    public void show() {

    }

    public void siguientePantalla(String lvl, int maxTropas){
        game.setScreen(new SeleccionScreen(stage.getBatch(), stage.getViewport(), lvl, maxTropas));
        dispose();
    }

    @Override
    public void render(float delta) {
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

    public Stage getStage() {
        return stage;
    }
}
