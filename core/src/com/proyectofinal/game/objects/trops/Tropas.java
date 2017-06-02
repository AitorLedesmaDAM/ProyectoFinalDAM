package com.proyectofinal.game.objects.trops;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.proyectofinal.game.objects.road.Camino;
import com.proyectofinal.game.utils.Settings;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ALUMNEDAM on 05/05/2017.
 */

public class Tropas extends Actor{

    public enum Estado {
        Atacando, Caminando
    }

    public enum Tipo{
        Caballero, Ninja, Robot
    }

    private Estado estado;
    private Vector2 position;
    protected int width = Settings.TROPA_WIDTH, height = Settings.TROPA_HEIGHT;
    protected int danyo, vida, velocidad;
    private Rectangle collisionRect;
    private int desviacionY, desviacionX;
    public float tiempoDeEstado = 0;
    public int casillaActual = 0, casillasParaLlegarATorre = 0;
    private boolean animacionCaminar, estaAtacando;
    private Random random = new Random();
    public boolean ataqueCuerpoaCuerpo;
    private int contadorBala = 119;

    public Tropas(float x, float y, int desviacionX, int desviacionY, int vida, int danyo, int velocidad){
        // Inicialitzem els arguments segons la crida del constructor
        position = new Vector2(x,y);
        this.desviacionY = desviacionY;
        this.desviacionX = desviacionX;
        animacionCaminar = true;
        // Creem el rectangle de col·lisions
        collisionRect = new Rectangle();
        ataqueCuerpoaCuerpo = true;

        this.vida = vida;
        this.danyo = danyo;
        this.velocidad = velocidad;

        // Per a la gestio de hit
        setBounds(position.x, position.y, width, height);
        setTouchable(Touchable.enabled);
        estado = Estado.Caminando;

    }

    public Tropas(){

    }

    /**
     * Dependiendo del tipo de tropa que se le pase, añadira una tropa u otra
     * @param tipo
     * @param x
     * @param y
     * @return
     */
    public Tropas crearTropa(Tipo tipo, float x, float y){
        Tropas tropa = null;
        switch (tipo){
            case Caballero:
                tropa = new Caballero(x, y, random.nextInt(125) + (-87), random.nextInt(150) + (-100), Settings.VIDA_CABALLERO, Settings.DANYO_CABALLERO, Settings.VELOCIDAD_CABALLERO);
                break;

            case Ninja:
                tropa = new Ninja(x, y, random.nextInt(125) + (-87), random.nextInt(150) + (-100), Settings.VIDA_NINJA, Settings.DANYO_NINJA, Settings.VELOCIDAD_NINJA);
                break;

            case Robot:
                tropa = new Robot(x, y, random.nextInt(125) + (-115), random.nextInt(150) + (-90), Settings.VIDA_ROBOT, Settings.DANYO_ROBOT, Settings.VELOCIDAD_ROBOT);
                break;
            default:
                return tropa;
        }
        return tropa;
    }

    public void ataque(Vector2 origen, Vector2 destino, Stage stage){

    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public Rectangle getCollisionRect() {
        return collisionRect;
    }

    public float getTiempoDeEstado() {
        return tiempoDeEstado;
    }

    public void setTiempoDeEstado(float tiempoDeEstado) {
        this.tiempoDeEstado = tiempoDeEstado;
    }

    /**
     * Va recojiendo el camino por el que tiene que ir y actualiza su posicion
     * @param camino
     */
    public void siguienteCasilla(ArrayList<Camino> camino){
        if (estado == Estado.Caminando && casillaActual < camino.size() - 1) {
            casillaActual++;
            position.x = camino.get(casillaActual).getX() + desviacionX;
            position.y = camino.get(casillaActual).getY() + desviacionY;
            animacionCaminar = true;
        }
    }

    /**
     * Devuelve un boolean
     * @param camino
     * @return
     */
    public boolean siguienteCasillaAtaque(ArrayList<Camino> camino){

        if (camino.size() - 1 > casillaActual) {
            casillaActual++;
            position.x = camino.get(casillaActual).getX() + desviacionX;
            position.y = camino.get(casillaActual).getY() + desviacionY;
            return true;
        }else{
            return false;
        }
    }

    /**
     * Dependiendo de la posicion de la torre, la tropa irá hacia arriba o hacia abajo. Una vez que
     * llegue a la posicion indicada, devolvera un false
     * @param comienzo
     * @param fin
     * @param posicionTorre
     * @return
     */
    public boolean llegarATorre(float comienzo, float fin, boolean posicionTorre){
        if (posicionTorre) {
            if(comienzo < fin) {
                position.y += 2;
                casillasParaLlegarATorre++; //Se le suma cada vez que tiene que ir para arriba
                return true;
            }
        }else{
            if(comienzo > fin) {
                position.y -= 2;
                casillasParaLlegarATorre--; //Se le resta cada vez que tiene que ir para abajo
                return true;
            }
        }
        return false;
    }

    /**
     * El metodo inverso a llegarATorre(). Devuelve un boolean dependiendo de si a vuelto a su
     * posicion original o no
     * @return
     */
    public boolean salirDeTorre(){
            if (casillasParaLlegarATorre < 0) {
                casillasParaLlegarATorre++;
                position.y += 2;
                return true;
            } else if (casillasParaLlegarATorre > 0) {
                casillasParaLlegarATorre--;
                position.y -= 2;
                return true;
            } else {
                return false;
            }
    }

    public void setCollisionRect(Rectangle collisionRect) {
        this.collisionRect = collisionRect;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getCasillaActual() {
        return casillaActual;
    }

    public boolean isanimacionCaminar() {
        return animacionCaminar;
    }

    public void setanimacionCaminar(boolean animacionCaminar) {
        this.animacionCaminar = animacionCaminar;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDanyo() {
        return danyo;
    }

    public void setDanyo(int danyo) {
        this.danyo = danyo;
    }

    public boolean isEstaAtacando() {
        return estaAtacando;
    }

    public void setEstaAtacando(boolean estaAtacando) {
        this.estaAtacando = estaAtacando;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getContadorBala() {
        return contadorBala;
    }

    public void setContadorBala(int contadorBala) {
        this.contadorBala = contadorBala;
    }
}
