package com.mygdx.mathdash.sprites.tileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.mathdash.MathDash;

public class Thorns extends InteractiveTileObject {

    public Thorns(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCateforyFilter(MathDash.THORNS_BIT);

    }

    @Override
    public void contacting() {
        Gdx.app.log("Шипы", "Соприкосновение");
    }
}
