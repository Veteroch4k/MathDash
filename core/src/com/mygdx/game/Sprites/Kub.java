package com.mygdx.game.Sprites;

import static com.badlogic.gdx.utils.JsonValue.ValueType.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MathDash;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.db.Preference;


public class Kub extends Sprite {
    public enum State { ALIVE, DEAD, FINISHED}
    public State currentState;
    public World world;
    public Body b2body;
    private Texture kubikSkin;

    public Kub(World world, PlayScreen screen) {
        this.world = world;
        defineKub();
        currentState = State.ALIVE;
        kubikSkin = new Texture("kubik.jpg");
        setBounds(0, 0, 16 / MathDash.PPM, 16 / MathDash.PPM);
        setRegion(kubikSkin);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }
//256
    public void defineKub() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(256 / MathDash.PPM, 32 / MathDash.PPM );
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        Rectangle rect = new Rectangle();
        rect.setWidth(13);
        rect.setHeight(13);

        shape.setAsBox(rect.getWidth() / 2 / MathDash.PPM, rect.getHeight() / 2  / MathDash.PPM);

        fdef.filter.categoryBits = MathDash.KUB_BIT;
        fdef.filter.maskBits =  MathDash.BRICK_BIT  | MathDash.THORNS_BIT | MathDash.FINISH_BLOCK_BIT | MathDash.DEFAULT_BIT;


        fdef.shape = shape;
        b2body.createFixture(fdef);


        Ordinarysurface(fdef);


        //  *   (-)   *  //

        Specialsurface(fdef);


    }


    private void Specialsurface(FixtureDef fdef) {
        EdgeShape edgeShape = new EdgeShape();
        edgeShape.set(new Vector2(6.5f / MathDash.PPM, -6.5f / MathDash.PPM), new Vector2(-6.5f / MathDash.PPM, -6.5f / MathDash.PPM));
        fdef.shape = edgeShape;
        fdef.isSensor = true;
        fdef.filter.categoryBits = MathDash.SP_SURFACE_KB;

        b2body.createFixture(fdef).setUserData(this);
    }


    private void Ordinarysurface(FixtureDef fdef) {
        PolygonShape polygonShape = new PolygonShape();
        Vector2[] surf = new Vector2[4];
        surf[0] = new Vector2(-6.5f,-3f).scl(1 / MathDash.PPM);
        surf[1] = new Vector2(-6.5f,6.5f).scl(1 / MathDash.PPM);
        surf[2] = new Vector2(6.5f,6.5f).scl(1 / MathDash.PPM);
        surf[3] = new Vector2(6.5f,-3f).scl(1 / MathDash.PPM);
        polygonShape.set(surf);
        fdef.shape = polygonShape;
        fdef.isSensor = true;

        fdef.filter.categoryBits = MathDash.ORD_SURFACE_BIT;

        b2body.createFixture(fdef).setUserData(this);

    }

    public void hitting() {
        currentState = State.DEAD;
    }
    public void finish() {
        currentState = State.FINISHED;
    }


}

