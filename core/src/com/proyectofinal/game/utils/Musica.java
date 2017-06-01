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
    private Array < Actor > actores = new Array < Actor > ();
    boolean parado;

    public void iconoMusica(Stage stage) {

        actores = stage.getActors();

        if (Settings.music) {

            if (Settings.pantalla == 1 || Settings.pantalla == 2) {
                AssetManager.musicStart.play();
            } else if (Settings.pantalla == 4) {
                AssetManager.musicEnd.play();

            }

            if (Settings.pantalla == 3) {

                Image musicIcono = new Image(AssetManager.musicIconoAttack); //Selección de musica

                musicIcono.setName("Music");
                containerMusic = new Container(musicIcono);

                containerMusic.setTransform(true);
                containerMusic.center();
                containerMusic.setSize(Settings.MUSICICONO_WIDTH + 10, Settings.MUSICICONO_HEIGHT + 10);
                containerMusic.setPosition(Settings.MUSICICONO_WIDTH + 40, 1450);
                stage.addActor(containerMusic);
                stage.addAction(Actions.removeActor(containerMusicMute));

            } else {
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
        }else{

            if (Settings.pantalla == 1 || Settings.pantalla == 2) {
                AssetManager.musicStart.pause();
            } else if (Settings.pantalla == 4) {
                AssetManager.musicEnd.pause();
            }else if (Settings.pantalla == 3) {
                //AssetManager.soundWalk.stop();
                //AssetManager.soundMelee.stop();
                //AssetManager.soundTowerDead.stop();
                //AssetManager.soundDead2.stop();
                //parado = true;
            }

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