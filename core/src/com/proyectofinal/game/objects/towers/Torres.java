package com.proyectofinal.game.objects.towers;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.proyectofinal.game.utils.Settings;

/**
 * Created by ALUMNEDAM on 15/05/2017.
 */

public class Torres extends Actor {
    private Vector2 position;
    private int width, height, vida, danyo;
    private Circle collisionCircle;
    public float tiempoDeEstado = 0;
    boolean orientacion, viva;
    private float radius, circuloWidth, circuloHeight;
    Vector2 posicionAtaque;
    public boolean orientacionBala;

    public Torres(float x, float y, boolean orientacion, float circuloWidth, float circuloHeight, int radio, int vida, int danyo, boolean orientacionB)
    {
        // Inicialitzem els arguments segons la crida del constructor
        this.width = Settings.TROPA_WIDTH;
        this.height = Settings.TROPA_HEIGHT;
        this.vida = vida;
        this.danyo = danyo;
        viva = true;
        position = new Vector2(x, y);
        this.orientacion = orientacion;
        // Creem el rectangle de col·lisions
        collisionCircle = new Circle();
        radius = radio;
        this.circuloWidth = circuloWidth;
        this.circuloHeight = circuloHeight;
        this.orientacionBala = orientacionB;
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
        if (viva) {
            collisionCircle.set(position.x + circuloWidth, position.y + circuloHeight, radius);
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

    public boolean isViva() {
        return viva;
    }

    public void setViva(boolean viva) {
        this.viva = viva;
    }
}

