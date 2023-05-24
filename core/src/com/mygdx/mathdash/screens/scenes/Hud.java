package com.mygdx.mathdash.screens.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mathdash.MathDash;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    public Integer score;

    Label levelLabel;
    Label worldLabel;

    @SuppressWarnings("DefaultLocale")
    public Hud(SpriteBatch sb) {

        score = MathDash.attempt;

        viewport = new FitViewport(MathDash.V_WIDTH, MathDash.V_WIDTH, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        worldLabel = new Label("Attempt " + Integer.toString(score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("Level 1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(levelLabel).expandX().padTop(10f);
        table.add(worldLabel).expandX().padTop(10f);

        stage.addActor(table);
    }


    @Override
    public void dispose() {
        stage.dispose();
    }
}
