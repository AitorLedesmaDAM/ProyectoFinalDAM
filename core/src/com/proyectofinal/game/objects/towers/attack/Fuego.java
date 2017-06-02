package com.proyectofinal.game.objects.towers.attack;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.proyectofinal.game.helpers.AssetManager;

/**
 * Created by ALUMNEDAM on 22/05/2017.
 */

public class Fuego extends Actor {

    //Atributos
    private float x,y;
    public float tiempoDeEstado = 0;
    private boolean visible;
    private float radio;
    private float curva;

    /**
     * Constructor de clase fuego, inicialitza atributos.
     * @param x
     * @param y
     * @param radio
     */
    public Fuego (float x, float y, int radio){
        this.x = x - 100;
        this.y = y - 100;
        this.radio =  radio - (radio / 3);
        curva = this.radio - (this.radio / 3);
        visible = false;
    }


    /**
     * Methodo override de draw. Por parametros le pasamos el batch y parentAlpha
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha); //llama a clase padre, y por parametros le pasamos el batch y parentAlpha
        //si el booleano de visible es true, dibuja fuego de torre.
        if (visible) {
            batch.draw(AssetManager.fuegoTorre.getKeyFrame(getTiempoDeEstado()), x, y + radio, 0, 0, 300, 300, 1f, 1f, 0);
            batch.draw(AssetManager.fuegoTorre.getKeyFrame(getTiempoDeEstado()), x + radio, y, 0, 0, 300, 300, 1f, 1f, 0);
            batch.draw(AssetManager.fuegoTorre.getKeyFrame(getTiempoDeEstado()), x, y - radio, 0, 0, 300, 300, 1f, 1f, 0);
            batch.draw(AssetManager.fuegoTorre.getKeyFrame(getTiempoDeEstado()), x - radio, y, 0, 0, 300, 300, 1f, 1f, 0);
            batch.draw(AssetManager.fuegoTorre.getKeyFrame(getTiempoDeEstado()), x + curva, y + curva, 0, 0, 300, 300, 1f, 1f, 0);
            batch.draw(AssetManager.fuegoTorre.getKeyFrame(getTiempoDeEstado()), x + curva, y - curva, 0, 0, 300, 300, 1f, 1f, 0);
            batch.draw(AssetManager.fuegoTorre.getKeyFrame(getTiempoDeEstado()), x - curva, y + curva, 0, 0, 300, 300, 1f, 1f, 0);
            batch.draw(AssetManager.fuegoTorre.getKeyFrame(getTiempoDeEstado()), x - curva, y - curva, 0, 0, 300, 300, 1f, 1f, 0);
        }
    }

    /**
     * Methodo que devuelve el tirmpo de estado
     * @return
     */
    public float getTiempoDeEstado() {
        return tiempoDeEstado;
    }

    /**
     * Methodo set para poner el tiempo de estado.
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
