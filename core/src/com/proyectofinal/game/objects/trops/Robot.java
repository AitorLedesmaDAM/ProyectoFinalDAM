package com.proyectofinal.game.objects.trops;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.proyectofinal.game.helpers.AssetManager;

/**
 * Created by ALUMNEDAM on 09/05/2017.
 */

public class Robot extends Tropas {

    public Robot(float x, float y, int desviacionX, int desviacionY) {
        super(x, y, desviacionX, desviacionY);
    }

    @Override
    public void act(float delta) {
        getCollisionRect().set(new Rectangle(getPosition().x+19,getPosition().y,90,130));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(AssetManager.robotRun.getKeyFrame(getTiempoDeEstado()), getX(), getY(), 0, 0, 142, 140, 1f, 1f, 0);
    }
}