package com.proyectofinal.game.objects.trops;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.objects.trops.attack.Bala;

/**
 * Created by ALUMNEDAM on 09/05/2017.
 */

public class Robot extends Tropas {

    private Bala bala;

    public Robot(float x, float y, int desviacionX, int desviacionY, int vida, int danyo, int velocidad) {
        super(x, y, desviacionX, desviacionY, vida, danyo, velocidad);
        ataqueCuerpoaCuerpo = false;
    }

    /**
     * Recojiendo las posiciones del robot y la torre a la que ataca se genera una bala
     * @param origen
     * @param destino
     * @param stage
     */
    @Override
    public void ataque(Vector2 origen, Vector2 destino, Stage stage) {
        super.ataque(origen, destino, stage);

        if (bala == null || bala.isDestruida()) {   //Siempre que no tenga una bala ya creada y que su última bala disparada ya este destruida...
            bala = new Bala(new Vector2(origen.x + getCollisionRect().getWidth() + 5, origen.y + (getCollisionRect().getHeight()/2) + 10), destino);
            bala.setDestruida(false);
            stage.addActor(bala);   //Se le añade al stage para que pueda verse
        }
    }


    @Override
    public void act(float delta) {
        getCollisionRect().set(new Rectangle(getPosition().x+19,getPosition().y,57,130));   //Rectangulo de colisiones del Robot
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        this.setName("Robots");
        if (!isanimacionCaminar()){
            batch.draw(AssetManager.robotAttack.getKeyFrame(getTiempoDeEstado()), getX(), getY(), 0, 0, 142, 140, 1f, 1f, 0);
        }else{
            batch.draw(AssetManager.robotRun.getKeyFrame(getTiempoDeEstado()), getX(), getY(), 0, 0, 142, 140, 1f, 1f, 0);
        }


    }


}