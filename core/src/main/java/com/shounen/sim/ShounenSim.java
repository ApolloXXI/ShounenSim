package com.shounen.sim;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class ShounenSim extends Game {
    Engine engine;

    @Override
    public void create() {
        engine = new Engine();

        Entity hero = new Entity();
        engine.addEntity(hero);

        System.out.println("Project Shounen Initialized!");
        System.out.println("Entities in World: " + engine.getEntities().size());
    }

    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        super.render();
        engine.update(Gdx.graphics.getDeltaTime());
    }
}
