package com.mygdx.mathdash.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mathdash.MathDash;
import com.mygdx.mathdash.screens.scenes.Hud;
import com.mygdx.mathdash.sprites.Kub;
import com.mygdx.mathdash.tools.B2WorldCreator;
import com.mygdx.mathdash.tools.WorlContactListenner;
import com.mygdx.mathdash.db.Preference;

public class PlayScreen implements Screen {

    private MathDash game;
    private Texture atlas;

    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Kub player;
    private Preference settings;

    private String selectedLevel;

    public PlayScreen(MathDash game) {
        this.game = game;

        atlas = new Texture("kubik.jpg");

        gameCam = new OrthographicCamera();
        gamePort = new FillViewport(MathDash.V_WIDTH / MathDash.PPM, MathDash.V_HEIGHT / MathDash.PPM, gameCam);
        settings = new Preference();

        hud = new Hud(game.batch);
        mapLoader = new TmxMapLoader();

        selectedLevel = LevelSelectionScreen.Selected_level;
        map = mapLoader.load(selectedLevel + ".tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / MathDash.PPM);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world, map);

        player = new Kub(world, this);

        world.setContactListener(new WorlContactListenner());


    }

    public Texture getAtlas() {
        return atlas; /** для спрайтов(анимация движений)*/
    }

    @Override
    public void show() {

    }
    /** Мир двигается не сам, а с нажатиями **/
    public void update(float dt) {
        handleInput();

        world.step(1/60f,6, 2);

        gameCam.position.x = player.b2body.getPosition().x;
        player.update(dt);

        gameCam.update();
        renderer.setView(gameCam);
    }
    /** прыгает нормально, перепрыгнуть 3 шипа может, загвостка в том, что он не крутиться, UPD: крутится*/
    int i = 1; /** для прокрутки */
    private void handleInput() {
        if(player.currentState == Kub.State.ALIVE) {
            /** здесь я обрабатываю нажатия относительно цетнра экрана **/
            if (Gdx.input.isTouched()) {
                if (Gdx.input.getX() > (Gdx.graphics.getWidth() / 2)) {
                    moveRight();
                } else if (Gdx.input.getX() < (Gdx.graphics.getWidth() / 2)) {
                    moveLeft();
                }
            }
        }
    }
    private void moveRight(){
        if (player.b2body.getLinearVelocity().x <= 1f) {
            player.b2body.applyLinearImpulse(new Vector2(0.5f, 0), new Vector2(-8 * 100, -800), true);

        }
        if (player.b2body.getLinearVelocity().y == 0) {
            /** Тут надо написать функцию, чтоб он двигался по параболле
             * UPD: уже есть :))) и функция не нужна, но он не крутится*/
            player.b2body.applyLinearImpulse(new Vector2(0, 3f), player.b2body.getWorldCenter(), true);
            player.setRotation(-90 * i);
            i++;

        }
    }
    private void moveLeft(){
        if (player.b2body.getLinearVelocity().x >= -1f) {
            player.b2body.applyLinearImpulse(new Vector2(-0.5f, 0), new Vector2(-8 * 100, -800), true);

        }
        if (player.b2body.getLinearVelocity().y == 0) {
            /** Тут надо написать функцию, чтоб он двигался по параболле
             * UPD: уже есть :))) и функция не нужна, но он не крутится*/
            player.b2body.applyLinearImpulse(new Vector2(0, 3f), player.b2body.getWorldCenter(), true);
            player.setRotation(-90 * i);
            i++;

        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        b2dr.render(world, gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();

        player.setOrigin(8/MathDash.PPM,8/MathDash.PPM);
        player.draw(game.batch);

        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        if(player.currentState == Kub.State.DEAD) {
            game.setScreen(new GameOverScreen(game));
            MathDash.attempt++;
        } else if(player.currentState == Kub.State.FINISHED) {
            if (settings.Attempt_Сounter.getInteger("worst_score") < MathDash.attempt || settings.Attempt_Сounter.getInteger("worst_score") == 0) {
                settings.Attempt_Сounter.putInteger("worst_score", MathDash.attempt);
                settings.Attempt_Сounter.flush();
            }
            if (settings.Attempt_Сounter.getInteger("best_score") > MathDash.attempt || settings.Attempt_Сounter.getInteger("best_score") == 0) {
                settings.Attempt_Сounter.putInteger("best_score", MathDash.attempt);
                settings.Attempt_Сounter.flush();
            }
            MathDash.attempt = 1;
            game.setScreen(new GameFinishedScreen(game));
        }

    }


    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);

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

        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();

    }
}
