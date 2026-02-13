package com.shounen.sim;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.shounen.sim.components.ProfileComponent;
import com.shounen.sim.components.StatsComponent;
import com.shounen.sim.data.logic.RegenFactory;
import com.shounen.sim.screens.CharacterCreationScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class ShounenSim extends Game {
    public Engine engine;
    public RegenFactory regenFactory;
    public CharacterCreationScreen characterCreationScreen;

    @Override
    public void create() {
        engine = new Engine();
        regenFactory = new RegenFactory();


        System.out.println("Starting UI");
        System.out.println("--------------------------------------------------");
        this.setScreen(new CharacterCreationScreen(this));
    }

    @Override
    public void render () {
        super.render();
    }
}
