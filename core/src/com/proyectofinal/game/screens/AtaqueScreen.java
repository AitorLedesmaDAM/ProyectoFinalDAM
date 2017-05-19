package com.proyectofinal.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.proyectofinal.game.TowerAttack;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.helpers.InputHandler;
import com.proyectofinal.game.objects.AtacarTorre;
import com.proyectofinal.game.objects.Nivel;
import com.proyectofinal.game.objects.road.Camino;
import com.proyectofinal.game.objects.towers.Torre_Fuego;
import com.proyectofinal.game.objects.trops.Caballero;
import com.proyectofinal.game.objects.trops.Ninja;
import com.proyectofinal.game.objects.trops.Robot;
import com.proyectofinal.game.objects.trops.Tropas;
import com.proyectofinal.game.utils.Settings;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ALUMNEDAM on 25/04/2017.
 */

public class AtaqueScreen implements Screen {

    TiledMap mapa;
    OrthogonalTiledMapRenderer renderer;
    private Nivel nivel;
    OrthographicCamera camera;
    private TowerAttack game;
    Batch batch;
    Viewport viewport;
    private Stage stage;

    ArrayList<Caballero> caballeros;
    ArrayList<Ninja> ninjas;
    ArrayList<Robot> robots;
    public ArrayList<Camino> camino;
    ArrayList<Tropas> tropasColisionadas;
    Random random = new Random();

    private long contador = 0, torreContador = 0;

    ArrayList<Torre_Fuego> torre_fuegos;

    public static boolean debug = false;
    private ShapeRenderer debugRenderer;

    public int maxCaballeros, maxNinjas, maxRobots;
    public int contadorCaballeros = 0, contadorNinjas = 0, contadorRobots = 0;
    private Container containerCaballero, containerNinja, containerRobot;

    public AtaqueScreen(TowerAttack game, int maxCaballeros, int maxNinjas, int maxRobots){
        this.game = game;
        Settings.pantalla = 3;
        this.maxCaballeros = maxCaballeros;
        this.maxNinjas = maxNinjas;
        this.maxRobots = maxRobots;

        debugRenderer = new ShapeRenderer();

        mapa = AssetManager.tiledMap;
        renderer = AssetManager.renderer;

        camera = AssetManager.camera;
        camera.setToOrtho(false,3200,1600);
        renderer.setView(camera);
        camera.update();

        nivel = new Nivel();

        camino = nivel.recojerCamino();
        tropasColisionadas = new ArrayList<Tropas>();

        caballeros = new ArrayList<Caballero>(maxCaballeros);
        ninjas = new ArrayList<Ninja>(maxNinjas);
        robots = new ArrayList<Robot>(maxRobots);



        Image caballero = new Image(AssetManager.caballeroSelecAtak);   //Selección de caballero
        caballero.setName("Caballero");
        containerCaballero = new Container(caballero);
        containerCaballero.setTransform(true);
        containerCaballero.center();
        containerCaballero.setSize(Settings.TROPA_SELEC_WIDTH, Settings.TROPA_SELEC_HEIGHT);
        containerCaballero.setPosition(Settings.GAME_WIDTH / 3 - Settings.TROPA_SELEC_WIDTH*2, Settings.GAME_HEIGHT /10);

        Image ninja = new Image(AssetManager.ninjaSelecAtak);   //Selección del ninja
        ninja.setName("Ninja");
        containerNinja = new Container(ninja);
        containerNinja.setTransform(true);
        containerNinja.center();
        containerNinja.setSize(Settings.TROPA_SELEC_WIDTH, Settings.TROPA_SELEC_HEIGHT);
        containerNinja.setPosition(Settings.GAME_WIDTH / 3 - Settings.TROPA_SELEC_WIDTH*2, Settings.GAME_HEIGHT / 10+ Settings.TROPA_SELEC_HEIGHT+20);

        Image robot = new Image(AssetManager.robotSelecAtak);   //Selección del robot
        robot.setName("Robot");
        containerRobot = new Container(robot);
        containerRobot.setTransform(true);
        containerRobot.center();
        containerRobot.setSize(Settings.TROPA_SELEC_WIDTH, Settings.TROPA_SELEC_HEIGHT);
        containerRobot.setPosition(Settings.GAME_WIDTH / 3 - Settings.TROPA_SELEC_WIDTH*2, Settings.GAME_HEIGHT / 10+ Settings.TROPA_SELEC_HEIGHT *2 +40);


        // Creem el viewport amb les mateixes dimensions que la càmera
        viewport = new StretchViewport(3200, 1600, camera);


        // Creem l'stage i assginem el viewport
        stage = new Stage(viewport);

        batch = stage.getBatch();


        if (maxCaballeros > 0) {
            stage.addActor(containerCaballero);
        }
        if (maxNinjas > 0){
            stage.addActor(containerNinja);
        }
        if (maxRobots > 0){
            stage.addActor(containerRobot);
        }


        //bucle para saber las posiciones de los objetos torre
        torre_fuegos = nivel.recojerTorres();

        for (int i = 0; i < torre_fuegos.size(); i++) {
            stage.addActor(torre_fuegos.get(i));
        }

        Gdx.input.setInputProcessor(new InputHandler(this));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        contador++;

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        renderer.setView(camera);
        renderer.render();


        if (caballeros != null) {
            for (int i = 0; i < caballeros.size(); i++) {
                caballeros.get(i).setTiempoDeEstado(caballeros.get(i).getTiempoDeEstado() + delta);
                if (caballeros.get(i).getEstado() == Tropas.Estado.Caminando) {
                    if (contador % 3 == 0) {
                        caballeros.get(i).siguienteCasilla(camino);
                    }
                }
            }
        }
        if (ninjas != null) {
            for (int i = 0; i < ninjas.size(); i++) {
                ninjas.get(i).setTiempoDeEstado(ninjas.get(i).getTiempoDeEstado() + delta);
                if (contador % 2 == 0) {
                    ninjas.get(i).siguienteCasilla(camino);
                }

            }
        }
        if (robots != null) {
            for (int i = 0; i < robots.size(); i++) {
                robots.get(i).setTiempoDeEstado(robots.get(i).getTiempoDeEstado() + delta);
                if (contador % 3 == 0) {
                    robots.get(i).siguienteCasilla(camino);
                }
            }
        }


        for (int c = 0; c < caballeros.size(); c++) {
            for (int i = 0; i < torre_fuegos.size(); i++) {

                if (Intersector.overlaps(torre_fuegos.get(i).getCollisionCircle(), caballeros.get(c).getCollisionRect())) {

                    torreContador ++;
                    if (torreContador == 300){
                        torre_fuegos.get(i).getCollisionCircle().set(new Circle(10,10,5));
                        torre_fuegos.get(i).remove();
                        torreContador = 0;
                    }
                    if(contador % 60 == 0){
                        caballeros.get(c).setVida(caballeros.get(c).getVida()-1);
                        if (caballeros.get(c).getVida() <= 0){
                            caballeros.get(c).remove();
                        }
                    }
                    caballeros.get(c).setEstado(Tropas.Estado.Atacando);
                    AtacarTorre at = new AtacarTorre(caballeros.get(c), torre_fuegos.get(i));
                    ArrayList<Camino> camino = at.caminarHaciaTorre();
                    if (contador % 3 == 0) {
                        if (!caballeros.get(c).siguienteCasillaAtaque(camino) && !caballeros.get(c).llegarATorre(caballeros.get(c).getY(), torre_fuegos.get(i).getPosicionAtaque().y, torre_fuegos.get(i).isOrientacion())) {
                                caballeros.get(c).setanimacionCaminar(false);
                        }
                    }
                }
            }
        }

        for (int c = 0; c < robots.size(); c++) {
            for (int i = 0; i < torre_fuegos.size(); i++) {

                boolean x = Intersector.overlaps(torre_fuegos.get(i).getCollisionCircle(), robots.get(c).getCollisionRect());
                if (x) {
                    robots.get(c).setEstado(Tropas.Estado.Atacando);
                    AtacarTorre at = new AtacarTorre(robots.get(c), torre_fuegos.get(1));
                    if (contador % 3 == 0) {


                        Robot.torreX = torre_fuegos.get(1).getPosicionAtaque().x;
                        Robot .torreY = torre_fuegos.get(1).getPosicionAtaque().y;
                        Robot.attack = true;


                    }

                }
            }

        }
        stage.draw();
        stage.act(delta);

        if (debug) renderDebug();

        batch.begin();

        batch.end();

    }


