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
import com.proyectofinal.game.objects.towers.Torres;
import com.proyectofinal.game.objects.towers.attack.Fuego;
import com.proyectofinal.game.objects.trops.Tropas;
import com.proyectofinal.game.utils.Settings;

import java.util.ArrayList;

/**
 * Created by ALUMNEDAM on 25/04/2017.
 */

public class AtaqueScreen implements Screen {

    Tropas tropas;
    TiledMap mapa;
    Torre_Fuego tf;

    //Rayo rayo;
    OrthogonalTiledMapRenderer renderer;
    private Nivel nivel;
    OrthographicCamera camera;
    private TowerAttack game;
    Batch batch;
    Viewport viewport;
    private Stage stage;
    private Fuego fuego;
    public boolean Final = false;
    boolean ganado = true;

    private ArrayList < Tropas > tropasEnMapa;
    public ArrayList < Camino > camino;
    ArrayList < Tropas > tropasColisionadas;

    private long contador = 0;

    ArrayList < Torres > torres;

    public static boolean debug = false;
    private ShapeRenderer debugRenderer;

    public int maxCaballeros, maxNinjas, maxRobots;
    public int contadorTropasMuertas = 0;
    private boolean compruebaAtaqueTorre = true;
    private Container containerCaballero, containerNinja, containerRobot;

