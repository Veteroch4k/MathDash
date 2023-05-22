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


public class MainMenu implements Screen {

    private Viewport viewport;
    private Stage stage;
    private OrthographicCamera gameCam;

    private Game game;

    private Label playAgain;
    private Label getResults;

    public MainMenu(final MathDash game) {
        this.game = game;
        viewport = new FitViewport(MathDash.V_WIDTH, MathDash.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);
        gameCam = new OrthographicCamera();

        gameCam.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        Gdx.input.setInputProcessor(stage);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("MATH DASH", font);
        playAgain = new Label("START THE GAME", font);
        getResults = new Label("Results", font);

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(playAgain).padTop(25f);
        table.row();
        table.add(getResults).padTop(10f);


        stage.addActor(table);

        playAgain.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new LevelSelectionScreen(game));
                dispose();

            }
        });
        getResults.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new ResultsScreen(game));
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
/*
Label gameOverLabel = new Label("Game Over", font);
        Label playAgain = new Label("Play Again", font);
 */