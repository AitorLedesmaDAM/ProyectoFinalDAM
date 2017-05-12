package com.proyectofinal.game.objects;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.objects.road.Camino;

import java.util.ArrayList;

/**
 * Created by ALUMNEDAM on 09/05/2017.
 */

public class Nivel {



    public void recojerCamino(ArrayList<Camino> posiciones){
        MapObjects objects = AssetManager.tiledMap.getLayers().get("CaminoObjetos").getObjects();

        for (int i = 0; i < objects.getCount(); i++) {
            RectangleMapObject rmo = (RectangleMapObject) objects.get(i);
            Rectangle rect = rmo.getRectangle();


            //if (i > 0) {
              //  posiciones.add(new Camino(posiciones.get(i - 1).getX() + rect.getX() / 2, (posiciones.get(i - 1).getY() + rect.getY()) / 2));
            //}
            posiciones.add(new Camino(rect.getX(), rect.getY()));

        }
    }
}
