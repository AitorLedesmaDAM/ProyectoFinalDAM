package com.proyectofinal.game.objects.trops;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by ALUMNEDAM on 05/05/2017.
 */

public abstract class Tropas extends Actor {

    protected enum Estado {
        Atacando, Caminando
    }
    protected Estado estado;
    protected int velocidad;
    protected int vida;
    protected int fuerza;


    public Tropas(int velocidad, int vida, int fuerza){
        this.velocidad = velocidad;
        this.vida = vida;
        this.fuerza = fuerza;
        estado = Estado.Caminando;
    }


    public Estado getState() {

        return estado;
    }

    public void setState(Estado estado) {
        this.estado = estado;
    }

}
