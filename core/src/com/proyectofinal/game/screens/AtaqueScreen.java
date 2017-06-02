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
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.proyectofinal.game.TowerAttack;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.helpers.InputHandler;
import com.proyectofinal.game.objects.towers.AtaqueTorre;
import com.proyectofinal.game.objects.trops.AtaqueTropa;
import com.proyectofinal.game.objects.Nivel;
import com.proyectofinal.game.objects.road.Camino;
import com.proyectofinal.game.objects.towers.Torres;
import com.proyectofinal.game.objects.trops.Tropas;
import com.proyectofinal.game.utils.Musica;
import com.proyectofinal.game.utils.Settings;

import java.util.ArrayList;

/**
 * Created by ALUMNEDAM on 25/04/2017.
 */

public class AtaqueScreen implements Screen {

    private Tropas tropas;
    private Nivel nivel;
    private TowerAttack game;
    private AtaqueTropa ataqueTropa;
    private AtaqueTorre ataqueTorre;

    private TiledMap mapa;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    public static boolean debug = false;
    private ShapeRenderer debugRenderer;

    private Batch batch;
    private Viewport viewport;
    private Stage stage;

    private ArrayList < Tropas > tropasEnMapa, tropasColisionadas;
    private ArrayList < Camino > camino;
    private ArrayList < Torres > torres;

    private long contador = 0;

    public int maxCaballeros, maxNinjas, maxRobots, lvl;

    private boolean compruebaAtaqueTorre = true, hayTropasColisionadas, orientacionUltimaTorre;

    private Container containerCaballero, containerNinja, containerRobot;

    private Musica m = new Musica();
    private boolean musicaActiva = Settings.music, sonidoAtacar = false, sonidoCaminar = false;

    public AtaqueScreen(TowerAttack game, int maxCaballeros, int maxNinjas, int maxRobots, int lvl) {
        this.game = game;
        Settings.pantalla = 3;
        this.maxCaballeros = maxCaballeros;
        this.maxNinjas = maxNinjas;
        this.maxRobots = maxRobots;
        this.lvl = lvl;

        ataqueTropa = new AtaqueTropa();
        ataqueTorre = new AtaqueTorre();

        debugRenderer = new ShapeRenderer();

        tropas = new Tropas();

        //dependiendo de el lvl se pondra un mapa o otro
        if (lvl == 1) {
            mapa = AssetManager.tiledMap1;
        } else if (lvl == 2) {
            mapa = AssetManager.tiledMap2;
        }

        renderer = new OrthogonalTiledMapRenderer(mapa);

        camera = AssetManager.camera;
        camera.setToOrtho(false, 3200, 1600);
        renderer.setView(camera);
        camera.update();

        nivel = new Nivel();

        tropasEnMapa = new ArrayList < Tropas > ();

        camino = nivel.recojerCamino(mapa);
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
        torres = nivel.recojerTorres(mapa);

        for (int i = 0; i < torres.size(); i++) {
            stage.addActor(torres.get(i));
        }

        canviarMusica();

        Gdx.input.setInputProcessor(new InputHandler(this));
    }


