package com.proyectofinal.game.objects.towers;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.proyectofinal.game.utils.Settings;

/**
 * Created by ALUMNEDAM on 15/05/2017.
 */

public class Torres extends Actor {

    protected Vector2 position, posicionAtaque;
    private Circle collisionCircle;

    private int width = Settings.TROPA_WIDTH, height = Settings.TROPA_HEIGHT;
    private int vida, danyo;
    protected boolean orientacion, viva = true, overlaps = false;
    protected float radius, circuloWidth, circuloHeight;
    protected String tipo;

    public Torres(float x, float y, boolean orientacion, float circuloWidth, float circuloHeight, int radio, int vida, int danyo, String tipo) {
        // Inicialitzem els arguments segons la crida del constructor
        this.vida = vida;
        this.danyo = danyo;
        this.tipo = tipo;
        position = new Vector2(x, y);
        this.orientacion = orientacion;
        // Creem el rectangle de col·lisions
        collisionCircle = new Circle();
        radius = radio;
        this.circuloWidth = circuloWidth;
        this.circuloHeight = circuloHeight;

        // Per a la gestio de hit
        setBounds(position.x, position.y, width, height);
        setTouchable(Touchable.enabled);

        //Dependiendo de la orientación de la torre, la posicion donde atacaran las torres será diferente
        if (!orientacion) {
            posicionAtaque = new Vector2(position.x + circuloWidth, position.y + (circuloHeight * 2) + 25);
        } else {
            posicionAtaque = new Vector2(position.x + circuloWidth, position.y - 50);
        }
    }

    public void act(float delta) {
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

    public void setOverlaps(boolean overlaps) {
        this.overlaps = overlaps;
    }

    public String getTipo() {
        return tipo;
    }

}

