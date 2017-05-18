package com.proyectofinal.game.objects.trops;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.proyectofinal.game.objects.road.Camino;
import com.proyectofinal.game.utils.Settings;

import java.util.ArrayList;

/**
 * Created by ALUMNEDAM on 05/05/2017.
 */

public abstract class Tropas extends Actor{

    public enum Estado {
        Atacando, Caminando
    }
    private Estado estado;
    private Vector2 position;
    private int width, height;
    private Rectangle collisionRect;
    private int desviacionY, desviacionX;
    //Spacecraft space;
    public float tiempoDeEstado = 0;
    public int casillaActual = 0;
    private boolean animacionCaminar;

    public Tropas(float x, float y, int desviacionX, int desviacionY)
    {
        // Inicialitzem els arguments segons la crida del constructor
        this.width = Settings.TROPA_WIDTH;
        this.height = Settings.TROPA_HEIGHT;
        position = new Vector2(x, y);
        this.desviacionY = desviacionY;
        this.desviacionX = desviacionX;
        animacionCaminar = true;
        // Creem el rectangle de col·lisions
        collisionRect = new Rectangle();

        // Per a la gestio de hit
        setBounds(position.x, position.y, width, height);
        setTouchable(Touchable.enabled);
        estado = Estado.Caminando;

    }



    public abstract void act(float delta);

    @Override
    public abstract void draw(Batch batch, float parentAlpha);

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
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

    public void siguienteCasilla(ArrayList<Camino> camino){
        if (estado == Estado.Caminando && casillaActual < camino.size() - 1) {
            casillaActual++;
            position.x = camino.get(casillaActual).getX() + desviacionX;
            position.y = camino.get(casillaActual).getY() + desviacionY;
        }
    }

    public boolean siguienteCasillaAtaque(ArrayList<Camino> camino){

        if (camino.size() - 1 > casillaActual) {
            casillaActual++;
            position.x = camino.get(casillaActual).getX() + desviacionX;
            position.y = camino.get(casillaActual).getY() + desviacionY;
            return true;
        }else{
            return false;
        }
    }

    public void subirAAtacar(float comienzo, float fin, boolean posicionTorre){
        if (posicionTorre) {
            if(comienzo < fin) {
                position.y += 1;
            }
        }else{
            if(comienzo > fin) {
                position.y -= 1;
            }
        }
        System.out.println("sad");
    }

    public void setCollisionRect(Rectangle collisionRect) {
        this.collisionRect = collisionRect;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getCasillaActual() {
        return casillaActual;
    }

    public boolean isanimacionCaminar() {
        return animacionCaminar;
    }

    public void setanimacionCaminar(boolean animacionCaminar) {
        this.animacionCaminar = animacionCaminar;
    }
}
