package com.proyectofinal.game.objects.towers;

/**
 * Created by ALUMNEDAM on 05/05/2017.
 */

public class Torre_Fuego extends Torres {


    public Torre_Fuego(float x, float y, boolean orientacion, float circuloWidth, float circuloHeight, String tipo) {
        super(x, y, orientacion, circuloWidth, circuloHeight, tipo);
        setVida(250);
        setDanyo(1);
    }
}
