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
import com.badlogic.gdx.math.Vector2;
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
import com.proyectofinal.game.objects.AtacarTorre;
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

    Tropas tropas;
    TiledMap mapa;

    OrthogonalTiledMapRenderer renderer;
    private Nivel nivel;
    OrthographicCamera camera;
    private TowerAttack game;
    Batch batch;
    Viewport viewport;
    private Stage stage;
    private boolean musicaActiva = Settings.music;

    public boolean esfinal = false;

    private ArrayList < Tropas > tropasEnMapa;
    public ArrayList < Camino > camino;
    ArrayList < Tropas > tropasColisionadas;

    private long contador = 0;

    ArrayList < Torres > torres;

    public static boolean debug = false;
    private ShapeRenderer debugRenderer;

    public int maxCaballeros, maxNinjas, maxRobots;
    public int contadorTropasMuertas = 0;
    private boolean compruebaAtaqueTorre = true, hayTropasColisionadas;
    private Container containerCaballero, containerNinja, containerRobot;
    public int lvl;

    Musica m = new Musica();

    private Boolean sonidoAtacar = false, sonidoCaminar = false;

    public AtaqueScreen(TowerAttack game, int maxCaballeros, int maxNinjas, int maxRobots, int lvl) {
        this.game = game;
        Settings.pantalla = 3;
        this.maxCaballeros = maxCaballeros;
        this.maxNinjas = maxNinjas;
        this.maxRobots = maxRobots;
        this.lvl = lvl;


        debugRenderer = new ShapeRenderer();

        tropas = new Tropas();

        if (lvl == 1){
            mapa = AssetManager.tiledMap1;
        }else if (lvl == 2){
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

    public void canviarMusica(){
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

        if (tropasEnMapa.size() == 0 && maxCaballeros == 0 && maxRobots == 0 && maxNinjas == 0) {
            game.setScreen(new FinalScreen(game, lvl, false));
            setEsfinal(true);

        } else {

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
                            nivel.comproFinal(camino, tropaActual.getCasillaActual());

                            if (nivel.comproFinal(camino, tropaActual.getCasillaActual())) {
                                game.setScreen(new FinalScreen(game, lvl, true));
                                setEsfinal(true);
                            }
                        }
                    }
                }
            }

            for (int defensoras = 0; defensoras < torres.size(); defensoras++) {
                Torres torreActual = torres.get(defensoras);
                for (int atacantes = 0; atacantes < tropasEnMapa.size(); atacantes++) {
                    Tropas tropaActual = tropasEnMapa.get(atacantes);

                    if (Intersector.overlaps(torreActual.getCollisionCircle(), tropaActual.getCollisionRect())) {

                        if (contador % 60 == 0 && tropasColisionadas.indexOf(tropaActual) == -1) {

                            sonidoAtacar = true;
                            tropaActual.setEstaAtacando(true);
                            tropasColisionadas.add(tropaActual);
                            hayTropasColisionadas = true;
                        }

                        if (hayTropasColisionadas) {
                            torreActual.setOverlaps(true);
                        }


                        if (contador % 60 == 0 && tropasColisionadas.size() > 0 && compruebaAtaqueTorre) {
                            compruebaAtaqueTorre = false;

                            if (torreActual.getTipo().equals("Fuego")) {
                                tropasColisionadas.get(0).setVida(tropasColisionadas.get(0).getVida() - torreActual.getDanyo());
                                if (tropasColisionadas.get(0).getVida() <= 0) {
                                    tropasColisionadas.get(0).setDanyo(0);
                                    tropasColisionadas.get(0).setVisible(false);
                                    if (tropasColisionadas.size() == 1) {
                                        hayTropasColisionadas = false;
                                    }

                                    Tropas remove = tropasColisionadas.remove(0);
                                    tropasEnMapa.remove(tropasEnMapa.indexOf(remove));
                                    borrarActorStage(remove);
                                    contadorTropasMuertas++;
                                }
                            } else if (torreActual.getTipo().equals("Rayo")) {
                                for (int i = 0; i < tropasColisionadas.size(); i++) {
                                    tropasColisionadas.get(i).setVida(torreActual.getDanyo());
                                    System.out.println(tropasColisionadas.get(i));
                                    if (tropasColisionadas.get(i).getVida() < 0) {
                                        Tropas remove = tropasColisionadas.remove(i);
                                        tropasEnMapa.remove(tropasEnMapa.indexOf(remove));
                                        borrarActorStage(remove);
                                        contadorTropasMuertas++;
                                        if (musicaActiva) {
                                            AssetManager.soundDead2.play();
                                        }
                                    }
                                }
                            }


                            for (int danyoTorre = 0; danyoTorre < tropasColisionadas.size(); danyoTorre++) {
                                if (!tropasColisionadas.get(danyoTorre).isanimacionCaminar()) {
                                    torreActual.setVida(torreActual.getVida() - tropasColisionadas.get(danyoTorre).getDanyo());
                                    if (torreActual.getVida() <= 0) {
                                        for (int tropas = 0; tropas < tropasColisionadas.size(); tropas++) {
                                            tropasColisionadas.get(tropas).setEstaAtacando(false);
                                        }
                                        if (torreActual.isOrientacion()) {
                                            torreActual.getCollisionCircle().setY(torreActual.getCollisionCircle().y + 50);
                                        }
                                        torreActual.getCollisionCircle().set(new Circle(10, 10, 5));
                                        if (musicaActiva){
                                            AssetManager.soundFireball.play();
                                        }

                                        torreActual.setViva(false);
                                        torreActual.setOverlaps(false);
                                    }
                                }
                            }

                            System.out.println("Vida de la torre: " + torreActual.getVida());
                        }

                        tropaActual.setEstado(Tropas.Estado.Atacando);

                        if (!tropaActual.getName().equals("Robots")) {
                            AtacarTorre at = new AtacarTorre(tropaActual, torreActual, camino);
                            ArrayList<Camino> camino = at.caminarHaciaTorre();
                            if (contador % tropaActual.getVelocidad() == 0) {
                                if (!tropaActual.siguienteCasillaAtaque(camino) && !tropaActual.llegarATorre(tropaActual.getY(), torreActual.getPosicionAtaque().y, torreActual.isOrientacion())) {
                                    tropaActual.setanimacionCaminar(false);

                                }
                            }
                        } else {
                            tropaActual.setContadorBala(tropaActual.getContadorBala() + 1);
                            tropaActual.setanimacionCaminar(false);
                            if (tropaActual.getContadorBala() % 120 == 0) {
                                tropaActual.ataque(tropaActual.getPosition(), new Vector2(torreActual.getCollisionCircle().x, torreActual.getCollisionCircle().y), stage);
                            }
                        }

                    } else {
                        if (tropasColisionadas.size() > 0) {
                            for (int j = 0; j < tropasColisionadas.size(); j++) {
                                if (!tropasColisionadas.get(j).isEstaAtacando()) {
                                    if (contador % tropasColisionadas.get(j).getVelocidad() == 0) {
                                        if (!tropasColisionadas.get(j).salirDeTorre()) {
                                            tropasColisionadas.get(j).setEstado(Tropas.Estado.Caminando);
                                            tropasColisionadas.remove(j);
                                        }
                                    } else {
                                        if (contador % tropasColisionadas.get(j).getVelocidad() == 0) {
                                            tropasColisionadas.get(j).siguienteCasilla(camino);

                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                for (int i = 0; i < tropasEnMapa.size(); i++){
                    if (tropasEnMapa.get(i).isanimacionCaminar()){
                        
                        sonidoCaminar = true;
                    }

                }
                System.out.println(musicaActiva);
                if (musicaActiva){
                    if (sonidoAtacar){
                        AssetManager.soundAttack.loop();

                    }
                    sonidoAtacar = false;
                    if (tropasColisionadas.size() == 0){
                        AssetManager.soundAttack.stop();
                    }
                    if (sonidoCaminar){
                        AssetManager.soundWalk.play();
                    }
                    sonidoCaminar = false;
                    if (tropasEnMapa.size() == 0){
                        AssetManager.soundWalk.stop();
                    }
                }else{
                    AssetManager.soundAttack.stop();
                    AssetManager.soundWalk.stop();
                    AssetManager.soundDead2.stop();
                }



                if (!hayTropasColisionadas) {
                    torreActual.setOverlaps(false);
                }
            }
            stage.draw();
            stage.act(delta);

            if (debug) renderDebug();

            batch.begin();

            batch.end();

        }
    }

    public void borrarActorStage(Tropas tropa){
        Array<Actor> actores = stage.getActors();
        for (Actor actor: actores) {
            if(actor.equals(tropa)){
                actor.remove();
            }
        }
    }

    public boolean isEsfinal() {
        return esfinal;
    }

    public void setEsfinal(boolean esfinal) {
        this.esfinal = esfinal;
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
    }

    @Override
    public void dispose() {
    }

    public Stage getStage() {
        return stage;
    }

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