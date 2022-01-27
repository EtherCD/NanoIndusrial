package eth.ethercd.nanic.machines.generators;

import eth.ethercd.nanic.tiles.generators.SolarPanel;

public class MediumVoltageSolarPanelTE extends SolarPanel {
    public MediumVoltageSolarPanelTE() {
        this.tier=2;
        this.genMax=64;
        this.genMin=32;
        this.maxOutput=128;
    }
}
