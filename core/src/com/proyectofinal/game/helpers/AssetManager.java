package com.proyectofinal.game.helpers;

import com.badlogic.gdx.Gdx;
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

    //Textura de la imagen de fondo
    public static Texture fondo, textureBtnCont;
    public static Animation<TextureRegion> caballeroRun, caballeroAttack;

    public static Sprite caballeroSelec, ninjaSelec, robotSelec, mapa1, btnContinuar, caballeroSelecAtak,ninjaSelecAtak,robotSelecAtak;

    //Fuente
    public static BitmapFont fontGrande, font, fontPequenia;
    public static Label.LabelStyle textStyle, textStyleTitulo, textStylePequenio;

    public static TextureRegion background;

    public static OrthographicCamera camera;

    public static TiledMap tiledMap;
    public static OrthogonalTiledMapRenderer renderer;

    public static void load() {
    //    Settings.GAME_WIDTH = Gdx.graphics.getWidth();
    //    Settings.GAME_HEIGHT = Gdx.graphics.getHeight();

        // Carreguem les textures en sprites
        caballeroSelecAtak = new Sprite(new Texture(Gdx.files.internal("tropas/caballeroSelec_prueba.png")));
        caballeroSelecAtak.flip(true, false);

        ninjaSelecAtak = new Sprite(new Texture(Gdx.files.internal("tropas/ninjaSelec_prueba.png")));
        ninjaSelecAtak.flip(true, false);

        robotSelecAtak = new Sprite(new Texture(Gdx.files.internal("tropas/robotSelec_prueba.png")));
        robotSelecAtak.flip(true, false);



        fondo = new Texture(Gdx.files.internal("fondos/fondo_degradado.jpg"));
        fondo.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        background = new TextureRegion(fondo);
        background.flip(false, true);

        mapa1 = new Sprite(new Texture(Gdx.files.internal("texturas/mapa1.png")));
        mapa1.flip(false, true);

        caballeroSelec = new Sprite(new Texture(Gdx.files.internal("tropas/caballeroSelec_prueba.png")));
        caballeroSelec.flip(false, true);

        ninjaSelec = new Sprite(new Texture(Gdx.files.internal("tropas/ninjaSelec_prueba.png")));
        ninjaSelec.flip(false, true);

        robotSelec = new Sprite(new Texture(Gdx.files.internal("tropas/robotSelec_prueba.png")));
        robotSelec.flip(false, true);

        textureBtnCont = new Texture(Gdx.files.internal("otros/ContinuarBtn1.png"));
        btnContinuar = new Sprite(textureBtnCont);
        btnContinuar.flip(false, true);

        //Carreguem les textures de les tropes
        TextureRegion[] regionCaballeroRun = TextureRegion.split(new Texture("tropas/knight_run.png"), 587, 707)[0];

        //Fuente
        FileHandle fontFile = Gdx.files.internal("fonts/fuente1.fnt");
        font = new BitmapFont(fontFile, true);
        font.getData().setScale(4f);

        fontGrande = new BitmapFont(fontFile, true);
        fontGrande.getData().setScale(5f);

        fontPequenia = new BitmapFont(fontFile, true);
        fontPequenia.getData().setScale(2f);

        // Creem l'estil de l'etiqueta
        textStyle = new Label.LabelStyle(font, null);

        textStyleTitulo = new Label.LabelStyle(fontGrande, null);

        textStylePequenio = new Label.LabelStyle(fontPequenia,null);

        // Creem la càmera de les dimensions del joc
        camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        // Posant el paràmetre a true configurem la càmera per a
        // que faci servir el sistema de coordenades Y-Down
        camera.setToOrtho(true);

        //Iniacialitzem el mapa on jugarem
        tiledMap = new TmxMapLoader().load("texturas/mapa1.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);


    }

    public static void dispose() {

        // Descrtem els recursos
        fondo.dispose();
        //  explosionSound.dispose();
        //  music.dispose();

    }

}

