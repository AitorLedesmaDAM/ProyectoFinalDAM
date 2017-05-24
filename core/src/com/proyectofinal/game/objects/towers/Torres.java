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
    private int width, height, vida, danyo;
    private Circle collisionCircle;
    public float tiempoDeEstado = 0;
    boolean orientacion;
    private float radius, circuloWidth, circuloHeight;
    Vector2 posicionAtaque;
    public String tipo;

    public Torres(float x, float y, boolean orientacion, float circuloWidth, float circuloHeight, String tipo)
    {
        // Inicialitzem els arguments segons la crida del constructor
        this.width = Settings.TROPA_WIDTH;
        this.height = Settings.TROPA_HEIGHT;
        position = new Vector2(x, y);
        this.orientacion = orientacion;
        this.tipo = tipo;
        // Creem el rectangle de col·lisions
        collisionCircle = new Circle();
        radius = 250;
        this.circuloWidth = circuloWidth;
        this.circuloHeight = circuloHeight;

        // Per a la gestio de hit
        setBounds(position.x, position.y, width, height);
        setTouchable(Touchable.enabled);

        if(!orientacion){
            posicionAtaque = new Vector2(position.x + circuloWidth, position.y + (circuloHeight*2) + 25);
        }else{
            posicionAtaque = new Vector2(position.x + circuloWidth, position.y - 50);
        }
    }



    public void act(float delta)
    {
        collisionCircle.set(position.x + circuloWidth,position.y + circuloHeight,250);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        if(tipo.equals("Hielo")){

            if(orientacion) {
                batch.draw(AssetManager.torreR, getX(), getY());
            }else{
                batch.draw(AssetManager.torreR2,getX(),getY());
            }

        }else{
            if(orientacion) {
                batch.draw(AssetManager.torreF, getX(), getY());
            }else{
                batch.draw(AssetManager.torreF2,getX(),getY());
            }
        }


    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public Circle getCollisionCircle() {
        return collisionCircle;
    }

    public Vector2 getPosicionAtaque() {
        return posicionAtaque;
    }

    public boolean isOrientacion() {
        return orientacion;
    }

    public int getDanyo() {
        return danyo;
    }

    public void setDanyo(int danyo) {
        this.danyo = danyo;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}

