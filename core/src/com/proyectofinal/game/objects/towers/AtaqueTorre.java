package com.proyectofinal.game.objects.towers;

import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.objects.trops.Tropas;
import com.proyectofinal.game.utils.Settings;

import java.util.ArrayList;

/**
 * Created by ALUMNEDAM on 31/05/2017.
 */

public class AtaqueTorre {

    public AtaqueTorre(){
    }

    /**
     * Recoje la torre y las tropas que han colisionado con ella y les hace daño
     * @param torre
     * @param tropas
     * @return
     */
    public ArrayList<Tropas> atacarTropas(Torres torre, ArrayList<Tropas> tropas){
        ArrayList<Tropas> tropasMuertas = new ArrayList<Tropas>();
        if (torre.getTipo().equals("Fuego")) {  //Si la torre es de fuego el daño se le hara de uno en uno
            if (Settings.music){
                AssetManager.soundFire.play();
            }
            tropas.get(0).setVida(tropas.get(0).getVida() - torre.getDanyo());  //Se le quita el daño de la torre
            if (tropas.get(0).getVida() <= 0) { //Si la tropa no tiene vida..
                tropas.get(0).setDanyo(0);
                tropas.get(0).setVisible(false);

                tropasMuertas.add(tropas.remove(0));    //Se le añade a la array list de tropas
            }
        } else if (torre.getTipo().equals("Rayo")) {    //Si la torre es de rayos, harà daño a 4 tropas
            if (Settings.music){
                AssetManager.soundThunder.play();
            }
            int contador = 0;
            for (int i = 0; i < tropas.size(); i++) {
                if (contador < 3) {
                    tropas.get(i).setVida(tropas.get(i).getVida() - torre.getDanyo());
                    if (tropas.get(i).getVida() < 0) {
                        tropasMuertas.add(tropas.remove(i));
                    }
                }else{
                    break;
                }
            }
        }
        return tropasMuertas;
    }

    public void mostrarAtaque(Torres torre, ArrayList<Tropas> tropas){
        if (tropas.size() > 0){
            torre.setOverlaps(true);
        }else{
            torre.setOverlaps(false);
        }
    }
}