package com.proyectofinal.game.objects.trops;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.proyectofinal.game.helpers.AssetManager;

/**
 * Created by ALUMNEDAM on 25/05/2017.
 */

public class Bala extends Actor {

    private Vector2 origen, destino, camino;
    public static Vector2 position;
    private float angle, tiempoDeEstado = 1;

    public Bala(Vector2 origen, Vector2 destino){
        angle = (float) Math.atan2(origen.y - destino.y, origen.x - destino.y);
        position = new Vector2(origen.x + 100, origen.y);
        camino = new Vector2(destino.x - origen.x, destino.y - origen.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.robotBullet.getKeyFrame(getTiempoDeEstado()), position.x, position.y, 0, 0, 142, 140, 1f, 1f, angle);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //position.x += camino.x * delta;
        //position.y += camino.y * delta;
    }

    public float getAngle() {
        return angle;
    }

    public float getTiempoDeEstado() {
        return tiempoDeEstado;
    }

    public void setTiempoDeEstado(float tiempoDeEstado) {
        this.tiempoDeEstado = tiempoDeEstado;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getCamino() {
        return camino;
    }
}
