package com.shounen.sim.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.shounen.sim.ShounenSim;
import com.shounen.sim.components.ProfileComponent;
import com.shounen.sim.components.StatsComponent;

public class CharacterCreationScreen implements Screen {
    final ShounenSim game;
    Stage stage;
    Skin skin;

    Label nameLabel;
    Label raceLabel;
    Label originLabel;
    Label statsLabel;

    public CharacterCreationScreen(final ShounenSim game) {
        this.game = game;

        // Setup Stage (container for UI)
        stage = new Stage(new ScreenViewport());

        // Create a basic skin so we don't need assets yet
        createBasicSkin();

        // Build the table
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        // --- TITLE ---
        Label title = new Label("Project Shounen", skin);
        title.setFontScale(2);
        root.add(title).padBottom(50).row();

        // --- INFO SECTION ---
        nameLabel = new Label("Name: ???", skin);
        root.add(nameLabel).padBottom(10).row();

        raceLabel = new Label("Race: ???", skin);
        root.add(raceLabel).padBottom(10).row();

        originLabel = new Label("Origin: ???", skin);
        root.add(originLabel).padBottom(10).row();

        // --- STATS SECTION ---
        statsLabel = new Label("Waiting for Soul... ", skin);
        root.add(statsLabel).padBottom(50).row();

        // --- BUTTONS ---
        TextButton rerollButton = new TextButton("Reroll soul", skin);
        root.add(rerollButton).width(200).height(50);

        rerollButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rollCharacter();
            }
        });

        rollCharacter();
    }

    private void rollCharacter() {
        Entity entity = game.regenFactory.createRandomCharacter();
        ProfileComponent profile = entity.getComponent(ProfileComponent.class);
        StatsComponent stats = entity.getComponent(StatsComponent.class);

        nameLabel.setText("Name: " + profile.name);
        raceLabel.setText("Race: " + profile.race);
        originLabel.setText("Origin: " + profile.origin);

        String statsText = String.format(
            "VIGOUR: %d\n" +
            "AETHER: %d\n" +
            "FLOW:   %d\n" +
            "INSIGHT: %d\n" +
            "PRESENCE: %d\n" +
            "FATE:    %d",
            stats.vigour, stats.aether, stats.flow, stats.insight, stats.presence, stats.fate
        );
        statsLabel.setText(statsText);
    }

    // --- Create UI without assets ---
    private void createBasicSkin() {
        skin = new Skin();

        // Generate a 1 * 1 white texture
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        Texture whiteTexture = new Texture(pixmap);
        pixmap.dispose();

        skin.add("white", whiteTexture);

        // Get default font
        skin.add("default", new BitmapFont());

        // Configure Label Style
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default");
        skin.add("default", labelStyle);

        // Configure button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = skin.getFont("default");
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.GRAY);
        skin.add("default", textButtonStyle);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.2f, 1);
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
