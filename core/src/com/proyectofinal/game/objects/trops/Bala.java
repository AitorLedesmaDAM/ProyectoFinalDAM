package com.proyectofinal.game.objects.trops;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.utils.Settings;

/**
 * Created by ALUMNEDAM on 25/05/2017.
 */

public class Bala extends Actor {

    private Vector2 camino, destino, origen;
    public static Vector2 position;
    private float angle, tiempoDeEstado = 1;

    public Bala(Vector2 origen, Vector2 destino){
        this.origen = origen;
        this.destino = destino;
        angle = (float) Math.atan2(origen.y - destino.y, origen.x - destino.x) * 120;
        position = new Vector2(origen.x + 25, origen.y + 25);
        camino = new Vector2(destino.x - origen.x, destino.y - origen.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.robotBullet.getKeyFrame(getTiempoDeEstado()), position.x, position.y, 0, 0, 50, 50, 1f, 1f, angle);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (origen.x < destino.x) {
            if (position.x < destino.x) {
                setTiempoDeEstado(getTiempoDeEstado() + delta);
                position.x += (camino.x * delta) / Settings.VELOCIDAD_BALA;
                position.y += (camino.y * delta) / Settings.VELOCIDAD_BALA;
            } else {
                this.remove();
            }
        }else{
            if (position.x > destino.x) {
                setTiempoDeEstado(getTiempoDeEstado() + delta);
                position.x += (camino.x * delta) / Settings.VELOCIDAD_BALA;
                position.y += (camino.y * delta) / Settings.VELOCIDAD_BALA;
            } else {
                this.remove();
            }
        }
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
