package com.shounen.sim.components;

import com.badlogic.ashley.core.Component;
import com.shounen.sim.data.enums.Origin;
import com.shounen.sim.data.enums.Race;

public class ProfileComponent implements Component {
    public String name;
    public int age;
    public Race race;
    public Origin origin;

    @Override
    public String toString() {
        return String.format("%s (%s %s, Age %d)", name, origin.toString(), race.toString(), age);
    }
}
