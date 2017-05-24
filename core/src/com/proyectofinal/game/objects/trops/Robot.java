package com.proyectofinal.game.objects.trops;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.proyectofinal.game.helpers.AssetManager;

/**
 * Created by ALUMNEDAM on 09/05/2017.
 */

public class Robot extends Tropas {

    public static boolean attack = false;
    public static float torreX, torreY;

    public Robot(int desviacionX, int desviacionY, int vida, int danyo, int velocidad) {
        super(desviacionX, desviacionY, vida, danyo, velocidad);
    }


    @Override
    public void act(float delta) {
        getCollisionRect().set(new Rectangle(getPosition().x+19,getPosition().y,90,130));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        int num = 0;

        if (attack){


            batch.draw(AssetManager.robotAttack.getKeyFrame(getTiempoDeEstado()), getX(), getY(), 0, 0, 142, 140, 1f, 1f, 0);
            /**           for (int i = 0; i < 4; i++){
             num += 100;


             batch.draw(AssetManager.robotBullet.getKeyFrame(getTiempoDeEstado()), getX() + num, getY(), 0, 0, 100, 150, 1f, 1f, 0);


             }
             */
            //batch.draw(AssetManager.robotBullet.getKeyFrame(getTiempoDeEstado()), getX() + num+100, getY(), 0, 0, 100, 150, 1f, 1f, 0);
            //batch.draw(AssetManager.robotBullet.getKeyFrame(getTiempoDeEstado()), getCollisionRect().x + num+100, getCollisionRect().y, 0, 0, 100, 150, 1f, 1f, 0);

            batch.draw(AssetManager.robotBullet.getKeyFrame(getTiempoDeEstado()), getCollisionRect().x + num+100, getCollisionRect().y, 0 , 0 , 100, 150, 1f, 1f, Math.abs(torreY - torreX));



            batch.draw(AssetManager.robotMuzzle.getKeyFrame(getTiempoDeEstado()), getCollisionRect().x + num +200, getCollisionRect().y, 0, 0, 100, 150, 1f, 1f, torreY - torreX);

        }else {
            batch.draw(AssetManager.robotRun.getKeyFrame(getTiempoDeEstado()), getX(), getY(), 0, 0, 142, 140, 1f, 1f, 0);
        }

    }
}