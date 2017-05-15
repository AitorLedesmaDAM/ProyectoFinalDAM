package com.proyectofinal.game.objects.trops;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.utils.Settings;

/**
 * Created by ALUMNEDAM on 09/05/2017.
 */

public class Robot extends Tropas {

    public Robot(float x, float y, int desviacionX, int desviacionY, boolean visible) {
        super(x, y, desviacionX, desviacionY, visible);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(AssetManager.robotRun.getKeyFrame(getTiempoDeEstado()), getX(), getY(), 0, 0, 142, 140, 1f, 1f, 0);
    }
}