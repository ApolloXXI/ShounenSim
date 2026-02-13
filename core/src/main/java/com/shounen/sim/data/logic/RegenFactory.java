package com.shounen.sim.data.logic;

import com.badlogic.ashley.core.Entity;
import com.shounen.sim.components.ProfileComponent;
import com.shounen.sim.components.StatsComponent;
import com.shounen.sim.data.enums.Origin;
import com.shounen.sim.data.enums.Race;

import java.util.Random;

public class RegenFactory {
    private final Random rng = new Random();

    // Sample names for testing
    private final String[] NAMES = {"Gon", "Killua", "Goku", "Vegeta", "Gohan", "Sonic"};

    public Entity createRandomCharacter(){
        Entity entity = new Entity();

        // 1. Roll Random Identity
        Race race = Race.values()[rng.nextInt(Race.values().length)];
        Origin origin = Origin.values()[rng.nextInt(Race.values().length)];
        String name = NAMES[rng.nextInt(NAMES.length)];

        // 2. Create Profile
        ProfileComponent profile = new ProfileComponent();
        profile.name = name;
        profile.age = 12 + rng.nextInt(6);
        profile.race = race;
        profile.origin = origin;
        entity.add(profile);

        StatsComponent stats = generateStats(race, origin);
        entity.add(stats);
        return entity;
    }

    private StatsComponent generateStats(Race race, Origin origin){
        StatsComponent stats = new StatsComponent();

        // Base Roll (10 - 40)
        stats.vigour = 10 + rng.nextInt(31);
        stats.aether = 10 + rng.nextInt(31);
        stats.flow = 10 + rng.nextInt(31);
        stats.insight = 10 + rng.nextInt(31);
        stats.presence = 10 + rng.nextInt(31);
        stats.fate = 10 + rng.nextInt(31);

        // APPLY RACE MODIFIERS
        switch(race){
            case HUMAN:
                stats.insight += 5;
                stats.fate += 5;
                break;
            case ANIMA:
                stats.vigour += 15;
                stats.flow += 15;
                stats.insight -= 10;
                break;
            case EIDOLON:
                stats.aether += 20;
                stats.presence -= 10;
                stats.vigour -= 5;
                break;
        }

        // APPLY ORIGIN MODIFIERS
        switch (origin){
            case NOBLE:
                stats.insight += 10;
                stats.presence += 10;
                break;
            case RURAL:
                stats.vigour += 10;
                break;
            case MILITARY:
                stats.flow += 10;
                stats.vigour += 5;
                break;
            case SLUM:
                stats.fate += 15;
                stats.vigour -= 5;
                break;
            case THEOCRATIC:
                stats.presence += 15;
                break;
        }

        // Avoid negatives
        stats.insight = Math.max(1, stats.insight);
        stats.presence = Math.max(1, stats.presence);
        stats.vigour = Math.max(1, stats.vigour);

        return stats;

    }
}