    public void canviarMusica() {
        m.iconoMusica(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        contador++;
        compruebaAtaqueTorre = true;
        musicaActiva = Settings.music;

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //si las tropasEnMapa, maxCaballeros, maxRobot y maxNinjas estan a 0 quiere decir que se te han muerto
        //todas las tropas y que has perdido
        if (tropasEnMapa.size() == 0 && maxCaballeros == 0 && maxRobots == 0 && maxNinjas == 0) {
            //se llama a la final Screen con el ganado en false
            game.setScreen(new FinalScreen(game, lvl, false));

            //sino se ha perdido la partida
        } else {


            camera.update();
            renderer.setView(camera);
            renderer.render();


            if (tropasEnMapa != null) {

                //se recorre tropasEnMapa
                for (int i = 0; i < tropasEnMapa.size(); i++) {
                    Tropas tropaActual = tropasEnMapa.get(i);
                    //se actualiza el tiempo de estasdo para poder ver la animación
                    tropaActual.setTiempoDeEstado(tropaActual.getTiempoDeEstado() + delta);
                    //si la tropa esta en estado caminando
                    if (tropaActual.getEstado() == Tropas.Estado.Caminando) {
                        if (contador % tropaActual.getVelocidad() == 0) {
                            //buscara la siguiente casilla de camino para moverse
                            tropaActual.siguienteCasilla(camino);
                            //y si la casilla actual de esta tropa es igual a la ultima de camino
                            if (nivel.comproFinal(camino, tropaActual.getCasillaActual())) {
                                //llamamos a la ultima pantalla con el ganado a true
                                game.setScreen(new FinalScreen(game, lvl, true));
                            }
                        }
                    }
                }
            }

            //recorremos las torres y las tropasEnMapa
            for (int defensoras = 0; defensoras < torres.size(); defensoras++) {
                Torres torreActual = torres.get(defensoras);
                for (int atacantes = 0; atacantes < tropasEnMapa.size(); atacantes++) {
                    Tropas tropaActual = tropasEnMapa.get(atacantes);

                    //si se hace el overlaps
                    if (Intersector.overlaps(torreActual.getCollisionCircle(), tropaActual.getCollisionRect())) {

                        //miramos la orientación de la torre
                        orientacionUltimaTorre = torreActual.isOrientacion();

                        //control para que solamente se introduzca la misma tropa solo una vez
                        if (contador % 60 == 0 && tropasColisionadas.indexOf(tropaActual) == -1) {

                            //activamos el sonido
                            sonidoAtacar = true;
                            //ponemos el estado a atacando
                            tropaActual.setEstaAtacando(true);
                            //añadimos la tropa a tropasColisionadas
                            tropasColisionadas.add(tropaActual);
                            hayTropasColisionadas = true;
                        }

                        if (hayTropasColisionadas) {
                            torreActual.setOverlaps(true);
                        }

                        if (contador % 60 == 0 && tropasColisionadas.size() > 0 && compruebaAtaqueTorre) {
                            compruebaAtaqueTorre = false;

                            ArrayList < Tropas > tropasMuertas = ataqueTorre.atacarTropas(torreActual, tropasColisionadas);
                            if (tropasMuertas != null) {
                                //Controla las tropas muertas
                                for (Tropas tropa: tropasMuertas) {
                                    tropasEnMapa.remove(tropasEnMapa.indexOf(tropa));
                                    borrarActorStage(tropa);
                                }
                            }
                            //muestra el ataque de la torre
                            ataqueTorre.mostrarAtaque(torreActual, tropasColisionadas);

                            ataqueTropa.atacarTorre(torreActual, tropasColisionadas, musicaActiva);

                        }
                        //cambio del estado de las tropas a atacando
                        tropaActual.setEstado(Tropas.Estado.Atacando);
                        //si la tropa qaue esta atacando es un robot no ira hacia la torre sino que se
                        //quedara en la misma posicion del overlaps y atacara desde allí
                        if (!tropaActual.getName().equals("Robots")) {
                            if (contador % tropaActual.getVelocidad() == 0) {
                                ataqueTropa.llegarATorre(torreActual, tropaActual, camino);
                            }
                        } else {
                            ataqueTropa.disparoRobot(torreActual, tropaActual, stage);
                        }

                    } else {
                        //mientras la tropa este atacando que no se mueva
                        if (!tropaActual.isEstaAtacando() && contador % tropaActual.getVelocidad() == 0){
                            tropaActual.setanimacionCaminar(true);
                            if (!tropaActual.salirDeTorre()) {
                                tropaActual.setEstado(Tropas.Estado.Caminando);
                                tropasColisionadas.remove(tropaActual);
                            }else{
                                //cuando termine de atacar que busque la siguiente casilla
                                tropaActual.siguienteCasilla(camino);
                            }
                        }
                    }
                    if (!hayTropasColisionadas) {
                        torreActual.setOverlaps(false);
                    }
                }
            }
            //hacemos las animaciones caminar/atacar
            if (contador % 30 == 0) {
                int caminar = 0, atacar = 0;
                for (int i = 0; i < tropasEnMapa.size(); i++) {
                    if (tropasEnMapa.get(i).isanimacionCaminar()) {
                        caminar++;
                    } else {
                        if (!tropasEnMapa.get(i).getName().equals("Robots"))
                            atacar++;
                    }
                }
                //Comprovamos el boolean musicaActiva para encender o parar la musica
                if (musicaActiva) {
                    if (contador % 30 == 0) {
                        if (atacar > 0) {
                            AssetManager.soundMelee.play();
                        } else {
                            AssetManager.soundMelee.stop();
                        }
                    }
                    if (caminar > 0) {
                        AssetManager.soundWalk.play();
                    }else{
                        AssetManager.soundWalk.stop();
                    }
                } else {
                    AssetManager.soundMelee.stop();
                    AssetManager.soundWalk.stop();
                }
            }

            stage.draw();
            stage.act(delta);

            if (debug) renderDebug();

            batch.begin();

            batch.end();
        }
    }

    /**
     *
     * Metodo para eliminar un actor del stage
     *
     * @param tropa
     */
    public void borrarActorStage(Tropas tropa) {
        Array < Actor > actores = stage.getActors();
        for (Actor actor: actores) {
            if (actor.equals(tropa)) {
                actor.remove();
            }
        }
    }

    /**
     *
     * Metodo para que cuando se pulse 'B' se vean los rectangulos y los circulos de colisiones de las tropas y las torres
     *
     */
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

    /**
     *
     * @param width
     * @param height
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

        stage.dispose();
        this.dispose();
    }

    @Override
    public void dispose() {}

    public Stage getStage() {
        return stage;
    }


    /**
     *
     * Metodo para añadir una tropa al camino y restar a la variable correspondiente
     *
     * @param tropa
     */

    public void soltarTropa(String tropa) {

        if (tropa.equals("Caballero")) {
            if (maxCaballeros > 0) {
                tropasEnMapa.add(tropas.crearTropa(Tropas.Tipo.Caballero, camino.get(0).getX(), camino.get(0).getY()));
                stage.addActor(tropasEnMapa.get(tropasEnMapa.size() - 1));
                maxCaballeros--;
            }
        } else if (tropa.equals("Ninja")) {
            if (maxNinjas > 0) {
                tropasEnMapa.add(tropas.crearTropa(Tropas.Tipo.Ninja, camino.get(0).getX(), camino.get(0).getY()));
                stage.addActor(tropasEnMapa.get(tropasEnMapa.size() - 1));
                maxNinjas--;
            }
        } else if (tropa.equals("Robot")) {
            if (maxRobots > 0) {
                tropasEnMapa.add(tropas.crearTropa(Tropas.Tipo.Robot, camino.get(0).getX(), camino.get(0).getY()));
                stage.addActor(tropasEnMapa.get(tropasEnMapa.size() - 1));
                maxRobots--;
            }
        }

    }
}