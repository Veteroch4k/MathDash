package com.mygdx.game.Sprites;

import static com.badlogic.gdx.utils.JsonValue.ValueType.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MathDash;
import com.mygdx.game.Screens.PlayScreen;

import java.awt.geom.RectangularShape;

public class Kub extends Sprite {
    public World world;
    public Body b2body;
    private Texture kubikSkin;


    public Kub(World world, PlayScreen screen) {
        this.world = world;
        defineKub();
        kubikSkin = new Texture("kubik.jpg");
        setBounds(0, 0, 16 / MathDash.PPM, 16 / MathDash.PPM);
        setRegion(kubikSkin);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    public void defineKub() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(64 / MathDash.PPM, 32 / MathDash.PPM );
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        Rectangle rect = new Rectangle();
        rect.setWidth(13);
        rect.setHeight(13);

        shape.setAsBox(rect.getWidth() / 2 / MathDash.PPM, rect.getHeight() / 2  / MathDash.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

}
