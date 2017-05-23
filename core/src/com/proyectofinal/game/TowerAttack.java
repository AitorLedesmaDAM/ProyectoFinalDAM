package com.proyectofinal.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyectofinal.game.helpers.AssetManager;
import com.proyectofinal.game.screens.MenuScreen;

public class TowerAttack extends Game {
	public static int puntos = 0;
	/**
	*metodo para cargar los recursos y definir la pantalla inicial
	*/
	@Override
	public void create() {

		AssetManager.load();
		setScreen(new MenuScreen(this));

	}

	/**
	* metodos para descartar los recursos ya cargados
	*/
	@Override
	public void dispose() {
		super.dispose();
		AssetManager.dispose();
	}
}
