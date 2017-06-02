package com.proyectofinal.game.helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.proyectofinal.game.screens.AtaqueScreen;
import com.proyectofinal.game.screens.FinalScreen;
import com.proyectofinal.game.screens.MenuScreen;
import com.proyectofinal.game.screens.SeleccionScreen;
import com.proyectofinal.game.utils.Settings;

/**
 * Created by ALUMNEDAM on 25/04/2017.
 */

public class InputHandler implements InputProcessor {

    private SeleccionScreen selecScreen;
    private MenuScreen menuScreen;
    private AtaqueScreen ataqueScreen;
    private FinalScreen finalScreen;
    private Stage stage;
    private Vector2 stageCoord;
    private int lvlInt;

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
        this.finalScreen = finalScreen;
        stage = finalScreen.getStage();
    }

    /**
     * Cuando se pulsa la tecla 'B' el atauqeScreen se pone en debug
     */
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.B) {
            AtaqueScreen.debug = !AtaqueScreen.debug;
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

    /**
     * Este metodo mira que boton a sido pulado de que pantalla
     * y llama a los metodos correspondientes.
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //En la pantalla 1
        if (Settings.pantalla == 1) {
            stageCoord = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
            Actor actorHit = stage.hit(stageCoord.x, stageCoord.y, true);
            if (actorHit != null) {
                String lvl = actorHit.toString().replaceAll("Label: ", "");
                //se pulsa el nivel 1
                if (lvl.equals("1")) {
                    lvlInt = Integer.parseInt(lvl.toString());
                    //se llama al metodo siguiente pantalla con los atributos para
                    //el nivel 1
                    menuScreen.siguientePantalla(lvlInt, Settings.MAX_TROPAS_LVL_1);
                    //si se pulsa el 2
                } else if (lvl.equals("2")) {
                    lvlInt = Integer.parseInt(lvl.toString());
                    //se llama al metodo siguiente pantalla con los atributos para
                    //el nivel 2
                    menuScreen.siguientePantalla(lvlInt, Settings.MAX_TROPAS_LVL_2);
                }
                //if para encender/apagar la musica
                if (lvl.equals("Music") || lvl.equals("MusicMute")) {
                    Settings.music = !Settings.music;
                    menuScreen.canviarMusica();
                }

            }
            return true;
            //En la pantalla 2
        } else if (Settings.pantalla == 2) {
            stageCoord = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
            Actor actorHit = stage.hit(stageCoord.x, stageCoord.y, true);
            if (actorHit != null) {
                String tropa = actorHit.toString();
                //si la tropa pulsada es caballero
                if (tropa.equals("Caballero")) {
                    //se llama al metodo sumarCaballero de selectScreen
                    selecScreen.modMaxTropas(Settings.TAMANYO_CABALLERO);
                    selecScreen.sumarCaballero();
                } else if (tropa.equals("Ninja")) {
                    selecScreen.modMaxTropas(Settings.TAMANYO_NINJA);
                    selecScreen.sumarNinja();
                } else if (tropa.equals("Robot")) {
                    selecScreen.modMaxTropas(Settings.TAMANYO_ROBOT);
                    selecScreen.sumarRobot();
                } else if (tropa.equals("Continuar")) {
                    AssetManager.musicStart.dispose();
                    selecScreen.siguientePantalla();
                } else {

                }
                //if para encender/apagar la musica
                if (tropa.equals("Music") || tropa.equals("MusicMute")) {
                    Settings.music = !Settings.music;
                    selecScreen.canviarMusica();
                }
            }
            return true;
            //En la pantalla 3
        } else if (Settings.pantalla == 3) {
            stageCoord = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
            Actor actorHit = stage.hit(stageCoord.x, stageCoord.y, true);
            if (actorHit != null) {
                String tropa = actorHit.toString();
                //si se pulsa en el boton de caballero
                if (tropa.equals("Caballero")) {
                    //se llama a soltarTropa de ataqueScreen
                    ataqueScreen.soltarTropa(tropa);
                } else if (tropa.equals("Ninja")) {
                    ataqueScreen.soltarTropa(tropa);
                } else if (tropa.equals("Robot")) {
                    ataqueScreen.soltarTropa(tropa);
                }

                if (actorHit.getName().equals("Music") || actorHit.getName().equals("MusicMute")) {
                    Settings.music = !Settings.music;
                    ataqueScreen.canviarMusica();
                }
            }
            return true;
            //En la pantalla 4
        } else if (Settings.pantalla == 4) {

            stageCoord = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
            Actor actorHit = stage.hit(stageCoord.x, stageCoord.y, true);
            if (actorHit != null) {
                String option = actorHit.toString();
                //si se pulsa el boton siguiente
                if (option.equals("Siguente")) {
                    //se llama al metodo botonSiguiente de finalScreen
                    finalScreen.botonSiguiente();
                    AssetManager.musicEnd.dispose();

                } else if (option.equals("Reniciar")) {
                    finalScreen.botonReiniciar();
                    AssetManager.musicEnd.dispose();

                } else if (option.equals("Salir")) {
                    finalScreen.botonSalir();
                    AssetManager.musicEnd.dispose();

                }

                if (option.equals("Music") || option.equals("MusicMute")) {
                    Settings.music = !Settings.music;
                    finalScreen.canviarMusica();
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
