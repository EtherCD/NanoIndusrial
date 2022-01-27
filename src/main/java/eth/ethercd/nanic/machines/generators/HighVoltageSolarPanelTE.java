package eth.ethercd.nanic.machines.generators;

import eth.ethercd.nanic.tiles.generators.SolarPanel;

public class HighVoltageSolarPanelTE extends SolarPanel {
    public HighVoltageSolarPanelTE() {
        this.tier=3;
        this.genMax=256;
        this.genMin=128;
        this.maxOutput=512;
    }
}
