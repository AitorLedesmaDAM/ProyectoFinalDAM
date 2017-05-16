package com.proyectofinal.game.objects;

import com.proyectofinal.game.objects.road.Camino;
import com.proyectofinal.game.objects.towers.Torre_Fuego;
import com.proyectofinal.game.objects.trops.Tropas;

import java.util.ArrayList;

/**
 * Created by ALUMNEDAM on 16/05/2017.
 */

public class AtacarTorre {

    Tropas objectTropa;
    Torre_Fuego objectTorre;
    Nivel nivel;
    ArrayList<Camino> caminoTropas;

    public AtacarTorre(Tropas tropa, Torre_Fuego torre) {
        this.objectTropa = tropa;
        this.objectTorre = torre;
        nivel = new Nivel();

        caminoTropas = new ArrayList<Camino>();
        caminoTropas = nivel.recojerCamino();
    }

    public float posicionTropaX(){
        return objectTropa.getX();
    }

    public float posicionTropaY(){
        return objectTropa.getY();
    }

    public float posicionTorre(){
        return objectTorre.getX();
    }

    public Camino recogerXTorre(){
        for (Camino caminos : caminoTropas) {
            if (caminos.getX() > objectTorre.getPosicionAtaque().x){
                return caminos;
            }
        }
        return null;
    }

    public ArrayList<Camino> caminarHaciaTorre(){
        int casillaTropa = objectTropa.getCasillaActual();
        Camino camino = recogerXTorre();
        int casillaTorre = caminoTropas.indexOf(camino);
        ArrayList<Camino> todasCasillas = new ArrayList<Camino>();
        for (int i = casillaTropa; i < casillaTorre; i++){
            todasCasillas.add(caminoTropas.get(i));
        }
        return todasCasillas;
    }

}
