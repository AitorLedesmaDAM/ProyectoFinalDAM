package com.proyectofinal.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.proyectofinal.game.screens.MenuScreen;
import com.proyectofinal.game.screens.SeleccionScreen;
import com.proyectofinal.game.utils.Settings;

/**
 * Created by ALUMNEDAM on 25/04/2017.
 */

public class InputHandler implements InputProcessor {

    private SeleccionScreen selecScreen;
    private MenuScreen menuScreen;
    private Stage stage;
    private Vector2 stageCoord;

    public InputHandler(SeleccionScreen selecScreen) {
        this.selecScreen = selecScreen;
    }

    public InputHandler(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
        stage = menuScreen.getStage();
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
        if (Settings.pantalla == 2) {
            selecScreen.modMaxTropas();
            return true;
        } else if (Settings.pantalla == 1){
            stageCoord = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
            Actor actorHit = stage.hit(stageCoord.x, stageCoord.y, true);
            if (actorHit != null) {
                Gdx.app.log("HIT", actorHit.getName()); //.toString().replaceAll("Label: ", "")
            }
        return true;
        }else{
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
