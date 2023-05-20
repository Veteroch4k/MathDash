package com.mygdx.game.db;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Preference {

    public Preferences Attempt_Сounter;

    public Preference() {

        Attempt_Сounter = Gdx.app.getPreferences("game_Score");

    }

}
