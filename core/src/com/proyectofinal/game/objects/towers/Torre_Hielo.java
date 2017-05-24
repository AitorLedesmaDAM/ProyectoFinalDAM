package com.proyectofinal.game.objects.towers;

/**
 * Created by ALUMNEDAM on 24/05/2017.
 */

public class Torre_Hielo extends Torres {


    public Torre_Hielo(float x, float y, boolean orientacion, float circuloWidth, float circuloHeight, String tipo) {
        super(x, y, orientacion, circuloWidth, circuloHeight, tipo);
        setVida(350);
        setDanyo(2);
    }
}