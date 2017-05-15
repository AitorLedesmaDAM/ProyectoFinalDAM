package com.proyectofinal.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.proyectofinal.game.TowerAttack;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.helpers.InputHandler;
import com.proyectofinal.game.objects.Nivel;
import com.proyectofinal.game.objects.road.Camino;
import com.proyectofinal.game.objects.towers.Torre_Fuego;
import com.proyectofinal.game.objects.trops.Caballero;
import com.proyectofinal.game.objects.trops.Ninja;
import com.proyectofinal.game.objects.trops.Robot;
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
    Torre_Fuego torreFuego;
    private float sizeDiv;

    ArrayList<Caballero> caballeros;
    ArrayList<Ninja> ninjas;
    ArrayList<Robot> robots;
    public ArrayList<Camino> camino;
    private long contador = 0;

    ArrayList<Torre_Fuego> torre_fuegos;

    public static boolean debug = false;
    private ShapeRenderer debugRenderer;

    public int maxCaballeros, maxNinjas, maxRobots;
    public int contadorCaballeros = 0, contadorNinjas = 0, contadorRobots = 0;
    private Container miniMapa, containerTropasMax, containerTextocontainerTropasMax, containerCaballero, containerCosteCaballero, containerCosteNinja, containerCosteRobot, containerNinja, containerRobot, containerBoton;

    public AtaqueScreen(TowerAttack game, int maxCaballeros, int maxNinjas, int maxRobots){
        this.game = game;
        Settings.pantalla = 3;
        this.maxCaballeros = maxCaballeros;
        this.maxNinjas = maxNinjas;
        this.maxRobots = maxRobots;

        debugRenderer = new ShapeRenderer();

        mapa = AssetManager.tiledMap;
        renderer = new OrthogonalTiledMapRenderer(mapa);

        camera = AssetManager.camera;
        camera.setToOrtho(false,3200,1600);
        renderer.setView(camera);
        camera.update();

        camino = new ArrayList<Camino>();
            MapObjects objects = AssetManager.tiledMap.getLayers().get("CaminoObjetos").getObjects();
            Camino camMitad, camDoble, camMitadMitad, caminoMitadMitadMitad, caminoMitadMitadDoble, caminoDobleMitad, caminoDobleDoble;
            for (int i = 0; i < objects.getCount(); i++) {
                RectangleMapObject rmo = (RectangleMapObject) objects.get(i);
                Rectangle rect = rmo.getRectangle();


                if (i > 0) {
                    camMitad = new Camino((camino.get(camino.size()-1).getX() + rect.getX()) / 2, (camino.get(camino.size()-1).getY() + rect.getY()) / 2);
                    camMitadMitad = new Camino((camino.get(camino.size() - 1).getX() + camMitad.getX()) / 2, (camino.get(camino.size() - 1).getY() + camMitad.getY()) / 2);
                    camDoble = new Camino((camMitad.getX() + rect.getX()) / 2, (camMitad.getY() + rect.getY()) / 2);

                    caminoMitadMitadMitad = new Camino((camino.get(camino.size() - 1).getX() + camMitadMitad.getX()) / 2, (camino.get(camino.size() - 1).getY() + camMitadMitad.getY()) / 2);
                    caminoMitadMitadDoble = new Camino((camMitad.getX() + camMitadMitad.getX()) / 2 , (camMitad.getY() + camMitadMitad.getY()) / 2);
                    caminoDobleMitad = new Camino((camMitad.getX() + camDoble.getX()) / 2, (camMitad.getY() + camDoble.getY()) / 2);
                    caminoDobleDoble = new Camino((camDoble.getX() + rect.getX()) / 2, (camDoble.getY() + rect.getY()) / 2);
                    camino.add(caminoMitadMitadMitad);
                    camino.add(camMitadMitad);
                    camino.add(caminoMitadMitadDoble);
                    camino.add(camMitad);
                    camino.add(caminoDobleMitad);
                    camino.add(camDoble);
                    camino.add(caminoDobleDoble);
                }
                camino.add(new Camino(rect.getX(), rect.getY()));
                //System.out.println(rect.getX() + " - " + rect.getY());
        }
        System.out.println(camino.size());

        Random random = new Random();
        caballeros = new ArrayList<Caballero>(maxCaballeros);
        for (int i = 0; i < maxCaballeros; i++){
            caballeros.add(new Caballero(camino.get(0).getX(),camino.get(0).getY(), random.nextInt(125) + (-62), random.nextInt(150)+(-75)));
        }
        ninjas = new ArrayList<Ninja>(maxNinjas);
        for (int i = 0; i < maxNinjas; i++){
            ninjas.add(new Ninja(camino.get(0).getX(),camino.get(0).getY()));
        }
        robots = new ArrayList<Robot>(maxRobots);
        for (int i = 0; i < maxRobots; i++){
            robots.add(new Robot(camino.get(0).getX(),camino.get(0).getY()));
        }



        System.out.println("nuumero de Caballeros: "+ maxCaballeros);
        System.out.println("nuumero de Ninjas: "+ maxNinjas);
        System.out.println("nuumero de Robots: "+ maxRobots);


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
        torre_fuegos = new ArrayList<Torre_Fuego>();
        MapObjects objectsT = mapa.getLayers().get("TorresObjetos").getObjects();

        for (int i = 0; i < objectsT.getCount(); i++) {
            RectangleMapObject rmo = (RectangleMapObject) objectsT.get(i);
            Rectangle rect = rmo.getRectangle();
            sizeDiv = (rect.getWidth()/2);
            int pos = i + 1;
            boolean x = mapa.getLayers().get("TorresObjetos").getObjects().get("torre"+pos).getProperties().containsKey("cara");
            System.out.println(x);
            torre_fuegos.add(new Torre_Fuego(rect.getX(), rect.getY(),x));
            System.out.println(torre_fuegos.get(i).getX()+" - "+torre_fuegos.get(i).getY());
        }

        //boolean x = mapa.getLayers().get("TorresObjetos").getProperties().containsKey("orientacion");
        //System.out.println(x);
        for (int i = 0; i < torre_fuegos.size(); i++) {
            System.out.println(torre_fuegos.get(i).getX()+" - "+torre_fuegos.get(i).getY());
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
                if (contador % 2 == 0) {

                    caballeros.get(i).siguienteCasilla(camino);
                }
            }
            if (ninjas != null) {
                for (int i = 0; i < ninjas.size(); i++) {
                    ninjas.get(i).setTiempoDeEstado(ninjas.get(i).getTiempoDeEstado() + delta);
                }
            }
            if (robots != null) {
                for (int i = 0; i < robots.size(); i++) {
                    robots.get(i).setTiempoDeEstado(robots.get(i).getTiempoDeEstado() + delta);
                }
            }
            stage.draw();
            stage.act(delta);

            if (debug) renderDebug();

            batch.begin();

            batch.end();

            for(int c = 0; c < caballeros.size(); c++) {
                for(int i = 0 ; i < torre_fuegos.size(); i++) {

                    boolean x = Intersector.overlaps(torre_fuegos.get(i).getCollisionCircle(), caballeros.get(c).getCollisionRect());
                    if (x){
                        System.out.println("Mas facil");
                    }

                }
            }

        }

    }


    private void renderDebug() {

        System.out.println("Estoy en pintar!");
        debugRenderer.setProjectionMatrix(camera.combined);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);

        debugRenderer.setColor(Color.RED);
        for (int i = 0; i < torre_fuegos.size(); i++){
            debugRenderer.circle(torre_fuegos.get(i).getX() + sizeDiv,torre_fuegos.get(i).getY() + sizeDiv, torre_fuegos.get(i).getRadius());
        }

        for (int i = 0; i < caballeros.size(); i++){
            debugRenderer.rect(caballeros.get(i).getX(), caballeros.get(i).getY(),caballeros.get(i).getWidth(),caballeros.get(i).getHeight());
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
        System.out.println("Disposed");
        batch.dispose();
        renderer.dispose();
    }

    public Stage getStage() {
        return stage;
    }

    public void soltarTropa(String tropa) {

        if(tropa.equals("Caballero2")){

            if(maxCaballeros > 0){
                //TODO SOLTAR CABALLERO
                System.out.println("Estoy en soltar caballero");
                caballeros.get(contadorCaballeros).casillaActual = 0;
                stage.addActor(caballeros.get(contadorCaballeros));
                contadorCaballeros++;
                maxCaballeros--;
            }

        }else if(tropa.equals("Ninja2")){

            if(maxNinjas > 0){
                System.out.println("Estoy en soltar Ninja");
                stage.addActor(ninjas.get(contadorNinjas));
                contadorNinjas++;
                maxNinjas--;
                //TODO SOLTAR NINJA

            }

        }else if(tropa.equals("Robot2")){

            if(maxRobots > 0){
                System.out.println("Estoy en soltar Robot");
                stage.addActor(robots.get(contadorRobots));
                contadorRobots++;
                maxRobots--;
                //TODO SOLTAR ROBOT

            }

        }


    }
}
