package com.mygdx.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MathDash;
import com.mygdx.game.Sprites.Thorns;

public class B2WorldCreator {

    public B2WorldCreator(World world, TiledMap map) {

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        /** щас здесь каша, но в будущем будет отдельно для каждого спрайта выполненно, а пока что естьБ то есть **/

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MathDash.PPM, (rect.getY() + rect.getHeight() / 2) / MathDash.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MathDash.PPM, rect.getHeight() / 2 / MathDash.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        /** */
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MathDash.PPM, (rect.getY() + rect.getHeight() / 2) / MathDash.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MathDash.PPM, rect.getHeight() / 2 / MathDash.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        /** */
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
             new Thorns(world, map, rect);
        }



    }
}
