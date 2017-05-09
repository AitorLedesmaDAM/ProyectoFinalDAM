package com.proyectofinal.game.objects.trops;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.proyectofinal.game.utils.Settings;

/**
 * Created by ALUMNEDAM on 05/05/2017.
 */

public class Caballero extends Actor {


    public final Vector2 position = new Vector2();

    private Rectangle collisionRect;    //El rectangulo que ocupara el laser.


    protected enum Estado {
        Atacando, Caminando
    }
    protected Estado estado;
    protected int velocidad;
    protected int vida;
    protected int fuerza;
    private int width, height;


    public Caballero(int velocidad, int vida, int fuerza){
        this.velocidad = velocidad;
        this.vida = vida;
        this.fuerza = fuerza;
        estado = Estado.Caminando;
        width = Settings.TROPA_WIDTH;
        height = Settings.TROPA_HEIGHT;

        collisionRect = new Rectangle();
    }

    public void draw(){

    }
    public void update(float delta){
        collisionRect.set(position.x, position.y, width, height);
    }



    public Estado getState() {

        return estado;
    }

    public void setState(Estado estado) {
        this.estado = estado;
    }


}
