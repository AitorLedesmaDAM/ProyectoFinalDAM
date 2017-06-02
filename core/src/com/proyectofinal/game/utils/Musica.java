package com.proyectofinal.game.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.proyectofinal.game.helpers.AssetManager;

/**
 * Created by ALUMNEDAM on 26/05/2017.
 */

public class Musica {

    //Containers para icono de musica en marcha y otro cuando esta parado
    private Container containerMusic, containerMusicMute;
    private Array < Actor > actores = new Array < Actor > ();

    
    /**
     * Methodo para poner icono de musica, por parametros le llega el Stage. en calse setting hay un booleano de musica
     * si esta true entonces pone musica de empezar que esta indicado en assetmanager y muestro el icono de musica en 
     * marcha. si el booleano esta false entonves para la musica y muestra el icono de musica parado.
     * @param stage
     */
    public void iconoMusica(Stage stage) {

        actores = stage.getActors();

        if (Settings.music) {

            //Muscia de pantalla 1 y 2.
            if (Settings.pantalla == 1 || Settings.pantalla == 2) {
                AssetManager.musicStart.play();
                //Musica para pantalla final.
            } else if (Settings.pantalla == 4) {
                AssetManager.musicEnd.play();

            }

            //Si esta en pantalla 3, que es attack Screen
            if (Settings.pantalla == 3) {

                //Tiene un icono diferentes, se crea image.
                Image musicIcono = new Image(AssetManager.musicIconoAttack); //Selección de musica
                //Ponemos nombre de "Musci" a ese imagen 
                musicIcono.setName("Music");
                containerMusic = new Container(musicIcono); //se anade al container

                //Ajustes de container, donde tiene que mostrar
                containerMusic.setTransform(true);
                containerMusic.center();
                containerMusic.setSize(Settings.MUSICICONO_WIDTH + 10, Settings.MUSICICONO_HEIGHT + 10);
                containerMusic.setPosition(Settings.MUSICICONO_WIDTH + 40, 1450);
                stage.addActor(containerMusic); //Se anade
                //Borrar el container de musica parado, eso es en caso de que el usuario vuelva a poner musica, para que 
                //no le muestra el icono de musica parado.
                stage.addAction(Actions.removeActor(containerMusicMute)); 

            } else {
                //sino carga ese icono de imagen, y hace mismos pasos que anterior
                Image musicIcono = new Image(AssetManager.musicIcono); //Selección de musica

                musicIcono.setName("Music");
                containerMusic = new Container(musicIcono);

                containerMusic.setTransform(true);
                containerMusic.center();
                containerMusic.setSize(Settings.MUSICICONO_WIDTH, Settings.MUSICICONO_HEIGHT);
                containerMusic.setPosition(Settings.MUSICICONO_WIDTH - 50, 20);
                stage.addActor(containerMusic);
                stage.addAction(Actions.removeActor(containerMusicMute));
            }
            
            //Si el booleano de musica es falso
        }else{
            
            //Hace un pause de musica segun en pantalla que este.
            if (Settings.pantalla == 1 || Settings.pantalla == 2) {
                AssetManager.musicStart.pause();
            } else if (Settings.pantalla == 4) {
                AssetManager.musicEnd.pause();
            }

            //si el pantalla de ataque, carrega la imagen indicado, que sera de muscia parado.
            if (Settings.pantalla == 3) {

                Image musicIcono = new Image(AssetManager.musicMuteAttack); //Selección de musica
                musicIcono.setName("MusicMute");
                containerMusicMute = new Container(musicIcono);
                containerMusicMute.setTransform(true);
                containerMusicMute.center();
                containerMusicMute.setSize(Settings.MUSICICONO_WIDTH + 10, Settings.MUSICICONO_HEIGHT + 10);
                containerMusicMute.setPosition(Settings.MUSICICONO_WIDTH + 40, 1450);

                stage.addActor(containerMusicMute);
                stage.addAction(Actions.removeActor(containerMusic));

            } else {

                //En otras pantalla tambien carrega el imagen de musica parado.
                Image musicIcono = new Image(AssetManager.musicMute); //Selección de musica
                musicIcono.setName("MusicMute");
                containerMusicMute = new Container(musicIcono);
                containerMusicMute.setTransform(true);
                containerMusicMute.center();
                containerMusicMute.setSize(Settings.MUSICICONO_WIDTH, Settings.MUSICICONO_HEIGHT);
                containerMusicMute.setPosition(Settings.MUSICICONO_WIDTH - 50, 20);

                stage.addActor(containerMusicMute);
                stage.addAction(Actions.removeActor(containerMusic));
            }
        }
    }
}
