package eth.ethercd.nanic.machines.generators;

import eth.ethercd.nanic.tiles.generators.SolarPanel;

public class LowVoltageSolarPanelTE extends SolarPanel {
    public LowVoltageSolarPanelTE() {
        this.tier=1;
        this.genMax=16;
        this.genMin=8;
        this.maxOutput=32;
    }
}