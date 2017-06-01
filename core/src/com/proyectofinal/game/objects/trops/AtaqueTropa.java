package com.proyectofinal.game.objects.trops;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.objects.road.Camino;
import com.proyectofinal.game.objects.towers.Torres;

import java.util.ArrayList;

/**
 * Created by ALUMNEDAM on 31/05/2017.
 */

public class AtaqueTropa {

    public AtaqueTropa(){
    }

    public void atacarTorre(Torres torre, ArrayList<Tropas> tropas, boolean musica){
        for (int danyoTorre = 0; danyoTorre < tropas.size(); danyoTorre++) {
            if (!tropas.get(danyoTorre).isanimacionCaminar()) {
                torre.setVida(torre.getVida() - tropas.get(danyoTorre).getDanyo());
                if (torre.getVida() <= 0) {
                    for (int i = 0; i < tropas.size(); i++) {
                        tropas.get(i).setEstaAtacando(false);
                    }
                    if (torre.isOrientacion() && torre.isViva()){
                        torre.getCollisionCircle().setY(torre.getCollisionCircle().y + 75);
                    }
                    torre.getCollisionCircle().setRadius(5);
                    torre.setViva(false);
                    torre.setOverlaps(false);
                    if (musica){
                        AssetManager.soundTowerDead.play();
                    }
                }
            }
        }
    }

    public void disparoRobot(Torres torre, Tropas robot, Stage stage){
        robot.setContadorBala(robot.getContadorBala() + 1);
        robot.setanimacionCaminar(false);
        if (robot.getContadorBala() % 120 == 0) {
            robot.ataque(robot.getPosition(), new Vector2(torre.getCollisionCircle().x, torre.getCollisionCircle().y), stage);
        }
    }

    public void llegarATorre(Torres torreActual, Tropas tropaActual, ArrayList<Camino> caminoTotal){
            LlegarATorre at = new LlegarATorre(tropaActual, torreActual, caminoTotal);
            ArrayList<Camino> camino = at.caminarHaciaTorre();
                if (!tropaActual.siguienteCasillaAtaque(camino) && !tropaActual.llegarATorre(tropaActual.getY(), torreActual.getPosicionAtaque().y, torreActual.isOrientacion())) {
                    tropaActual.setanimacionCaminar(false);

                }
    }
}
