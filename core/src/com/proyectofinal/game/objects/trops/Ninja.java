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

public class Ninja extends Actor {

    private Vector2 position;
    private int width, height;
    private Rectangle collisionRect;
    //Spacecraft space;
    public float tiempoDeEstado = 0;

    public Ninja(float x, float y)
    {
        // Inicialitzem els arguments segons la crida del constructor
        this.width = Settings.TROPA_WIDTH;
        this.height = Settings.TROPA_HEIGHT;
        position = new Vector2(x, y);

        // Creem el rectangle de col·lisions
        collisionRect = new Rectangle();

        // Per a la gestio de hit
        setBounds(position.x, position.y, width, height);
        setTouchable(Touchable.enabled);

    }
    public void act(float delta)
    {
        this.position.x += 90*delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.ninjaRun.getKeyFrame(getTiempoDeEstado()), getX(), getY(), 0, 0, 110, 120, 1f, 1f, 0);
    }

    public float getX() {
        return position.x;
    }

    public Rectangle getCollisionRect() {
        return collisionRect;
    }

    public float getTiempoDeEstado() {
        return tiempoDeEstado;
    }

    public void setTiempoDeEstado(float tiempoDeEstado) {
        this.tiempoDeEstado = tiempoDeEstado;
    }
}