    public AtaqueScreen(TowerAttack game, int maxCaballeros, int maxNinjas, int maxRobots) {
        this.game = game;
        Settings.pantalla = 3;
        this.maxCaballeros = maxCaballeros;
        this.maxNinjas = maxNinjas;
        this.maxRobots = maxRobots;


        debugRenderer = new ShapeRenderer();

        tropas = new Tropas();

        mapa = AssetManager.tiledMap;
        renderer = AssetManager.renderer;

        camera = AssetManager.camera;
        camera.setToOrtho(false, 3200, 1600);
        renderer.setView(camera);
        camera.update();

        //rayo = new Rayo();

        nivel = new Nivel();

        tropasEnMapa = new ArrayList < Tropas > ();

        camino = nivel.recojerCamino();
        tropasColisionadas = new ArrayList < Tropas > ();

        Image caballero = new Image(AssetManager.caballeroSelecAtak); //Selección de caballero
        caballero.setName("Caballero");
        containerCaballero = new Container(caballero);
        containerCaballero.setTransform(true);
        containerCaballero.center();
        containerCaballero.setSize(Settings.TROPA_SELEC_WIDTH, Settings.TROPA_SELEC_HEIGHT);
        containerCaballero.setPosition(Settings.GAME_WIDTH / 3 - Settings.TROPA_SELEC_WIDTH * 2, Settings.GAME_HEIGHT / 10);

        Image ninja = new Image(AssetManager.ninjaSelecAtak); //Selección del ninja
        ninja.setName("Ninja");
        containerNinja = new Container(ninja);
        containerNinja.setTransform(true);
        containerNinja.center();
        containerNinja.setSize(Settings.TROPA_SELEC_WIDTH, Settings.TROPA_SELEC_HEIGHT);
        containerNinja.setPosition(Settings.GAME_WIDTH / 3 - Settings.TROPA_SELEC_WIDTH * 2, Settings.GAME_HEIGHT / 10 + Settings.TROPA_SELEC_HEIGHT + 20);

        Image robot = new Image(AssetManager.robotSelecAtak); //Selección del robot
        robot.setName("Robot");
        containerRobot = new Container(robot);
        containerRobot.setTransform(true);
        containerRobot.center();
        containerRobot.setSize(Settings.TROPA_SELEC_WIDTH, Settings.TROPA_SELEC_HEIGHT);
        containerRobot.setPosition(Settings.GAME_WIDTH / 3 - Settings.TROPA_SELEC_WIDTH * 2, Settings.GAME_HEIGHT / 10 + Settings.TROPA_SELEC_HEIGHT * 2 + 40);

        // Creem el viewport amb les mateixes dimensions que la càmera
        viewport = new StretchViewport(3200, 1600, camera);

        // Creem l'stage i assginem el viewport
        stage = new Stage(viewport);

        batch = stage.getBatch();

        if (maxCaballeros > 0) {
            stage.addActor(containerCaballero);
        }
        if (maxNinjas > 0) {
            stage.addActor(containerNinja);
        }
        if (maxRobots > 0) {
            stage.addActor(containerRobot);
        }

        //bucle para saber las posiciones de los objetos torre
        torres = nivel.recojerTorres();

        for (int i = 0; i < torres.size(); i++) {
            stage.addActor(torres.get(i));
        }

        fuego = new Fuego(0, 0, 1);

        Gdx.input.setInputProcessor(new InputHandler(this));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if(isFinal() != true){

            contador++;
            compruebaAtaqueTorre = true;

            Gdx.gl.glClearColor(0, 0, 0, 0);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            camera.update();
            renderer.setView(camera);
            renderer.render();

            if (tropasEnMapa != null) {
                for (int i = 0; i < tropasEnMapa.size(); i++) {
                    Tropas tropaActual = tropasEnMapa.get(i);
                    tropaActual.setTiempoDeEstado(tropaActual.getTiempoDeEstado() + delta);
                    if (tropaActual.getEstado() == Tropas.Estado.Caminando) {
                        if (contador % tropaActual.getVelocidad() == 0) {
                            tropaActual.siguienteCasilla(camino);
                            System.out.println(tropaActual.getCasillaActual());

                            if(nivel.comproFinal(camino, tropaActual.getCasillaActual()) == true) {

                                setFinal(true);
                            }

                        }

                    }
                }
            }



            for (int defensoras = 0; defensoras < torres.size(); defensoras++) {
                for (int atacantes = 0; atacantes < tropasEnMapa.size(); atacantes++) {
                    Tropas tropaActual = tropasEnMapa.get(atacantes);
                    Torres torreActual = torres.get(defensoras);


                    if (Intersector.overlaps(torreActual.getCollisionCircle(), tropaActual.getCollisionRect())) {

                        torreActual.setOverlaps(true);


                        if (tropasColisionadas.indexOf(tropaActual) == -1) {
                            tropaActual.setEstaAtacando(true);
                            tropasColisionadas.add(tropaActual);
                            fuego.setX(tropasColisionadas.get(0).getX());
                            fuego.setY(tropasColisionadas.get(0).getY());
                            fuego.setVisible(true);
                        }

                        //if (tropasColisionadas.size() > 0){

                        //}

                        if (contador % 60 == 0 && contadorTropasMuertas < tropasColisionadas.size() && compruebaAtaqueTorre) {
                            compruebaAtaqueTorre = false;

                            tropasColisionadas.get(contadorTropasMuertas).setVida(tropasColisionadas.get(contadorTropasMuertas).getVida() - 1);
                            if (tropasColisionadas.get(contadorTropasMuertas).getVida() < 0) {
                                tropasColisionadas.get(contadorTropasMuertas).setDanyo(0);
                                tropasColisionadas.get(contadorTropasMuertas).remove();
                                tropasEnMapa.get(tropasEnMapa.indexOf(tropasColisionadas.get(contadorTropasMuertas))).remove();
                                contadorTropasMuertas++;
                            }

                            for (int danyoTorre = 0; danyoTorre < tropasColisionadas.size(); danyoTorre++) {
                                torreActual.setVida(torreActual.getVida() - tropasColisionadas.get(danyoTorre).getDanyo());

                                if (torreActual.getVida() <= 0) {
                                    torreActual.getCollisionCircle().set(new Circle(10, 10, 5));
                                    torreActual.setViva(false);
                                    torreActual.setOverlaps(false);
                                    for (int tropas = 0; tropas < tropasColisionadas.size(); tropas++) {
                                        tropasColisionadas.get(tropas).setEstaAtacando(false);
                                    }
                                }
                            }
                        }

                        tropaActual.setEstado(Tropas.Estado.Atacando);

                        if(!tropasEnMapa.get(atacantes).getName().equals("Robots")){
                            //System.out.println("entro en caminar hacia torre");
                            AtacarTorre at = new AtacarTorre(tropaActual, torreActual);
                            ArrayList < Camino > camino = at.caminarHaciaTorre();
                            if (contador % tropaActual.getVelocidad() == 0) {
                                if (!tropaActual.siguienteCasillaAtaque(camino) && !tropaActual.llegarATorre(tropaActual.getY(), torreActual.getPosicionAtaque().y, torreActual.isOrientacion())) {
                                    tropaActual.setanimacionCaminar(false);
                                }
                            }
                        }else{
                            // System.out.println(orientacionRobot);
                           // if(orientacionRobot){
                           //     tropaActual.orientacionBala = true;
                           // }else{
                          //      tropaActual.orientacionBala = false;
                          //  }
                          //  tropaActual.setanimacionCaminar(false);

                        }

                    } else {
                        if (tropasColisionadas.size() > 0) {
                            for (int j = 0; j < tropasColisionadas.size(); j++) {
                                if (!tropasColisionadas.get(j).isEstaAtacando()) {
                                    if (!tropasColisionadas.get(j).salirDeTorre() && contador % tropasColisionadas.get(j).getVelocidad() == 0) {
                                        tropasColisionadas.get(j).setEstado(Tropas.Estado.Caminando);
                                        tropasColisionadas.remove(j);
                                    }
                                } else {
                                    if (contador % tropasColisionadas.get(j).getVelocidad() == 0) {
                                        tropasColisionadas.get(j).siguienteCasilla(camino);
                                    }
                                }
                            }
                        } else {
                            fuego.setVisible(false);
                        }
                    }

                }
            }

            stage.draw();
            stage.act(delta);

            if (debug) renderDebug();

            batch.begin();

            batch.end();

        }else{

            game.setScreen(new FinalScreen(ganado));

        }
    }

