package com.proyectofinal.game.objects.trops;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.proyectofinal.game.helpers.AssetManager;

/**
 * Created by ALUMNEDAM on 05/05/2017.
 */

public class Caballero extends Tropas {


    public Caballero(float x, float y, int desviacionX, int desviacionY, boolean visible) {
        super(x, y, desviacionX, desviacionY, visible);

    }

    @Override
    public void act(float delta) {
        setCollisionRect(new Rectangle(getPosition().x+12,getPosition().y,90,130));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(AssetManager.caballeroRun.getKeyFrame(getTiempoDeEstado()), getX(), getY(), 0, 0, 116, 140, 1f, 1f, 0);
    }
}

