package com.proyectofinal.game.objects.towers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.objects.towers.attack.Rayo;

/**
 * Created by ALUMNEDAM on 24/05/2017.
 */

public class Torre_Rayo extends Torres {

    private Rayo rayo;

    public Torre_Rayo(float x, float y, boolean orientacion, float circuloWidth, float circuloHeight, int radio, int vida, int danyo, String tipo) {
        super(x, y, orientacion, circuloWidth, circuloHeight, radio, vida, danyo, tipo);

        rayo = new Rayo(x - radio + circuloWidth, y - radio + circuloHeight, radio);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (orientacion) {  //Dependiendo de la orientación y de si esta viva, se mostrara una imagen o no
            if (viva) {
                batch.draw(AssetManager.torreR, getX(), getY());
                if (overlaps) { //Si hay tropas colisionadas se mostraran los rayos o no
                    rayo.setVisible(true);
                } else {
                    rayo.setVisible(false);
                }
            } else {
                batch.draw(AssetManager.torreRMuerta, getX(), getY());
                rayo.setVisible(false);
            }
        } else {
            if (viva) {
                batch.draw(AssetManager.torreR2, getX(), getY());
                if (overlaps) {
                    rayo.setVisible(true);
                } else {
                    rayo.setVisible(false);
                }
            } else {
                batch.draw(AssetManager.torreR2Muerta, getX(), getY());
                rayo.setVisible(false);
            }
        }
        rayo.draw(batch, parentAlpha);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        rayo.setTiempoDeEstado(rayo.getTiempoDeEstado() + (delta / 2));
    }
}