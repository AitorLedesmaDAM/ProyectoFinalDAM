package com.proyectofinal.game.objects.trops;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.proyectofinal.game.helpers.AssetManager;

/**
 * Created by ALUMNEDAM on 09/05/2017.
 */

public class Robot extends Tropas {

    public static boolean attack = true;
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

        this.setName("Robots");
        //System.out.println(attack);
        //System.out.println(orientacionBala());
        if (!isanimacionCaminar()){
            if(attack){

           // if(!orientacionBala()) {
                batch.draw(AssetManager.robotAttack.getKeyFrame(getTiempoDeEstado()), getX(), getY(), 0, 0, 142, 140, 1f, 1f, 0);
                batch.draw(AssetManager.robotBullet.getKeyFrame(getTiempoDeEstado()), getCollisionRect().x + num+150, getCollisionRect().y, 0 , 0 , 100, 150, 1f, 1f, Math.abs(torreY + torreX));
                batch.draw(AssetManager.robotMuzzle.getKeyFrame(getTiempoDeEstado()), getCollisionRect().x + 400 , getCollisionRect().y, 0, 0, 100, 150, 1f, 1f, (float)Math.atan2(getX() - 0, getY() - 0));
            }else{
                batch.draw(AssetManager.robotAttack.getKeyFrame(getTiempoDeEstado()), getX(), getY(), 0, 0, 142, 140, 1f, 1f, 0);
                batch.draw(AssetManager.robotBullet.getKeyFrame(getTiempoDeEstado()), getCollisionRect().x + num+100, getCollisionRect().y, 0 , 0 , 100, 150, 1f, 1f, Math.abs(torreY - torreX));
                batch.draw(AssetManager.robotMuzzle.getKeyFrame(getTiempoDeEstado()), getCollisionRect().x + num + 200, getCollisionRect().y, 0, 0, 100, 150, 1f, 1f, (float)Math.atan2(getX() - 0, getY() - 0));
            }
        }else {

            batch.draw(AssetManager.robotRun.getKeyFrame(getTiempoDeEstado()), getX(), getY(), 0, 0, 142, 140, 1f, 1f, 0);
        }


    }
}