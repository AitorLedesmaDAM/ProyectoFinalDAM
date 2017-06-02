package com.proyectofinal.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.proyectofinal.game.TowerAttack;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.helpers.InputHandler;
import com.proyectofinal.game.utils.Musica;
import com.proyectofinal.game.utils.Settings;

public class SeleccionScreen implements Screen {

    // Atributos
    private Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;

    private TowerAttack game;

    public int numCaballero, numNinja, numRobot;
    private int lvl;

    private Label.LabelStyle textStyle, textStylePequenio;

    private Label contador, textoTropas, costeCaballero, costeNinja, costeRobot;
    private int maxTropasContador, contadorTropas;

    private Container miniMapa, containerTropasMax, containerTextocontainerTropasMax, containerCaballero,
            containerCosteCaballero, containerCosteNinja, containerCosteRobot, containerNinja,
            containerRobot, containerBoton;

    private Musica m = new Musica();

    public SeleccionScreen() {
    }

    /**
     * Constructor
     * Le pasamos por parametros
     * * game
     * * batch
     * * viewport
     * * lvl : Nivel que se a elejido jugar
     * * maxTropasContador : El numero de tropas maximas permitidas para ese nivel
     */
    public SeleccionScreen(TowerAttack game, Viewport _viewport, int lvl, int _maxTropasContador) {
        this.game = game;
        Settings.pantalla = 2;

        camera = AssetManager.camera;
        this.lvl = lvl;

        // Ponemos los estilos de texto a los LabelStyle
        textStyle = AssetManager.textStyle;
        textStylePequenio = AssetManager.textStylePequenio;

        // Pasamos el maximo de tropas posibles de lo que se pasa por parametros a variables locales
        maxTropasContador = _maxTropasContador;
        contadorTropas = _maxTropasContador;

        // Creamos todos los Contenedores de datos para la segunda pantalla
        if (lvl == 1) {
            miniMapa = new Container(new Image(AssetManager.mapa1)); //Imagen del mapa
        } else if (lvl == 2) {
            miniMapa = new Container(new Image(AssetManager.mapa2)); //Imagen del mapa
        }
        miniMapa.setTransform(true);
        miniMapa.center();
        miniMapa.setSize(Settings.MINIMAPA_WIDTH, Settings.MINIMAPA_HEIGHT); //Tamaño de la Imagen
        miniMapa.setPosition(Settings.GAME_WIDTH - Settings.MINIMAPA_WIDTH, 0);

        contador = new Label("" + contadorTropas, textStyle); //Contador de tropas
        containerTropasMax = new Container(contador);
        containerTropasMax.setTransform(true);
        containerTropasMax.center();
        containerTropasMax.setPosition(350, 250);

        textoTropas = new Label("Tropas máximas restantes: ", textStylePequenio); //Texto encima del contador
        containerTextocontainerTropasMax = new Container(textoTropas);
        containerTextocontainerTropasMax.setTransform(true);
        containerTextocontainerTropasMax.center();
        containerTextocontainerTropasMax.setPosition(400, 150);

        Image caballero = new Image(AssetManager.caballeroSelec); //Selección de caballero
        caballero.setName("Caballero");
        containerCaballero = new Container(caballero);
        containerCaballero.setTransform(true);
        containerCaballero.center();
        containerCaballero.setSize(Settings.TROPA_SELEC_WIDTH, Settings.TROPA_SELEC_HEIGHT);
        containerCaballero.setPosition(Settings.GAME_WIDTH / 3 - Settings.TROPA_SELEC_WIDTH * 2, Settings.GAME_HEIGHT / 2);

        Image ninja = new Image(AssetManager.ninjaSelec); //Selección del ninja
        ninja.setName("Ninja");
        containerNinja = new Container(ninja);
        containerNinja.setTransform(true);
        containerNinja.center();
        containerNinja.setSize(Settings.TROPA_SELEC_WIDTH, Settings.TROPA_SELEC_HEIGHT);
        containerNinja.setPosition(Settings.GAME_WIDTH / 3 - (Settings.TROPA_SELEC_WIDTH / 2 + Settings.TROPA_SELEC_WIDTH / 3), Settings.GAME_HEIGHT / 2);

        Image robot = new Image(AssetManager.robotSelec); //Selección del robot
        robot.setName("Robot");
        containerRobot = new Container(robot);
        containerRobot.setTransform(true);
        containerRobot.center();
        containerRobot.setSize(Settings.TROPA_SELEC_WIDTH, Settings.TROPA_SELEC_HEIGHT);
        containerRobot.setPosition(Settings.GAME_WIDTH / 3 + Settings.TROPA_SELEC_WIDTH / 3, Settings.GAME_HEIGHT / 2);

        Image continuar = new Image(AssetManager.btnContinuar); //Seleccion del botón
        continuar.setName("Continuar");
        containerBoton = new Container(continuar);
        containerBoton.setTransform(false);
        containerBoton.center();
        containerBoton.setSize(400, 200);
        containerBoton.setPosition(Settings.GAME_WIDTH - 450, Settings.GAME_HEIGHT / 2 + Settings.GAME_HEIGHT / 4);
        containerBoton.setVisible(false);

        // Contenedores de los costes de cada tropa
        costeCaballero = new Label("coste: 1", textStylePequenio);
        containerCosteCaballero = new Container(costeCaballero);
        containerCosteCaballero.center();
        containerCosteCaballero.setPosition(Settings.GAME_WIDTH / 3 - (Settings.TROPA_SELEC_WIDTH * 2) + Settings.TROPA_SELEC_WIDTH / 2, Settings.GAME_HEIGHT / 2 + Settings.TROPA_SELEC_HEIGHT + 30);

        costeNinja = new Label("coste: 2", textStylePequenio);
        containerCosteNinja = new Container(costeNinja);
        containerCosteNinja.center();
        containerCosteNinja.setPosition(Settings.GAME_WIDTH / 3 - (Settings.TROPA_SELEC_WIDTH / 2 + Settings.TROPA_SELEC_WIDTH / 3) + Settings.TROPA_SELEC_WIDTH / 2, Settings.GAME_HEIGHT / 2 + Settings.TROPA_SELEC_HEIGHT + 30);

        costeRobot = new Label("coste: 3", textStylePequenio);
        containerCosteRobot = new Container(costeRobot);
        containerCosteRobot.center();
        containerCosteRobot.setPosition(Settings.GAME_WIDTH / 3 + Settings.TROPA_SELEC_WIDTH / 3 + Settings.TROPA_SELEC_WIDTH / 2, Settings.GAME_HEIGHT / 2 + Settings.TROPA_SELEC_HEIGHT + 30);

        // Creamos el Viewport con las mismas dimensiones que la Camara
        viewport = _viewport;

        // Creamos el Stage y le asignamos el Viewport
        stage = new Stage(viewport);

        // Añadimos el Fondo
        stage.addActor(new Image(AssetManager.background));

        // Añadimos los Contenedores como Actores al Stage
        stage.addActor(miniMapa);
        stage.addActor(containerTropasMax);
        stage.addActor(containerTextocontainerTropasMax);
        stage.addActor(containerCaballero);
        stage.addActor(containerNinja);
        stage.addActor(containerRobot);
        stage.addActor(containerBoton);
        stage.addActor(containerCosteCaballero);
        stage.addActor(containerCosteNinja);
        stage.addActor(containerCosteRobot);

        canviarMusica();

        // Hacemos que los Contenedores se puedan pulsar
        Gdx.input.setInputProcessor(new InputHandler(this));
    }

