package com.proyectofinal.game.objects;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.objects.road.Camino;
import com.proyectofinal.game.objects.towers.Torre_Fuego;
import com.proyectofinal.game.objects.towers.Torres;

import java.util.ArrayList;

/**
 * Created by ALUMNEDAM on 09/05/2017.
 */

public class Nivel {

    public Nivel() {
    }

    public ArrayList<Torre_Fuego> recojerTorres(){
        ArrayList<Torre_Fuego> torres = new ArrayList<Torre_Fuego>();

        int pos = 0;
        MapObjects objectsT = AssetManager.tiledMap.getLayers().get("TorresObjetos").getObjects();

        for (int i = 0; i < objectsT.getCount(); i++) {
            RectangleMapObject rmo = (RectangleMapObject) objectsT.get(i);
            Rectangle rect = rmo.getRectangle();
            pos++;
            boolean orientacion = AssetManager.tiledMap.getLayers().get("TorresObjetos").getObjects().get("torre"+pos).getProperties().containsKey("cara");
            System.out.println(orientacion);
            torres.add(new Torre_Fuego(rect.getX(), rect.getY(),orientacion , rect.getWidth()/2, rect.getHeight()/2));
        }
        return torres;
    }

    public ArrayList<Camino> recojerCamino() {

        ArrayList<Camino> camino = new ArrayList<Camino>();

        MapObjects objects = AssetManager.tiledMap.getLayers().get("CaminoObjetos").getObjects();
        Camino camMitad, camDoble, camMitadMitad, caminoMitadMitadMitad, caminoMitadMitadDoble, caminoDobleMitad, caminoDobleDoble;

        for (int i = 0; i < objects.getCount(); i++) {

            RectangleMapObject rmo = (RectangleMapObject) objects.get(i);
            Rectangle rect = rmo.getRectangle();

            if (i > 0) {

                camMitad = new Camino((camino.get(camino.size() - 1).getX() + rect.getX()) / 2, (camino.get(camino.size() - 1).getY() + rect.getY()) / 2);
                camMitadMitad = new Camino((camino.get(camino.size() - 1).getX() + camMitad.getX()) / 2, (camino.get(camino.size() - 1).getY() + camMitad.getY()) / 2);
                camDoble = new Camino((camMitad.getX() + rect.getX()) / 2, (camMitad.getY() + rect.getY()) / 2);

                caminoMitadMitadMitad = new Camino((camino.get(camino.size() - 1).getX() + camMitadMitad.getX()) / 2, (camino.get(camino.size() - 1).getY() + camMitadMitad.getY()) / 2);
                caminoMitadMitadDoble = new Camino((camMitad.getX() + camMitadMitad.getX()) / 2, (camMitad.getY() + camMitadMitad.getY()) / 2);
                caminoDobleMitad = new Camino((camMitad.getX() + camDoble.getX()) / 2, (camMitad.getY() + camDoble.getY()) / 2);
                caminoDobleDoble = new Camino((camDoble.getX() + rect.getX()) / 2, (camDoble.getY() + rect.getY()) / 2);

                camino.add(caminoMitadMitadMitad);
                camino.add(camMitadMitad);
                camino.add(caminoMitadMitadDoble);
                camino.add(camMitad);
                camino.add(caminoDobleMitad);
                camino.add(camDoble);
                camino.add(caminoDobleDoble);

            }
            camino.add(new Camino(rect.getX(), rect.getY()));
        }
        return camino;
    }
}

