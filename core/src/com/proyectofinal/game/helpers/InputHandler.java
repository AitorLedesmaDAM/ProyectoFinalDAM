package com.proyectofinal.game.helpers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.proyectofinal.game.TowerAttack;
import com.proyectofinal.game.screens.AtaqueScreen;
import com.proyectofinal.game.screens.MenuScreen;
import com.proyectofinal.game.screens.SeleccionScreen;
import com.proyectofinal.game.utils.Settings;

/**
 * Created by ALUMNEDAM on 25/04/2017.
 */

public class InputHandler implements InputProcessor {

    private TowerAttack towerAttack;
    private SeleccionScreen selecScreen;
    private MenuScreen menuScreen;
    private AtaqueScreen ataqueScreen;
    private Stage stage;
    private Vector2 stageCoord;

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

    @Override
    public boolean keyDown(int keycode) {
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
                }else{
                    selecScreen.siguientePantalla();
                }
            }
            return true;
        }else if (Settings.pantalla == 3){
            System.out.println("has pulsado algo!! :D");
            stageCoord = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
            Actor actorHit = stage.hit(stageCoord.x, stageCoord.y, true);
            if (actorHit != null) {
                String tropa = actorHit.toString();
                if (tropa.equals("Caballero2")){

                    System.out.println("Sale caballero");
                }else if (tropa.equals("Ninja2")){

                    System.out.println("Sale Ninja");
                }else if (tropa.equals("Robot2")) {

                    System.out.println("Sale Robot");
                }
            }
            return true;
        }else {
            return true;
        }
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
