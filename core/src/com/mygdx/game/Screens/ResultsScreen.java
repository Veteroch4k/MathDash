package com.mygdx.game.Screens;

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
import com.mygdx.game.MathDash;
import com.mygdx.game.db.Preference;

public class ResultsScreen implements Screen {
    private Viewport viewport;
    private Stage stage;

    private Preference settings;

    private Game game;
    private Label best;
    private Label worst;
    private Label menuBack;

    public ResultsScreen(final MathDash game) {
        this.game = game;
        viewport = new FitViewport(MathDash.V_WIDTH, MathDash.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, (game).batch);
        settings = new Preference();

        int bestScore = settings.Attempt_Сounter.getInteger("best_score");
        int wosrtScore = settings.Attempt_Сounter.getInteger("worst_score");


        Gdx.input.setInputProcessor(stage);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("Results", font);
        if(bestScore !=0 && wosrtScore != 0) {
            best = new Label("Best Attempt: " + bestScore, font);
            worst = new Label("Worst Attempt: " + wosrtScore, font);
            menuBack = new Label("Back to Menu", font);
        } else {
            best = new Label("Best Attempt: " + "No Data", font);
            worst = new Label("Worst Attempt: " + "No Data", font);
            menuBack = new Label("Back to Menu", font);
        }


        table.add(gameOverLabel).top().expandX();
        table.row();
        table.add(best).padTop(25f);
        table.row();
        table.add(worst).padTop(5f);
        table.row();
        table.add(menuBack).padTop(10f);

        stage.addActor(table);

        menuBack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new MainMenu(game));
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