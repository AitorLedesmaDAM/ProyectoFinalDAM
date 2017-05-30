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

    public Container containerMusic, containerMusicMute;
    Array<Actor> actores = new Array<Actor>();

    public void iconoMusica(Stage stage){

        actores = stage.getActors();


        if (Settings.music){

            if (Settings.pantalla == 1 || Settings.pantalla == 2) {
                AssetManager.musicStart.play();
            }else{
                AssetManager.musicEnd.play();
            }
            Image musicIcono = new Image(AssetManager.musicIcono);   //Selección de musica

            musicIcono.setName("Music");
            containerMusic = new Container(musicIcono);
            containerMusic.setTransform(true);
            containerMusic.center();
            containerMusic.setSize(Settings.MUSICICONO_WIDTH, Settings.MUSICICONO_HEIGHT);
            containerMusic.setPosition(Settings.MUSICICONO_WIDTH - 50, 20);
            stage.addActor(containerMusic);
            stage.addAction(Actions.removeActor(containerMusicMute));

        }
        if (Settings.music==false ){


            if (Settings.pantalla == 1 || Settings.pantalla == 2) {
                AssetManager.musicStart.dispose();
            }else{
                AssetManager.musicEnd.dispose();
            }
            Image musicIcono = new Image(AssetManager.musicMute);   //Selección de musica
            musicIcono.setName("MusicMute");
            containerMusicMute = new Container(musicIcono);
            containerMusicMute.setTransform(true);
            containerMusicMute.center();
            containerMusicMute.setSize(Settings.MUSICICONO_WIDTH, Settings.MUSICICONO_HEIGHT);
            containerMusicMute.setPosition(Settings.MUSICICONO_WIDTH - 50, 20);

            //stage.addAction(Actions.removeActor(containerMusic));
            stage.addActor(containerMusicMute);
            stage.addAction(Actions.removeActor(containerMusic));

        }

    }



}


/**
 public void iconoMusica(Stage stage){

 actores = stage.getActors();
 System.out.println(actores.size);


 if (Settings.music){

 AssetManager.musicStart.play();
 Image musicIcono = new Image(AssetManager.musicIcono);   //Selección de musica

 musicIcono.setName("Music");
 containerMusic = new Container(musicIcono);
 containerMusic.setTransform(true);
 containerMusic.center();
 containerMusic.setSize(Settings.MUSICICONO_WIDTH, Settings.MUSICICONO_HEIGHT);
 containerMusic.setPosition(Settings.MUSICICONO_WIDTH - 50, 20);
 stage.addActor(containerMusic);
 stage.addAction(Actions.removeActor(containerMusicMute));

 }
 if (Settings.music==false ){


 AssetManager.musicStart.dispose();
 Image musicIcono = new Image(AssetManager.musicMute);   //Selección de musica
 musicIcono.setName("MusicMute");
 containerMusicMute = new Container(musicIcono);
 containerMusicMute.setTransform(true);
 containerMusicMute.center();
 containerMusicMute.setSize(Settings.MUSICICONO_WIDTH, Settings.MUSICICONO_HEIGHT);
 containerMusicMute.setPosition(Settings.MUSICICONO_WIDTH - 50, 20);

 //stage.addAction(Actions.removeActor(containerMusic));
 stage.addActor(containerMusicMute);
 stage.addAction(Actions.removeActor(containerMusic));

 }

 }*/