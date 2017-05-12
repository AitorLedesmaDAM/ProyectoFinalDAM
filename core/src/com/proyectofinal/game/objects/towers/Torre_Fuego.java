package com.proyectofinal.game.objects.towers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.utils.Settings;

/**
 * Created by ALUMNEDAM on 05/05/2017.
 */

public class Torre_Fuego extends Actor {

    private Vector2 position;
    private int width, height;
    private Circle collisionCircle;
    //Spacecraft space;
    public float tiempoDeEstado = 0;
    boolean orientacion;

    public Torre_Fuego(float x, float y, boolean orientacion)
    {
        // Inicialitzem els arguments segons la crida del constructor
        this.width = Settings.TROPA_WIDTH;
        this.height = Settings.TROPA_HEIGHT;
        position = new Vector2(x, y);
    this.orientacion = orientacion;
        // Creem el rectangle de col·lisions
        collisionCircle = new Circle();

        collisionCircle.set(position.x + width / 2.0f, position.y + width / 2.0f, width / 2.0f);
        // Per a la gestio de hit
        setBounds(position.x, position.y, width, height);
        setTouchable(Touchable.enabled);

    }
  /*  public void act(float delta)
    {
       // this.position.x += 60*delta;
         }*/

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        if(orientacion == true) {
            batch.draw(AssetManager.torreF, getX(), getY()-35);
        }else{
            batch.draw(AssetManager.torreF2,getX(),getY());
        }
        }

    public float getX() {
        return position.x;
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
}

