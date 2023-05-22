package com.mygdx.mathdash.tools;


import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.mathdash.MathDash;
import com.mygdx.mathdash.sprites.tileObjects.Brick;
import com.mygdx.mathdash.sprites.Kub;

public class WorlContactListenner implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        /** Добавляя новый объект, не забудь добавить его в B2WorldCreator and Kub(collision) **/
        switch (cDef) {
            case  MathDash.ORD_SURFACE_BIT | MathDash.BRICK_BIT :
            case  MathDash.ORD_SURFACE_BIT | MathDash.THORNS_BIT:
                if(fixA.getFilterData().categoryBits == MathDash.ORD_SURFACE_BIT) ((Kub) fixA.getUserData()).hitting();
                else  ((Kub) fixB.getUserData()).hitting();
                break;

            case  MathDash.ORD_SURFACE_BIT | MathDash.FINISH_BLOCK_BIT:
                if(fixA.getFilterData().categoryBits == MathDash.ORD_SURFACE_BIT)
                    ((Kub) fixA.getUserData()).finish();
                else  ((Kub) fixB.getUserData()).finish();
                break;


            case MathDash.SP_SURFACE_KB | MathDash.FINISH_BLOCK_BIT:
                if(fixA.getFilterData().categoryBits == MathDash.SP_SURFACE_KB)
                    ((Kub) fixA.getUserData()).finish();
                else  ((Kub) fixB.getUserData()).finish();
                break;

            case  MathDash.SP_SURFACE_KB | MathDash.BRICK_BIT :
                if(fixA.getFilterData().categoryBits == MathDash.BRICK_BIT)
                    ((Brick) fixA.getUserData()).contacting();
                else  ((Brick) fixB.getUserData()).contacting();
                break;

            case  MathDash.SP_SURFACE_KB | MathDash.THORNS_BIT :
                if(fixA.getFilterData().categoryBits == MathDash.SP_SURFACE_KB) ((Kub) fixA.getUserData()).hitting();
                else  ((Kub) fixB.getUserData()).hitting();
                break;

        }
    }

    @Override
    public void endContact(Contact contact) {
        //Gdx.app.log("Конец контакта", "");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
