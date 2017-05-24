package com.proyectofinal.game.objects.towers.attack;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.proyectofinal.game.helpers.AssetManager;

/**
 * Created by ALUMNEDAM on 22/05/2017.
 */

public class Fuego extends Actor {
    private float x,y;
    public float tiempoDeEstado = 0;
    private boolean visible;

    public Fuego (float x, float y, int radio){
        this.x = x;
        this.y = y;
        visible = false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        if (visible) {
            batch.draw(AssetManager.fuegoTorre.getKeyFrame(getTiempoDeEstado()), x, y, 0, 0, 60, 60, 1f, 1f, 0);
        }
    }

    public float getTiempoDeEstado() {
        return tiempoDeEstado;
    }

    public void setTiempoDeEstado(float tiempoDeEstado) {
        this.tiempoDeEstado = tiempoDeEstado;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
