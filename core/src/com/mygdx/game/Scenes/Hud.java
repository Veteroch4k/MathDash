package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MathDash;

import java.awt.Label;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    /*private Integer worldTimer;
    private float timeCount;
    private Integer score;
*/
    /*Label countdownLabel;
    Label scoreLabel;
    Label levelLabel;
    Label timeLabel;
    Label worldLabel;
    Label MathDashLabel;*/

    /** надо будет сделать меню с % пройденности карты*/

    public Hud(SpriteBatch sb) {
        /*worldTimer = 300;
        timeCount = 0;
        score = 0;*/
        viewport = new FitViewport(MathDash.V_WIDTH, MathDash.V_WIDTH, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        /*Table table = new Table();
        table.top();
        table.setFillParent(true);
*/
    }


    @Override
    public void dispose() {
        stage.dispose();
    }
}