    private void renderDebug() {

        debugRenderer.setProjectionMatrix(camera.combined);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);

        debugRenderer.setColor(Color.RED);
        for (int i = 0; i < torres.size(); i++) {
            debugRenderer.circle(torres.get(i).getCollisionCircle().x, torres.get(i).getCollisionCircle().y, torres.get(i).getCollisionCircle().radius);
        }
        debugRenderer.setColor(Color.GREEN);
        for (int i = 0; i < tropasEnMapa.size(); i++) {
            debugRenderer.rect(tropasEnMapa.get(i).getCollisionRect().x, tropasEnMapa.get(i).getCollisionRect().y, tropasEnMapa.get(i).getCollisionRect().width, tropasEnMapa.get(i).getCollisionRect().height);
        }
        debugRenderer.setColor(Color.PURPLE);
        for (int i = 0; i < tropasColisionadas.size(); i++) {
            debugRenderer.rect(tropasColisionadas.get(i).getCollisionRect().x - 1, tropasColisionadas.get(i).getCollisionRect().y - 1, tropasColisionadas.get(i).getCollisionRect().width + 2, tropasColisionadas.get(i).getCollisionRect().height + 2);
        }
        debugRenderer.setColor(Color.GOLD);
        for (int i = 0; i < torres.size(); i++) {
            debugRenderer.rect(torres.get(i).getPosicionAtaque().x, torres.get(i).getPosicionAtaque().y, 50, 50);
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

        if (tropa.equals("Caballero")) {
            if (maxCaballeros > 0) {
                //caballeros.add(new Caballero(camino.get(0).getX(), camino.get(0).getY(), random.nextInt(125) + (-87), random.nextInt(150) + (-100)));
                tropasEnMapa.add(tropas.crearTropa(Tropas.Tipo.Caballero));
                stage.addActor(tropasEnMapa.get(tropasEnMapa.size() - 1));
                maxCaballeros--;
            }
        } else if (tropa.equals("Ninja")) {
            if (maxNinjas > 0) {
                //ninjas.add(new Ninja(camino.get(0).getX(), camino.get(0).getY(), random.nextInt(125) + (-87), random.nextInt(150) + (-100)));
                tropasEnMapa.add(tropas.crearTropa(Tropas.Tipo.Ninja));
                stage.addActor(tropasEnMapa.get(tropasEnMapa.size() - 1));
                maxNinjas--;
            }
        } else if (tropa.equals("Robot")) {
            if (maxRobots > 0) {
                //robots.add(new Robot(camino.get(0).getX(), camino.get(0).getY(), random.nextInt(125) + (-115), random.nextInt(150) + (-90)));
                tropasEnMapa.add(tropas.crearTropa(Tropas.Tipo.Robot));
                stage.addActor(tropasEnMapa.get(tropasEnMapa.size() - 1));
                maxRobots--;
            }
        }

    }

    public boolean isFinal() {
        return Final;
    }

    public void setFinal(boolean aFinal) {
        Final = aFinal;
    }
}