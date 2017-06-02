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

    public ArrayList<Tropas> atacarTropas(Torres torre, ArrayList<Tropas> tropas){
        ArrayList<Tropas> tropasMuertas = new ArrayList<Tropas>();
        if (torre.getTipo().equals("Fuego")) {
            if (Settings.music){
                AssetManager.soundFire.play();
            }
            tropas.get(0).setVida(tropas.get(0).getVida() - torre.getDanyo());
            if (tropas.get(0).getVida() <= 0) {
                tropas.get(0).setDanyo(0);
                tropas.get(0).setVisible(false);

                tropasMuertas.add(tropas.remove(0));
            }
        } else if (torre.getTipo().equals("Rayo")) {
            if (Settings.music){
                AssetManager.soundThunder.play();
            }
            for (int i = 0; i < tropas.size(); i++) {
                tropas.get(i).setVida(tropas.get(i).getVida() - torre.getDanyo());
                if (tropas.get(i).getVida() < 0) {
                    tropasMuertas.add(tropas.remove(i));
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