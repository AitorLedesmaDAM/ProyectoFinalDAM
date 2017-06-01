package com.proyectofinal.game.objects.trops;

import com.proyectofinal.game.objects.Nivel;
import com.proyectofinal.game.objects.road.Camino;
import com.proyectofinal.game.objects.towers.Torre_Fuego;
import com.proyectofinal.game.objects.towers.Torres;
import com.proyectofinal.game.objects.trops.Tropas;

import java.util.ArrayList;

/**
 * Created by ALUMNEDAM on 16/05/2017.
 */

public class LlegarATorre {

    private Tropas objectTropa;
    private Torres objectTorre;
    private Nivel nivel;
    private ArrayList<Camino> caminoTropas;

    public LlegarATorre(Tropas tropa, Torres torre, ArrayList<Camino> camino) {
        this.objectTropa = tropa;
        this.objectTorre = torre;
        nivel = new Nivel();

        caminoTropas = camino;
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
        Camino camino = recogerXTorre();
        int casillaTorre = caminoTropas.indexOf(camino);
        ArrayList<Camino> todasCasillas = new ArrayList<Camino>();
        for (int i = 0; i < casillaTorre; i++){
            todasCasillas.add(caminoTropas.get(i));
        }
        return todasCasillas;
    }
}

