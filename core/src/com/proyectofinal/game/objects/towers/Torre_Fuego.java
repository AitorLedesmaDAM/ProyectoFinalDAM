package com.proyectofinal.game.objects.towers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.proyectofinal.game.helpers.AssetManager;

/**
 * Created by ALUMNEDAM on 05/05/2017.
 */

public class Torre_Fuego extends Torres {


    public Torre_Fuego(float x, float y, boolean orientacion, float circuloWidth, float circuloHeight, int radio, int vida, int danyo) {
        super(x, y, orientacion, circuloWidth, circuloHeight, radio, vida, danyo);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(orientacion) {
            if (viva) {
                batch.draw(AssetManager.torreF, getX(), getY());
            }else{
                batch.draw(AssetManager.torreFMuerta, getX(), getY());
            }
        }else{
            if (viva){
                batch.draw(AssetManager.torreF2,getX(),getY());
            }else{
                batch.draw(AssetManager.torreF2Muerta,getX(),getY());
            }

        }
    }
}
