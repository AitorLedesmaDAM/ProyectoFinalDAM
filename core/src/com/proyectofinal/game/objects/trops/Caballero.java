package com.proyectofinal.game.objects.trops;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.utils.Settings;

/**
 * Created by ALUMNEDAM on 05/05/2017.
 */

public class Caballero{


    public final Vector2 position = new Vector2();
    public final  Vector2 velocity = new Vector2();

    public boolean avanzaDerecha = true;
    public float tiempoDeEstado = 0;

    private Rectangle collisionRect;    //El rectangulo que ocupara el laser.


    protected enum Estado {
        Atacando, Caminando
    }
    protected Estado estado;
    protected float velocidad;
    protected int vida;
    protected int fuerza;
    private int width, height;


    public Caballero(float velocidad, int vida, int fuerza){
        this.velocidad = velocidad;
        this.vida = vida;
        this.fuerza = fuerza;
        estado = Estado.Caminando;
        width = Settings.TROPA_WIDTH;
        height = Settings.TROPA_HEIGHT;

        collisionRect = new Rectangle();
    }

    public void derecha(){
        velocity.x = velocidad;
        velocity.y = 0;
        setEstado(Estado.Caminando);
        avanzaDerecha = true;
    }

    public void arriba(){
        velocity.x = 0;
        velocity.y = -velocidad;
        setEstado(Estado.Caminando);
        avanzaDerecha = true;
    }

    public void abajo(){
        velocity.x = 0;
        velocity.y = velocidad;
        setEstado(Estado.Caminando);
        avanzaDerecha = true;
    }

    public void atacando(){
        velocity.set(0,0);
        setEstado(Estado.Atacando);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public float getTiempoDeEstado() {
        return tiempoDeEstado;
    }

    public void setTiempoDeEstado(float tiempoDeEstado) {
        this.tiempoDeEstado = tiempoDeEstado;
    }
}
