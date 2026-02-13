package com.shounen.sim;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.shounen.sim.components.ProfileComponent;
import com.shounen.sim.components.StatsComponent;
import com.shounen.sim.data.logic.RegenFactory;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class ShounenSim extends Game {
    public Engine engine;
    public RegenFactory regenFactory;

    @Override
    public void create() {
        engine = new Engine();
        regenFactory = new RegenFactory();

        System.out.println("Simulation started");
        System.out.println("--------------------------------------------------");

        for(int i = 0; i < 5; i++) {
            Entity character = regenFactory.createRandomCharacter();
            engine.addEntity(character);

            ProfileComponent profileComponent = character.getComponent(ProfileComponent.class);
            StatsComponent statsComponent = character.getComponent(StatsComponent.class);

            System.out.printf("Spawned: %s \n",  profileComponent.toString());
            System.out.println(statsComponent.toString());
            System.out.println("---------------------------------------");
        }
    }

    public void render () {
        ScreenUtils.clear(0, 0, 0, 1);
        super.render();
        engine.update(Gdx.graphics.getDeltaTime());
    }
}
