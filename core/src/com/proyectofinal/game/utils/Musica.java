package com.proyectofinal.game.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.utils.Array;

/**
 * Created by ALUMNEDAM on 26/05/2017.
 */

public class Musica {

    public Container containerMusic, containerMusicMute;
    Array<Actor> actores = new Array<Actor>();

    public void iconoMusica(Stage stage){

        actores = stage.getActors();
        System.out.println(actores.size);
       /* for (int i = 0; i < actores.size; i++){
            System.out.println(actores.get(i).getName());

        }
        for (int i = 0; i < actores.size; i++){
            System.out.println(actores.get(i).getName());
            if (actores.get(i).getName().equals("Music")){
                actores.get(i).remove();
            }else if (actores.get(i).getName().equals("MusicMute")){
                actores.get(i).remove();
            }
        }

        if (Settings.music) {
            //stage.addAction(Actions.removeActor(containerMusic));
            AssetManager.musicStart.play();

            Image musicIcono = new Image(AssetManager.musicIcono);   //Selección de musica

            musicIcono.setName("Music");
            containerMusic = new Container(musicIcono);
            containerMusic.setTransform(true);
            containerMusic.center();
            containerMusic.setSize(Settings.MUSICICONO_WIDTH, Settings.MUSICICONO_HEIGHT);
            containerMusic.setPosition(Settings.MUSICICONO_WIDTH - 50, 20);
            stage.addActor(containerMusic);


        }
        if (Settings.music==false ){

            //stage.addAction(Actions.removeActor(containerMusic));
            AssetManager.musicStart.dispose();
            Image musicIcono = new Image(AssetManager.musicMute);   //Selección de musica
            musicIcono.setName("MusicMute");
            containerMusicMute = new Container(musicIcono);
            containerMusicMute.setTransform(true);
            containerMusicMute.center();
            containerMusicMute.setSize(Settings.MUSICICONO_WIDTH, Settings.MUSICICONO_HEIGHT);
            containerMusicMute.setPosition(Settings.MUSICICONO_WIDTH - 50, 20);
            stage.addAction(Actions.removeActor(containerMusic));
            stage.addActor(containerMusicMute);
      }*/



    }



}
