package com.proyectofinal.game.objects.trops.attack;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.utils.Settings;

/**
 * Created by ALUMNEDAM on 25/05/2017.
 */

public class Bala extends Actor {

    private boolean destruida = false;

    private Vector2 camino, destino, origen, position;
    private float angle, tiempoDeEstado = 1;

    public Bala(Vector2 origen, Vector2 destino) {
        this.origen = origen;
        this.destino = destino;
        angle = (float) Math.atan2(origen.y - destino.y, origen.x - destino.x) * 120;
        position = new Vector2(origen.x, origen.y);
        camino = new Vector2(destino.x - origen.x, destino.y - origen.y);
        if (Settings.music) {
            AssetManager.soundShoot.play();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.robotBullet.getKeyFrame(getTiempoDeEstado()), position.x, position.y, 0, 0, 50, 50, 1f, 1f, angle);

    }

    /**
     * Se ejecuta el movimiento de la bala
     *
     * @param delta
     */
    @Override
    public void act(float delta) {
        super.act(delta);

        setTiempoDeEstado(getTiempoDeEstado() + delta);
        if (origen.x < destino.x) {     //Si la coordenada X es mayor del origen que del destino...
            if (position.x < destino.x) {
                position.x += camino.x * (Settings.VELOCIDAD_BALA / 60);
                position.y += camino.y * (Settings.VELOCIDAD_BALA / 60);
            } else {
                this.remove();  //Borra el objeto del stage
                this.setDestruida(true);
            }
        } else {
            if (position.x > destino.x) {
                position.x += (camino.x * delta) / (Settings.VELOCIDAD_BALA / 60);
                position.y += (camino.y * delta) / (Settings.VELOCIDAD_BALA / 60);
            } else {
                this.remove();
                this.setDestruida(true);
            }
        }
    }

    public float getTiempoDeEstado() {
        return tiempoDeEstado;
    }

    public void setTiempoDeEstado(float tiempoDeEstado) {
        this.tiempoDeEstado = tiempoDeEstado;
    }

    public boolean isDestruida() {
        return destruida;
    }

    public void setDestruida(boolean destruida) {
        this.destruida = destruida;
    }
}
