package com.proyectofinal.game.objects;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.proyectofinal.game.objects.road.Camino;
import com.proyectofinal.game.objects.towers.Torre_Fuego;
import com.proyectofinal.game.objects.towers.Torre_Rayo;
import com.proyectofinal.game.objects.towers.Torres;
import com.proyectofinal.game.objects.trops.Tropas;
import com.proyectofinal.game.utils.Settings;

import java.util.ArrayList;

/**
 * Created by ALUMNEDAM on 09/05/2017.
 */

public class Nivel {

    private Tropas tropas;
    private boolean finalJuego = false;

    public Nivel() {
        tropas = new Tropas();
    }


    /**
     * Metodo que recoge los objetos del tiled de la capa TorresObjetos y a cada uno les añade una torre nueva
     * También controla la orientacion de la torre
     *
     * @param mapa
     * @return
     */
    public static ArrayList<Torres> recojerTorres(TiledMap mapa) {
        ArrayList<Torres> torres = new ArrayList<Torres>();
        int vida, danyo, radio;
        int pos = 0;

        //Declaramos un MapObject y cogemos del mapa todos los objetos de la capa TorresObjetos
        MapObjects objectsT = mapa.getLayers().get("TorresObjetos").getObjects();

        for (int i = 0; i < objectsT.getCount(); i++) {
            RectangleMapObject rmo = (RectangleMapObject) objectsT.get(i);
            Rectangle rect = rmo.getRectangle();
            pos++;
            //En el mapa dentro de un objeto, se le pueden poner atributos
            //Comprobamos si tienen un atributo cara, esto servira para la orientación
            boolean orientacion = mapa.getLayers().get("TorresObjetos").getObjects().get("torre" + pos).getProperties().containsKey("cara");

            //Si la torre en sus atributos tiene uno TorreR, se añadira una torre de tipo rayo a ese objeto
            if (mapa.getLayers().get("TorresObjetos").getObjects().get("torre" + pos).getProperties().containsKey("TorreR")) {

                vida = Settings.VIDA_TORRE_RAYO;
                danyo = Settings.DANYO_TORRE_RAYO;
                radio = Settings.RADIO_TORRE_RAYO;

                torres.add(new Torre_Rayo(rect.getX(), rect.getY(), orientacion, rect.getWidth() / 2, rect.getHeight() / 2, radio, vida, danyo, "Rayo"));

                //si no se añadira una de tipo fuego
            } else {

                vida = Settings.VIDA_TORRE_FUEGO;
                danyo = Settings.DANYO_TORRE_FUEGO;
                radio = Settings.RADIO_TORRE_FUEGO;

                torres.add(new Torre_Fuego(rect.getX(), rect.getY(), orientacion, rect.getWidth() / 2, rect.getHeight() / 2, radio, vida, danyo, "Fuego"));
            }
        }

        return torres;
    }


    /**
     * Metodo para dividir cada uno de los objetos de Camino objetos
     * en 8 partes para que las tropas que 'caminen' por estos objetos
     * se vean más fluidas.
     *
     * @param mapa
     * @return
     */
    public static ArrayList<Camino> recojerCamino(TiledMap mapa) {

        ArrayList<Camino> camino = new ArrayList<Camino>();

        //Obtenemos los objetos de la capa CaminoObjetos
        MapObjects objects = mapa.getLayers().get("CaminoObjetos").getObjects();
        Camino camMitad, camDoble, camMitadMitad, caminoMitadMitadMitad, caminoMitadMitadDoble, caminoDobleMitad, caminoDobleDoble;

        //Por cada objeto
        for (int i = 0; i < objects.getCount(); i++) {

            RectangleMapObject rmo = (RectangleMapObject) objects.get(i);
            Rectangle rect = rmo.getRectangle();

            if (i > 0) {

                //dividimos en 8 partes el objeto

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
            //lo añadimos al camino
            camino.add(new Camino(rect.getX(), rect.getY()));
        }
        return camino;
    }

    /**
     * Metodo para comprobar si se llega a la casilla final
     *
     * @param c
     * @param casillaActual
     * @return
     */
    public boolean comproFinal(ArrayList<Camino> c, int casillaActual) {

        if (c.size() - 1 == casillaActual) {
            finalJuego = true;
        } else {
            finalJuego = false;
        }

        return finalJuego;
    }

    public boolean isFinalJuego() {
        return finalJuego;
    }
}

