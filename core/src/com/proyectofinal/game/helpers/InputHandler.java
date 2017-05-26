package com.proyectofinal.game.helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.proyectofinal.game.TowerAttack;
import com.proyectofinal.game.objects.trops.Robot;
import com.proyectofinal.game.screens.AtaqueScreen;
import com.proyectofinal.game.screens.FinalScreen;
import com.proyectofinal.game.screens.MenuScreen;
import com.proyectofinal.game.screens.SeleccionScreen;
import com.proyectofinal.game.utils.Musica;
import com.proyectofinal.game.utils.Preferences;
import com.proyectofinal.game.utils.Settings;

/**
 * Created by ALUMNEDAM on 25/04/2017.
 */

public class InputHandler implements InputProcessor {

    private TowerAttack towerAttack;
    private SeleccionScreen selecScreen;
    private MenuScreen menuScreen;
    private AtaqueScreen ataqueScreen;
    private FinalScreen finalScreen;
    private Stage stage;
    private Vector2 stageCoord;
    private Container containerMusic, containerMusicMute;

    Musica m = new Musica();

    public InputHandler(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
        stage = menuScreen.getStage();
    }

    public InputHandler(SeleccionScreen selecScreen) {
        this.selecScreen = selecScreen;
        stage = selecScreen.getStage();
    }

    public InputHandler(AtaqueScreen ataqueScreen) {
        this.ataqueScreen = ataqueScreen;
        stage = ataqueScreen.getStage();
    }

    public InputHandler(FinalScreen finalScreen) {
        this.ataqueScreen = ataqueScreen;
        stage = ataqueScreen.getStage();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.B) {
            AtaqueScreen.debug = !AtaqueScreen.debug;
        }

        if (keycode == Input.Keys.C) {

            Robot.attack = !Robot.attack;
            System.out.println("C: pulsado");

        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (Settings.pantalla == 1){
            stageCoord = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
            Actor actorHit = stage.hit(stageCoord.x, stageCoord.y, true);
            if (actorHit != null) {
                String lvl = actorHit.toString().replaceAll("Label: ", "");
                if (lvl.equals("1")){
                    menuScreen.siguientePantalla(lvl, 50);
                }
                if (lvl.equals("Music") || lvl.equals("MusicMute")){
                    menuScreen.canviarMusica();
                }



        /**        String musica = actorHit.toString();

                if (Settings.music && musica.equals("Music")) {


                    AssetManager.musicStart.dispose();
                    Image musicIcono = new Image(AssetManager.musicMute);   //Selección de musica
                    musicIcono.setName("MusicMute");
                    containerMusicMute = new Container(musicIcono);
                    containerMusicMute.setTransform(true);
                    containerMusicMute.center();
                    containerMusicMute.setSize(Settings.MUSICICONO_WIDTH, Settings.MUSICICONO_HEIGHT);
                    containerMusicMute.setPosition(Settings.MUSICICONO_WIDTH - 50, 20);
                    stage.addActor(containerMusicMute);
                    Settings.music = !Settings.music;

                }
                if (Settings.music==false && musica.equals("MusicMute")){

                    stage.addAction(Actions.removeActor(containerMusicMute));
                    AssetManager.musicStart.play();
                    Image musicIcono = new Image(AssetManager.musicIcono);   //Selección de musica
                    musicIcono.setName("Music");
                    containerMusic = new Container(musicIcono);
                    containerMusic.setTransform(true);
                    containerMusic.center();
                    containerMusic.setSize(Settings.MUSICICONO_WIDTH, Settings.MUSICICONO_HEIGHT);
                    containerMusic.setPosition(Settings.MUSICICONO_WIDTH - 50, 20);
                    stage.addActor(containerMusic);
                    Settings.music = !Settings.music;
                    stage.addAction(Actions.removeActor(containerMusicMute));

                }

*/
            }
            return true;
        } else if (Settings.pantalla == 2) {
            stageCoord = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
            Actor actorHit = stage.hit(stageCoord.x, stageCoord.y, true);
            if (actorHit != null) {
                String tropa = actorHit.toString();
                if (tropa.equals("Caballero")){
                    selecScreen.modMaxTropas(1);
                    selecScreen.sumarCaballero(1);
                }else if (tropa.equals("Ninja")){
                    selecScreen.modMaxTropas(2);
                    selecScreen.sumarNinja(1);
                }else if (tropa.equals("Robot")){
                    selecScreen.modMaxTropas(3);
                    selecScreen.sumarRobot(1);
                }else if (tropa.equals("Continuar")){
                    AssetManager.musicStart.dispose();
                    selecScreen.siguientePantalla();
                }else{

                }

/**
                String musica = actorHit.toString();

                if (Settings.music && musica.equals("Music")) {



                    AssetManager.musicStart.dispose();
                    Image musicIcono = new Image(AssetManager.musicMute);   //Selección de musica
                    musicIcono.setName("MusicMute");
                    containerMusicMute = new Container(musicIcono);
                    containerMusicMute.setTransform(true);
                    containerMusicMute.center();
                    containerMusicMute.setSize(Settings.MUSICICONO_WIDTH, Settings.MUSICICONO_HEIGHT);
                    containerMusicMute.setPosition(Settings.MUSICICONO_WIDTH - 50, 20);
                    stage.addActor(containerMusicMute);
                    Settings.music = !Settings.music;


                }else if (Settings.music==false && musica.equals("MusicMute")){

                    stage.addAction(Actions.removeActor(containerMusicMute));
                    AssetManager.musicStart.play();
                    Image musicIcono = new Image(AssetManager.musicIcono);   //Selección de musica
                    musicIcono.setName("Music");
                    containerMusic = new Container(musicIcono);
                    containerMusic.setTransform(true);
                    containerMusic.center();
                    containerMusic.setSize(Settings.MUSICICONO_WIDTH, Settings.MUSICICONO_HEIGHT);
                    containerMusic.setPosition(Settings.MUSICICONO_WIDTH - 50, 20);
                    stage.addActor(containerMusic);
                    Settings.music = !Settings.music;

                }

*/






            }
            return true;
        }else if (Settings.pantalla == 3){
            stageCoord = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
            Actor actorHit = stage.hit(stageCoord.x, stageCoord.y, true);
            if (actorHit != null) {
                String tropa = actorHit.toString();
                if (tropa.equals("Caballero")){
                    ataqueScreen.soltarTropa(tropa);
                }else if (tropa.equals("Ninja")){
                    ataqueScreen.soltarTropa(tropa);
                }else if (tropa.equals("Robot")) {
                    ataqueScreen.soltarTropa(tropa);
                }
            }

            return true;
        }else if (Settings.pantalla == 4) {

            stageCoord = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
            Actor actorHit = stage.hit(stageCoord.x, stageCoord.y, true);
            if (actorHit != null) {
                String opt = actorHit.toString();
                if (opt.equals("Siguente")) {

                } else if (opt.equals("Reniciar")) {

                } else if (opt.equals("Salir")) {


                }
            }


        }
            return true;


    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
