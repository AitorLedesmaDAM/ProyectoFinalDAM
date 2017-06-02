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

    public AtaqueTropa() {
    }

    /**
     * Recorre todas las tropas que estan atacando le quitan vida a la torre
     *
     * @param torre
     * @param tropas
     * @param musica
     */
    public void atacarTorre(Torres torre, ArrayList<Tropas> tropas, boolean musica) {
        for (int danyoTorre = 0; danyoTorre < tropas.size(); danyoTorre++) {
            if (!tropas.get(danyoTorre).isanimacionCaminar()) { //Si la animacion no es la de atacar...
                torre.setVida(torre.getVida() - tropas.get(danyoTorre).getDanyo()); //A la vida se le resta el daño de la tropa
                if (torre.getVida() <= 0) { //Cuando la vida de la torre es menor a 0
                    for (int i = 0; i < tropas.size(); i++) {
                        tropas.get(i).setEstaAtacando(false);
                    }
                    if (torre.isOrientacion() && torre.isViva()) {   //Dependiendo de la orientación el rectangulo de colisiones
                        torre.getCollisionCircle().setY(torre.getCollisionCircle().y + 75);
                    }
                    torre.getCollisionCircle().setRadius(5);    //Cambia el radio de la torre
                    torre.setViva(false);
                    torre.setOverlaps(false);
                    if (musica) {
                        AssetManager.soundTowerDead.play();
                    }
                }
            }
        }
    }

    /**
     * Ejecuta el disparo del robot
     *
     * @param torre
     * @param robot
     * @param stage
     */
    public void disparoRobot(Torres torre, Tropas robot, Stage stage) {
        robot.setContadorBala(robot.getContadorBala() + 1);
        robot.setanimacionCaminar(false);
        if (robot.getContadorBala() % 120 == 0) {   //Cada 2 segundos entre cada disparo
            robot.ataque(robot.getPosition(), new Vector2(torre.getCollisionCircle().x, torre.getCollisionCircle().y), stage);
        }
    }

    /**
     * Ejecuta el metodo que hace que se llegue a la torre para atacarla
     *
     * @param torreActual
     * @param tropaActual
     * @param caminoTotal
     */
    public void llegarATorre(Torres torreActual, Tropas tropaActual, ArrayList<Camino> caminoTotal) {
        LlegarATorre llegar = new LlegarATorre(tropaActual, torreActual, caminoTotal);
        ArrayList<Camino> camino = llegar.caminarHaciaTorre();
        if (!tropaActual.siguienteCasillaAtaque(camino) && !tropaActual.llegarATorre(tropaActual.getY(), torreActual.getPosicionAtaque().y, torreActual.isOrientacion())) {
            tropaActual.setanimacionCaminar(false);
        }
    }
}
