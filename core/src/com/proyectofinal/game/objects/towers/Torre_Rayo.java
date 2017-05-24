package com.proyectofinal.game.objects.towers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.proyectofinal.game.helpers.AssetManager;

/**
 * Created by ALUMNEDAM on 24/05/2017.
 */

public class Torre_Rayo extends Torres {


    public Torre_Rayo(float x, float y, boolean orientacion, float circuloWidth, float circuloHeight, int radio, int vida, int danyo) {
        super(x, y, orientacion, circuloWidth, circuloHeight, radio, vida, danyo);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (orientacion) {
            if (viva) {
                batch.draw(AssetManager.torreR, getX(), getY());
            }else{
                batch.draw(AssetManager.torreRMuerta, getX(), getY());
            }
        } else {
            if (viva) {
                batch.draw(AssetManager.torreR2, getX(), getY());
            }else{
                batch.draw(AssetManager.torreR2Muerta, getX(), getY());
            }
        }
    }
}