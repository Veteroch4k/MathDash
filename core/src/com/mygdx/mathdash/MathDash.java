package com.mygdx.mathdash;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mathdash.screens.MainMenu;

public class MathDash extends Game {
	public SpriteBatch batch;
	private Game game;

	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100;
	public static int attempt = 1;

	// Collision Bits
	public static final short DEFAULT_BIT = 1;
	public static final short KUB_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short THORNS_BIT = 8;
	public static final short FINISH_BLOCK_BIT = 16;
	public static final short ORD_SURFACE_BIT = 32;
	public static final short SP_SURFACE_KB = 64;

	@Override
	public void create () {
		game = this;
		batch = new SpriteBatch();
		setScreen(new MainMenu((MathDash) game));
	}

	@Override
	public void render () {
		super.render();
	}

}
