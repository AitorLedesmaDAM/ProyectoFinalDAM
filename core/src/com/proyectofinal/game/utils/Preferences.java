package com.proyectofinal.game.utils;


import com.badlogic.gdx.Gdx;

/**
 * Created by ALUMNEDAM on 23/05/2017.
 */

public class Preferences {


    private com.badlogic.gdx.Preferences preferences;


    public Preferences() {
    }

    /**
     * Obtiene las prenferencias del sistema
     * @return
     */
    protected com.badlogic.gdx.Preferences getPrefs() {
        if(preferences == null){
            preferences = Gdx.app.getPreferences("Preferencias");
        }
        return preferences;
    }


    /**
     * Guarda el nivel que te acabas de pasar
     * @param nivell
     */
    public void guardarPreferences(int nivell){

        //put some Integer
        if (obtenerPreference() < nivell) {
            getPrefs().putInteger("nivell", nivell);
        }

        //persist preferences
        getPrefs().flush();

    }

    /**
     * Obtiene el nivel actual
     * @return
     */
    public int obtenerPreference(){

        int nivel = getPrefs().getInteger("nivell");

        return nivel;
    }


}