    private void renderDebug() {

        debugRenderer.setProjectionMatrix(camera.combined);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);

        debugRenderer.setColor(Color.RED);
        for (int i = 0; i < torre_fuegos.size(); i++){
            debugRenderer.circle(torre_fuegos.get(i).getCollisionCircle().x,torre_fuegos.get(i).getCollisionCircle().y, torre_fuegos.get(i).getCollisionCircle().radius);
        }
        debugRenderer.setColor(Color.GREEN);
        for (int i = 0; i < caballeros.size(); i++){
            debugRenderer.rect(caballeros.get(i).getCollisionRect().x, caballeros.get(i).getCollisionRect().y, caballeros.get(i).getCollisionRect().width,caballeros.get(i).getCollisionRect().height);
        }
        debugRenderer.setColor(Color.PURPLE);
        for (int i = 0; i < ninjas.size(); i++){
            debugRenderer.rect(ninjas.get(i).getCollisionRect().x, ninjas.get(i).getCollisionRect().y, ninjas.get(i).getCollisionRect().width, ninjas.get(i).getCollisionRect().height);
        }
        debugRenderer.setColor(Color.BLUE);
        for (int i = 0; i < robots.size(); i++){
            debugRenderer.rect(robots.get(i).getCollisionRect().x, robots.get(i).getCollisionRect().y,robots.get(i).getCollisionRect().width,robots.get(i).getCollisionRect().height);
        }
        debugRenderer.setColor(Color.GOLD);
        for (int i = 0; i < torre_fuegos.size(); i++){
            debugRenderer.rect(torre_fuegos.get(i).getPosicionAtaque().x,torre_fuegos.get(i).getPosicionAtaque().y, 50, 50);
        }
        debugRenderer.end();

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
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        renderer.dispose();
    }

    public Stage getStage() {
        return stage;
    }

    public void soltarTropa(String tropa) {

        if(tropa.equals("Caballero")){
            if(maxCaballeros > 0){
                caballeros.add(new Caballero(camino.get(0).getX(),camino.get(0).getY(), random.nextInt(125) + (-87), random.nextInt(150)+(-100)));
                stage.addActor(caballeros.get(contadorCaballeros));
                contadorCaballeros++;
                maxCaballeros--;
            }
        }else if(tropa.equals("Ninja")){
            if(maxNinjas > 0){
                ninjas.add(new Ninja(camino.get(0).getX(),camino.get(0).getY(), random.nextInt(125) + (-87), random.nextInt(150)+(-100)));
                stage.addActor(ninjas.get(contadorNinjas));
                contadorNinjas++;
                maxNinjas--;
            }
        }else if(tropa.equals("Robot")){
            if(maxRobots > 0){
                robots.add(new Robot(camino.get(0).getX(),camino.get(0).getY(), random.nextInt(125) + (-115), random.nextInt(150)+(-90)));
                stage.addActor(robots.get(contadorRobots));
                contadorRobots++;
                maxRobots--;
            }
        }


    }
}
