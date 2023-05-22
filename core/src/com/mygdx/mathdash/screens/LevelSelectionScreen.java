package com.mygdx.mathdash.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mathdash.MathDash;


public class LevelSelectionScreen implements Screen {
    private Viewport viewport;
    private Stage stage;

    private Game game;

    private Label menuBack;
    private Label level_1;
    public static String Selected_level;

    public LevelSelectionScreen(final MathDash game) {
        this.game = game;
        viewport = new FitViewport(MathDash.V_WIDTH, MathDash.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, (game).batch);


        Gdx.input.setInputProcessor(stage);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);
        Label selectLevel = new Label("Choose a level", font);
        menuBack = new Label("Back to Menu", font);
        level_1 = new Label("Level " + 1, font);

        table.add(selectLevel).expandX();
        table.row();
        table.add(level_1).padTop(10f);
        table.row();

        table.add(menuBack).padTop(25f);

        stage.addActor(table);
        menuBack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new MainMenu(game));
                dispose();

            }
        });

        level_1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Selected_level = String.valueOf(level_1.getText());
                game.setScreen(new PlayScreen(game));
                dispose();

            }
        });

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
