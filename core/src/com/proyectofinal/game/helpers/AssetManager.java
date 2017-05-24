package com.proyectofinal.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.proyectofinal.game.utils.Settings;

/**
 * Created by ALUMNEDAM on 25/04/2017.
 */

public class AssetManager {

    //Atibutos como Texturas.
    public static Texture fondo, textureBtnCont, textureBtnReiniciar, textureBtnSalir, textureBtnSig;
    //Atributos de Animaciones
    public static Animation<TextureRegion> caballeroRun, caballeroAttack, ninjaRun, ninjaAttack, robotRun, robotAttack, robotBullet, robotMuzzle, fuegoTorre;

    //Sprites
    public static Sprite caballeroSelec, ninjaSelec, robotSelec, mapa1, btnContinuar, caballeroSelecAtak,ninjaSelecAtak,robotSelecAtak, btnReiniciar, btnSalir, btnSig;
    public static Sprite torreF, torreF2, torreFMuerta, torreF2Muerta, torreR, torreR2, torreRMuerta, torreR2Muerta;

    //Bitmap para difeentes fuentes
    public static BitmapFont fontGrande, font, fontPequenia;
    //estilo de las letras
    public static Label.LabelStyle textStyle, textStyleTitulo, textStylePequenio;

    //Texture Region para poner el fondo
    public static TextureRegion background;

    //Camera
    public static OrthographicCamera camera;

    //TiledMap
    public static TiledMap tiledMap;
    //OrthogonalTiledMapRenderer
    public static OrthogonalTiledMapRenderer renderer;

    public static Music musicStart, musicEnd;
    public static Sound soundAttack, soundFireball, soundWalk, soundDead, soundDead1, soundDead2;

    /**
     * Methodo Load
     */
    public static void load() {

        //Carrgamos las texturas en sprites
        caballeroSelecAtak = new Sprite(new Texture(Gdx.files.internal("tropas/caballeroSelec_prueba.png")));
        caballeroSelecAtak.flip(true, false);

        ninjaSelecAtak = new Sprite(new Texture(Gdx.files.internal("tropas/ninjaSelec_prueba.png")));
        ninjaSelecAtak.flip(true, false);

        robotSelecAtak = new Sprite(new Texture(Gdx.files.internal("tropas/robotSelec_prueba.png")));
        robotSelecAtak.flip(true, false);



        //Carrgamos les textures para poner de fondo
        fondo = new Texture(Gdx.files.internal("fondos/fondo_degradado.jpg"));
        //Ponemos el fondo
        fondo.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        background = new TextureRegion(fondo);
        background.flip(false, true);

        //Se carga la textura de mapa en sprite.
        mapa1 = new Sprite(new Texture(Gdx.files.internal("texturas/mapa1.png")));
        mapa1.flip(false, true);

        //Carrgamos las texturas de selecionar tropas en sprites.
        caballeroSelec = new Sprite(new Texture(Gdx.files.internal("tropas/caballeroSelec_prueba.png")));
        caballeroSelec.flip(false, true);

        ninjaSelec = new Sprite(new Texture(Gdx.files.internal("tropas/ninjaSelec_prueba.png")));
        ninjaSelec.flip(false, true);

        robotSelec = new Sprite(new Texture(Gdx.files.internal("tropas/robotSelec_prueba.png")));
        robotSelec.flip(false, true);

        //Carrgamos las texturas de torres en sprites, de diferentes sentidos.
        torreF = new Sprite(new Texture(Gdx.files.internal("torres/torreFuego.png")));
        torreF.flip(true, false);

        torreF2 = new Sprite(new Texture(Gdx.files.internal("torres/torreFuego2.png")));
        torreF2.flip(true, false);

        torreFMuerta = new Sprite(new Texture(Gdx.files.internal("torres/torreFuego_muerta.png")));
        torreFMuerta.flip(true, false);

        torreF2Muerta = new Sprite(new Texture(Gdx.files.internal("torres/torreFuego2_muerta.png")));
        torreF2Muerta.flip(true, false);

        torreR = new Sprite(new Texture(Gdx.files.internal("torres/torreRayo.png")));
        torreR.flip(true, false);

        torreRMuerta = new Sprite(new Texture(Gdx.files.internal("torres/torreRayo_muerta.png")));
        torreRMuerta.flip(true, false);

        torreR2Muerta = new Sprite(new Texture(Gdx.files.internal("torres/torreRayo2_muerta.png")));
        torreR2Muerta.flip(true, false);


        //Texturas de botones, para continuar , siguiente reiniciar y salir
        textureBtnCont = new Texture(Gdx.files.internal("otros/ContinuarBtn1.png"));
        btnContinuar = new Sprite(textureBtnCont);
        btnContinuar.flip(false, true);

        textureBtnReiniciar = new Texture(Gdx.files.internal("otros/Reiniciar.png"));
        btnReiniciar = new Sprite(textureBtnReiniciar);
        btnReiniciar.flip(false, true);

        textureBtnSalir = new Texture(Gdx.files.internal("otros/Salir.png"));
        btnSalir = new Sprite(textureBtnSalir);
        btnSalir.flip(false, true);

        textureBtnSig = new Texture(Gdx.files.internal("otros/Siguiente.png"));
        btnSig = new Sprite(textureBtnSig);
        btnSig.flip(false, true);


        //Musica
        musicStart = Gdx.audio.newMusic(Gdx.files.internal("musica/start.mp3"));
        musicStart.setVolume(0.5f);
        musicStart.setLooping(true);

        musicEnd = Gdx.audio.newMusic(Gdx.files.internal("musica/end.mp3"));
        musicEnd.setVolume(0.2f);
        musicEnd.setLooping(true);


        soundAttack = Gdx.audio.newSound(Gdx.files.internal("musica/attack.mp3"));
        soundAttack.setVolume(1, 0.2f);
        soundAttack.setLooping(1, true);

        soundFireball = Gdx.audio.newSound(Gdx.files.internal("musica/fireball.wav"));
        soundFireball.setVolume(2, 0.2f);
        soundFireball.setLooping(2, true);


        soundWalk = Gdx.audio.newSound(Gdx.files.internal("musica/footstep1.mp3"));
        soundWalk.setVolume(3, 0.8f);
        soundWalk.setLooping(3, true);

        soundDead = Gdx.audio.newSound(Gdx.files.internal("musica/dead.wav"));
        soundDead.setVolume(4, 0.2f);
        soundDead.setLooping(4, true);

        soundDead1 = Gdx.audio.newSound(Gdx.files.internal("musica/dead1.wav"));
        soundDead1.setVolume(5, 0.2f);
        soundDead1.setLooping(5, true);

        soundDead2 = Gdx.audio.newSound(Gdx.files.internal("musica/dead2.wav"));
        soundDead2.setVolume(6, 0.2f);
        soundDead2.setLooping(6, true);





        //Cargamos las texturas de las tropas, ponmeos animacion de caminando o atacando.
        TextureRegion[] regionCaballeroRun = TextureRegion.split(new Texture("tropas/knight_run.png"), 587, 707)[0];
        caballeroRun = new Animation(0.10f, regionCaballeroRun);
        caballeroRun.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] regionCaballeroAtaque = TextureRegion.split(new Texture("tropas/knight_attack.png"), 587, 707)[0];
        caballeroAttack = new Animation(0.10f, regionCaballeroAtaque);
        caballeroAttack.setPlayMode(Animation.PlayMode.LOOP);

