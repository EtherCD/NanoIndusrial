package eth.ethercd.nanic.machines.generators;

import eth.ethercd.nanic.tiles.generators.SolarPanel;

public class HighVoltageSolarPanelTE extends SolarPanel {
    public HighVoltageSolarPanelTE() {
        this.tier=3;
        this.genMax=256;
        this.genMin=129;
        this.maxOutput=512;
    }
}
