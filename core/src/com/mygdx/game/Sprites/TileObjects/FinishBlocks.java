package com.mygdx.game.Sprites.TileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MathDash;

public class FinishBlocks extends InteractiveTileObject{
    public FinishBlocks(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCateforyFilter(MathDash.FINISH_BLOCK_BIT);
    }

    @Override
    public void contacting() {
        Gdx.app.log("Финиш", "Конец");
    }
}
