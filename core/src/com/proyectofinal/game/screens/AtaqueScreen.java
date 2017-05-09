package com.proyectofinal.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.proyectofinal.game.TowerAttack;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.helpers.InputHandler;
import com.proyectofinal.game.utils.Settings;

/**
 * Created by ALUMNEDAM on 25/04/2017.
 */

public class AtaqueScreen implements Screen {

    TiledMap mapa;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    private TowerAttack game;
    SeleccionScreen segunda;
    SpriteBatch batch;
    Viewport viewport;
    private Stage stage;

    private Container miniMapa, containerTropasMax, containerTextocontainerTropasMax, containerCaballero, containerCosteCaballero, containerCosteNinja, containerCosteRobot, containerNinja, containerRobot, containerBoton;

    public AtaqueScreen(TowerAttack game, int numCaballero, int numNinja, int numRobot, Batch batch, Viewport viewport){
        this.game = game;
        Settings.pantalla = 3;

        mapa = AssetManager.tiledMap;
        renderer = new OrthogonalTiledMapRenderer(mapa);

        camera = AssetManager.camera;
        camera.setToOrtho(false,3200,1600);
        renderer.setView(camera);
        camera.update();

        this.batch = new SpriteBatch();

        System.out.println("nuumero de Caballeros: "+ numCaballero);
        System.out.println("nuumero de Ninjas: "+ numNinja);
        System.out.println("nuumero de Robots: "+ numRobot);


        Image caballero = new Image(AssetManager.caballeroSelecAtak);   //Selección de caballero
        caballero.setName("Caballero2");
        containerCaballero = new Container(caballero);
        containerCaballero.setTransform(true);
        containerCaballero.center();
        containerCaballero.setSize(Settings.TROPA_SELEC_WIDTH, Settings.TROPA_SELEC_HEIGHT);
        containerCaballero.setPosition(Settings.GAME_WIDTH / 3 - Settings.TROPA_SELEC_WIDTH*2, Settings.GAME_HEIGHT /10);

        Image ninja = new Image(AssetManager.ninjaSelecAtak);   //Selección del ninja
        ninja.setName("Ninja2");
        containerNinja = new Container(ninja);
        containerNinja.setTransform(true);
        containerNinja.center();
        containerNinja.setSize(Settings.TROPA_SELEC_WIDTH, Settings.TROPA_SELEC_HEIGHT);
        containerNinja.setPosition(Settings.GAME_WIDTH / 3 - Settings.TROPA_SELEC_WIDTH*2, Settings.GAME_HEIGHT / 10+ Settings.TROPA_SELEC_HEIGHT+20);

        Image robot = new Image(AssetManager.robotSelecAtak);   //Selección del robot
        robot.setName("Robot2");
        containerRobot = new Container(robot);
        containerRobot.setTransform(true);
        containerRobot.center();
        containerRobot.setSize(Settings.TROPA_SELEC_WIDTH, Settings.TROPA_SELEC_HEIGHT);
        containerRobot.setPosition(Settings.GAME_WIDTH / 3 - Settings.TROPA_SELEC_WIDTH*2, Settings.GAME_HEIGHT / 10+ Settings.TROPA_SELEC_HEIGHT *2 +40);


        // Creem el viewport amb les mateixes dimensions que la càmera
        viewport = new StretchViewport(3200, 1600, camera);


        // Creem l'stage i assginem el viewport
        stage = new Stage(viewport, batch);


        stage.addActor(containerCaballero);
        stage.addActor(containerNinja);
        stage.addActor(containerRobot);


        Gdx.input.setInputProcessor(new InputHandler(this));
        
        
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        renderer.setView(camera);
        renderer.render();

        stage.draw();
        stage.act(delta);

        batch.begin();

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        //viewport.update(width, height);
        //camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        System.out.println("Disposed");
        batch.dispose();
        renderer.dispose();
    }

    public Stage getStage() {
        return stage;
    }
}
