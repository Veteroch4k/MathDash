package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.MainMenu;
import com.mygdx.game.Screens.PlayScreen;

public class MathDash extends Game {
	public PlayScreen playScreen;
	public SpriteBatch batch;
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100;

	// Collision Bits
	public static final short DEFAULT_BIT = 1;
	public static final short KUB_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short THORNS_BIT = 16;
	public static final short ORD_SURFACE_BIT = 32;
	public static final short SP_SURFACE_KB = 64;

	@Override
	public void create () {
		batch = new SpriteBatch();
		playScreen = new PlayScreen(this);
		setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();
	}

}
