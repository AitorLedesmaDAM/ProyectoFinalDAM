package com.proyectofinal.game.objects.towers.attack;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.proyectofinal.game.helpers.AssetManager;

/**
 * Created by ALUMNEDAM on 25/05/2017.
 */

public class Rayo extends Actor {

    //Atributos
    private float x,y;
    public float tiempoDeEstado = 0;
    private boolean visible;
    private float radio;

    /**
     * Constructor, se inicializa las variables.
     * @param x
     * @param y
     * @param radio
     */
    public Rayo (float x, float y, float radio){
        this.x = x + 25;
        this.y = y;
        this.radio = radio * 2;
    }

    /**
     * Methodo override de draw, para dibujar animacion de ray al torre.
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        if (visible) {
            batch.draw(AssetManager.rayoTorre.getKeyFrame(getTiempoDeEstado()), x, y, 0, 0, radio, radio, 1f, 1f, 0);
        }
    }

    /**
     * Methodo para obtener el tiempo de estado.
     * @return
     */
    public float getTiempoDeEstado() {
        return tiempoDeEstado;
    }

    /**
     * Methodo ser para poner el tiempo de estado.
     * @param tiempoDeEstado
     */
    public void setTiempoDeEstado(float tiempoDeEstado) {
        this.tiempoDeEstado = tiempoDeEstado;
    }


    //Methodos overrides
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