    public void canviarMusica() {
        m.iconoMusica(stage);
    }

    /**
     * Metodo para restar de las tropas maximas el valor de la tropa seleccionada
     * y en caso de pasar de la mitad de tropas maximas Selecionadas
     * se active el boton de continuar
     */
    public void modMaxTropas(int resta) {

        if (contadorTropas - resta > -1) {
            contadorTropas = contadorTropas - resta;
            contador.setText("" + contadorTropas);
        }
        if (contadorTropas < maxTropasContador / 2) {
            containerBoton.setVisible(true);
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

    /**
     * Metodo para cambiar de pantalla al hacer click en continuar
     */
    public void siguientePantalla() {
        if (contadorTropas < (maxTropasContador / 2)) {
            // Se le pasara a la siguiente pantalla el numero de cada tropa seleccionada
            game.setScreen(new AtaqueScreen(game, numCaballero, numNinja, numRobot, lvl));

        }
    }

    public Stage getStage() {
        return stage;
    }

    /**
     * Metodo que sumara uno cada vez a la variable numCaballero
     */
    public void sumarCaballero() {
        numCaballero++;
    }

    /**
     * Metodo que sumara uno cada vez a la variable numNinja
     */
    public void sumarNinja() {
        numNinja++;
    }

    /**
     * Metodo que sumara uno cada vez a la variable numRobot
     */
    public void sumarRobot() {
        numRobot++;
    }

}