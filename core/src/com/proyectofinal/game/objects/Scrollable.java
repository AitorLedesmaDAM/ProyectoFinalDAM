package com.proyectofinal.game.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by ALUMNEDAM on 05/05/2017.
 */

public class Scrollable extends Actor  {

    protected Vector2 position;
    protected float velocity;
    protected float width;
    protected float height;


    public Scrollable(float x, float y, float width, float height, float velocity) {
        //position = new Vector2(x, y);
        this.velocity = velocity;
        this.width = width;
        this.height = height;

    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }


}
