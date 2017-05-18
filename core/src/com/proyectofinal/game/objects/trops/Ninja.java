package com.proyectofinal.game.objects.trops;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.proyectofinal.game.helpers.AssetManager;

/**
 * Created by ALUMNEDAM on 09/05/2017.
 */

public class Ninja extends Tropas {

    public Ninja(float x, float y, int desviacionX, int desviacionY) {
        super(x, y, desviacionX, desviacionY);
    }

    @Override
    public void act(float delta) {
        setCollisionRect(new Rectangle(getPosition().x,getPosition().y,78,90));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(AssetManager.ninjaRun.getKeyFrame(getTiempoDeEstado()), getX(), getY(), 0, 0, 77, 88, 1f, 1f, 0);
    }
}