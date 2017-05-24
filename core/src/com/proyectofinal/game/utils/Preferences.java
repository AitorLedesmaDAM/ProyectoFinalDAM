package com.proyectofinal.game.utils;


import com.badlogic.gdx.Gdx;

/**
 * Created by ALUMNEDAM on 23/05/2017.
 */

public class Preferences {


    private com.badlogic.gdx.Preferences preferences;


    public Preferences() {
    }

    protected com.badlogic.gdx.Preferences getPrefs() {
        if(preferences==null){
            preferences = Gdx.app.getPreferences("Preferencias");
        }
        return preferences;
    }


    public void guardarPreferences(int nivell){

        //put some Integer
        if (obtenerPreference() < nivell) {
            getPrefs().putInteger("nivell", nivell);
        }

        //persist preferences
        getPrefs().flush();

    }

    public int obtenerPreference(){

        int nivel = getPrefs().getInteger("nivell");

        return nivel;
    }


}
