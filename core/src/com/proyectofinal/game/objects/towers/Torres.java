package com.proyectofinal.game.objects.towers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.utils.Settings;

/**
 * Created by ALUMNEDAM on 15/05/2017.
 */

public abstract class Torres extends Actor {
    private Vector2 position;
    private int width, height;
    private Circle collisionCircle;
    //Spacecraft space;
    public float tiempoDeEstado = 0;
    boolean orientacion;
    private float radius, circuloWidth, circuloHeight;

    public Torres(float x, float y, boolean orientacion, float circuloWidth, float circuloHeight)
    {
        // Inicialitzem els arguments segons la crida del constructor
        this.width = Settings.TROPA_WIDTH;
        this.height = Settings.TROPA_HEIGHT;
        position = new Vector2(x, y);
        this.orientacion = orientacion;
        // Creem el rectangle de col·lisions
        collisionCircle = new Circle();
        radius = 250;
        this.circuloWidth = circuloWidth;
        this.circuloHeight = circuloHeight;

        // Per a la gestio de hit
        setBounds(position.x, position.y, width, height);
        setTouchable(Touchable.enabled);

    }

    public void act(float delta)
    {
        // this.position.x += 60*delta;
        collisionCircle.set(position.x + circuloWidth,position.y + circuloHeight,250);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        if(orientacion == true) {
            batch.draw(AssetManager.torreF, getX(), getY());
        }else{
            batch.draw(AssetManager.torreF2,getX(),getY());
        }
    }

    public float getX() {
        //System.out.println("x clase torre fuego "+position.x);
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public Circle getCollisionCircle() {
        return collisionCircle;
    }

    public float getTiempoDeEstado() {
        return tiempoDeEstado;
    }

    public void setTiempoDeEstado(float tiempoDeEstado) {
        this.tiempoDeEstado = tiempoDeEstado;
    }

    public float getRadius() {
        return radius;
    }

    public float getCirculoHeight() {
        return circuloHeight;
    }

    public float getCirculoWidth() {
        return circuloWidth;
    }
}

