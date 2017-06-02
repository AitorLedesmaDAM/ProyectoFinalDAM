package com.proyectofinal.game.objects.road;

/**
 * Created by ALUMNEDAM on 11/05/2017.
 */

public class Camino {

    //Atributos x, y;
    private float x;
    private float y;

    /**
     * Constructor de Camino
     * @param x
     * @param y
     */
    public Camino(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Methodo para obtener el X.
     * @return
     */
    public float getX() {
        return x;
    }

    /**
     * Methodo para obtener Y.
     * @return
     */
    public float getY() {
        return y;
    }

}
