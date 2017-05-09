package com.proyectofinal.game.objects;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.proyectofinal.game.helpers.AssetManager;

/**
 * Created by ALUMNEDAM on 09/05/2017.
 */

public class Nivel {

    public static void getRoad (int startX, int startY, int endX, int endY, Array<Rectangle> tiles, Pool<Rectangle> rectPool) {
        TiledMapTileLayer irrompibles = (TiledMapTileLayer) AssetManager.tiledMap.getLayers().get("CapaAndar");
        rectPool.freeAll(tiles);
        tiles.clear();
        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                TiledMapTileLayer.Cell cellIrrompibles = irrompibles.getCell(x, y);
                if (cellIrrompibles != null) {
                    Rectangle rect = rectPool.obtain();
                    rect.set(x, y, 1, 1);
                    tiles.add(rect);
                }
            }
        }
    }
}
