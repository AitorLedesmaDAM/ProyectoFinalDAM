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
	@Override
	public void create() {

		// A l'iniciar el joc carreguem els recursos
		AssetManager.load();
		// I definim la pantalla d'splash com a pantalla
		setScreen(new MenuScreen(this));

	}

	// Cridem per descartar els recursos carregats.
	@Override
	public void dispose() {
		super.dispose();
		AssetManager.dispose();
	}
}