        TextureRegion[] regionNinjaRun = TextureRegion.split(new Texture("tropas/ninja_run.png"), 363, 458)[0];
        ninjaRun = new Animation(0.10f, regionNinjaRun);
        ninjaRun.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] regionNinjaAtaque = TextureRegion.split(new Texture("tropas/ninja_attack.png"), 496, 458)[0];
        ninjaAttack = new Animation(0.10f, regionNinjaAtaque);
        ninjaAttack.setPlayMode(Animation.PlayMode.LOOP);

        TextureRegion[] regionRobotRun = TextureRegion.split(new Texture("tropas/robot_run.png"), 567, 556)[0];
        robotRun = new Animation(0.12f, regionRobotRun);
        robotRun.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] regionRobotAtaque = TextureRegion.split(new Texture("tropas/robot_shoot.png"), 567, 556)[0];
        robotAttack = new Animation(0.12f, regionRobotAtaque);
        robotAttack.setPlayMode(Animation.PlayMode.LOOP);

        TextureRegion[] regionRobotBullet = TextureRegion.split(new Texture("tropas/robot_bullet.png"), 172, 139)[0];
        robotBullet = new Animation(0.12f, regionRobotBullet);
        robotBullet.setPlayMode(Animation.PlayMode.LOOP);


        TextureRegion[] regionRobotMuzzle = TextureRegion.split(new Texture("tropas/robot_muzzle.png"), 19, 241)[0];
        robotMuzzle = new Animation(0.12f, regionRobotMuzzle);
        robotMuzzle.setPlayMode(Animation.PlayMode.LOOP);

        TextureRegion[] regionfuegoTorre = TextureRegion.split(new Texture("torres/fuego_torre.png"), 192, 192)[0];
        fuegoTorre = new Animation(0.12f, regionfuegoTorre);
        fuegoTorre.setPlayMode(Animation.PlayMode.LOOP);



        //Cargar el fichero de fuentes, para aplicar.
        FileHandle fontFile = Gdx.files.internal("fonts/fuente1.fnt");
        font = new BitmapFont(fontFile, true);
        font.getData().setScale(4f);

        //Fuentes Grande
        fontGrande = new BitmapFont(fontFile, true);
        fontGrande.getData().setScale(5f);

        //Fuente Pequeño
        fontPequenia = new BitmapFont(fontFile, true);
        fontPequenia.getData().setScale(2f);

        // Creamos l'estilo de l'etiquetas
        textStyle = new Label.LabelStyle(font, null);

        textStyleTitulo = new Label.LabelStyle(fontGrande, null);

        textStylePequenio = new Label.LabelStyle(fontPequenia,null);

        //Creamos la camara de dimensiones de juego.
        camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        //Pniendo un true po parametros, configuramos la camera para que haga servir el sistema de coordenades y-Down
        camera.setToOrtho(true);

        //Inicializamos el mapa donde se juega el juego.
        tiledMap = new TmxMapLoader().load("texturas/mapa1.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);


    }

    /**
     * Methodo dispose
     */
    public static void dispose() {

        // Descrtem els recursos
        fondo.dispose();
        musicStart.dispose();
        musicEnd.dispose();
        soundAttack.dispose();
        soundFireball.dispose();

    }

}

