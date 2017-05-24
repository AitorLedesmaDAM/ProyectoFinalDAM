package com.proyectofinal.game.objects.towers;

/**
 * Created by ALUMNEDAM on 24/05/2017.
 */

public class Torre_Rayo extends Torres {


    public Torre_Rayo(float x, float y, boolean orientacion, float circuloWidth, float circuloHeight, String tipo) {
        super(x, y, orientacion, circuloWidth, circuloHeight, tipo);
        setVida(350);
        setDanyo(2);
    }
}