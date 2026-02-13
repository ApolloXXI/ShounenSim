package com.shounen.sim.components;

import com.badlogic.ashley.core.Component;

public class StatsComponent implements Component {
    public int vigour; // Body
    public int aether; // Energy
    public int flow; // Speed
    public int insight; // Mind
    public int presence; // Soul
    public int fate; // Luck

    @Override
    public String toString() {
        return String.format(" [VIG: %-3d] [AETH: %-3d] [FLO: %-3d] [INS: %-3d] [PRE: %-3d] [FATE: %-3d]",vigour,aether,flow,insight,presence,fate);
    }
}
